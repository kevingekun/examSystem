/** 
 * @(#)ListenedStatictis.java May 15, 2010
 * 
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.falcon.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author suhualin
 * @version $Revision$ May 15, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public class ListenedStatictis {
	private static Log logger = LogFactory.getLog("connection.listen");

	private static Log monitor = LogFactory.getLog("connection.monitor");

	private static List<ConnectionUnWrapper> connectionUnWrappers = new ArrayList<ConnectionUnWrapper>();

	private static Timer timer = new Timer();

	private static final Map counters = new HashMap();

	private static final Map indexes = new HashMap();

	private static final Set currentConnections = new HashSet();

	private static final Object m1 = new Object();

	private static boolean listened = false;

	private static long timeout = 10000;

	private static int maxConnections = 0;

	private static int curConnections = 0;

	private static long listenPeriod = 5000L;

	private static boolean showTime = true;

	private static boolean showTrace = true;

	public void setTimeout(long timeout) {
		ListenedStatictis.timeout = timeout * 1000;
	}

	public void setListenPeriod(long listenPeriod) {
		ListenedStatictis.listenPeriod = listenPeriod * 1000;
	}

	public void setShowTime(boolean showTime) {
		ListenedStatictis.showTime = showTime;
	}

	public void setShowTrace(boolean showTrace) {
		ListenedStatictis.showTrace = showTrace;
	}

	public void setConnectionUnWrappers(List connectionUnWrappers) {
		ListenedStatictis.connectionUnWrappers = connectionUnWrappers;
	}

	/**
	 * @return the maxConnections
	 */
	public static int getMaxConnections() {
		synchronized (m1) {
			return maxConnections;
		}
	}

	/**
	 * @return the curConnections
	 */
	public static int getCurConnections() {
		synchronized (m1) {
			return curConnections;
		}
	}

	public static String getConnectionIdentifier(Connection connection) {
		int hash;
		try {
			Connection rawConnection = connection.getMetaData().getConnection();
			for (ConnectionUnWrapper unWrapper : connectionUnWrappers) {
				if (unWrapper.match(rawConnection)) {
					rawConnection = unWrapper.unwrap(rawConnection);
					break;
				}
			}
			hash = System.identityHashCode(rawConnection);
		} catch (SQLException e) {
			hash = System.identityHashCode(connection);
		}
		return Integer.toHexString(hash);
	}

	private static synchronized int index(String cid) {
		Counter index = (Counter) indexes.get(cid);
		if (index == null) {
			index = new Counter();
			indexes.put(cid, index);
			counters.put(cid, index);
		} else {
			index.increment();
		}
		return index.getCount();
	}

	protected static void info(String prefix, String info) {
		logger.info(new StringBuffer(prefix.length() + info.length() + 10)
				.append(prefix).append(":").append(info));
	}

	public static ListenableConnection connect(Connection rawConnection) {
		String cid = getConnectionIdentifier(rawConnection);

		ListenableConnection listenableConnection = new ListenableConnection(
				rawConnection, cid, index(cid));

		synchronized (m1) {
			curConnections++;
			if (curConnections > maxConnections) {
				maxConnections = curConnections;
			}
			currentConnections.add(listenableConnection);
		}
		info(listenableConnection.toString(), "open");

		return listenableConnection;
	}

	public static void disconnect(ListenableConnection connection) {
		synchronized (m1) {
			curConnections--;
			connection.setStopTime(new Date());
			if (connection.getCost() < timeout) {// 超时的情况等同于没关闭
				currentConnections.remove(connection);
			}
			info(connection.toString(), "close");
		}
	}

	public static void listen() {
		if (!listened) {
			monitor.info("Start listening with [period=" + listenPeriod
					+ ",timeout=" + timeout + ",showTime=" + showTime
					+ ",showTrace=" + showTrace + "]");
			timer.schedule(new TimerTask() {
				private int oldSize = -1;

				@Override
				public void run() {
					monitor(new ListenCallback() {
						public void listen(Set currentConnections) {
							if (oldSize != 0 || currentConnections.size() != 0) {
								oldSize = currentConnections.size();
								StringBuffer buffer = new StringBuffer(
										50 * currentConnections.size() + 200);
								buffer.append("connections:").append(
										currentConnections.size());

								for (Iterator itr = currentConnections
										.iterator(); itr.hasNext();) {
									ListenableConnection connection = (ListenableConnection) itr
											.next();
									buffer.append("\n").append(
											connection.toString(showTime,
													showTrace));
								}
								monitor.info(buffer);
							}
						}
					});
				}
			}, 0, listenPeriod);
		}
	}

	public static void monitor(ListenCallback callback) {
		synchronized (m1) {
			if (callback != null) {
				callback.listen(currentConnections);
			}
		}
	}

	static class Counter {
		private int count = 1;

		private final Object m = new Object();

		public void increment() {
			synchronized (m) {
				count++;
			}
		}

		public void decrese() {
			synchronized (m) {
				count--;
			}
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			synchronized (m) {
				return count;
			}
		}
	}
}
