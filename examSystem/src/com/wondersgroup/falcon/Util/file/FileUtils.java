package com.wondersgroup.falcon.Util.file;

import java.io.*;
import java.util.*;

import javax.servlet.ServletContext;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import com.wondersgroup.falcon.Util.StringUtils;

public class FileUtils {

	public static String ENCODING_DEFAULT = "ISO-8859-1";
	public static String GET_ENCODING_DEFAULT = "ISO-8859-1";
	public static String FILE_WRITING_ENCODING = "GBK";
	public static String TXT_FILE_SEPARATOR = "\t"  ;

	public static final int ERR_FILEOP_FAIL = 50;
	public static final int ERR_FILEOP_OPEN = 51;
	public static final int ERR_FILEOP_CLOSE = 52;
	public static final int ERR_FILEOP_READ = 53;
	public static final int ERR_FILEOP_WRITE = 54;
	public static final int ERR_FILE_NOTFOUND = 55;

	public FileUtils()
	{
	}

	public static boolean fileExists(String _sPathFileName)
	{
		File file = new File(_sPathFileName);
		return file.exists();
	}

	public static boolean pathExists(String _sPathFileName)
	{
		String sPath = extractFilePath(_sPathFileName);
		return fileExists(sPath);
	}

	public static String extractFileName(String _sFilePathName)
	{
		int nPos = _sFilePathName.lastIndexOf(File.separatorChar);
		return _sFilePathName.substring(nPos + 1);
	}

	public static String extractHttpFileName(String _sFilePathName)
	{
		if(_sFilePathName.equals("")) return new String("");

		int nPos = _sFilePathName.lastIndexOf("/");
		return _sFilePathName.substring(nPos + 1);
	}

	public static String extractFileExt(String _sFilePathName)
	{
		int nPos = _sFilePathName.lastIndexOf(46);
		return nPos < 0 ? "" : _sFilePathName.substring(nPos + 1);
	}

	public static String extractFilePath(String _sFilePathName)
	{
		int nPos = _sFilePathName.lastIndexOf(File.separatorChar);
		return nPos < 0 ? "" : _sFilePathName.substring(0, nPos + 1);
	}

	public static String toAbsolutePathName(String _sFilePathName)
	{
		File file = new File(_sFilePathName);

		return file.getAbsolutePath();
	}

	public static String extractFileDrive(String _sFilePathName)
	{
		int nLen = _sFilePathName.length();
		if(nLen > 2 && _sFilePathName.charAt(1) == ':')
			return _sFilePathName.substring(0, 2);
		if(nLen > 2 && _sFilePathName.charAt(0) == File.separatorChar && _sFilePathName.charAt(1) == File.separatorChar)
		{
			int nPos = _sFilePathName.indexOf(File.separatorChar, 2);
			if(nPos >= 0)
				nPos = _sFilePathName.indexOf(File.separatorChar, nPos + 1);
			return nPos < 0 ? _sFilePathName : _sFilePathName.substring(0, nPos);
		} else
		{
			return "";
		}
	}

	public static boolean deleteFile(String _sFilePathName)
	{
		File file = new File(_sFilePathName);
		return file.delete();
	}

	public static boolean makeDir(String _sDir, boolean _bCreateParentDir)
	{
		File file = new File(_sDir);
		if(_bCreateParentDir)
			return file.mkdirs();
		else
			return file.mkdir();
	}
	public static boolean makeFile(String _sFile) throws FileException{

		String filepath = extractFilePath(_sFile);
		String filename = StringUtils.replace(_sFile,filepath,"");
		System.out.println(filepath);
		System.out.println(filename);
		try{

			makeDir(filepath,true);
			//File dir = new File(filepath);
			//if(dir.canRead()) System.out.println("read");
			//if(dir.canWrite()) System.out.println("canwrite");
			//File.createTempFile("aaa","txt");
			//writeFile(_sFile,"");
			File file = new File(_sFile);
			file.createNewFile();


		}catch(IOException ex){
			throw new FileException(ERR_FILEOP_WRITE, "写文件时错误(FileUtils.makeFile) Filepath=" + _sFile, ex);
		}
		return true;

	}

	public static boolean deleteDir(String _sDir)
	{
		return deleteDir(_sDir, false);
	}

