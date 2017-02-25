/**
 * 
 */
package com.wondersgroup.falcon.question.beans;


import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.exceptions.DAOException;
import com.wondersgroup.falcon.exceptions.ServiceException;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.dao.EBusinesstypeDAO;
import com.wondersgroup.falcon.question.dao.EQuestionsDAO;
import com.wondersgroup.falcon.question.model.*;
import com.wondersgroup.kaoshi.bo.Ae02;

/**
 * <p>Title:[�������Ϣ������] </p>
 * <p>Description: [ʵ�ֶ�������Ϣ�Ĺ���]</p> 
 *
 * Created by [Kevin Liang] [2009-6-26]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public class EQuestionsService {
	private static final Log log = LogFactory.getLog(EQuestionsService.class);
	/**
	 * 
	 * <p>Description:[��ӿ�������] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-29]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param obj
	 */
	public void saveOrUpdateQuestion(EQuestions obj) throws Exception{
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			//HibernateUtil.beginTransaction();
			dao.saveOrUpdate(obj);
			log.info("���Ᵽ��ɹ���");
			//HibernateUtil.commitTransaction();
		} catch (RuntimeException ex) {
			//HibernateUtil.rollbackTransaction();
			throw ex;
		} finally {
			//HibernateUtil.closeSession();
		}		
	}
	public void saveEBusinesstype(EBusinesstype e) throws Exception{
		try {
			EBusinesstypeDAO dao=new EBusinesstypeDAO();
			//HibernateUtil.beginTransaction();
			dao.save(e);
			log.info("���Ᵽ��ɹ���");
			//HibernateUtil.commitTransaction();
		} catch (RuntimeException ex) {
			//HibernateUtil.rollbackTransaction();
			throw ex;
		} finally {
			//HibernateUtil.closeSession();
		}		
	}
	
	public void saveTmdot(Tmdot tmdot){
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			
				dao.saveOrUpdate_tmdot(tmdot);
			
			log.info("save Tmdot success");
			HibernateUtil.commitTransaction();
		} catch (RuntimeException ex) {
			HibernateUtil.rollbackTransaction();
			throw ex;
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}		
	}
	
	public void saveOrUpdateAe02(Ae02 ae02){
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			dao.saveOrUpdate_ae02(ae02);
			log.info("save Ae02 success");
			HibernateUtil.commitTransaction();
		} catch (RuntimeException ex) {
			HibernateUtil.rollbackTransaction();
			throw ex;
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public int getNumOfQuestionSet(String sjid){
		EQuestionsDAO dao=new EQuestionsDAO();
		return dao.getNumOfQuestionSet(sjid);
	}
	
	public boolean checkSumOfQuestionSet(String sjid,String stfz){
		EQuestionsDAO dao=new EQuestionsDAO();
		return dao.checkSumOfQuestionSet(sjid,stfz);
	}
	
	public void savelog(EModefy obj) throws Exception{
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			dao.savelog(obj);
			log.info("���Ᵽ��ɹ���");
			HibernateUtil.commitTransaction();
		} catch (RuntimeException ex) {
			HibernateUtil.rollbackTransaction();
			throw ex;
		} finally {
			HibernateUtil.closeSession();
		}		
	}
	
	public EQuestions findById(Long id) {
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			EQuestions e = dao.findById(id);

			return e;
		} catch (DAOException ex) {
			throw new ServiceException(ex);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
		}	
		return null;
	}
	public void passAll(){
		EQuestionsDAO dao=new EQuestionsDAO();
		dao.passAll();
	}
	public EQuestionsVO findById_loadcheck(long id){
		try {
			//HibernateUtil.beginTransaction();
			EQuestionsDAO dao=new EQuestionsDAO();
			EQuestionsVO e = dao.getByid_loadcheck(id);
			//HibernateUtil.commitTransaction();			
			return e;
		} catch (DAOException ex) {
			//HibernateUtil.rollbackTransaction();
			throw new ServiceException(ex);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			//HibernateUtil.closeSession();
		}	
		return null;
	}
	

	public int delete(Long[] delId){   
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			int row = dao.delete(delId);
			HibernateUtil.commitTransaction();
			log.info("��ɾ��ɹ���");
			return row;
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			log.debug("��ɾ��ʧ�ܣ�");
			throw new ServiceException(ex);
		} finally {
			HibernateUtil.closeSession();
		}		  
	}
	
	public void batchCheck(String[] ids){
		EQuestionsDAO dao=new EQuestionsDAO();
		try {
			dao.batchCheck(ids);
			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
			throw new ServiceException(e);
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public int turn(Long[] delId){   
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			int row = dao.turn(delId);
			HibernateUtil.commitTransaction();
			log.info("��ɾ��ɹ���");
			return row;
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			log.debug("��ɾ��ʧ�ܣ�");
			throw new ServiceException(ex);
		} finally {
			HibernateUtil.closeSession();
		}		  
	}  
	
	public int turnjd(Long[] delId){   
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			int row = dao.turnjd(delId);
			HibernateUtil.commitTransaction();
			log.info("��ɾ��ɹ���");
			return row;
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			log.debug("��ɾ��ʧ�ܣ�");
			throw new ServiceException(ex);
		} finally {
			HibernateUtil.closeSession();
		}		  
	}  

	public int unturnjd(Long[] delId){   
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			int row = dao.unturnjd(delId);
			HibernateUtil.commitTransaction();
			log.info("��ɾ��ɹ���");
			return row;
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			log.debug("��ɾ��ʧ�ܣ�");
			throw new ServiceException(ex);
		} finally {
			HibernateUtil.closeSession();
		}		  
	} 
	
	public int turnback(Long[] delId){   
		try {
			EQuestionsDAO dao=new EQuestionsDAO();
			int row = dao.turnback(delId);
			HibernateUtil.commitTransaction();
			log.info("��ɾ��ɹ���");
			return row;
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			log.debug("��ɾ��ʧ�ܣ�");
			throw new ServiceException(ex);
		} finally {
			HibernateUtil.closeSession();
		}		  
	}

	/**
	 * 
	 * <p>Description:[ȡ�ü�¼����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-30]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @return
	 * @throws Exception 
	 */
	public int getQuestionsTotalCount(
			String chuchu,
			String subject,
			String documentnum,
			long examsign,//�������־
			Date recorddatebegin,
			Date recorddateend,
			Date modifiydatebegin,
			Date modifiydateend,
			long businesstype,
			long importance,
			long questiontype,
			long Paperid,
			long deletesign,
			String gzid,
			String gzdj,
			int state,
			String sjid){
		EQuestionsDAO dao=new EQuestionsDAO();
		log.info("��ѯ��¼������ɹ���");
		try {
			return dao.getQuestionsTotalCount(chuchu,subject, documentnum, examsign, 
					recorddatebegin, recorddateend, modifiydatebegin,
					modifiydateend, businesstype, importance, 
					questiontype,Paperid,deletesign,gzid,gzdj,state,sjid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/**
	 * 
	 * <p>Description:[ȡ�ü�¼����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-30]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @return
	 * @throws Exception 
	 */
	public int getQuestionsUncheckedTotalCount(
			String chuchu,
			String subject,
			String documentnum,
			long examsign,//�������־
			Date recorddatebegin,
			Date recorddateend,
			Date modifiydatebegin,
			Date modifiydateend,
			long businesstype,
			long importance,
			long questiontype,
			long Paperid,
			long deletesign,
			String gzid,
			String gzdj,
			String sjid){
		EQuestionsDAO dao=new EQuestionsDAO();
		log.info("��ѯ��¼������ɹ���");
		try {
			return dao.getQuestionsTotalCountUnchecked(chuchu,subject, documentnum, examsign, 
					recorddatebegin, recorddateend, modifiydatebegin,
					modifiydateend, businesstype, importance, 
					questiontype,Paperid, deletesign, gzid, gzdj,sjid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int getQuestionsUnpassTotalCount(
			String chuchu,
			String subject,
			String documentnum,
			long examsign,//�������־
			Date recorddatebegin,
			Date recorddateend,
			Date modifiydatebegin,
			Date modifiydateend,
			long businesstype,
			long importance,
			long questiontype,
			long Paperid,
			long deletesign,
			String gzid,
			String gzdj){
		EQuestionsDAO dao=new EQuestionsDAO();
		log.info("��ѯ��¼������ɹ���");
		try {
			return dao.getQuestionsTotalCountUnpass(chuchu,subject, documentnum, examsign, 
					recorddatebegin, recorddateend, modifiydatebegin,
					modifiydateend, businesstype, importance, 
					questiontype,Paperid, deletesign,gzid,gzdj);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
		
	}
	

	/**
	 * 
	 * <p>Description:[�����ҳ��ѯ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-1]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param subject ��Ŀ
	 * @param documentnum �ĺ�
	 * @param examsign �����־
	 * @param recorddatebegin	¼��ʱ�俪ʼ
	 * @param recorddateend		¼��ʱ�����
	 * @param modifiydatebegin	�޸�ʱ�俪ʼ
	 * @param modifiydateend	�޸�ʱ�����
	 * @param operation	ҵ������
	 * @param importance	��Ҫ��
	 * @param questiontype	��������
	 * @param 	��ǰҳ
	 * @param pagecutPaginteSize	ҳ����
	 * @return
	 */
	public List findQuestions(
			String chuchu,
			String subject,
			String documentnum,
			long examsign,//�������־
			Date recorddatebegin,
			Date recorddateend,
			Date modifiydatebegin,
			Date modifiydateend,
			long businesstype,
			long importance,
			long questiontype,
			long Paperid,
			int currentPage, int pageSize,
			String orderby,long deletesign,
			String gzid,
			String gzdj,
			int state,
			String sjid) throws ServiceException {
		List list= null;
			EQuestionsDAO dao=new EQuestionsDAO();
			try {
				list = dao.findQuestions(chuchu,subject, documentnum, examsign, 
						recorddatebegin, recorddateend,
						modifiydatebegin, modifiydateend, 
						businesstype, importance, questiontype,Paperid,
						currentPage, pageSize, orderby,deletesign,gzid,gzdj,state,sjid);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			log.info("��ѯ������Ϣ�ɹ���");
			//HibernateUtil.commitTransaction();
			return list;

	}
	
	public List findQuestionsUnchecked(
			String chuchu,
			String subject,
			String documentnum,
			long examsign,//�������־
			Date recorddatebegin,
			Date recorddateend,
			Date modifiydatebegin,
			Date modifiydateend,
			long businesstype,
			long importance,
			long questiontype,
			long Paperid,
			int currentPage, int pageSize,
			String orderby,long deletesign,
			String gzid, String gzdj,
			String sjid) throws ServiceException {
		List list= null;
			EQuestionsDAO dao=new EQuestionsDAO();
			try {
				list = dao.findQuestionsUnchecked(chuchu,subject, documentnum, examsign, 
						recorddatebegin, recorddateend,
						modifiydatebegin, modifiydateend, 
						businesstype, importance, questiontype,Paperid,
						currentPage, pageSize, orderby,deletesign, gzid, gzdj,sjid);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			log.info("��ѯ������Ϣ�ɹ���");
			//HibernateUtil.commitTransaction();
			return list;

	}
	
	public List findQuestionsUnpass(
			String chuchu,
			String subject,
			String documentnum,
			long examsign,//�������־
			Date recorddatebegin,
			Date recorddateend,
			Date modifiydatebegin,
			Date modifiydateend,
			long businesstype,
			long importance,
			long questiontype,
			long Paperid,
			int currentPage, int pageSize,
			String orderby,
			long deletesign ,
			String gzid,
			String gzdj ) throws ServiceException {
		List list= null;
			EQuestionsDAO dao=new EQuestionsDAO();
			try {
				list = dao.findQuestionsUnpass(chuchu,subject, documentnum, examsign, 
						recorddatebegin, recorddateend,
						modifiydatebegin, modifiydateend, 
						businesstype, importance, questiontype,Paperid,
						currentPage, pageSize, orderby,deletesign,gzid,gzdj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("��ѯ������Ϣ�ɹ���");
			//HibernateUtil.commitTransaction();
			return list;

	}
	public Ae02 findAe02BystId(long id){
		EQuestionsDAO dao = new EQuestionsDAO();
		try {
			return dao.findAe02BystId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
