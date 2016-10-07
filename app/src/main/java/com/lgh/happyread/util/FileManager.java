package com.lgh.happyread.util;


public class FileManager {

	public static String getCacheFileSavePath() {
		if (CommonUtil.hasSDCard()) {
			return CommonUtil.getRootFilePath() + "com.lgh.happyread/cache/";
		} else {
			return CommonUtil.getRootFilePath() + "com.lgh.happyread/cache/";
		}
	}
	
	public static String getDownloadFileSavePath() {
		if (CommonUtil.hasSDCard()) {
			return CommonUtil.getRootFilePath() + "com.lgh.happyread/files/";
		} else {
			return CommonUtil.getRootFilePath() + "com.lgh.happyread/files/";
		}
	}
}
