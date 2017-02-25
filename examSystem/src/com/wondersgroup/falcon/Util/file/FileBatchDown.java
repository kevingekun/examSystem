package com.wondersgroup.falcon.Util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileBatchDown {
	/**
	 * @param inputFileName
	 * @param zipFileName
	 * @throws Exception
	 */
	public static void zip(String inputFileName, String zipFileName) throws Exception {
		zip(zipFileName, new File(inputFileName));
	}

	public static void zip(String zipFileName, File inputFile) throws Exception {
		//ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName), "GBK");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, inputFile, "");
		// System.out.println("zip done");
		out.close();
	}

	public static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			// System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}




}
