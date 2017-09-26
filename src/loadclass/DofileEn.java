package loadclass;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class DofileEn {
	public static void main(String[] args) throws IOException {
	String path="D:/project/workspace/TFDSYS/webroot/tfd/WEB-INF/classes/";
	File file= new File(path);
	fileEn(file);
	}
	public static void fileEn(File file) throws IOException
	{
		File[] filelist = file.listFiles();
		for(int i=0;filelist.length>i;i++)
		{
			if(filelist[i].isFile())
			{
				String path1=filelist[i].getAbsolutePath();
				String path2=path1.replace("D:", "e:");
				System.out.println(path2);
				FileInputStream in = new FileInputStream(path1);
				  try {
		                ByteArrayOutputStream buffer = new ByteArrayOutputStream();  
		                int ch;  
		                while ((ch = in.read()) != -1) {  
		                    buffer.write((byte)(ch)-2);  
		                }
		                buffer.write(12);
		                buffer.write(19);
		                buffer.write(17);
		                String tempath = path2.substring(0,path2.lastIndexOf("\\"));
		                File tmpfile = new File(tempath);
		                if(!tmpfile.exists())
		                {
		                	tmpfile.mkdirs();
		                	System.out.println("创建成功！");
		                }
		                DataOutputStream dos=new DataOutputStream(new FileOutputStream(new File(path2)));  
		                dos.write(buffer.toByteArray());
		                dos.close();
		            } finally {
		            	in.close();
		            }
			}else
			{
				fileEn(filelist[i]);
			}
		}
	}
	
}
