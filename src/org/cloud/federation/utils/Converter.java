package org.cloud.federation.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * author Raouia Bouabdallah
 */

//import com.ttn.console.bean.Upload;
//import com.ttn.console.dao.UploadDAO;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Converter {

	public static String fileToString(String pathname) throws Exception {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(pathname));
			baos = new ByteArrayOutputStream();

			int size = 1024;
			byte[] buffer = new byte[size];

			while (true) {
				final int n = bis.read(buffer, 0, size);
				if (n == -1) {
					break;
				}
				baos.write(buffer, 0, n);
			}
			final File tmpImageFile = new File("file.ovf");
			FileOutputStream tmpOutputStream = null;
			String chaine = "";

			tmpOutputStream = new FileOutputStream(tmpImageFile);
			tmpOutputStream.write(baos.toByteArray());
			if (tmpOutputStream != null)

				tmpOutputStream.close();

			InputStream ips = new FileInputStream(tmpImageFile.getPath());
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				chaine += ligne + "\n";
			}
			br.close();

			return chaine;
		} finally {
			if (bis != null) {
				bis.close();
			}

		}
	}

	public static byte[] toByteArray(String pathname) throws Exception {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(pathname));
			baos = new ByteArrayOutputStream();

			int size = 1024;
			byte[] buffer = new byte[size];

			while (true) {
				final int n = bis.read(buffer, 0, size);
				// System.out.println("le nembre n:" + n);
				if (n == -1) {
					break;
				}
				baos.write(buffer, 0, n);
			}
			return baos.toByteArray();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (baos != null) {
				baos.close();
			}
		}
	}

	public static void main(String args[]) throws Exception {
		for (int i = 1; i <= 22; i++) {
			final String path = "C:/Users/rawia/Documents/NetBeansProjects/ttn-console/src/com/ttn/console/view/resources/images/resources/Photo"
					+ i + ".png";

			/*
			 * UploadDAO uploadDAO = new UploadDAO(); Upload upload = new
			 * Upload();
			 * 
			 * long x = 0L + i; upload.setId(x); upload.setCreation(new
			 * File(path).lastModified()); upload.setName(new
			 * File(path).getName()); upload.setMimeType("png"); byte[] bytes =
			 * toByteArray(path);
			 * 
			 * upload.setBytes(bytes); uploadDAO.create(upload);
			 * ///////////////// byte[] bytes=null; try {
			 * System.out.println("bytes: "+bytes); bytes =
			 * Converter.toByteArray(file.getPath());
			 * System.out.println("bytes: "+bytes); upload.setBytes(bytes);
			 * upload.setId(1L+uploadDAO.getUploadCount());
			 * uploadDAO.create(upload); this.setVisible(false); } catch
			 * (Exception ex) {
			 * 
			 * ex.printStackTrace(); }
			 */
		}
	}
}
