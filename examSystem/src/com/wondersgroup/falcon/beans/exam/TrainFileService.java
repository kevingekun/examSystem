package com.wondersgroup.falcon.beans.exam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.wondersgroup.falcon.dao.exam.TrainFileDAO;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.exam.TrainFile;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class TrainFileService {

	public void saveOrUpdate(Object obj){
		try{
			TrainFileDAO sfd = new TrainFileDAO();
			sfd.saveOrUpdate(obj);
			HibernateUtil.commitTransaction();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void saveTrainFile(TrainFile sfb) throws SQLException{
		try {
			TrainFileDAO sfd = new TrainFileDAO();
			sfd.saveTrainFile(sfb);
			HibernateUtil.commitTransaction();
		} catch (Exception ex) {
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public List findTrainFile(int first,int size){
		try{
			TrainFileDAO sfd = new TrainFileDAO();
			List list = new ArrayList();
			list = sfd.findTrainFile(first, size);
			for(int i=0;i<list.size();i++){
				TrainFile sfb = (TrainFile)list.get(i);
				Hibernate.initialize(sfb.getFujian());
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
	public List findTrainFileByCrit(int first,int size,String username,String realname,String status,String starttime,String endtime){
		try{
			TrainFileDAO sfd = new TrainFileDAO();
			List list = new ArrayList();
			list = sfd.findTrainFileByCrit(first, size, username, realname, status, starttime, endtime);
			for(int i=0;i<list.size();i++){
				TrainFile sfb = (TrainFile)list.get(i);
				Hibernate.initialize(sfb.getFujian());
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
			TrainFileDAO sfd = new TrainFileDAO();
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
	
	
	public int findAllTrainFile(){
		try{
			TrainFileDAO sfd = new TrainFileDAO();
			int count = sfd.findAllTrainFile();
			HibernateUtil.commitTransaction();
			return count;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return 0;
	}	
	
	public List findTrainFileBySender(String username,int first,int size){
		try{
			TrainFileDAO sfd = new TrainFileDAO();
			List list = new ArrayList();
			list = sfd.findTrainFileBySender(username, first, size);
			for(int i=0;i<list.size();i++){
				TrainFile sfb = (TrainFile)list.get(i);
				Hibernate.initialize(sfb.getFujian());
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
			TrainFileDAO sfd = new TrainFileDAO();
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
	
	public TrainFile findOneTrainFile(String sfbid){
		try{
			TrainFileDAO sfd = new TrainFileDAO();
			TrainFile sfb = sfd.findOneTrainFile(sfbid); 
			Hibernate.initialize(sfb.getFujian());
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
			TrainFileDAO sfd = new TrainFileDAO();
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
			TrainFileDAO sfd = new TrainFileDAO();
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
