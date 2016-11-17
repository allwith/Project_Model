package com.ruanmeng.project_model.UpLoadPic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Map;

public class Test {

	private static final String BOUNDARY = "----WebKitFormBoundaryT1HoybnYeFOGFlBR";

	/**
	 *
	 * @param params
	 *            传递的普通参数
	 * @param uploadFile
	 *            需要上传的文件名
	 * @param fileFormName
	 *            需要上传文件表单中的名字
	 * @param newFileName
	 *            上传的文件名称，不填写将为uploadFile的名称
	 * @param urlStr
	 *            上传的服务器的路径
	 * @throws IOException
	 */
	public void uploadForm(Map<String, String> params, String fileFormName,
						   File uploadFile, String newFileName, String urlStr)
			throws IOException {
		if (newFileName == null || newFileName.trim().equals("")) {
			newFileName = uploadFile.getName();
		}

		StringBuilder sb = new StringBuilder();
		/**
		 * 普通的表单数据
		 */
		if (params != null)
			for (String key : params.keySet()) {
				sb.append("--" + BOUNDARY + "\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + key
						+ "\"" + "\r\n");
				sb.append("\r\n");
				sb.append(params.get(key) + "\r\n");
			}
		/**
		 * 上传文件的头
		 */
		sb.append("--" + BOUNDARY + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"" + fileFormName
				+ "\"; filename=\"" + newFileName + "\"" + "\r\n");
		sb.append("Content-Type: image/jpeg" + "\r\n");// 如果服务器端有文件类型的校验，必须明确指定ContentType
		sb.append("\r\n");

		byte[] headerInfo = sb.toString().getBytes("UTF-8");
		byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
		System.out.println(sb.toString());
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + BOUNDARY);
		conn.setRequestProperty("Content-Length", String
				.valueOf(headerInfo.length + uploadFile.length()
						+ endInfo.length));
		conn.setDoOutput(true);

		OutputStream out = conn.getOutputStream();
		InputStream in = new FileInputStream(uploadFile);
		out.write(headerInfo);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) != -1)
			out.write(buf, 0, len);

		out.write(endInfo);
		in.close();
		out.close();
		if (conn.getResponseCode() == 200) {
			System.out.println("上传成功");
		}

	}

	/**
	 *
	 * @param params
	 *            传递的普通参数
	 * @param uploadFile
	 *            需要上传的文件名
	 * @param fileFormName
	 *            需要上传文件表单中的名字
	 * @param newFileName
	 *            上传的文件名称，不填写将为uploadFile的名称
	 * @param urlStr
	 *            上传的服务器的路径
	 * @throws IOException
	 */
	public void uploadFromBySocket(Map<String, String> params,
								   String fileFormName, File uploadFile, String newFileName,
								   String urlStr) throws IOException {
		if (newFileName == null || newFileName.trim().equals("")) {
			newFileName = uploadFile.getName();
		}

		StringBuilder sb = new StringBuilder();
		/**
		 * 普通的表单数据
		 */
		if (params != null)
			for (String key : params.keySet()) {
				sb.append("--" + BOUNDARY + "\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + key
						+ "\"" + "\r\n");
				sb.append("\r\n");
				sb.append(params.get(key) + "\r\n");
			}
		else {
			sb.append("\r\n");
		}

		/**
		 * 上传文件的头
		 */
		sb.append("--" + BOUNDARY + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"" + fileFormName
				+ "\"; filename=\"" + newFileName + "\"" + "\r\n");
		sb.append("Content-Type: image/jpeg" + "\r\n");// 如果服务器端有文件类型的校验，必须明确指定ContentType
		sb.append("\r\n");

		byte[] headerInfo = sb.toString().getBytes("UTF-8");
		byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");

		System.out.println(sb.toString());

		URL url = new URL(urlStr);
		Socket socket = new Socket(url.getHost(), url.getPort());
		OutputStream os = socket.getOutputStream();
		PrintStream ps = new PrintStream(os, true, "UTF-8");

		// 写出请求头
		ps.println("POST " + urlStr + " HTTP/1.1");
		ps.println("Content-Type: multipart/form-data; boundary=" + BOUNDARY);
		ps.println("Content-Length: "
				+ String.valueOf(headerInfo.length + uploadFile.length()
				+ endInfo.length));

		InputStream in = new FileInputStream(uploadFile);
		// 写出数据
		os.write(headerInfo);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) != -1)
			os.write(buf, 0, len);

		os.write(endInfo);

		in.close();
		os.close();
		ps.close();
	}

	public static void main(String[] args) {
		try {

			File file = new File("D:/dtd", "dwr30.dtd");

			new Test().uploadForm(null, "uploadFile", file, "helloworld.txt",
					"http://localhost:8080/strurts2fileupload/uploadAction");

			new Test().uploadFromBySocket(null, "uploadFile", file,
					"hibernate-configuration-3.0.dtd",
					"http://localhost:8080/strurts2fileupload/uploadAction");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}