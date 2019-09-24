package com.example.administrator.demo.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import okhttp3.ResponseBody;

/**
 * 文件类 所有文件操作 2014-06-04
 * 
 * @author mabo
 * 
 */
public class FileUtils {

	/** 最外层目录 **/
	public static String SYS_TEMMD = "xinshangyu";

	/** 最外层目录 **/
	public static String PROPERTY = "property";

	/** 资讯 **/
	public static String ZIXUN = "zixun";

	/** 图片信息 **/
	public static String IMAGE = "image";
	
	/** 离线图片信息 **/
	public static String OFFLINE_IMAGE = "offlineimage";

	/** 缓存目录 **/
	public static String CACHE = "cache";
	/** 日志文件目录 **/
	public static String LOG = "log";
	/**
	 */

	/**
	 * 根据任务号
	 * 得到图片图片目录
	 *
	 * @param
	 * @return
	 */
	public static String getImagePath() {
		String sdcard = Environment.getExternalStorageDirectory().toString();

		return sdcard+"/" +"zsbx.jpg";
	}

	/**
	 * 得到缓存目录
	 * 
	 * @param
	 * @return
	 */
	public static String getCacheMD() {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/";
	}

	/**
	 * 得到用户新建目录
	 *
	 * @param
	 * @return
	 */
	public static String getUserNew(String fileName) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" + fileName);
		if(!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" + fileName + "/";
	}



