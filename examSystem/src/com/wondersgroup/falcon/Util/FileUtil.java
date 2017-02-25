/**
 * <p>Title: 飞鱼论坛</p>
 * <p>Description: 飞鱼论坛－－文件操作共用类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 飞鱼工作室</p>
 * @author 飞鱼
 * @version 2.0
 */

package com.wondersgroup.falcon.Util;

import java.io.*;

import org.apache.log4j.Logger;


public final class FileUtil {

	private static Logger log = Logger.getLogger(FileUtil.class);

  private FileUtil() { // prevent instantiation
  }

  public static void createDir(String dir, boolean ignoreIfExitst) throws
      IOException {
    File file = new File(dir);

    if (ignoreIfExitst && file.exists()) {
      return;
    }

    if (file.mkdir() == false) {
      throw new IOException("不能读取该目录 = " + dir);
    }
  }

  public static void createDirs(String dir, boolean ignoreIfExitst) throws
      IOException {
    File file = new File(dir);

    if (ignoreIfExitst && file.exists()) {
      return;
    }

    if (file.mkdirs() == false) {
      throw new IOException("不能读取该目录 = " + dir);
    }
  }

  public static void deleteFile(String filename) throws IOException {
    File file = new File(filename);
    log.warn("Delete file = " + filename);
    if (file.isDirectory()) {
      throw new IOException("IOException -> BadInputException: 不是一个合法的文件.");
    }
    if (file.exists() == false) {
      throw new IOException(
          "IOException -> BadInputException: 文件不存在.");
    }
    if (file.delete() == false) {
      throw new IOException("不能删除文件. filename = " + filename);
    }
  }

  public static void deleteDir(File dir) throws IOException {
    if (dir.isFile()) {
      throw new IOException(
          "IOException -> BadInputException: 不是一个目录.");
    }
    File[] files = dir.listFiles();
    if (files != null) {
      for (int i = 0; i < files.length; i++) {
        File file = files[i];
        if (file.isFile()) {
          file.delete();
        }
        else {
          deleteDir(file);
        }
      }
    } //if
    dir.delete();
  }

  public static long getDirLength(File dir) throws IOException {
    if (dir.isFile()) {
      throw new IOException("BadInputException: 不是一个目录.");
    }
    long size = 0;
    File[] files = dir.listFiles();
    if (files != null) {
      for (int i = 0; i < files.length; i++) {
        File file = files[i];
        long length = 0;
        if (file.isFile()) {
          length = file.length();
        }
        else {
          length = getDirLength(file);
        }
        size += length;
      } //for
    } //if
    return size;
  }

  public static long getDirLength_onDisk(File dir) throws IOException {
    if (dir.isFile()) {
      throw new IOException("BadInputException:  不是一个目录.");
    }
    long size = 0;
    File[] files = dir.listFiles();
    if (files != null) {
      for (int i = 0; i < files.length; i++) {
        File file = files[i];
        long length = 0;
        if (file.isFile()) {
          length = file.length();
        }
        else {
          length = getDirLength_onDisk(file);
        }
        double mod = Math.ceil( ( (double) length) / 512);
        if (mod == 0) {
          mod = 1;
        }
        length = ( (long) mod) * 512;
        size += length;
      }
    } //if
    return size;
  }

  public static byte[] getBytes(InputStream inputStream) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
        1024);
    byte[] block = new byte[512];
    while (true) {
      int readLength = inputStream.read(block);
      if (readLength == -1) {
        break; // end of file
      }
      byteArrayOutputStream.write(block, 0, readLength);
    }
    byte[] retValue = byteArrayOutputStream.toByteArray();
    byteArrayOutputStream.close();
    return retValue;
  }

  public static String getFileName(String fullFilePath) {
    if (fullFilePath == null) {
      return "";
    }
    int index1 = fullFilePath.lastIndexOf('/');
    int index2 = fullFilePath.lastIndexOf('\\');

    //index is the maximum value of index1 and index2
    int index = (index1 > index2) ? index1 : index2;
    if (index == -1) {
      // not found the path separator
      return fullFilePath;
    }
    String fileName = fullFilePath.substring(index + 1);
    return fileName;
  }
}
