package com.wondersgroup.falcon.Util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.wondersgroup.falcon.Util.PropertiesUtil;
import com.wondersgroup.falcon.Util.StringUtil;
import com.wondersgroup.falcon.Util.StringUtils;
/**
 * 
 * <p>Title:[sftpЭ��FTP���񹤾���] </p>
 * <p>Description: [sftpЭ�� ��FTP�����£��ļ��Ĵ���]</p> 
 *
 * Created by [Kevin Liang] [2010-2-1]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public class SFtpUtil  {

	/**
	 * ����sftp������
	 * @param host ����
	 * @param port �˿�
	 * @param username �û���
	 * @param password ����
	 * @return
	 */
	public ChannelSftp connect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			//System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			//System.out.println("Session connected.");
			//System.out.println("Opening Channel.");

			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;

			//System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftp;
	}
	public ChannelSftp connect() {
		PropertiesUtil pu = new PropertiesUtil();
		String host = pu.getProperties("JHK.server.ip");
		String port = pu.getProperties("JHK.server.port");		
		String username = pu.getProperties("JHK.server.username");
		String password = pu.getProperties("JHK.server.password");						
		//String tofile =pu.getProperties("JHK.server.filepath");

		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, Integer.parseInt(port));
			Session sshSession = jsch.getSession(username, host, Integer.parseInt(port));
			//System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			//System.out.println("Session connected.");
			//System.out.println("Opening Channel.");

			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;

			//System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftp;
	}
	/**
	 * �ϴ��ļ�
	 * @param directory �ϴ���Ŀ¼
	 * @param uploadFile Ҫ�ϴ����ļ�
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp) throws Exception{
		try {
			sftp.cd(directory);
			File file=new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {

			e.printStackTrace();
			throw e ;
		}
	}

	/**
	 * �����ļ�
	 * @param directory ����Ŀ¼
	 * @param downloadFile ���ص��ļ�
	 * @param saveFile ���ڱ��ص�·��
	 * @param sftp
	 */
	public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) throws Exception{
		try {
			sftp.cd(directory);
			File file=new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
			throw e ; 
		}
	}

	/**
	 * ɾ���ļ�
	 * @param directory Ҫɾ���ļ�����Ŀ¼
	 * @param deleteFile Ҫɾ�����ļ�
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) throws Exception {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e ; 
		}
	}

	/**
	 * �г�Ŀ¼�µ��ļ�
	 * @param directory Ҫ�г���Ŀ¼
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException{
		return sftp.ls(directory);
	}

	/**
	 * 
	 * <p>Description:[����������������] </p>
	 * 
	 * Created by [Kevin Liang] [2010-5-5]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param srcDirectory �����ļ���
	 * @param directory ftp�ļ���
	 * @param sftp
	 * @throws SftpException
	 */
	public void directoryFilesSynchronization(String srcDirectory, String directory, ChannelSftp sftp)throws SftpException{

		File file=new File(srcDirectory);
		if(!file.isDirectory()) return ; 

		Vector fileV = sftp.ls(directory);
		File []srcF = file.listFiles();

		for(int i = 0 ;i<srcF.length ; i ++){
			
			boolean flag = false ;
			for(int ii = 0 ;ii<fileV.size() ; ii ++){
				LsEntry ls  = (LsEntry)fileV.get(ii);

				if(srcF[i].getName().equals(ls.getFilename())){
					flag = true ;
					//System.out.println("0------------------------"+srcF[i].getName());
				}
			}
			if (!flag){
				try {
					this.upload(directory, srcDirectory+srcF[i].getName(), sftp);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {

		SFtpUtil sf = new SFtpUtil(); 
		String host = "192.168.0.1";
		int port = 22;
		String username = "root";
		String password = "root";
		String directory = "/home/httpd/test/";
		String uploadFile = "D:\\tmp\\upload.txt";
		String downloadFile = "upload.txt";
		String saveFile = "D:\\tmp\\download.txt";
		String deleteFile = "delete.txt";

		ChannelSftp sftp=sf.connect(host, port, username, password);

		try{
			sf.upload(directory, uploadFile, sftp);
			sf.download(directory, downloadFile, saveFile, sftp);
			sf.delete(directory, deleteFile, sftp);

			sftp.cd(directory);
			sftp.mkdir("ss");
			System.out.println("finished");
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
}