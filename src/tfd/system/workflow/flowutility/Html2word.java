package tfd.system.workflow.flowutility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.system.tool.RequestUtil;

public class Html2word {

private String path = "temp.doc";


public int test1() {
	String domain = "http://127.0.0.1:8080/tfd/system/workflow/dowork/bpmtest/print/index.jsp?runId=D578FE83-503D-A7A6-C96D-33F240407EED";
	String fileByte = RequestUtil.sendPost(domain,null);
try {
byte b[] = fileByte.getBytes("UTF-8");
System.out.println(fileByte.toString());
System.out.println(b.length);
ByteArrayInputStream bais = new ByteArrayInputStream(b);
POIFSFileSystem fs = new POIFSFileSystem();
DirectoryEntry directory = fs.getRoot();
directory.createDocument("WordDocument", bais);
FileOutputStream ostream = new FileOutputStream("d:\\"+path);
ostream.write(b);
fs.writeFilesystem(ostream);
bais.close();
ostream.close();
} catch (IOException e) {
e.printStackTrace();
}
return 1;
}

public static void main(String[] args) {

Html2word poi = new Html2word();
System.out.println(poi.test1());
}
} 