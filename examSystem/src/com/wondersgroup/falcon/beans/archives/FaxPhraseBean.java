package com.wondersgroup.falcon.beans.archives;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;

import org.hibernate.Session;

import com.wondersgroup.falcon.dao.archives.FaxDAO;
import com.wondersgroup.falcon.model.archives.FaxNode;
import com.wondersgroup.falcon.model.archives.FaxPhrase;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class FaxPhraseBean {

	public final static int ONLY_FILE = 1;

	public final static int ONLY_TABLE = 2;

	private final static String BASE_DIR = "C:/IVR/fax/";

	private final static String TEXT_DIR = BASE_DIR + "text/";

	private final static int BASE = 20000;

	private int current;

	private PrintWriter wr;

	public FaxPhraseBean() {
	}

	/**
	 * 
	 * @param type
	 * @throws Exception
	 */
	public void createVoicePhrase(final int type) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				current = BASE;
				FaxDAO dao = new FaxDAO();
				FaxNode root = dao.findRoot();
				if (type == ONLY_FILE) {
					new File(TEXT_DIR).mkdirs();
					wr = new PrintWriter(BASE_DIR + "phrases.txt");
					export_file(root);
					wr.close();
				} else if (type == ONLY_TABLE) {
					clear(session);
					export_db(root, session);
				}
				return null;
			}

		});

	}

	/**
	 * 导出语音文件，进行tts转换
	 * 
	 */
	private void export_file(FaxNode parent) throws Exception {
		PrintWriter pw = new PrintWriter(TEXT_DIR + current + ".txt");
		wr.println(current + " " + parent.getId());
		current++;
		// file
		if (parent.getVoiceFile() != null) {
			// 生成要进行tts转换的文字内容
			// pw.print("您选择的文件是：" + parent.getName());
			pw.print("" + parent.getName());
			pw.close();
		} else {
			// dir
			int i;
			Iterator it;
			String str = "";

			for (it = parent.getChildren().iterator(), i = 1; it.hasNext(); i++) {
				FaxNode child = (FaxNode) it.next();
				str = str + child.getName() + "请按" + i % 10 + "，，";
				export_file(child);
			}
			pw.print(str);
			pw.close();
		}
		return;
	}

	/**
	 * 导出
	 * 
	 * @param parent
	 * @throws Exception
	 */
	private void export_db(FaxNode parent, Session session) throws Exception {
		FaxPhrase phrase = new FaxPhrase();
		phrase.setVoiceId(parent.getId());
		if (parent.getParent() == null) {
			phrase.setParent_id(null);
		} else {
			phrase.setParent_id(parent.getParent().getId());
		}
		phrase.setOrdering(parent.getOrdering());
		phrase.setPhraseId(new Long(current));
		current++;
		// file
		if (parent.getVoiceFile() != null) {
			// 生成要进行tts转换的文字内容
			// phrase.setBody("您选择的文件是：" + parent.getName());
			phrase.setBody(" " + parent.getName());
			phrase.setDir(false);
		} else {
			// dir
			int i;
			Iterator it;
			String str = "";

			for (it = parent.getChildren().iterator(), i = 1; it.hasNext(); i++) {
				FaxNode child = (FaxNode) it.next();
				str = str + child.getName() + "请按" + i % 10 + "，，";
				export_db(child, session);
			}
			phrase.setBody(str);
			phrase.setDir(true);
		}
		session.save(phrase);
		return;
	}

	/**
	 * 清空表
	 * 
	 * @throws Exception
	 */
	private void clear(Session session) throws Exception {
		session.createQuery("delete FaxPhrase").executeUpdate();
	}

}
