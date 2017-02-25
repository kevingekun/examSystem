package com.wondersgroup.gonggao.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;



import com.jspsmart.upload.SmartUpload;
import com.opensymphony.xwork2.ActionContext;
import com.wondersgroup.falcon.Util.ParamUtil;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.gonggao.service.GgService;
import com.wondersgroup.gonggao.service.impl.GgServiceImpl;
import com.wondersgroup.gonggao.bo.TBm;
import com.wondersgroup.gonggao.bo.TGg;
import com.wondersgroup.gonggao.bo.TGgfj;
import com.wondersgroup.gonggao.bo.TGglm;

public class GgAction extends AbstractPageNavAction{
	//service
	private GgService ggService;

	//页面显示
	private List<TGg> tggs;
	//公告栏目 公告部门
	private List<TBm> tbms;
	private List<TGglm> tgglms;
	private TGgfj fj;

	//传参
	private String biaoti;
	private Date sjbegin;
	private Date sjend;

	@Override
	//查询公告方法
	public String doAcion() throws Exception {
		this.pageReturn=this.ggService.findAll(this.pageTool,this.biaoti,this.sjbegin,this.sjend);
		tggs=this.pageReturn.getReturnList();
		return SUCCESS;
	}
	//传参
	private String ggId;
	private String lxid;
	private String fjdz;
	//页面显示
	private TGg tgg;
	/**
	 * 显示详细信息; 
	 * @return
	 * @throws Exception
	 */
	//查看公告详细的方法
	public String viewgg() throws Exception {
		tgg=this.ggService.loadTgg(ggId);
		fj=this.ggService.loadTGgfj(ggId);
		return SUCCESS;
	}

	//删除公告方法
	public String removegg() throws Exception {
		this.ggService.removegg(ggId);
		return SUCCESS;
	}
	//下载附件方法
	String inputPath;
	String chinaName ;
	InputStream inputName;
	public InputStream getInputStream() {

		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);

	}

	public String downloadfj() throws Exception {
		
		fj=this.ggService.loadTGgfj(ggId);
		inputPath = fj.getFjdz();
		chinaName = fj.getFjmc();
		System.out.println("----------------------"+chinaName);
		ActionContext actionContext = ActionContext.getContext(); 
		Map session=actionContext.getSession();

		return SUCCESS;
	}

	public TGg getTgg() {
		return tgg;
	}

	public void setTgg(TGg tgg) {
		this.tgg = tgg;
	}

	public String toaddgg() throws Exception{
		this.tbms=this.ggService.getAlltbm();
		this.tgglms=this.ggService.getAllgglm();
		return SUCCESS;	

	}

	//传参
	private String bmid;
	private String lmid;
	private String ggbt;
	private String ggnr;
	private File upload;
	private String uploadFileName;//文件名
	private String uploadContentType;//文件类型

	private String savePath =  "/upload/gg";

	public String getSavePath() throws Exception
	{
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	//增加公告方法
	@SuppressWarnings("static-access")
	public String addgg() throws Exception{

		//得到人员
		AcegiUtil acegiUtil=new AcegiUtil();
		EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
		//公告增加
		TGg tgg=new TGg();
		tgg.setBmid(bmid);
		tgg.setGgbt(ggbt);
		tgg.setGgnr(ggnr);
		tgg.setGgrq(new Date());
		TGglm ggglm=this.ggService.loadTGglm(lmid); 
		tgg.setGgglm(ggglm);
		tgg.setRyid(user.getUsername());
		ggService.addGg(tgg);
		String ggfjid=tgg.getGgid();


		//上传文件保存
		if(uploadFileName!=null){
			Date date = new Date();
			SimpleDateFormat sFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now_time = sFormat.format(date);
			String nowtimes = now_time.replace('-','1');
			nowtimes=nowtimes.replace(':','1');
			nowtimes=nowtimes.substring(0,10)+nowtimes.substring(11);
			//取得文件后缀名	
			String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."),uploadFileName.length());

			String savefileName=getSavePath()+"\\"+nowtimes + suffix;

//			System.out.println("-=----------------------------"+uploadFileName);
//			System.out.println(savefileName);

			FileOutputStream fos=new FileOutputStream(savefileName);
			FileInputStream fis=new FileInputStream(upload);      
			byte[] b=new byte[1024];
			int len=0;
			while((len=fis.read(b))>0)
			{
				fos.write(b,0,len);
			}

			TGgfj ggfj=new TGgfj(ggfjid,"1",uploadFileName,savePath+"/"+nowtimes + suffix,"公告附件",b);
			this.ggService.saveFf(ggfj);
		}
		//ggService.addGg(tgg);
		return SUCCESS;	

	}




	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getGgId() {
		return ggId;
	}

	public void setGgId(String ggId) {
		this.ggId = ggId;
	}

	public List<TGg> getTggs() {
		return tggs;
	}



	public void setTggs(List<TGg> tggs) {
		this.tggs = tggs;
	}

	public void setGgService(GgService ggService) {
		this.ggService = ggService;
	}



	public void setEpostService(GgService ggService) {
		this.ggService = ggService;
	}

	public String getBmid() {
		return bmid;
	}

	public void setBmid(String bmid) {
		this.bmid = bmid;
	}

	public String getLmid() {
		return lmid;
	}

	public void setLmid(String lmid) {
		this.lmid = lmid;
	}

	public String getGgbt() {
		return ggbt;
	}

	public void setGgbt(String ggbt) {
		this.ggbt = ggbt;
	}

	public String getGgnr() {
		return ggnr;
	}

	public void setGgnr(String ggnr) {
		this.ggnr = ggnr;
	}

	public List<TBm> getTbms() {
		return tbms;
	}

	public void setTbms(List<TBm> tbms) {
		this.tbms = tbms;
	}

	public List<TGglm> getTggmls() {
		return tgglms;
	}

	public void setTggmls(List<TGglm> tggmls) {
		this.tgglms = tggmls;
	}

	public List<TGglm> getTgglms() {
		return tgglms;
	}

	public void setTgglms(List<TGglm> tgglms) {
		this.tgglms = tgglms;
	}
	public Date getSjbegin() {
		return sjbegin;
	}

	public void setSjbegin(Date sjbegin) {
		this.sjbegin = sjbegin;
	}

	public Date getSjend() {
		return sjend;
	}

	public void setSjend(Date sjend) {
		this.sjend = sjend;
	}

	public String getLxid() {
		return lxid;
	}

	public void setLxid(String lxid) {
		this.lxid = lxid;
	}


	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public TGgfj getFj() {
		return fj;
	}

	public void setFj(TGgfj fj) {
		this.fj = fj;
	}

	public String getFjdz() {
		return fjdz;
	}

	public void setFjdz(String fjdz) {
		this.fjdz = fjdz;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getChinaName() {
		String downFileName = chinaName; 
		try { 
			downFileName = new String(downFileName.getBytes(), "ISO8859-1"); 
			
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
		} 
		return downFileName; 
		

	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}


}