	/**
	 * 得到video目录
	 * 
	 * @param
	 * @return
	 */
	public String getVideoMD() {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + "video");
		if (!file2.exists())
			file2.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + "video";
	}
	
	public String getphoneMonitorMD(String reportno) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + "monitor");
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + "monitor"+"/"+reportno);
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + "monitor"+"/"+reportno;
	}
	
	/**
	 * 得到离线video目录
	 * 
	 * @param
	 * @return
	 */
	public String getOfflineVideoMD(String um) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + "offlinevideo");
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + "offlinevideo"+"/"+um);
		if (!file3.exists())
			file3.mkdir();
		
		return sdcard + "/" + SYS_TEMMD + "/" + "offlinevideo"+"/"+um;
	}
	

	/**
	 * 根据任务号
	 * 得到图片图片目录
	 * 
	 * @param
	 * @return
	 */
	public static String getImageMd(String reportNo) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = null;
		if (!(file = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+ "/" + IMAGE + "/"
				+ reportNo)).exists()) {
			file.mkdirs();
		}

		return file.getPath();
	}

	/**
	 * 得到展业图片目录
	 */
	public static String getImageMd() {
		String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		File file = null;
		if (!(file = new File(sdcard + "/" + "Pictures" + "/" + "浙商保险")).exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}


	/**
	 	根据 cInfoId  主键
	 * 得到资讯目录
	 * @param
	 * @return
	 */
	public static String getZiXun(String cInfoId) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+ZIXUN);
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +ZIXUN+"/"+cInfoId + ".pa";
	}

	/**
	 * 根据用户名
	 * 得到记事本目录
	 *
	 * @param
	 * @return
	 */
	public static String getNoteMd() {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = null;
		if (!(file = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"
				+ "notes")).exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}
	/**
	 * 得到图片图片目录
	 * 
	 * @param
	 * @return
	 */
	public String getOfflineImageMd(String um) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = null;
		if (!(file = new File(sdcard + "/" + SYS_TEMMD + "/" + OFFLINE_IMAGE + "/"
				+ um)).exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}
	

	/**
	 * 获取本地案件
	 * 
	 * @param
	 * @return
	 */
	public String getCommunicateInfo() {
		return carateTaskMd("property_communicate_list");
	}
	
	/**
	 * 获取本地案件
	 * 
	 * @param
	 * @return
	 */
	public String getTaskInfo() {
		return carateTaskMd("property_task_list");
	}

	/**
	 * 获取本地案件
	 * 
	 * @param
	 * @return
	 */
	public String getPayHistory() {
		return carateTaskMd("pay_history_list");
	}

	
	/**
	 * 获取案件处理本地案件
	 * 
	 * @param
	 * @return
	 */
	public String getDoTask() {
		return carateTaskMd("do_task_list");
	}
	
	

	/**
	 * 获取立案本地案件
	 * 
	 * @param
	 * @return
	 */
	public String getRegisterTask() {
		return carateTaskMd("register_task_list");
	}
	
	
	/**
	 * 获取消息提醒本地立案案件
	 * 
	 * @param
	 * @return
	 */
	public String getMessageRemindInfo() {
		return carateTaskMd("message_remind_list");
	}
	
	
	
	/**
	 * 获取消息提醒本地结案案件
	 * 
	 * @param
	 * @return
	 */
	public String getMessageColseInfo() {
		return carateTaskMd("message_colse_list");
	}
	
	/**
	 * 获取结案处理本地案件
	 * 
	 * @param
	 * @return
	 */
	public String getCloseTask() {
		return carateTaskMd("close_task_list");
	}
	
	
	
	/**
	 * 获取收单案件
	 * 
	 * @param
	 * @return
	 */
	public String getCaseTask() {
		return carateTaskMd("case_task_list");
	}
	
	
	
	
	/**
	 * 获取委托查勘
	 * 
	 * @param
	 * @return
	 */
	public String getSurveyTask() {
		return carateTaskMd("survey_task_list");
	}
	
	
	/**
	 * 获取上传单证
	 * 
	 * @param
	 * @return
	 */
	public String getUpdateImage(String proNO) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists()) 
		file.mkdir(); 
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists()) 
		file2.mkdir(); 
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+"updateimagetype");
		if (!file3.exists()) 
		file3.mkdir(); 
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +"updateimagetype"+"/"+proNO + ".pa"; 
		}
	
	
	/**
	 * 获取查勘提交信息路径
	 * 
	 * @param
	 * @return
	 */
	public static String carateSubmitSurvMainInfoPath(String proNO) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+"submitsurvmaininfo");
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +"submitsurvmaininfo"+"/"+proNO + ".pa";
	}


	/**
	 * 获取记事本路径
	 *
	 * @param
	 * @return
	 */
	public static String carateNotesPath(String time) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+"notes");
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +"notes"+"/"+time + ".pa";
	}

	/**
	 * 获取抄单路径
	 *
	 * @param
	 * @return
	 */
	public String carateCopyMainInfoPath(String proNO) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+"copymaininfo");
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +"copymaininfo"+"/"+proNO + ".pa";
	}

	/**
	 * 查勘已提交路径
	 *
	 * @param
	 * @return
	 */
	public String carateSurveyAlreadySubmitPath(String proNO) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+"surveyalreadysubmit");
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +"surveyalreadysubmit"+"/"+proNO + ".pa";
	}

	/**
	 *  根据任务号 获取车辆定损提交信息路径
	 *
	 * @param
	 * @return
	 */
	public static String carateSubmitCarLossMainInfoPath(String taskNo) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+"submitcarlossmaininfo");
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +"submitcarlossmaininfo"+"/"+taskNo + ".pa";
	}

	/**
	 *  根据任务号 获取财物定损提交信息路径
	 *
	 * @param
	 * @return
	 */
	public static String carateSubmitPropLossMainInfoPath(String taskNo) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+"/"+"submitpropertymaininfo");
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" +"submitpropertymaininfo"+"/"+taskNo + ".pa";
	}

	private String carateTaskMd(String str) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/" + str + ".pa";
	}

	/**
	 *  根据日期 获取日志路径
	 *
	 * @param
	 * @return
	 */
	public static String carateLogPath(String log) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE+ "/" +LOG);
		if (!file3.exists())
			file3.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE+ "/" + LOG+"/"+log + ".log";
	}

	/**
	 * 获得报案信息
	 * 
	 * @return
	 */
	public String getReportInto(String taskID) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = null;
		if (!(file = new File(sdcard + "/" + SYS_TEMMD + "/" + PROPERTY
				+ "/report_info" + "/" + taskID + ".pa")).exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}

	/**
	 * Get PDF file Intent
	 */
	public Intent getPdfFileIntent(String path) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.addCategory(Intent.CATEGORY_DEFAULT);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(path));
		i.setDataAndType(uri, "application/pdf");
		return i;
	}

	// android获取一个用于打开Word文件的intent
	public Intent getWordFileIntent(String path) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(path));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	/**
	 * 根据路径打开文件
	 * @param filePath
	 * @return
	 */
	public Intent openFile(String filePath) {

		File file = new File(filePath);

		if ((file == null) || !file.exists() || file.isDirectory())
			return null;

		/* 取得扩展名 */
		String end = file
				.getName()
				.substring(file.getName().lastIndexOf(".") + 1,
						file.getName().length()).toLowerCase();
		/* 依扩展名的类型决定MimeType */
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			return getAudioFileIntent(filePath);
		} else if (end.equals("3gp") || end.equals("mp4")) {
			return getAudioFileIntent(filePath);
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			return getImageFileIntent(filePath);
		} else if (end.equals("apk")) {
			return getApkFileIntent(filePath);
		} else if (end.equals("ppt")) {
			return getPptFileIntent(filePath);
		} else if (end.equals("xls")) {
			return getExcelFileIntent(filePath);
		} else if (end.equals("doc")||end.equals("docx")) {
			return getWordFileIntent(filePath);
		} else if (end.equals("pdf")) {
			return getPdfFileIntent(filePath);
		} else if (end.equals("chm")) {
			return getChmFileIntent(filePath);
		} else if (end.equals("txt")) {
			return getTextFileIntent(filePath, false);
		} else {
			return getAllIntent(filePath);
		}
	}

	// Android获取一个用于打开APK文件的intent
	public Intent getAllIntent(String param) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "*/*");
		return intent;
	}

	// Android获取一个用于打开APK文件的intent
	public Intent getApkFileIntent(String param) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		return intent;
	}

	// Android获取一个用于打开VIDEO文件的intent
	public Intent getVideoFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "video/*");
		return intent;
	}

	// Android获取一个用于打开AUDIO文件的intent
	public Intent getAudioFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "audio/*");
		return intent;
	}

	// Android获取一个用于打开Html文件的intent
	public Intent getHtmlFileIntent(String param) {

		Uri uri = Uri.parse(param).buildUpon()
				.encodedAuthority("com.android.htmlfileprovider")
				.scheme("content").encodedPath(param).build();
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setDataAndType(uri, "text/html");
		return intent;
	}

	// Android获取一个用于打开图片文件的intent
	public Intent getImageFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "image/*");
		return intent;
	}

	// Android获取一个用于打开PPT文件的intent
	public Intent getPptFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
		return intent;
	}

	// Android获取一个用于打开Excel文件的intent
	public Intent getExcelFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-excel");
		return intent;
	}

	// Android获取一个用于打开CHM文件的intent
	public Intent getChmFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/x-chm");
		return intent;
	}

	// Android获取一个用于打开文本文件的intent
	public Intent getTextFileIntent(String param, boolean paramBoolean) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (paramBoolean) {
			Uri uri1 = Uri.parse(param);
			intent.setDataAndType(uri1, "text/plain");
		} else {
			Uri uri2 = Uri.fromFile(new File(param));
			intent.setDataAndType(uri2, "text/plain");
		}
		return intent;
	}

	/**
	 * 保存文件
	 * 
	 * @param jsonData
	 *            (保存的数据)
	 */
	public static boolean saveExceptionFile(String jsonData) {

		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" +CACHE);
		if (!file2.exists())
			file2.mkdir();
		File file3 = new File(sdcard + "/" + SYS_TEMMD + "/" +CACHE+"/" +"exception");
		if (!file3.exists())
			file3.mkdir();
		String filePath=sdcard + "/" + SYS_TEMMD + "/" +CACHE+"/" +"exception"+"/"+ DateUtil.getLongData()+".pa";
		try {
			java.io.FileOutputStream out = new java.io.FileOutputStream(
					new File(filePath));
			out.write(jsonData.getBytes());
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 保存图片上传信息
	 *
	 * @param jsonData
	 *            (保存的数据)
	 */
	public static boolean saveFile(String jsonData, String filePath) {

		try {
			java.io.FileOutputStream out = new java.io.FileOutputStream(
					new File(filePath));
			out.write(jsonData.getBytes());
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存文件
	 *
	 * @param jsonData
	 *            (保存的数据)
	 * @param filePath
	 *            (保存的路径)
	 */
	public static boolean saveFileByte(byte[] jsonData, String filePath) {

		try {
			java.io.FileOutputStream out = new java.io.FileOutputStream(
					new File(filePath));
			out.write(jsonData);
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 根据路径获取文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFile(String filePath) {

		FileInputStream is = null;
		if (filePath != null && !"".equals(filePath)) {
			try {
				is = new FileInputStream(new File(filePath));
				return readFile(is);
			} catch (IOException e) {
				return null;
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}

		return null;
	}

	private static String readFile(FileInputStream inputStream) {
		String readedStr = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				readedStr += tmp;
			}

		} catch (UnsupportedEncodingException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (br != null)
					br.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return readedStr;
	}
	
	
//	/**
//	 * 根据报案号删除任务
//	 * @param reportNo
//	 */
//	public void deleteTask(String reportNo){
//
//		try{
//		String	fileName = getTaskInfo();
//		if(new File(fileName).exists()){
//
//			List<TaskInfoDetailVO> taskInfoDetailVO = new ArrayList<TaskInfoDetailVO>();
//			String resultJson = NFileUtils.getFile(fileName);
//			ResponseNewTaskRequestVO result = JSON.parseObject(resultJson, ResponseNewTaskRequestVO.class);
//			if (result.getResult().getCode().equals("1")) {
//				taskInfoDetailVO = result.getData().getTaskInfoDetailList();
//
//				if(null!=taskInfoDetailVO && taskInfoDetailVO.size()>0){
//
//					for(int i=0;i<taskInfoDetailVO.size();i++){
//						if(reportNo.equals(taskInfoDetailVO.get(i).getReportNo())){
//							result.getData().getTaskInfoDetailList().remove(i);
//							break;
//						}
//					}
//
//				}
//			}
//			NFileUtils.saveFile(JSON.toJSONString(result), fileName);
//		}
//
//
//		}catch(Exception e){
//			e.printStackTrace();
//
//		}
//	}

	/**
	 *  删除文件路径下所有文件
	 * @param
	 * @return
	 */
	public static void deletePath(String path){
		try {
			File fileTemp = new File(path);
			DeleteFile(fileTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取缓存路径路径
	 *
	 * @param
	 * @return
	 */
	public static String getCachePath() {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File file = new File(sdcard + "/" + SYS_TEMMD);
		if (!file.exists())
			file.mkdir();
		File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
		if (!file2.exists())
			file2.mkdir();
		return sdcard + "/" + SYS_TEMMD + "/" + CACHE ;
	}

	/**
	 * 递归删除文件和文件夹
	 *
	 * @param file 要删除的目录
	 */
	public static void DeleteFile(File file) {
		if (file.exists() == false) {
			return;
		} else {
			if (file.isFile()) {
				file.delete();
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					file.delete();
					return;
				}
				for (File f : childFile) {
					DeleteFile(f);
				}
				file.delete();
			}
		}
	}

	/**
	 * bitmap保存到本地
	 * @param bm
	 */
	public static void saveBitmap(Bitmap bm) {
		String sdcard = Environment.getExternalStorageDirectory().toString();
		File f = new File(sdcard, "qrPay.png");
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 下载到本地
	 *
	 * @param body 内容
	 * @return 成功或者失败
	 */
	public static boolean writeResponseBodyToDisk(ResponseBody body) {
		try {
			//判断文件夹是否存在
			File files = new File(getCacheMD());//跟目录一个文件夹
			if (!files.exists()) {
				//不存在就创建出来
				files.mkdirs();
			}
			//创建一个文件
			File futureStudioIconFile = new File(getCacheMD() + "xinshangyu.apk");
			//初始化输入流
			InputStream inputStream = null;
			//初始化输出流
			OutputStream outputStream = null;
			try {
				//设置每次读写的字节
				byte[] fileReader = new byte[4096];
				long fileSize = body.contentLength();
				long fileSizeDownloaded = 0;
				//请求返回的字节流
				inputStream = body.byteStream();
				//创建输出流
				outputStream = new FileOutputStream(futureStudioIconFile);
				//进行读取操作
				while (true) {
					int read = inputStream.read(fileReader);
					if (read == -1) {
						break;
					}
					//进行写入操作
					outputStream.write(fileReader, 0, read);
					fileSizeDownloaded += read;
				}

				//刷新
				outputStream.flush();
				return true;
			} catch (IOException e) {
				return false;
			} finally {
				if (inputStream != null) {
					//关闭输入流
					inputStream.close();
				}
				if (outputStream != null) {
					//关闭输出流
					outputStream.close();
				}
			}
		} catch (IOException e) {
			return false;
		}
	}

	public static void hex(String hexStr) {
		String javaStr= new BigInteger(hexStr, 16).intValue() + 1 + "";
		byte [] bytes = hexStringToByte(javaStr);
		byte temp;
		for(int i=0;i<bytes.length;i++){
			temp = bytes[i];
			bytes[i] = (byte) (~temp);
		}
		String bths = bytesToHexString(bytes);
		System.out.println(bths.toUpperCase());
	}

	/**
      * 将数字字符串转化为二进制byte数组
      * @param hex
      * @return
      */
		public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

		private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}
	/**
      * 将二进制数组转化为16进制字符串
      * @param src
      * @return
      */
	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			//stringBuilder.append(i + ":");//序号 2个数字为1组
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}


}
