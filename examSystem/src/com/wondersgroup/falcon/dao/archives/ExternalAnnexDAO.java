/**
 * 
 */
package com.wondersgroup.falcon.dao.archives;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import oracle.sql.BLOB;
import oracle.sql.CLOB;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.lob.SerializableBlob;
import org.hibernate.lob.SerializableClob;

import com.wondersgroup.falcon.abstracts.HibernateDAO;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.ExternalAnnex;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.persistence.lob.OracleLobUtil;

/**
 * <p>Title:[�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2010-2-2]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public class ExternalAnnexDAO {

	/**
	 * 
	 */
	public ExternalAnnexDAO() {

	}

	public void saveAnnex(final ExternalAnnex annex ,final  String filename)throws HibernateException {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				s.save(annex);
				s.flush();

				s.refresh(annex, LockMode.UPGRADE);
				//System.out.println(img.getImg().getClass()); 
				//���ص���SerializableBlob���͵Ķ������Ǹ�ʵ����Blob�ӿڵĶ��󣬵�����ֱ������ΪBLOB 
				SerializableBlob sb = (SerializableBlob)annex.getFileText();
				//��Ҫ��������getWrappedBlob���ͳ��� 
//				BLOB blob = (BLOB)sb.getWrappedBlob();
				
				Blob wrapped = sb.getWrappedBlob();
				BLOB blob = OracleLobUtil.unwrapBlob(wrapped);
				
				//��ĳ���ļ������д��Blob�ֶε�������� 
				OutputStream os;
				FileInputStream fis;
				byte[] buff;

				try {
					os = blob.getBinaryOutputStream();

					try {
						fis = new FileInputStream(filename);
						try {
							buff = new byte[fis.available()];

							fis.read(buff); 
							fis.close(); 
							os.write(buff); 
							os.close();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				s.flush(); 

				return null ; 
			}
		});


	}
//	public void upadateAnnex(ExternalAnnex annex , String filepath){


//	}
}
