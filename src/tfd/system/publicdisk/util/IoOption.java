package tfd.system.publicdisk.util;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;


import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.filetool.UpFileTool;
import com.system.tool.SysFileTool;

public class IoOption {
	private boolean flag;
	private File file;
	/** 
	 *  根据路径删除指定的目录或文件，无论存在与否 
	 *@param sPath  要删除的目录或文件 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public boolean DeleteFolder(String sPath) {  
	    flag = false;  
	    file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}  
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String sPath) {  
	    flag = false;  
	    file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	} 
	/**
	 * 新建文件夹
	 * Author:Yzz
	 * Time:2015-5-29
	 * @param path
	 * @throws Exception
	 */
	public int createFolder(String path)throws Exception{
		if(path.substring(path.length()-1, path.length()).equals("/")){
			path = path+"新建文件夹";
		}
		File file =new File(path);    
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory()){       
		    file .mkdir();
		    return 1;
		} else{
			int i = 2;
			String newPath = path + "(" + i + ")";
			file =new File(newPath);
			while(!(!file .exists()  && !file .isDirectory())){
				i++;
				newPath = path + "(" + i + ")";
				file =new File(newPath);
			}
			file .mkdir(); 
			return 1;
		}  
	}
	
	//重命名  
	 public int changeFolderName(String path,String folderName,String newName){  
		 if(!folderName.equals(newName)){//新的文件名和以前文件名不同时,才有必要进行重命名 
	            File oldfile=new File(path+"/"+folderName); 
	            File newfile=new File(path+"/"+newName); 
	            if(!oldfile.exists()){
	                return 0;//重命名文件不存在
	            }
	            if  (!newfile .exists()  && !newfile .isDirectory()){       
	            	oldfile.renameTo(newfile);
	    		    return 1;
	    		} else{
		            int i = 2;
					String newPath = path + "(" + i + ")";
					newfile =new File(newPath);
					while(!(!newfile .exists()  && !newfile .isDirectory())){
						i++;
						newPath = path + "(" + i + ")";
						newfile =new File(newPath);
					}
		            oldfile.renameTo(newfile); 
		            return 1;
	    		}
	        }else{
	            return 0;
	        }
	 }
	 /**
	  * 附件上传
	  * Author:Yzz
	  * Time:2015-6-1
	  * @param conn
	  * @param path
	  * @param attachId
	  * @throws Exception
	  */
	 public int uploadfiles(String path,String attachId,String type)throws Exception{
		UpFileTool upFileTool = new UpFileTool();
		String srcFilePath = upFileTool.getAttachPath(attachId);
		//System.out.println(srcFilePath);
		String extName = srcFilePath.substring(srcFilePath.lastIndexOf("."),srcFilePath.length());
		String fileName = upFileTool.getAttachName(attachId);
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
		File file = new File(path+"/"+fileName+extName);
		if(!file .exists()){
			SysFileTool.copyFile(srcFilePath, path+"/"+fileName+extName);
			deleteFile(srcFilePath);
			delAttach(attachId);
			return 1;
		}else{
			int i = 2;
			String newfileName =  fileName + "(" + i + ")";
			String newPath = path +"/"+ newfileName + extName;
			file =new File(newPath);
			while(file .exists()){
				i++;
				newfileName =  fileName + "(" + i + ")";
				newPath = path +"/"+ newfileName + extName;
				file =new File(newPath);
			}
			fileName = newfileName;
			SysFileTool.copyFile(srcFilePath, path+"/"+fileName + extName);
			deleteFile(srcFilePath);
			delAttach(attachId);
			return 1;
		}
	 }
	 public void delAttach(String attachId)throws Exception{
		Connection dbConn = null;
		try{
			dbConn = DbPoolConnection.getInstance().getConnection();
			String queryStr="DELETE FROM ATTACHMENT WHERE ATTACHMENT_ID=?";
			PreparedStatement ps = dbConn.prepareStatement(queryStr);
			ps.setString(1, attachId);
			ps.executeUpdate();
			ps.close();
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
	 }
	 public int copyFile(String srcFilePath,String path)throws Exception{
		String extName = srcFilePath.substring(srcFilePath.lastIndexOf("."),srcFilePath.length());
		String fileName = srcFilePath.substring(srcFilePath.lastIndexOf("\\"),srcFilePath.lastIndexOf("."));
		File file = new File(path+"/"+fileName+extName);
		if(!file .exists()){
			SysFileTool.copyFile(srcFilePath, path+"/"+fileName+extName);
			return 1;
		}else{
			int i = 2;
			String newfileName =  fileName + "(" + i + ")";
			String newPath = path +"/"+ newfileName + extName;
			file =new File(newPath);
			while(file .exists()){
				i++;
				newfileName =  fileName + "(" + i + ")";
				newPath = path +"/"+ newfileName + extName;
				file =new File(newPath);
			}
			fileName = newfileName;
			SysFileTool.copyFile(srcFilePath, path+"/"+fileName + extName);
			return 1;
		}
	 }
	 
	 public int cutFile(String srcFilePath,String path)throws Exception{
			String extName = srcFilePath.substring(srcFilePath.lastIndexOf("."),srcFilePath.length());
			String fileName = srcFilePath.substring(srcFilePath.lastIndexOf("\\"),srcFilePath.lastIndexOf("."));
			File file = new File(path+"/"+fileName+extName);
			if(!file .exists()){
				SysFileTool.copyFile(srcFilePath, path+"/"+fileName+extName);
				deleteFile(srcFilePath);
				return 1;
			}else{
				int i = 2;
				String newfileName =  fileName + "(" + i + ")";
				String newPath = path +"/"+ newfileName + extName;
				file =new File(newPath);
				while(file .exists()){
					i++;
					newfileName =  fileName + "(" + i + ")";
					newPath = path +"/"+ newfileName + extName;
					file =new File(newPath);
				}
				fileName = newfileName;
				SysFileTool.copyFile(srcFilePath, path+"/"+fileName + extName);
				deleteFile(srcFilePath);
				return 1;
			}
		 }
}
