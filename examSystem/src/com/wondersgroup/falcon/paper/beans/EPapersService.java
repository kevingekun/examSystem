/**
 * 
 */
package com.wondersgroup.falcon.paper.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.paper.dao.EPapersDAO;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;

/**
 * <p>
 * Title:[�����]
 * </p>
 * <p>
 * Description: [�๦������]
 * </p>
 * 
 * Created by [Kevin Liang] [2009-7-7] Midified by [�޸���] [�޸�ʱ��]
 * 
 */
public class EPapersService {

	private static final Log log = LogFactory.getLog(EPapersService.class);

	public int getPapersTotalCount(String sjMc, long sjZt, Date sjKksjbegin,
			Date sjKksjend, Date sjZjsjbegin, Date sjZjsjend) {
		EPapersDAO dao = new EPapersDAO();

		try {
			return dao.getPapersTotalCount(sjMc, sjZt, sjKksjbegin, sjKksjend,
					sjZjsjbegin, sjZjsjend);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public List findPapers(String sjMc, long sjZt, Date sjKksjbegin,
			Date sjKksjend, Date sjZjsjbegin, Date sjZjsjend, int currentPage,
			int pageSize, String orderby) {

		// try{
		EPapersDAO dao = new EPapersDAO();

		try {
			return dao.findPapers(sjMc, sjZt, sjKksjbegin, sjKksjend, sjZjsjbegin,
					sjZjsjend, currentPage, pageSize, orderby);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }catch (Exception ex){
		// HibernateUtil.rollbackTransaction();
		// throw new ServiceException(ex);
		// }finally {
		// HibernateUtil.closeSession();
		// }
		return null;

	}

	/**
	 * 
	 * <p>
	 * Description:[�����䲻ͬ�ȼ�������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-7] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param questionNum
	 * @return
	 */
	public int[] getNumbergfromImportance(int questionNum) {

		int[] nums = new int[3];
		nums[0] = 0;
		nums[1] = 0;
		nums[2] = 0;
		try {
			List list1 = new ArrayList();
			List list2 = new ArrayList();
			List list3 = new ArrayList();

			for (int i = 0; i < questionNum; i++) {
				int randomnum = (int) Math.ceil(Math.random() * 300);
				// System.out.println(randomnum);
				if (randomnum >= 1 && randomnum <= 100)
					list1.add(new Integer(randomnum));
				if (randomnum > 100 && randomnum <= 200)
					list2.add(new Integer(randomnum));
				if (randomnum > 200 && randomnum <= 300)
					list3.add(new Integer(randomnum));
			}

			if (list1 != null && list1.size() > 0)
				nums[0] = list1.size();
			if (list2 != null && list2.size() > 0)
				nums[1] = list2.size();
			if (list3 != null && list3.size() > 0)
				nums[2] = list3.size();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nums;
	}

	/**
	 * 
	 * <p>
	 * Description:[����������͡���Ҫ�Լ����������� ȡ������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-7] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param questionNum
	 * @param importanceLevel
	 * @param questionType
	 * @return
	 */
	public List getPaperQuestionsRandom(int questionNum, int importanceLevel,
			int questionType, String serviceType, String bxType,String zycd,String ndxs) {
		List list = null;
		EPapersDAO dao = new EPapersDAO();
		try {
			list = dao.getPaperQuestionsRandom(questionNum, importanceLevel,
					questionType, serviceType, bxType,zycd,ndxs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据鉴定要素进行出题
	 * @param questionNum
	 * @param importanceLevel
	 * @param questionType
	 * @param lis
	 * @param serviceType
	 * @param bxType
	 * @return
	 */
	public List getPaperQuestionsRandomByJd(int questionNum, String importanceLevel,int questionType, String serviceType, String bxType,String zycd,String ndxs,List<Long> quid,String grade,String jddid) {
		List list = null;
		EPapersDAO dao = new EPapersDAO();
		try {
			list = dao.getPaperQuestionsRandomByJd(questionNum, importanceLevel, questionType, serviceType, bxType,zycd,ndxs,quid,grade,jddid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List getPaperQuestionsQustionid(int questionNum,
			int importanceLevel, int questionType, List<Long> lis,
			String serviceType, String bxType) {
		List list = null;
		EPapersDAO dao = new EPapersDAO();
		try {
			list = dao.getPaperQuestionsRandom2(questionNum, importanceLevel,
					questionType, lis, serviceType, bxType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int getQuestionCountByType(Long type, String serviceType,
			String bxType,String zycd,String ndxs) {
		int i = 0;
		try {
			EPapersDAO dao = new EPapersDAO();
			i = dao.getQuestionCountByType(type, serviceType, bxType,zycd,ndxs);
			HibernateUtil.commitTransaction();
		} catch (RuntimeException ex) {
			HibernateUtil.rollbackTransaction();
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return i;
	}

	/**
	 * 
	 * <p>
	 * Description:[�Ծ���Ϣ����]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-14] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param ePapers
	 */
	public void savePage(EPapers ePapers) {
		try {
			EPapersDAO dao = new EPapersDAO();
			try {
				dao.saveOrUpdate(ePapers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//HibernateUtil.commitTransaction();
		} catch (RuntimeException ex) {
			//HibernateUtil.rollbackTransaction();
			throw ex;
		} finally {
			//HibernateUtil.closeSession();
		}
	}
	/**
	 * 获得坚定要素比例
	 */
	public List<Tdjobexamdot> queryTdjobexamdot(String ccz137){
		try {
			return new EPapersDAO().queryTdjobexamdot(ccz137);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String chekcUser(){
		return new EPapersDAO().checkUser();
	}
	
}