	public static boolean deleteDir(String _sDir, boolean _bDeleteChildren)
	{
		File file = new File(_sDir);
		if(!file.exists())
			return false;
		if(_bDeleteChildren)
		{
			File files[] = file.listFiles();
			for(int i = 0; i < files.length; i++)
				if(files[i].isDirectory())
					deleteDir(files[i].getAbsolutePath(), _bDeleteChildren);
				else
					files[i].delete();

		}
		return file.delete();
	}

	public static String readFile(String _sFileName)
	throws FileException
	{
		//File file = null;
		FileReader fileReader = null;
		StringBuffer buffContent = null;
		try
		{
			FileInputStream fis = new FileInputStream(_sFileName);
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(fis, FILE_WRITING_ENCODING));
			String sLine;
			while((sLine = buffReader.readLine()) != null) 
			{
				if(buffContent == null)
					buffContent = new StringBuffer();
				else
					buffContent.append("\n");
				buffContent.append(sLine);
			}
			buffReader.close();
			fis.close();
			String s = buffContent != null ? buffContent.toString() : "";
			return s;
		}
		catch(FileNotFoundException ex)
		{
			throw new FileException(ERR_FILE_NOTFOUND, "要读取得文件没有找到(FileUtils.readFile)", ex);
		}
		catch(IOException ex)
		{
			throw new FileException(ERR_FILEOP_READ, "读文件时错误(FileUtils.readFile)", ex);
		}
		finally
		{
			if(fileReader != null)
				try
			{
					fileReader.close();
			}
			catch(Exception exception1) { }
		}
	}

	public static boolean writeFile(String _sFileName, String _sFileContent)
	throws FileException
	{
		boolean bRet = false;
		try
		{
			FileOutputStream fos = new FileOutputStream(_sFileName);
			Writer outWriter = new OutputStreamWriter(fos, FILE_WRITING_ENCODING);
			outWriter.write(_sFileContent);
			bRet = true;
			outWriter.close();
			fos.close();
		}
		catch(Exception ex)
		{
			throw new FileException(ERR_FILEOP_WRITE, "写文件错误(FileUtils.writeFile)", ex);
		}
		return bRet;
	}

	public static boolean writeFile(String _sFileName, String _sFileContent, String _encoding)
	throws FileException
	{
		boolean bRet = false;
		try
		{
			FileOutputStream fos = new FileOutputStream(_sFileName);
			Writer outWriter = new OutputStreamWriter(fos, _encoding);
			outWriter.write(_sFileContent);
			bRet = true;
			outWriter.close();
			fos.close();
		}
		catch(Exception ex)
		{
			throw new FileException(ERR_FILEOP_WRITE, "写文件错误(FileUtils.writeFile)", ex);
		}
		return bRet;
	}

	public static boolean appendFile(String _sFileName, String _sAddContent)
	throws FileException
	{
		boolean bResult = false;
		try
		{
			RandomAccessFile raf = new RandomAccessFile(_sFileName, "rw");
			raf.seek(raf.length());
			raf.writeBytes(_sAddContent);
			raf.close();
			bResult = true;
		}
		catch(Exception ex)
		{
			throw new FileException(ERR_FILEOP_FAIL, "向文件追加内容时发生异常(FileUtils.appendFile)", ex);
		}
		return bResult;
	}

	public static void copyShareFile(String localFile, String remoteFile)throws FileException{

		InputStream in = null;   
        OutputStream out = null;   
        try {   
        	System.out.println(localFile);
        	System.out.println(remoteFile);
//        	UniAddress dc = UniAddress.getByName(DOMAIN_IP);   
//        	NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(DOMAIN_NAME, LOGIN_NAME, PASSWORD);   
//        	SmbSession.logon(dc, authentication);   
//        	  
//        	SmbFile remoteFile = new SmbFile(fileURL,  authentication); 
        	              
            SmbFile remoteF = new jcifs.smb.SmbFile(remoteFile);   
               
            in = new FileInputStream(new File(localFile));   
            
            byte[] buffer = new byte[1024*8];   
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteF));   
            while((in.read(buffer))!=-1){   
                out.write(buffer);          
            } 
            out.close();   
            in.close();
            
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
	}
	public static boolean copyFile(String _sSrcFile, String _sDstFile)
	throws FileException
	{
		boolean bResult = false;
		byte buffer[] = new byte[2048];
		try
		{
			FileInputStream fis = new FileInputStream(_sSrcFile);
			FileOutputStream fos = new FileOutputStream(_sDstFile);
			DataInputStream dis = new DataInputStream(fis);
			DataOutputStream dos = new DataOutputStream(fos);
			//int i = 0;
			int bytes;
			while((bytes = dis.read(buffer, 0, 2048)) > 0) 
				dos.write(buffer, 0, bytes);
			dis.close();
			dos.close();
			fis.close();
			fos.close();
			bResult = true;
		}
		catch(FileNotFoundException ex)
		{
			throw new FileException(ERR_FILE_NOTFOUND, "要复制的原文件没有发现(FileUtils.copyFile)", ex);
		}
		catch(IOException ex)
		{
			throw new FileException(ERR_FILEOP_FAIL, "复制文件时发生异常(FileUtils.copyFile)", ex);
		}
		return bResult;
	}


	public static File[] listFiles(String _dir, String _extendName)
	{
		File files[];
		File fDir = new File(_dir);
		if(_extendName.equals("")){
			files = fDir.listFiles();
		}else{
			if(_extendName.charAt(0) != '.')
				_extendName = ".".concat(String.valueOf(String.valueOf(_extendName)));
			files = fDir.listFiles(new FilenameFilterImpl(_extendName));
		}
		return files;
	}

	public static File[] listDirs(String _dir){
		File fDir = new File(_dir);
		File[] files = fDir.listFiles();
		ArrayList dirs = new ArrayList();
		for(int i = 0; i< files.length; i++){
			File file = files[i];
			if(file.isDirectory()){ 
				dirs.add(file);
			}
		}
		File[] fDirs = new File[dirs.size()];
		for(int j = 0; j < dirs.size() ; j ++ )
			fDirs[j] = (File)dirs.get(j);
		return fDirs;
	}

	public static File[] listFiles(String _dir){
		File fDir = new File(_dir);
		File[] files = fDir.listFiles();
		ArrayList dirs = new ArrayList();
		for(int i = 0; i< files.length; i++){
			File file = files[i];
			if(file.isFile()){ 
				dirs.add(file);
			}
		}
		File[] fDirs = new File[dirs.size()];
		for(int j = 0; j < dirs.size() ; j ++ )
			fDirs[j] = (File)dirs.get(j);
		return fDirs;
	}

	public static File[] listDirs(File _dir){
		File fDir = _dir;
		File[] files = fDir.listFiles();
		ArrayList dirs = new ArrayList();
		for(int i = 0; i< files.length; i++){
			File file = files[i];
			if(file.isDirectory()){ 
				dirs.add(file);
			}
		}
		File[] fDirs = new File[dirs.size()];
		for(int j = 0; j < dirs.size() ; j ++ )
			fDirs[j] = (File)dirs.get(j);
		return fDirs;
	}

	//获取目录树
	public static File[] getAllSubDir(File _rootdir){
		ArrayList array = new ArrayList();
		File fRootDir = _rootdir;
		addDir(array, fRootDir);
		File[] childs = listDirs(fRootDir);
		for(int i=0; i<childs.length; i++){
			File child = childs[i];
			if(listDirs(child).length == 0) 
				array = addDir(array, child);
			else{
				File[] fchilds = getAllSubDir(child);
				array = addDir(array, fchilds);
			}

		}
		File[] result = new File[array.size()];
		for(int j = 0; j < array.size(); j++){
			result[j] = (File)array.get(j);
		}
		return result;

	}
	public static ArrayList addDir(ArrayList al, File file){
		if(al == null) al = new ArrayList();
		if(file == null ) return al ;
		else{
			//ArrayList real = al;
			al.add(file);
			return al;
		}
	}

	public static ArrayList addDir(ArrayList al, File[] files){
		if(al == null) al = new ArrayList();
		if(files == null ) return al ;
		else{
			//ArrayList real = al;
			for(int i=0; i<files.length; i++)
				al.add(files[i]);
			return al;
		}
	}

	public static String getDirTreeId(File file){
		if(file == null )
			return "";
		String fname = file.getName();
		String fpath = file.getPath();
		//int deep = StringUtils.countCharInStr(fpath, '\\');
		int deep = StringUtils.countCharInStr(fpath, File.separatorChar);
		String fid = fname+deep;
		fid = StringUtils.filterForVariableName(fid);
		return fid;

	}

