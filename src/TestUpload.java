import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUpload {
    public static void main(String[] args) {
        String end = "\r\n";
        String twoHypens = "--";
        String boundary = "*****";
        String fileName = "";
        File uploadFile = new File("");
        try {
            URL url = new URL("");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
            ds.writeBytes("Content-Disposition:form-data;" + "name=\"file\";filename=\"" + fileName + "\""
                    + end);
            ds.writeBytes(end);
            FileInputStream fis = new FileInputStream(uploadFile);
            int buffersize = 1024;
            byte[] buffer = new byte[buffersize];
            int length = -1;
            while ((length = fis.read(buffer)) != -1) {
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHypens + boundary + twoHypens + end);
            fis.close();
            ds.flush();
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer sb = new StringBuffer();
            while ((ch = is.read()) != -1) {
                sb.append((char) ch);
            }
            System.out.println("上传成功");
            ds.close();
        } catch (Exception e) {
            System.out.println("上传失败");
        }
    }
}
