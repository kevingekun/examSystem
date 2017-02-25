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
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2010-2-2]
 * Midified by [修改人] [修改时间]
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
				//返回的是SerializableBlob类型的对象，这是个实现了Blob接口的对象，但不能直接造型为BLOB 
				SerializableBlob sb = (SerializableBlob)annex.getFileText();
				//需要调用它的getWrappedBlob造型出来 
//				BLOB blob = (BLOB)sb.getWrappedBlob();
				
				Blob wrapped = sb.getWrappedBlob();
				BLOB blob = OracleLobUtil.unwrapBlob(wrapped);
				
				//将某个文件读入后，写到Blob字段的输出流中 
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
