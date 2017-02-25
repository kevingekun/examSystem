/**
 * 
 */
package com.wondersgroup.falcon.dao.archives;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.Util.PropertiesUtil;
import com.wondersgroup.falcon.beans.archives.PolicyTree;
import com.wondersgroup.falcon.model.jhk.AppHlpdoc;
import com.wondersgroup.falcon.oa.util.DateUtil;
import com.wondersgroup.falcon.persistence.JHKOperation;

/**
 * <p>Title:[�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2010-1-11]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public class PolicyTransferDAO {
	private static Log log = LogFactory.getLog(PolicyTransferDAO.class);
	/**
	 * 
	 */
	public PolicyTransferDAO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * <p>Description:[�Ƿ����߷����Ѿ�����] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param docId
	 * @return
	 */
	public boolean isExistAppHlpdoc(String docId){

		JHKOperation jhk = new JHKOperation();

		String sql = "select * from app_hlpdoc where id ='"+docId+"'";

		AppHlpdoc app = (AppHlpdoc) jhk.get(sql, AppHlpdoc.class);
		
		if (app!=null ){
			
			log.info("���� �������Ϊ"+app.getTitle()+"�ļ�¼��");
			return true ; 
		}else {
			log.info("������ ��������¼��");
			return false ; 
		}

	}

	public void addAppHlpdoc(AppHlpdoc doc) throws Exception{

		log.info("ִ�����ӣ�");

		StringBuffer sql = new StringBuffer();
		sql.append("insert into app_hlpdoc ");
		sql.append("(id, title, type, publishdate, busisysid, busimenuid, content, keyword) ");
		sql.append("values ('"+doc.getId()+"', '"+doc.getTitle()+"', '"+doc.getType()+"', TO_DATE('"+doc.getPublishdate().toLocaleString()+"','yyyy-MM-dd HH24:mi:ss'), '"
				+doc.getBusisysid()+"', '"+doc.getBusimenuid()+"', empty_clob(), '"+doc.getKeyword()+"') ");

		JHKOperation jhk = new JHKOperation();
		try {

			boolean flag = jhk.update(sql.toString());

			//����SQL 

			if (flag)
				jhk.updateCLOB(doc.getId(), doc.getContent());


			log.info("���ӳɹ���");
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e; 
		}


	}
	public void updateAppHlpdoc(AppHlpdoc doc)throws Exception{
		
		log.info("ִ���޸ģ�");

		StringBuffer sql = new StringBuffer();

		sql.append("update app_hlpdoc set title = '"+doc.getTitle()+"',");
		sql.append("type ='"+doc.getType()+"',");
		sql.append("publishdate =TO_DATE('"+doc.getPublishdate().toLocaleString()+"','yyyy-MM-dd HH24:mi:ss'),");
		sql.append("busisysid ='"+doc.getBusisysid()+"',");
		sql.append("busimenuid ='"+doc.getBusimenuid()+"',");
		sql.append("content = empty_clob(),");
		sql.append("keyword ='"+doc.getKeyword()+"'");
		sql.append(" where id ='"+doc.getId()+"'");

		JHKOperation jhk = new JHKOperation();
		try {

			boolean flag = jhk.update(sql.toString());

			//����SQL 

			if (flag)
				jhk.updateCLOB(doc.getId(), doc.getContent());


			log.info("�޸ĳɹ���");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;

		} 		

	}
	public void delAppHlpdoc(String docId)throws Exception{
		log.info("ִ��ɾ����");

		StringBuffer sql = new StringBuffer();

		sql.append("delete app_hlpdoc  where id ='"+docId+"'");

		JHKOperation jhk = new JHKOperation();
		try {

			boolean flag = jhk.update(sql.toString());

			log.info("ɾ���ɹ���");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;

		} 	

	}


	public static void main(String[] args) throws Exception {
		PolicyTransferDAO dao = new PolicyTransferDAO();
		dao.isExistAppHlpdoc("1");

	}
}
