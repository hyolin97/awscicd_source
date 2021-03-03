package kr.co.mz.config;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	private static final Logger logger = LoggerFactory.getLogger(Constants.class);
	
	public enum UPLOADS{ROOT, UPLOAD_ROOT};
	
	private static  String WEB_ROOT_PATH = "";
	private static  String UPLOAD_ROOT_PATH = "";
	
	private static final String UPLOAD_ROOT_URL = "/Upload/";

	public static void setConstants(String WebRootPath)
	{
	logger.debug("-----Set Constants...-----");
	Constants.WEB_ROOT_PATH = WebRootPath;
	logger.debug("-Constants.WEB_ROOT_PATH={}-",Constants.WEB_ROOT_PATH);
    	
    	Constants.UPLOAD_ROOT_PATH = WebRootPath+Constants.UPLOAD_ROOT_URL;
    	mkdir(Constants.UPLOAD_ROOT_PATH);
    	logger.debug("-Constants.UPLOAD_ROOT_PATH={}-",Constants.UPLOAD_ROOT_PATH);
	}
	
	public static String getURL(UPLOADS para) {
	     String rtnURL = "";
    	     switch(para)
    	     {
    		case ROOT:
			rtnURL = "/";
			break;
			
		case UPLOAD_ROOT:
			rtnURL = Constants.UPLOAD_ROOT_URL;
			break;
		default:
			rtnURL = "/";
			break;
	     }
	     return rtnURL;
	}

	public static String getPATH(UPLOADS para) {
	      String rtnPATH = "";
	      switch(para) 
	      {
		case ROOT:
			rtnPATH = Constants.WEB_ROOT_PATH;
			break;
			
		case UPLOAD_ROOT:
			rtnPATH = Constants.UPLOAD_ROOT_PATH;
			break;
		default:
			rtnPATH = Constants.WEB_ROOT_PATH;
			break;
	      }
	      return rtnPATH;
	}
	
	private static void mkdir(String path) {
    	File upDir = new File(path);
    	if(!upDir.exists())//해당 디렉토리의 존재여부를 확인
    	{
    		upDir.mkdirs();//없다면 생성 
    	}
	}
}
