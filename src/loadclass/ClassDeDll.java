package loadclass;
public class ClassDeDll {
	static  
    {   
        System.loadLibrary("declassjar");
    } 
	 public native static byte[] Classde(String msg); 
}
