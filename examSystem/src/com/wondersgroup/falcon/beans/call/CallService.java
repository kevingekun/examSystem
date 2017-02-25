package com.wondersgroup.falcon.beans.call;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.wondersgroup.falcon.beans.common.CommFunc;
import com.wondersgroup.falcon.dao.call.CallDAO;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class CallService {
	private List list = new ArrayList();
	
	/**
	 * 得到录音文件url
	 * 
	 * @param callid
	 * @return
	 */
	public List getRecordUrl(String callid) {
		try{
			CallDAO cd = new CallDAO();
			list = cd.getRecordUrl(callid);
			//HibernateUtil.commitTransaction();
			List result = new Vector();
			String url = null;
			String ftp_ip = null;
			String vox_dir = null;
			String name = null;

			for (Iterator it = list.iterator(); it.hasNext();) {
				Object[] tuple = (Object[]) it.next();
				
				ftp_ip = (String) tuple[1];
				if (ftp_ip.equals("168.30.1.96")) {
					vox_dir = "/vox/";
				} else {
					vox_dir = "/vox1/";
				}
				name = (String) tuple[0];
//		示例		ftp://administrator:voicecyber@168.30.1.96/vox/00/048/E0004820081008143443.wav
				url = "ftp://administrator:voicecyber@" + ftp_ip + vox_dir
						+ ((String) tuple[0]).substring(1, 3) + "/"
						+ ((String) tuple[0]).substring(3, 6) + "/"
						+ (String) tuple[0] + ".wav";
				String[] array = { url,name,CommFunc.convertDT((Date) tuple[2]), (String) tuple[3] };
				result.add(array);
			}		
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
		//	HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List getHisRecordUrl(String callid) {
		try{
			CallDAO cd = new CallDAO();
			list = cd.getHisRecordUrl(callid);
		//	HibernateUtil.commitTransaction();
			List result = new Vector();
			String url = null;
			String ftp_ip = null;
			String vox_dir = null;
			String name = null;

			for (Iterator it = list.iterator(); it.hasNext();) {
				Object[] tuple = (Object[]) it.next();
				
				ftp_ip = (String) tuple[1];
				if (ftp_ip.equals("172.16.54.68")) {
					vox_dir = "/vox/";
				} else {
					vox_dir = "/vox1/";
				}
				name = (String) tuple[0];
//		示例		ftp://administrator:voicecyber@168.30.1.96/vox/00/048/E0004820081008143443.wav
				url = "ftp://administrator:123456@" + ftp_ip + vox_dir
						+ ((String) tuple[0]).substring(1, 3) + "/"
						+ ((String) tuple[0]).substring(3, 6) + "/"
						+ (String) tuple[0] + ".wav";
				String[] array = { url,name,CommFunc.convertDT((Date) tuple[2]), (String) tuple[3],(String) tuple[4] };
				result.add(array);
			}		
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
		//	HibernateUtil.closeSession();
		}
		return null;
	}	
	
	
	public List findRecordByCrit(String username,String starttime,String endtime){
		try{
			CallDAO cd = new CallDAO();
			list = cd.findRecordByCrit(username,starttime,endtime);
		//	HibernateUtil.commitTransaction();
			List result = new Vector();
			String url = null;
			String ftp_ip = null;
			String vox_dir = null;
			String name = null;

			for (Iterator it = list.iterator(); it.hasNext();) {
				Object[] tuple = (Object[]) it.next();
				
				ftp_ip = (String) tuple[1];
				if (ftp_ip.equals("168.30.1.96")) {
					vox_dir = "/vox/";
				} else {
					vox_dir = "/vox1/";
				}
				name = (String) tuple[0];
//		示例		ftp://administrator:voicecyber@168.30.1.96/vox/00/048/E0004820081008143443.wav
				url = "ftp://administrator:voicecyber@" + ftp_ip + vox_dir
						+ ((String) tuple[0]).substring(1, 3) + "/"
						+ ((String) tuple[0]).substring(3, 6) + "/"
						+ (String) tuple[0] + ".wav";
				String[] array = { url,name,CommFunc.convertDT((Date) tuple[2]), (String) tuple[3] };
				result.add(array);
			}		
			return result;			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
		//	HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List findTopTenHistory(int first,int size){
		try{
			CallDAO hd = new CallDAO();
			List list = new ArrayList();
			list = hd.findTopTenHistory(first,size);
		//	HibernateUtil.commitTransaction();
			if(list!=null&&list.size()!=0){
				return list;
			}
		}catch(Exception ex){
		//	HibernateUtil.rollbackTransaction();
			ex.printStackTrace();
		}finally{
		//	HibernateUtil.closeSession();
		}
		return null;		
	}
	
	public List findHistoryByCrit(String username,String starttime,String endtime){
		try{
			CallDAO hd = new CallDAO();
			List list = new ArrayList();
			list = hd.findHistoryByCrit(username, starttime, endtime);
		//	HibernateUtil.commitTransaction();
			if(list!=null&&list.size()!=0){
				return list;
			}
		}catch(Exception ex){
		//	HibernateUtil.rollbackTransaction();
			ex.printStackTrace();
		}finally{
		//	HibernateUtil.closeSession();
		}
		return null;		
	}	
	
	public List getDataByID(Long id) throws Exception{
		try{
			CallDAO hd = new CallDAO();
			List ht = hd.getDataByID(id); 
		//	HibernateUtil.commitTransaction();
			return ht;
		}catch(Exception ex){
		//	HibernateUtil.rollbackTransaction();
			throw ex;
		}finally{
		//	HibernateUtil.closeSession();
		}
	}
	
	
	public List getArichivelog(String callid){
		try{
			CallDAO hd = new CallDAO();
			List ht = hd.getArichivelog(callid); 
		//	HibernateUtil.commitTransaction();
			return ht;
		}catch(Exception ex){
		//	HibernateUtil.rollbackTransaction();
		}finally{
		//	HibernateUtil.closeSession();
		}
		return list;		
	}
}