//	==================== 文件处理的方法 =========================

	/**
	 * 删除文件
	 * @param infile 要被删除的文件名（绝对路径）
	 * @return 是否成功
	 */
	public static boolean removeFile(String infile) {
		if (infile == null ) return false;
		File f = new File(infile);
		if (f == null ) return false;
		return  f.delete();
	}

	/**
	 * 获得目录下文件列表
	 * @param indir 目录名（绝对路径）
	 * @return 目录下文件列表
	 */
	public static String[] getDirList(String indir) {
		File f = new File(indir);
		return  f.list();
	}


	/**
	 * 获取文件名，除去它的路径和后缀。
	 *
	 * @param fname 包含路径和后缀的完整文件名
	 * @param suff 文件名的后缀
	 * @return 除去路径和后缀的文件名
	 */
	public static String getFileName(String fname, String suff) {

		String mycont = fname;
		int sind = fname.lastIndexOf( "/" ) + 1;
		if ( sind < 1 ) sind = fname.lastIndexOf( "\\" ) + 1;
		if ( sind < 1 ) sind = 0;
		int eind = fname.lastIndexOf(suff);
		if ( eind < 0 ) {
			mycont = fname.substring(sind);
		} else {
			mycont = fname.substring(sind, eind);
		}

		return mycont;

	}

	/**
	 * 获取文件名，除去它的路径。
	 *
	 * @param fname 包含路径的完整文件名
	 * @return 除去路径的文件名
	 */
	public static String getFileName(String fname) {

		int sind = fname.lastIndexOf( "/" ) + 1;
		if ( sind < 1 ) sind = fname.lastIndexOf( "\\" ) + 1;
		if ( sind < 1 ) sind = 0;

		return fname.substring(sind);

	}

	/**
	 * 获取文件的路径，除去它的文件名。
	 *
	 * @param fname 包含路径的完整文件名
	 * @return 除去文件名的路径
	 */
	public static String getFilePath(String fname) {

		int sind = fname.lastIndexOf( "/" ) + 1;
		if ( sind < 1 ) sind = fname.lastIndexOf( "\\" ) + 1;
		if ( sind < 1 ) sind = 0;

		return fname.substring(0, sind);

	}
	/**
	 * 获取URL路径的文件或者目录名
	 * @param url
	 * @return
	 */
	public static String getURLFileName(String url){

		int sind = url.lastIndexOf("/") + 1;
		if( sind < 1) sind = 0;

		return url.substring(sind);

	}
	/**
	 * 获取URL路径的上层目录名
	 * @param url
	 * @return
	 */

	public static String getURLUpDirName(String url){

		int sind = url.lastIndexOf("/") + 1;
		if( sind < 1) sind = 0;

		return url.substring(0, sind);
	}
	/**
	 * 将数据写入一个txt文件
	 * @param fileName 文件名（绝对路径）
	 * @param fileEncoding 文件的编码
	 * @param names 数据名
	 * @param data 数据值
	 * @param outName 是否写入表头行
	 * @return 是否成功
	 */
	public static String  writeTxtFile(String fileName,
			String fileEncoding,
			ArrayList names,
			ArrayList data,
			boolean  outName) {
		if ( names == null ) return null;
		if ( names.size() < 1 ) return null;
		try {

			File f = new File(fileName);
			f.createNewFile();

			//注意这里要设定字符集！
			PrintWriter out
			= new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(f),fileEncoding)));

			String myname,myvalue;
			if ( outName ) {
				out.print(names.get(0).toString() );
				for (int j = 1; j < names.size(); j++) {
					myname = names.get(j).toString();
					out.print(TXT_FILE_SEPARATOR + myname   );
				}
				out.println("");
			}
			Hashtable myd;
			for ( int i=0; i< data.size(); i++) {
				myd = (Hashtable)data.get(i);
				myname = names.get(0).toString();
				if ( myd.get(myname) == null )
					myvalue = "";
				else
					myvalue = myd.get(myname).toString();
				out.print(myvalue);
				for (int j = 1; j < names.size(); j++) {
					myname = names.get(j).toString();
					if ( myd.get(myname) == null )
						myvalue = "";
					else
						myvalue = myd.get(myname).toString();
					out.print(TXT_FILE_SEPARATOR + myvalue);
				}
				out.println("");
			}
			out.close();
			return "successful";
		} catch (IOException e) {
			return "io_exception";
		}

	}


	public static String getRealPath(ServletContext application, String url){
		return application.getRealPath(url);
	}


	public static void main(String args[])
	{
		String root = "D:/WCMS_ROOT";
		File file = new File(root);

		File[] files = FileUtils.listDirs(root);

		System.out.println(files.length);
	}


}




