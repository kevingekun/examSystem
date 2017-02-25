package com.wondersgroup.falcon.beans.exam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.wondersgroup.falcon.dao.exam.TrainExampleDAO;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.exam.TrainExample;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class TrainExampleService {

	public void saveOrUpdate(Object obj){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			sfd.saveOrUpdate(obj);
			HibernateUtil.commitTransaction();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void saveTrainExample(TrainExample sfb) throws SQLException{
		try {
			TrainExampleDAO sfd = new TrainExampleDAO();
			sfd.saveTrainExample(sfb);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public List findTrainExample(int first,int size){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			List list = new ArrayList();
			list = sfd.findTrainExample(first, size);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	public List findTrainExampleByCrit(int first,int size,String username,String realname,String status,String starttime,String endtime){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			List list = new ArrayList();
			list = sfd.findTrainExampleByCrit(first, size, username, realname, status, starttime, endtime);
			for(int i=0;i<list.size();i++){
				TrainExample sfb = (TrainExample)list.get(i);
			}
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}	
	
	public int findCountByCrit(int first,int size,String username,String realname,String status,String starttime,String endtime){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			int count = sfd.findCountByCrit(first, size, username, realname, status, starttime, endtime);
			HibernateUtil.commitTransaction();
			return count;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return 0;
	}	
	
	
	public int findAllTrainExample(){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			int count = sfd.findAllTrainExample();
			HibernateUtil.commitTransaction();
			return count;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return 0;
	}	
	
	public List findTrainExampleBySender(String username,int first,int size){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			List list = new ArrayList();
			list = sfd.findTrainExampleBySender(username, first, size);
			for(int i=0;i<list.size();i++){
				TrainExample sfb = (TrainExample)list.get(i);
			}
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public int findCountBySender(String username,int first,int size){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			int count = 0;
			count = sfd.findCountBySender(username, first, size);
			HibernateUtil.commitTransaction();
			return count;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return 0;
	}		
	
	public TrainExample findOneTrainExample(String sfbid){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			TrainExample sfb = sfd.findOneTrainExample(sfbid); 
			Hibernate.initialize(sfb);
			HibernateUtil.commitTransaction();
			return sfb;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;		
	}
	
	public List findReplyBySid(String sfbid){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			List list = new ArrayList();
			list = sfd.findReplyBySid(sfbid);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;		
	}
	
	/**
	 * 找出某人未读的回复
	 * @param username 用户名
	 * @return 某人未读的业务交流回复
	 */
	
	public List findNoReadReply(String username){
		try{
			TrainExampleDAO sfd = new TrainExampleDAO();
			List list = new ArrayList();
			list = sfd.findNoReadReply(username);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;		
	}
}
