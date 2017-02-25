package com.wondersgroup.falcon.Util.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jspsmart.upload.File;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;


/**
 * ftp upload download
 * @author luke
 *
 */
public class FtpUtil {

	String server = "";
	int port = 21;
	String username = "";
	String password = "";
	String path = "";
	String downloadFile = "";
	String downloadDestFile = "";
	String uploadFile = "";
	String uploadFileName = "";

	public FtpUtil(String ser ,String usern,String pwd,String path)
	{
		server = ser;
		username = usern;
		password = pwd;
		this.path = path;
	}
	public FtpUtil(String ser ,int port ,String usern,String pwd,String path)
	{
		server = ser;
		this.port = port;
		username = usern;
		password = pwd;
		this.path = path;
	}
	public static void main(String [] args)
	{ 
		try
		{
			FtpUtil ftp = new FtpUtil("172.16.39.43",21,"hch","hch","/gg");			
			//List lstTemp = ftp.fileList();
			//System.out.println(" 11111111=======>" + lstTemp);
			//for(int i = 0 ;i<lstTemp.size();i++)
			//{
			// System.out.println(i +" : " +
			//   new String(lstTemp.get(i).toString().getBytes("ISO8859_1"),"gbk"));
			// }
			ftp.setUploadFile("c:\\tyyymh.log");
			ftp.setUploadFileName("tyyymh.log");
			ftp.upload();
			//ftp.setDownloadFile("user111.xml");
			//ftp.setDownloadDestFile("C:\\user111.xml");
			//ftp.download();
			System.out.println(" 11111111=======>");
		}
		catch(Exception ex)
		{
			System.out.println(" ftp client ex : " + ex.getMessage());
		}

	}
	/**
	 * ftp current path   file list 
	 * @return
	 * @throws Exception
	 */
	public List fileList()throws Exception 
	{
		List lstFiles = new ArrayList();

		FtpClient client = new FtpClient();
		client.openServer(server,port);
		
		client.login(username, password);
		if(path.length()>0)
		{
			client.cd(path);
		}
		sun.net.TelnetInputStream telnetinput = client.list();

		int c; 
		String bfstr = "";
		while ((c=telnetinput.read())!=-1) { 
			System.out.print((char) c +"");

			if(c == '\n')
			{
				/**
				 * substring(55) file name 
				 */
				lstFiles.add(bfstr.substring(55));
				bfstr = "";
			}
			else
			{
				bfstr += (char)c;
			}
		} 
		telnetinput.close();

		client.closeServer();

		return lstFiles;
	}
	public void del(String filename) throws Exception
	{
		FtpClient ftpclient = new FtpClient();
		ftpclient.openServer(server,port);
		ftpclient.login(username, password);
		if(path.length() > 0)
		{
			ftpclient.cd(path);   
		}
		
		ftpclient.sendServer("dele "+filename +"\r\n");

		ftpclient.closeServer();
		
	}
	/**
	 * upload ftp file
	 * @throws Exception
	 */
	public void upload() throws Exception 
	{
		boolean flag = false;

		FtpClient ftpclient = new FtpClient();
		ftpclient.openServer(server,port);
		ftpclient.login(username, password);
		
		
		if(path.length() > 0)
		{
			ftpclient.cd(path);   
		}
		ftpclient.binary();
		TelnetOutputStream telnet = ftpclient.put(this.uploadFileName);
		FileInputStream input = new FileInputStream(uploadFile);
		int BYTE_SIZE = 1024;
		byte [] bytes = new byte[BYTE_SIZE];
		int c ; 
		while(( c = input.read(bytes,0,BYTE_SIZE)) != -1)
		{
			telnet.write(bytes,0,c);
		}
		telnet.close();
		input.close();
		ftpclient.closeServer();


	}
	/**
	 * download ftp file 
	 * @throws Exception
	 */
	public void download() throws Exception 
	{
		FtpClient ftpclient = new FtpClient();
		ftpclient.openServer(server,port);
		ftpclient.login(username, password);
		if(path.length() > 0)
		{
			ftpclient.cd(path);
		}
		ftpclient.binary();
		TelnetInputStream telnet = ftpclient.get(this.getDownloadFile());

		FileOutputStream output = new FileOutputStream(this.getDownloadDestFile());

		int BYTE_SIZE = 1024;
		byte [] bytes = new byte[BYTE_SIZE];
		int c ;
		while((c = telnet.read(bytes, 0, BYTE_SIZE)) != -1)
		{
			output.write(bytes, 0, c);
		}
		telnet.close();
		output.close();
		ftpclient.closeServer();

	}



	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getDownloadDestFile() {
		return downloadDestFile;
	}
	public void setDownloadDestFile(String downloadDestFile) {
		this.downloadDestFile = downloadDestFile;
	}
	public String getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}
	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
