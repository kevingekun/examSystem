package com.wondersgroup.kaoshi.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.hibernate.ObjectNotFoundException;

import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.kaoshi.bo.EAnswertemp;
import com.wondersgroup.kaoshi.dao.EQuestiontypeDAOImpl;
import com.wondersgroup.kaoshi.dao.EpaperquestionsDAO;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class EpaperquestionsServiceImpl implements EpaperquestionsService {
	private EpaperquestionsDAO epaperquestionsDAO;

	private EQuestiontypeDAOImpl equestiontypeDAOImpl;

	public void setEquestiontypeDAOImpl(
			EQuestiontypeDAOImpl equestiontypeDAOImpl) {
		this.equestiontypeDAOImpl = equestiontypeDAOImpl;
	}

	public EpaperquestionsDAO getEpaperquestionsDAO() {
		return epaperquestionsDAO;
	}

	/**
	 * ��ҳ�������е�����
	 */
	public PageReturn findAllByPage(PageTool pageInfo, EQuestions equestion) {
		try {
			return this.epaperquestionsDAO.findAllByPage(pageInfo, equestion);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����Ծ��������
	 */
	public PageReturn findAllByPage(PageTool pageTool, String sjid,
			EQuestions equestion) {
		try {
			return this.epaperquestionsDAO.findAllByPage(pageTool, sjid,
					equestion);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public void setEpaperquestionsDAO(EpaperquestionsDAO epaperquestionsDAO) {
		this.epaperquestionsDAO = epaperquestionsDAO;
	}

	/**
	 * ͨ���Ծ�id��������
	 */
	public List findAllById(String sjid) {
		try {
			return this.epaperquestionsDAO.findAllById(sjid);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public int getAllNunber(String questionId) {
		try {
			return this.epaperquestionsDAO.getAllNunber(questionId);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * <p>
	 * Description:�ҵ����е���������,��ͨ��priority��������
	 * </p>
	 * 
	 * Created by [www] [Aug 11, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @return
	 */
	public List<EQuestiontype> findEQuestiontype() {
		try {
			return this.equestiontypeDAOImpl.findEQuestiontype();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���idɾ��t�ӱ��е�����
	 */
	public void removeById(String id) {
		try {
			try {
				this.epaperquestionsDAO.deleteById(id);
			} catch (Exception e) {

				e.printStackTrace();
			}
		} catch (ObjectNotFoundException e) {
			System.out
					.println("��ݿ���û��������ݡ���������������������������������������������������������");
		}
	}

	/**
	 * ���idɾ��t�hch godspeed he
	 */

	public void deleteById(String id) {
		try {
			try {
				this.epaperquestionsDAO.deleteAll(id);
			} catch (Exception e) {

				e.printStackTrace();
			}
		} catch (ObjectNotFoundException e) {
		}
	}

	/**
	 * 
	 * <p>
	 * Description:���Ծ��в�������͵�����
	 * </p>
	 * 
	 * Created by [www] [Aug 13, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param questionIds
	 */
	public void addQuestion(String[] questionIds, String sjid,
			Map<String, Double> map) {
		try {
			this.epaperquestionsDAO.add(questionIds, sjid, map);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 代替上述方法
	public void addQs(String[] questionIds, String sjid, Double fenzhi) {
		try {
			this.epaperquestionsDAO.addQs(questionIds, sjid, fenzhi);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * ͨ���Ծ��ʶ����Ŀ����������
	 */
	public List findAllByIdType(String paperId, String type) {
		try {
			return this.epaperquestionsDAO.findQuestionByIdType(paperId, type);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public void addPaperQuestions(String paperid, String newpaperId) {
		try {
			this.epaperquestionsDAO.addEquestionPaper(paperid, newpaperId);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:�ҵ����е�ҵ������
	 * </p>
	 * 
	 * Created by [www] [Aug 20, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @return
	 */
	public List findallbuType() {
		try {
			return this.epaperquestionsDAO.findallbuType();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public List findallEimportances() {
		try {
			return this.epaperquestionsDAO.findallEimportances();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ�Ծ���ѡ����ķ���
	 */
	public double findPaperQuestionFensu(String paperid) {
		try {
			return this.epaperquestionsDAO.findpaperquestionfenshu(paperid);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return new Double(0);
	}

	/**
	 * ����Ծ��id������������ҵ�������͵��������
	 */
	public double findFenshuByTypeAnd(String sjid, String typeId) {
		List lis;
		double rsu = 0;
		try {
			lis = this.epaperquestionsDAO.findQuestionByIdType(sjid, typeId);
			for (int i = 0; i < lis.size(); i++) {
				EPaperquestions ep = (EPaperquestions) lis.get(i);
				rsu += ep.getSjStfs();

			}
			// System.out.println("json="+rsu);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return rsu;
	}

	public List<EQuestiontype> findEQuestiontypeByPaperType() {
		try {
			return this.equestiontypeDAOImpl.findEQuestiontypeByPaperType();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

	public List findEQuestiontypeAll() {
		try {
			return this.equestiontypeDAOImpl.findEQuestiontypeAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	// 添加答案临时表
	public void addAnswerTemp(String userid, String answer, String shijuanid,
			String questionid, String questionidtype,Connection conn) {
		try {
			this.equestiontypeDAOImpl.addAnswerTemp(userid, answer, shijuanid,
					questionid, questionidtype,conn);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public List<EAnswertemp> pingIp(String examid) {
		try {
			return this.equestiontypeDAOImpl.pingIp(examid);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkPaperRelate(String sjid) {
		return this.epaperquestionsDAO.checkPaperRelate(sjid);
	}
}
