/**
 * 
 */
package com.wondersgroup.falcon.Util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import java.util.zip.GZIPInputStream;
import java.io.DataInputStream;

import com.wondersgroup.falcon.Util.PropertiesUtil;


/**
 * <p>Title:[�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-11-27]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */

public class ZipManager {



	/**
	 * zipѹ�����ܲ���. ��d:\\temp\\zipoutĿ¼�µ������ļ���ͬ��Ŀ¼ѹ����d:\\temp\\out.zip.
	 *
	 * @param baseDir ��Ҫѹ����Ŀ¼������������·����
	 * @param objFileName ѹ������ļ���
	 * @throws Exception
	 */
	public void createZip(String baseDir, String objFileName) throws Exception {

		File folderObject = new File(baseDir);

		if (folderObject.exists()){
			List fileList = getSubFiles(new File(baseDir));

			//ѹ���ļ���
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(objFileName));

			ZipEntry ze = null;
			byte[] buf = new byte[1024];
			int readLen = 0;
			for (int i = 0; i < fileList.size(); i++) {
				File f = (File) fileList.get(i);
				System.out.println("Adding: " + f.getPath() + f.getName());

				//����һ��ZipEntry��������Name��������һЩ����
				ze = new ZipEntry(getAbsFileName(baseDir, f));
				ze.setSize(f.length());
				ze.setTime(f.lastModified());

				//��ZipEntry�ӵ�zos�У���д��ʵ�ʵ��ļ�����
				zos.putNextEntry(ze);
				InputStream is = new BufferedInputStream(new FileInputStream(f));
				while ((readLen = is.read(buf, 0, 1024)) != -1) {
					zos.write(buf, 0, readLen);
				}
				is.close();
				System.out.println("done...");
			}
			zos.close();
		}else{
			throw new Exception("this folder isnot exist!");
		}
	}
	/**
	 * zipѹ�����ܲ���. ��ָ���ļ�ѹ����浽һѹ���ļ���
	 *
	 * @param baseDir ��Ҫѹ�����ļ���
	 * @param objFileName ѹ������ļ���
	 * @return ѹ�����ļ��Ĵ�С
	 * @throws Exception
	 */
	public long createFileToZip(String sourceFileName,String zipFilename) throws Exception {
		File  sourceFile = new File(sourceFileName);

		byte[] buf = new byte[1024];

		//ѹ���ļ���
		File objFile = new File(zipFilename);

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(objFile));

		ZipEntry ze = null;
		//����һ��ZipEntry��������Name��������һЩ����
		ze = new ZipEntry(sourceFile.getName());
		ze.setSize(sourceFile.length());
		ze.setTime(sourceFile.lastModified());

		//��ZipEntry�ӵ�zos�У���д��ʵ�ʵ��ļ�����
		zos.putNextEntry(ze);

		InputStream is = new BufferedInputStream(new FileInputStream(sourceFile));

		int readLen = -1;
		while ((readLen = is.read(buf, 0, 1024)) != -1) {
			zos.write(buf, 0, readLen);
		}
		is.close();
		zos.close();

		return objFile.length();
	}
	public long createFilesToZip(String sourceFileName[],String zipFilename) throws Exception {
		
		if(sourceFileName==null || sourceFileName.length==0)
			return 0;

		//ѹ���ļ���
		File objFile = new File(zipFilename);

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(objFile));
		
		for(int i = 0 ; i<sourceFileName.length ; i++){
			//System.out.println(sourceFileName[i]);
			
			File  sourceFile = new File(sourceFileName[i]);

			byte[] buf = new byte[1024];
			
			ZipEntry ze = null;
			//����һ��ZipEntry��������Name��������һЩ����
			ze = new ZipEntry(sourceFile.getName());
			ze.setSize(sourceFile.length());
			ze.setTime(sourceFile.lastModified());

			//��ZipEntry�ӵ�zos�У���д��ʵ�ʵ��ļ�����
			zos.putNextEntry(ze);

			InputStream is = new BufferedInputStream(new FileInputStream(sourceFile));

			int readLen = -1;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				zos.write(buf, 0, readLen);
			}
			is.close();
		}
		
		zos.close();

		return objFile.length();
	}
	/**
	 * zipѹ�����ܲ���. ��ָ���ļ�ѹ����浽һѹ���ļ���
	 *
	 * @param baseDir ��Ҫѹ�����ļ���
	 * @param objFileName ѹ������ļ���
	 * @return ѹ�����ļ��Ĵ�С
	 * @throws Exception
	 */
	public long createFileToZip(File sourceFile,File zipFile)throws IOException {

		byte[] buf = new byte[1024];

		//ѹ���ļ���
		File objFile = zipFile;

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(objFile));

		ZipEntry ze = null;
		//����һ��ZipEntry��������Name��������һЩ����
		ze = new ZipEntry(sourceFile.getName());
		ze.setSize(sourceFile.length());
		ze.setTime(sourceFile.lastModified());

		//��ZipEntry�ӵ�zos�У���д��ʵ�ʵ��ļ�����
		zos.putNextEntry(ze);

		InputStream is = new BufferedInputStream(new FileInputStream(sourceFile));

		int readLen = -1;
		while ((readLen = is.read(buf, 0, 1024)) != -1) {
			zos.write(buf, 0, readLen);
		}
		is.close();
		zos.close();

		return objFile.length();
	}



	/**
	 * ���Խ�ѹ������. ��d:\\download\\test.zip��ͬ��Ŀ¼��ѹ��d:\\temp\\zipoutĿ¼��.
	 *
	 * @throws Exception
	 */
	public void releaseZipToFile(String sourceZip, String outFileName)
	throws IOException{
		ZipFile zfile=new ZipFile(sourceZip);
		System.out.println(zfile.getName());
		Enumeration zList=zfile.entries();
		ZipEntry ze=null;
		byte[] buf=new byte[1024];
		while(zList.hasMoreElements()){
			//��ZipFile�еõ�һ��ZipEntry
			ze=(ZipEntry)zList.nextElement();
			if(ze.isDirectory()){
				continue;
			}
			//��ZipEntryΪ�����õ�һ��InputStream����д��OutputStream��
			OutputStream os=new BufferedOutputStream(new FileOutputStream(getRealFileName(outFileName, ze.getName())));
			InputStream is=new BufferedInputStream(zfile.getInputStream(ze));
			int readLen=0;
			while ((readLen=is.read(buf, 0, 1024))!=-1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
			//System.out.println("Extracted: "+ze.getName());
		}
		zfile.close();

	}



	/**
	 * ȡ��ָ��Ŀ¼�µ������ļ��б�������Ŀ¼.
	 *
	 * @param baseDir
	 *            File ָ����Ŀ¼
	 * @return ����java.io.File��List
	 */
	private List getSubFiles(File baseDir) {
		List ret = new ArrayList();
		//File base=new File(baseDir);
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}
			if (tmp[i].isDirectory()) {
				ret.addAll(getSubFiles(tmp[i]));
			}
		}
		return ret;
	}



	/**
	 * ������Ŀ¼������һ�����·������Ӧ��ʵ���ļ���.
	 *
	 * @param baseDir
	 *            ָ����Ŀ¼
	 * @param absFileName
	 *            ���·������������ZipEntry�е�name
	 * @return java.io.File ʵ�ʵ��ļ�
	 */
	private File getRealFileName(String baseDir, String absFileName) {
		String[] dirs = absFileName.split("/");
		//System.out.println(dirs.length);
		File ret = new File(baseDir);
		//System.out.println(ret);
		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				ret = new File(ret, dirs[i]);
			}
		}
		if (!ret.exists()) {
			ret.mkdirs();
		}
		ret = new File(ret, dirs[dirs.length - 1]);
		return ret;
	}



	/**
	 * ������Ŀ¼��������һ���ļ��������·��������zip�ļ��е�·��.
	 *
	 * @param baseDir
	 *            java.lang.String ��Ŀ¼
	 * @param realFileName
	 *            java.io.File ʵ�ʵ��ļ���
	 * @return ����ļ���
	 */
	private String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else {
				ret = real.getName() + "/" + ret;
			}
		}
		//System.out.println("TTTTT" + ret);
		return ret;
	}


	public void testReadZip() throws Exception{
		String baseDir="D:\\libstore.war\\policy";
		ZipFile zfile=new ZipFile("d:\\src.zip");
		System.out.println(zfile.getName());
		Enumeration zList=zfile.entries();
		ZipEntry ze=null;
		byte[] buf=new byte[1024];
		while(zList.hasMoreElements()){
			//��ZipFile�еõ�һ��ZipEntry
			ze=(ZipEntry)zList.nextElement();
			if(ze.isDirectory()){
				continue;
			}
			//��ZipEntryΪ�����õ�һ��InputStream����д��OutputStream��
			OutputStream os=new BufferedOutputStream(new FileOutputStream(getRealFileName(baseDir, ze.getName())));
			InputStream is=new BufferedInputStream(zfile.getInputStream(ze));
			int readLen=0;
			while ((readLen=is.read(buf, 0, 1024))!=-1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
			System.out.println("Extracted: "+ze.getName());
		}
		zfile.close();
	}


	public static void main(String args[]){
		ZipManager manager = new ZipManager();
		try {
			//manager.releaseZipToFile("c:\\test.zip","c:\\test");
			//manager.testReadZip();
			//manager.createZip("D:\\libstore.war\\policy","d:\\test.zip");
			PropertiesUtil p = new PropertiesUtil();
			String filepath = p.getProperties("path.policy");
			String fileUrlPath = p.getProperties("filepath.url.policy");
			
			String s [] = {"D:\\libstore.war\\policy\\1377.html","D:\\libstore.war\\policy\\287.html","D:\\libstore.war\\policy\\6051.html"};
			manager.createFilesToZip(s, filepath+"uploadfile.zip");
			
			manager.createFileToZip("d:\\test.zip", "D:\\libstore.war\\policy\\1377.html");
			manager.createFileToZip("d:\\test.zip", "D:\\libstore.war\\policy\\287.html");
			//manager.releaseZipToFile("d:\\test.zip","D:\\libstore.war\\policy");
		}
		catch (Exception e) {}
		System.out.println("over");
	}



}


