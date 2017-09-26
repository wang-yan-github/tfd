package com.system.tool;

import java.awt.Insets;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStreamReader;  
import java.net.URL;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.zefer.pd4ml.PD4ML;  


public class Html2Pdf {

	public static void main(String[] args) {  
		Html2Pdf pdf = new Html2Pdf();  
	       pdf.processFile("d://123/", "tfd.pdf", "https://www.baidu.com/", "index.php");  
	   }  
	     
	   /** 
	    *  
	    * @param folder 生成pdf后放在哪个目录 
	    * @param filename pdf的名称 
	    * @param baseurl 要生成pdf的url 
	    * @param jspString 要把哪个生成pdf 
	    * @return 
	    */  
	   public boolean processFile(String folder, String filename,String baseurl,String jspString){  
	       boolean res = false;  
	       InputStreamReader isr = null;  
	       try{  
	           File f = new File(folder);  
	           if (f.isDirectory()) {  
	               f.mkdir();  
	           }  
	           String fullfilename = folder + filename;  
	             
	           HttpClient client = new DefaultHttpClient();  
	           HttpGet h_request = new HttpGet(baseurl + jspString);  
	           HttpResponse h_response = client.execute(h_request);  
	           isr = new InputStreamReader(h_response.getEntity().getContent(), "UTF-8");  
	 
	           Html2Pdf converter = new Html2Pdf();  
	           //String localpath = getServletContext().getInitParameter("pdfDir") + admin.getUserid() + "/" + lessonid + ".pdf";  
	           //String downloadUrl = getServletContext().getInitParameter("pdfUrl") + admin.getUserid() + "/" + lessonid + ".pdf";  
	             
	           res = converter.generatePDF(isr, fullfilename, baseurl);  
	             
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }finally{  
	            try{isr.close();isr=null;}catch(Exception e){}  
	        }  
	  
	        return res;  
	          
	    }  
	    /** 
	     * 生成pdf 
	     * @param isr 
	     * @param pdfFilename 
	     * @param baseurl 
	     * @return 
	     */  
	    public boolean generatePDF(InputStreamReader isr, String pdfFilename, String baseurl){  
	        FileOutputStream fos = null;  
	        boolean res = false;  
	  
	        try{  
	            fos = new FileOutputStream(new File(pdfFilename));  
	            PD4ML pd4ml = new PD4ML();  
	            pd4ml.setPageInsets(new Insets(10, 10, 10, 10));  
	            pd4ml.setHtmlWidth(1000);  
	            pd4ml.enableImgSplit(false);  
	            //Dimension format = PD4ML.A4;  
	            //pd4ml.setPageSize(pd4ml.changePageOrientation(format)); // landscape page orientation  
	            pd4ml.useTTF("java:fonts", true );  
	            //pd4ml.enableDebugInfo();  
	              
	            // footer if needed  
	            //PD4PageMark footer = new PD4PageMark();   
	            //footer.setPageNumberTemplate("page $[page] of $[total]");     
	            //footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);     
	            //footer.setInitialPageNumber(1);     
	            //footer.setPagesToSkip(1);     
	            //footer.setFontSize(10);     
	            //footer.setAreaHeight(18);        
	            //pd4ml.setPageFooter(footer);   
	              
	              
	            pd4ml.render(isr, fos, new URL(baseurl));  
	            res = true;  
	              
	            //res = baos.toByteArray();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }finally{  
	            try{fos.close();fos=null;}catch(Exception ee){}  
	        }  
	  
	        return res;  
	    } 

}
