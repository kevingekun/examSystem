package com.wondersgroup.kaoshi.service.impl;

import java.util.Date;
import java.util.List;

import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.dao.EquestionsDAO;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class EQuestionsServiceImpl implements EQuestionsService {

	private EquestionsDAO dao;

	/**
	 * ͨ�����Ͳ�������
	 */
	public PageReturn findQuetionsByType(PageTool pageInfo, String typeId,
			String stMc, Long busType, Date inputtime, Long eimportance,
			Date sjbegin, Date sjend,String xingzhi,String gzid,String gzdj,long difficulty) {
		try {
			return this.dao.findQuetionsByType(pageInfo, typeId, stMc, busType,
					inputtime, eimportance, sjbegin, sjend,xingzhi,gzid,gzdj,difficulty);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setDao(EquestionsDAO dao) {
		this.dao = dao;
	}

	public EQuestions findOneQuestion(int sequence, String stTg,
			long equestions_type, long seequestionBuTypes, long important)
			throws Exception {
		return this.dao.findOneQuestion(sequence, stTg, equestions_type,
				seequestionBuTypes, important);
	}

	public PageReturn findQuetionsBytiaojian(PageTool pageInfo,
			EQuestions equestions) {

		try {
			return this.dao.findQuetionsBytiaojian(pageInfo, equestions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public EQuestions load(Long id) {

		try {
			return this.dao.load(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findAdvice(PageTool pageInfo, Long id) {

		try {
			return this.dao.findAdvice(pageInfo, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:����Ծ�������ҵ�������
	 * </p>
	 * 
	 * Created by [www] [Nov 7, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param type
	 * @return
	 */
	public int findqQuestionCountByType(Long type) {
		try {
			return this.dao.findCountByType(type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void saveQuestion(EQuestions equestions) {
		try {
			this.dao.saveQuestion(equestions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveAdvice(long ST_ID, String content) {
		try {
			this.dao.saveAdvice(ST_ID, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PageReturn findQuestionsByNodeState(PageTool pageInfo) {
		try {
			return this.dao.findQuestionsByNodeState(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 政策法规
	public PageReturn findPolicyNodeByNodeState(PageTool pageInfo) {
		try {
			return this.dao.findPolicyNodeByNodeState(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findstBypolicyNodeId(String policyNodeId,
			PageTool pageInfo) {
		try {
			return this.dao.findstBypolicyNodeId(policyNodeId, pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updatestate(String id) {
		try {
			this.dao.updatestate(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateStmodefy(String id) {
		try {
			this.dao.updateStmodefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateModefy(String id) {
		try {
			this.dao.updateModefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 办事指南
	public PageReturn findServiceNodeByNodeState(PageTool pageInfo) {
		try {
			return this.dao.findServiceNodeByNodeState(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findSstBypolicyNodeId(String serviceNodeId,
			PageTool pageInfo) {
		try {
			return this.dao.findSstBypolicyNodeId(serviceNodeId, pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateSstate(String id) {
		try {
			this.dao.updateSstate(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateSmodefy(String id) {
		try {
			this.dao.updateSmodefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateSModefy(String id) {
		try {
			this.dao.updateSModefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 问答资料
	public PageReturn findFaqNodeByNodeState(PageTool pageInfo) {
		try {
			return this.dao.findFaqNodeByNodeState(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findFstBypolicyNodeId(String faqNodeId, PageTool pageInfo) {
		try {
			return this.dao.findFstBypolicyNodeId(faqNodeId, pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateFstate(String id) {
		try {
			this.dao.updateFstate(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateFmodefy(String id) {
		try {
			this.dao.updateFmodefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateFModefy(String id) {
		try {
			this.dao.updateFModefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 学习资料
	public PageReturn findCaseNodeByNodeState(PageTool pageInfo) {
		try {
			return this.dao.findCaseNodeByNodeState(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findCstBypolicyNodeId(String caseNodeId, PageTool pageInfo) {
		try {
			return this.dao.findCstBypolicyNodeId(caseNodeId, pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateCstate(String id) {
		try {
			this.dao.updateCstate(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCmodefy(String id) {
		try {
			this.dao.updateCmodefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCModefy(String id) {
		try {
			this.dao.updateCModefy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<EQuestions> findQuestionCounts(String serviceType, String bxType) {
		try {
			return this.dao.findCounts(serviceType, bxType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
