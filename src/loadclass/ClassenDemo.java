package loadclass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassenDemo {
public void DoClassEn(String path) throws IOException
{
  	String basepath = path;// 项目物理地址  
    FileInputStream in = new FileInputStream(basepath);
    try {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();  
        int ch;  
        while ((ch = in.read()) != -1) {
            buffer.write((byte)(ch)-2);
        }
	  in.close();	
	  DataOutputStream dos=new DataOutputStream(new FileOutputStream(new File(path)));  
	  dos.write(buffer.toByteArray());
	  dos.close();
    } finally {
      in.close();  
    }
}

}
