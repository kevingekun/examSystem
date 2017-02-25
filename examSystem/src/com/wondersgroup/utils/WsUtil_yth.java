package com.wondersgroup.utils;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.local.bo.Answerpaper_wsDTO;
import com.wondersgroup.local.bo.DownloadPaperAndUserInfoRequest;
import com.wondersgroup.local.bo.DownloadPaperAndUserInfoResponse;
import com.wondersgroup.local.bo.E_answerpaper;
import com.wondersgroup.local.bo.E_paperquestions_ws;
import com.wondersgroup.local.bo.E_papers_ws;
import com.wondersgroup.local.bo.E_user_team_ws;
import com.wondersgroup.local.bo.Elogmonitor_wsDTO;
import com.wondersgroup.local.bo.Equestions_ws;
import com.wondersgroup.local.bo.GetPaperInfoRequest;
import com.wondersgroup.local.bo.GetPaperInfoResponse;
import com.wondersgroup.local.bo.HZ95_ws;
import com.wondersgroup.local.bo.ImportUserRequest;
import com.wondersgroup.local.bo.ImportUserResponse;
import com.wondersgroup.local.bo.PaperInfo;
import com.wondersgroup.local.bo.Temp_count;
import com.wondersgroup.local.bo.Temp_cur;
import com.wondersgroup.local.bo.Temp_cur_c;
import com.wondersgroup.local.bo.Temp_hf11;
import com.wondersgroup.local.bo.Temp_hf12;
import com.wondersgroup.local.bo.Tjobsubject_ws;
import com.wondersgroup.local.bo.UploadScoreToJdzxRequest;
import com.wondersgroup.local.bo.UploadScoreToJdzxResponse;
import com.wondersgroup.local.bo.UploadScoreToYthRequest;
import com.wondersgroup.local.bo.UploadScoreToYthResponse;
import com.wondersgroup.local.bo.User_examinfo;
import com.wondersgroup.local.bo.User_ws;
import com.wondersgroup.local.bo.WrongPersent_ws;

public class WsUtil_yth {

