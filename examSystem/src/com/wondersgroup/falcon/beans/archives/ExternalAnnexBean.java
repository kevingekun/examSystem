package com.wondersgroup.falcon.beans.archives;

import java.io.FileInputStream;
import java.sql.Blob;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.archives.CaseDAO;
import com.wondersgroup.falcon.dao.archives.ExternalAnnexDAO;
import com.wondersgroup.falcon.model.archives.ExternalAnnex;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

/**
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2010-2-2]
 * Midified by [修改人] [修改时间]
 *
 */ 
public class ExternalAnnexBean {
	   
	 
	
	private static Log log = LogFactory.getLog(ExternalAnnexBean.class);
	/**
	 * 
	 */
	public ExternalAnnexBean() {

	}

	public void saveAnnex(final ExternalAnnex annex , final String filename)throws Exception {

	 HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Blob fileText = null;    
				
				ExternalAnnexDAO dao = new ExternalAnnexDAO();	
				
				annex.setFileText(Hibernate.createBlob(new byte[1]));

				dao.saveAnnex(annex , filename);


				return null  ;
			}
		});
		
		
	}
}
