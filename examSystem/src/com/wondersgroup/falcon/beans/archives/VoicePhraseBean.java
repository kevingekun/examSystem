package com.wondersgroup.falcon.beans.archives;

import java.io.File;
import java.io.PrintWriter;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.archives.VoiceDAO;
import com.wondersgroup.falcon.model.archives.VoiceNode;
import com.wondersgroup.falcon.model.archives.VoicePhrase;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class VoicePhraseBean {

	private final static String BASE_DIR = "C:/IVR/voice/";

	private final static String DUMP_DIR = BASE_DIR + "text/";

	private final static int BASE = 10000;

	private int current;

	private Session session;

	public VoicePhraseBean() {
	}

	public void createVoicePhrase() throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
		clear();
		current = BASE;
		VoiceDAO dao = new VoiceDAO();
		VoiceNode root = dao.findRoot();
		export(root);
		return root;
			}

		});
	}

	private void export(VoiceNode parent) throws Exception {
		VoicePhrase phrase = new VoicePhrase();
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
			phrase.setBody(parent.getVoiceFile());
			phrase.setDir(false);
		} else {
			// dir
			int i;
			Iterator it;
			String str = "";

			for (it = parent.getChildren().iterator(), i = 1; it.hasNext(); i++) {
				VoiceNode child = (VoiceNode) it.next();
				str = str + child.getName() + "请按" + i % 10 + "，，";
				export(child);
			}
			phrase.setBody(str);
			phrase.setDir(true);
		}
		session.save(phrase);
		return;
	}

	private void clear() throws Exception {
		session.createQuery("delete VoicePhrase").executeUpdate();
	}

	public void dump() throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
		Query query = session
				.createQuery("from VoicePhrase vp order by vp.phraseId asc");
		List ls = query.list();
		new File(BASE_DIR).mkdirs();
		new File(DUMP_DIR).mkdirs();
		PrintWriter wr = new PrintWriter(BASE_DIR + "phrases.txt");

		for (Iterator it = ls.iterator(); it.hasNext();) {
			VoicePhrase phrase = (VoicePhrase) it.next();
			PrintWriter pw = new PrintWriter(DUMP_DIR + phrase.getPhraseId()
					+ ".txt");
			pw.print(phrase.getBody());
			pw.close();
			wr.println(phrase.getPhraseId() + " " + phrase.getVoiceId());
		}
		wr.close();
		return null;
		
			}

		});
	}
}
