package kr.co.mz.config;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * 시스템 초기화 클래스이다.
 * 서버 실행시 업로드경로설정등의 초기화 작업을 처리한다.
 *
 */
public class Startup {

	/**
	 * 서버 실행시 업로드경로설정등의 초기화 작업을 처리한다.
	 */
	public void init() {
	System.out.println("#################################################");
    	System.out.println("-spring4basic web server FOR CF SYSTEM Starting -");
    	System.out.println("#################################################");
    	LoadInit();
    	System.out.println("#################################################");
    	System.out.println("-spring4basic web server FOR CF SYSTEM Started  -");
    	System.out.println("#################################################");
	}
	
	private void LoadInit() 
	{
		try {
			System.out.println("-----LoadInitData...-----");
			
			String[] wrPathArr = this.getClass().getResource("/").getPath().split("/");
			String   wrPath = org.apache.commons.lang.StringUtils.join(wrPathArr, "/", 0, wrPathArr.length-2);
	
			System.out.println("-Web Root Path = {}-"+wrPath);
			Constants.setConstants(wrPath);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("#####################################################");
			System.out.println("- spring4basic web server FOR CF SYSTEM Start Fail! -");
			System.out.println("#####################################################");
			
			ExceptionUtils.getFullStackTrace(e);
		}
	}
}
