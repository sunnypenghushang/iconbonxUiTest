package systemutils;

import java.io.File;

import baseutils.Constant;
import baseutils.ToolFunctions;

public class FilePath {
	
	/**
	 * 获取事件日志路径
	 * @param name
	 * @return
	 */
	
	public static String getEventlogFilePath(String name)
	{
		String RESULTS_BASE_PATH = Constant.EVENTLOG+name;
		File file =new File(RESULTS_BASE_PATH);
		if (!new File(RESULTS_BASE_PATH).exists())
		{
			  new File(RESULTS_BASE_PATH).mkdirs();
		}
		String resultsPath = file.getAbsolutePath();
		return  resultsPath;
		
	}
	
	/**
	 * 获取截图文件路径
	 * @param name
	 * @return
	 */
	public static String getScreencapFilePath()
	{
		String RESULTS_BASE_PATH = Constant.SCREENCAP;
		File file =new File(RESULTS_BASE_PATH);
		if (!file.exists())
			  file.mkdir();
		
		String resultsPath = file.getAbsolutePath();
		return  resultsPath;
		
	}
	
	/**
	 * 获取测试数据路径
	 */
	public static String getTestDatapFilePath()
	{
		String RESULTS_BASE_PATH = Constant.TESTDATA;
		File file =new File(RESULTS_BASE_PATH);
		if (!file.exists())
			  file.mkdir();
		
		String resultsPath = file.getAbsolutePath();
		return  resultsPath;
		
	}
	
	
	

	
	
	

	/**
	 * 返回日志存在的文件路径
	 * @return
	 */
	public static String getlogFilePath(String modelfile){
		String RESULTS_BASE_PATH = Constant.LOGGERFILEPATH+modelfile;
		File file =new File(RESULTS_BASE_PATH);
		if (!new File(RESULTS_BASE_PATH).exists())
		{
			  new File(RESULTS_BASE_PATH).mkdirs();
		}
		String resultsPath = file.getAbsolutePath();
		return  resultsPath;
	}

	
	
	
	
	/**
	 * 返回日志存在的文件路径
	 * @return
	 */
	public static String getlogFilePath(){
		String RESULTS_BASE_PATH = Constant.LOGGERFILEPATH;
		File file =new File(RESULTS_BASE_PATH);
		if (!new File(RESULTS_BASE_PATH).exists())
		{
			  new File(RESULTS_BASE_PATH).mkdirs();
		}
		String resultsPath = file.getAbsolutePath();
		return  resultsPath;
	}
	
	/**
	 * 返回打印日志的text 文件名
	 * @param resultsPath
	 * @param caseName
	 * @return
	 */
	public static String getInfolgPath(String resultsPath,String caseName ){
		String resultAppiumLogFileName =null;
		try {
			resultAppiumLogFileName = resultsPath + File.separator
					+ caseName + "_" +  ToolFunctions.getCurrentTime() + "_"
					+ "_appiumLog.log";
		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultAppiumLogFileName; 
	}
	
	
	/**
	 * 返回错误截图的路径
	 * @param resultsPath
	 * @param caseName
	 * @return
	 */
	public static File getPicturePath(String caseName,String dateStr ,String captureName){
		String RESULTS_BASE_PATH  = "Log/ScreenCap/"+caseName+"/" +dateStr+ "_" + captureName;
		File file =new File(RESULTS_BASE_PATH);
		return file; 
	}
	
	

	

}
