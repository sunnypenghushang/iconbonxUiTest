package baseutils;

/**
 * 文件操作类
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 文件操作类
 * @author Administrator
 *
 */
public class FileOperate {
	private String filepath;
	private File file;

	
	public FileOperate(String filepath)
	{
		this.filepath=filepath;
		file=new File(filepath);
		if(!file.exists())
		{
			if(file.isDirectory())
				file.mkdir();
			else
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
	
	
	
	/*
	 * 返回文件名称
	 */
	public String getFileName()
	{
		return file.getName();
	}
	
	/*
	 * 成员方法，删除文件
	 */
	
	public void deleteFolder()
	{
		
		  if (file.isDirectory()) 
		  {
			  File files[]= file.listFiles();
			  for(File f:files)
				  deleteFolder(f);
		  }
		  else
		  {
			  file.delete();
			  if(file.exists())
				  System.out.println("文件删除失败");
		  }
	}
	
	
	/*
	 * 类方法，删除文件夹下所有文件
	 * @param filePath 文件路径
	 */
	public  static void deleteFolder(File folder)
	{    
		  File[] files = folder.listFiles();
	        if(files!=null) { 
	            for(File f: files) {
	                if(f.isDirectory()) {
	                    deleteFolder(f);
	                } else {
	                    f.delete();
	                }
	            }
	        }
	        folder.delete();
		
	}
	

	/*
	 * 返回文件路径
	 * @return filepath文件路径名
	 */
	public  String getFilePath()
	{
		return filepath;
	
	}
	
	
	/**
	 * 拷贝文件至指定路径
	 * @param newPath
	 */
	
	public  void copyFile(String purposePath) { 
		try { 
		int bytesum = 0; 
		int byteread = 0; 
		File sourcefile = new File(filepath); 
		File purposefile=new File(purposePath);
		if (sourcefile.exists()) { //文件存在时 
		InputStream inStream = new FileInputStream( sourcefile); //读入原文件 
		FileOutputStream fs = new FileOutputStream(purposefile); 
		byte[] buffer = new byte[1444]; 
		int length; 
		while ( (byteread = inStream.read(buffer)) != -1) { 
		bytesum += byteread; //字节数 文件大小 
		fs.write(buffer, 0, byteread); 
		} 
		inStream.close(); 
		} 
		} 
		catch (Exception e) { 
		e.printStackTrace(); 
		} 
		
		} 
	
	
	
	public static void main(String[] args)
	{
		FileOperate file=new FileOperate("D:\\迅雷下载");
		
	}
}
