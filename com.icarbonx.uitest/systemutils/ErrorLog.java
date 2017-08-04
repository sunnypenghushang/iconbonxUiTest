package systemutils;

/**
 * 抓取错误日志
 * @author penghong
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import baseutils.Constant;
import baseutils.FileOperate;
import baseutils.TimeUtils;

/**
 * 抓取错误日志
 * @author test1
 *
 */
public class ErrorLog {
	static Process logProcess;
	static String logFilePath;
	static String logFileName;
	
	public static  void catchLog(String casename)
	{   


		File path = new File(logFilePath);
		
		try {
			File logFile=new File(path,TimeUtils.gettime()+casename+".txt");
			logFileName=logFile.getName();
			if(!logFile.exists())
					logFile.createNewFile();
			else
				logFile.delete();
			
			
			logProcess=Runtime.getRuntime().exec("cmd /k adb logcat -v threadtime");
			InputStreamReader ir=new InputStreamReader(logProcess.getInputStream());
			BufferedReader br=new BufferedReader(ir);
			String str;
			
			long t1= System.currentTimeMillis();
			FileWriter fw=new FileWriter(logFile,true);
			while((str=br.readLine())!=null)
			{   
				long t2 = System.currentTimeMillis();

				
				fw.write(str);
				fw.write("\n");
				fw.flush();
				if(t2-t1>1000*20)
				{   
					
					break;
				}
			}
			ir.close();
			br.close();
			fw.close();
		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	public static String getLogFilename()
	{
		return logFileName;
	}
	
	public static void clearLog()
	{
		try {
			Runtime.getRuntime().exec("cmd /k adb logcat -c");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void delLog()
	{
		File file=new File(logFileName);
		file.delete();
	}
	
	
	/**
	 * 获取报错日志路径
	 * @param name
	 * @return
	 */
	
	public static String getAnrlogFilePath()
	{
		String RESULTS_BASE_PATH = Constant.ANRLOG;
		File file =new File(RESULTS_BASE_PATH);
		if (!new File(RESULTS_BASE_PATH).exists())
		{
			  new File(RESULTS_BASE_PATH).mkdirs();
		}
		String resultsPath = file.getAbsolutePath();
		return  resultsPath;
		
		
	}


}