	public static ImportUserResponse getUserFromYth(String pcid) throws ServiceException, RemoteException{
		String endpoint = ProcedureUrl.WS_URL_YTH;
		Service service = new Service();
		ImportUserRequest request = new ImportUserRequest();
		request.setPcid(pcid);
		Call call = (Call) service.createCall();      
		call.setTargetEndpointAddress(endpoint); 
		
		//这里的QName的ns和wsdd文件中的对应       
		QName resp = new QName("urn:ImportUserResponse", "ImportUserResponse");
		QName requ = new QName("urn:ImportUserRequest", "ImportUserRequest");
		QName temp_cur = new QName("urn:Temp_cur", "Temp_cur");
		QName temp_cur_c = new QName("urn:Temp_cur_c", "Temp_cur_c");
		QName temp_count = new QName("urn:Temp_count", "Temp_count");
		QName temp_hf12 = new QName("urn:Temp_hf12", "Temp_hf12");
		QName temp_hf11 = new QName("urn:Temp_hf11", "Temp_hf11");
		//这里是将对象序列化和反序列化的配置 
		call.registerTypeMapping(ImportUserResponse.class, resp, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(ImportUserRequest.class, requ, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Temp_cur.class, temp_cur, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Temp_cur_c.class, temp_cur_c, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Temp_count.class, temp_count, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Temp_hf12.class, temp_hf12, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Temp_hf11.class, temp_hf11, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		
		call.setOperationName("importUserFromYth");
		call.setReturnClass(ImportUserResponse.class);
		//设置参数类型
		call.addParameter("request", requ, ParameterMode.IN);
		ImportUserResponse response =  (ImportUserResponse) call.invoke(new Object[] { request });
		return response;
	}

	public static UploadScoreToYthResponse uploadScoreToYth(UploadScoreToYthRequest request) throws RemoteException, ServiceException{
		String endpoint = ProcedureUrl.WS_URL_YTH;
		Service service = new Service();
		Call call = (Call) service.createCall();      
		call.setTargetEndpointAddress(endpoint); 
		
		//这里的QName的ns和wsdd文件中的对应       
		QName requ = new QName("urn:UploadScoreToYthRequest", "UploadScoreToYthRequest");
		QName resp = new QName("urn:UploadScoreToYthResponse", "UploadScoreToYthResponse");
		QName uinfo = new QName("urn:User_examinfo", "User_examinfo");
		QName eans = new QName("urn:E_answerpaper", "E_answerpaper");
		//这里是将对象序列化和反序列化的配置 
		call.registerTypeMapping(UploadScoreToYthRequest.class, requ, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(UploadScoreToYthResponse.class, resp, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(User_examinfo.class, uinfo, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(E_answerpaper.class, eans, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		
		call.setOperationName("uploadScoreToYth");
		call.setReturnClass(UploadScoreToYthResponse.class);
		//设置参数类型
		call.addParameter("input", requ, ParameterMode.IN);
		UploadScoreToYthResponse response =  (UploadScoreToYthResponse) call.invoke(new Object[] { request });
		return response;
	}
	
	public static GetPaperInfoResponse getPaperInfo(GetPaperInfoRequest request) throws ServiceException, RemoteException{
		String endpoint = ProcedureUrl.WS_URL_JDZX;
		Service service = new Service();
		Call call = (Call) service.createCall();      
		call.setTargetEndpointAddress(endpoint);
		
		QName requ = new QName("urn:GetPaperInfoRequest", "GetPaperInfoRequest");
		QName resp = new QName("urn:GetPaperInfoResponse", "GetPaperInfoResponse");
		QName pinfo = new QName("urn:PaperInfo", "PaperInfo");
		
		call.registerTypeMapping(GetPaperInfoRequest.class, requ, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(GetPaperInfoResponse.class, resp, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(PaperInfo.class, pinfo, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		
		call.setOperationName("getPaperInfo");
		call.setReturnClass(GetPaperInfoResponse.class);
		
		// 设置参数类型
		call.addParameter("input", requ, ParameterMode.IN);
		GetPaperInfoResponse response =  (GetPaperInfoResponse) call.invoke(new Object[] { request });
		return response;
	}
	
	public static DownloadPaperAndUserInfoResponse downloadPaperAndUserInfo(DownloadPaperAndUserInfoRequest request) throws RemoteException, ServiceException{
		String endpoint = ProcedureUrl.WS_URL_JDZX;
		Service service = new Service();
		Call call = (Call) service.createCall();      
		call.setTargetEndpointAddress(endpoint); 
		
		//这里的QName的ns和wsdd文件中的对应       
		QName requ = new QName("urn:DownloadPaperAndUserInfoRequest", "DownloadPaperAndUserInfoRequest");
		QName resp = new QName("urn:DownloadPaperAndUserInfoResponse", "DownloadPaperAndUserInfoResponse");
		QName questions = new QName("urn:Equestions_ws", "Equestions_ws");
		QName papers = new QName("urn:E_papers_ws", "E_papers_ws");
		QName paperquestions = new QName("urn:E_paperquestions_ws", "E_paperquestions_ws");
		QName user_team = new QName("urn:E_user_team_ws", "E_user_team_ws");
		QName hz95 = new QName("urn:HZ95_ws", "HZ95_ws");
		QName users = new QName("urn:User_ws", "User_ws");
		QName jobsub = new QName("urn:Tjobsubject_ws", "Tjobsubject_ws");
		//这里是将对象序列化和反序列化的配置 
		call.registerTypeMapping(DownloadPaperAndUserInfoRequest.class, requ, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(DownloadPaperAndUserInfoResponse.class, resp, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Equestions_ws.class, questions, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(E_papers_ws.class, papers, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(E_paperquestions_ws.class, paperquestions, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(E_user_team_ws.class, user_team, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(HZ95_ws.class, hz95, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(User_ws.class, users, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Tjobsubject_ws.class, jobsub, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		
		call.setOperationName("downloadPaperAndUserInfo");
		call.setReturnClass(DownloadPaperAndUserInfoResponse.class);
		//设置参数类型
		call.addParameter("input", requ, ParameterMode.IN);
		DownloadPaperAndUserInfoResponse response =  (DownloadPaperAndUserInfoResponse) call.invoke(new Object[] { request });
		return response;
	}
	
	public static UploadScoreToJdzxResponse uploadScoreToJdzx(UploadScoreToJdzxRequest request) throws ServiceException, RemoteException{
		String endpoint = ProcedureUrl.WS_URL_JDZX;
		Service service = new Service();
		Call call = (Call) service.createCall();      
		call.setTargetEndpointAddress(endpoint);
		
		QName requ = new QName("urn:UploadScoreToJdzxRequest", "UploadScoreToJdzxRequest");
		QName resp = new QName("urn:UploadScoreToJdzxResponse", "UploadScoreToJdzxResponse");
		QName ansp = new QName("urn:Answerpaper_wsDTO", "Answerpaper_wsDTO");
		QName elog = new QName("urn:Elogmonitor_wsDTO", "Elogmonitor_wsDTO");
		QName wp = new QName("urn:WrongPersent_ws", "WrongPersent_ws");
		
		call.registerTypeMapping(UploadScoreToJdzxRequest.class, requ, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(UploadScoreToJdzxResponse.class, resp, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Answerpaper_wsDTO.class, ansp, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(Elogmonitor_wsDTO.class, elog, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		call.registerTypeMapping(WrongPersent_ws.class, wp, BeanSerializerFactory.class, BeanDeserializerFactory.class);
		
		call.setOperationName("uploadScoreToJdzx");
		call.setReturnClass(UploadScoreToJdzxResponse.class);
		
		// 设置参数类型
		call.addParameter("input", requ, ParameterMode.IN);
		UploadScoreToJdzxResponse response =  (UploadScoreToJdzxResponse) call.invoke(new Object[] { request });
		return response;
	}
}
