package com.system.tool;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import com.system.global.SysConst;
import com.system.global.SysProps;
import com.system.global.SysPropKey;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class SysAuthenticator {
	/**
	   * 执行MD5算法生成密码的指纹

	   * @param saltLength           
	   * @param passWord
	   * @return
	   * @throws ErrorMessage
	   */
	  public static byte[] encrypt(int saltLength,
	      byte[] passWord)
	      throws ErrorMessage, NoSuchAlgorithmException {
	    byte[] rtArray = null;
	    byte[] salt = new byte[saltLength];
	    byte[] digest = null;
	    
	    if (passWord == null) {
	      throw new ErrorMessage("COMMON_ERROR_NULL_PASSWORD");
	    }
	    
	    SecureRandom random = new SecureRandom();
	    random.nextBytes(salt);
	    MessageDigest md = null;
	    try {
	      md = MessageDigest.getInstance("MD5");
	    }catch(NoSuchAlgorithmException ex) {
	      throw ex;
	    }
	    md.update(salt);
	    md.update(passWord);
	    digest = md.digest();

	    rtArray = new byte[salt.length + digest.length];
	    System.arraycopy(salt, 0, rtArray, 0, salt.length);
	    System.arraycopy(digest, 0, rtArray, salt.length, digest.length);
	    return rtArray;
	  }
	  
	  /**
	   * 判断用户密码是否合法
	   * @param saltLength
	   * @param inputPass
	   * @param savedPass
	   * @return
	   * @throws ErrorMessage
	   * @throws NoSuchAlgorithmException
	   */
	  public static boolean isValidPassword(
	      String inputPass,
	      String savedPass)
	    throws ErrorMessage,
	      NoSuchAlgorithmException, IOException {
	    
	    return isValidPassword(SysProps.getInt(SysPropKey.SALT_LENGTH),
	        inputPass.getBytes(SysConst.DEFAULT_CODE),
	        new BASE64Decoder().decodeBuffer(savedPass));
	  }
	  
	  /**
	   * 判断用户密码是否合法
	   * @param saltLength
	   * @param inputPass
	   * @param savedPass
	   * @return
	   * @throws ErrorMessage
	   * @throws NoSuchAlgorithmException
	   */
	  public static boolean isValidPassword(
	      byte[] inputPass,
	      byte[] savedPass)
	    throws ErrorMessage, NoSuchAlgorithmException {
	    
	    return isValidPassword(SysProps.getInt(SysPropKey.SALT_LENGTH), inputPass, savedPass);
	  }
	  
	  /**
	   * 判断用户密码是否合法
	   * @param saltLength
	   * @param inputPass
	   * @param savedPass
	   * @return
	   * @throws ErrorMessage
	   * @throws NoSuchAlgorithmException
	   */
	  public static boolean isValidPassword(int saltLength,
	      byte[] inputPass,
	      byte[] savedPass)
	    throws ErrorMessage, NoSuchAlgorithmException {
	    
	    if (savedPass == null || savedPass.length < saltLength) {
	      throw new ErrorMessage("COMMON_ERROR_SAVED_PASS_INVALID");
	    }
	    
	    byte[] salt = new byte[saltLength];
	    System.arraycopy(savedPass, 0, salt, 0, saltLength);
	    MessageDigest md = null;
	    try {
	      md = MessageDigest.getInstance("MD5");
	    }catch(NoSuchAlgorithmException ex) {
	      throw ex;
	    }
	    md.update(salt);
	    md.update(inputPass);
	    byte[] inputDigest = md.digest();
	    byte[] savedPassDigest = new byte[savedPass.length - saltLength];
	    System.arraycopy(savedPass, saltLength,
	        savedPassDigest, 0, savedPass.length - saltLength);
	    
	    if (Arrays.equals(inputDigest, savedPassDigest)) {
	      return true;
	    }
	    return false;
	  }
	  
	  /**
	   * 生成摘要，然后Base64编码
	   * @param saltLength
	   * @param passWord
	   * @return
	   */
	  public static String encryptBase64(
	      int saltLength,
	      byte[] passWord) throws  Exception {
	    
	    return new BASE64Encoder().encode(encrypt(saltLength, passWord));
	  }
	  
	  /**
	   * 生成摘要，然后Base64编码
	   * @param saltLength
	   * @param passWord
	   * @return
	   */
	  public static String encryptBase64(
	      int saltLength,
	      String passWord) throws  Exception {
	    
	    return new BASE64Encoder().encode(encrypt(saltLength, passWord.getBytes(SysConst.DEFAULT_CODE)));
	  }
	  
	  /**
	   * 判断两密码是否匹配

	   * @param saltLength
	   * @param inputPassStr
	   * @param savedPassStr
	   * @return
	   * @throws Exception
	   */
	  public static boolean isValidPasswordBase64(int saltLength,
	      String inputPassStr,
	      String savedPassStr) throws  Exception {
	    
	    byte[] inputPass = inputPassStr.getBytes(SysConst.DEFAULT_CODE); 
	    byte[] savedPass = new BASE64Decoder().decodeBuffer(savedPassStr);
	    
	    return isValidPassword(saltLength, inputPass, savedPass);
	  }
	  
	  /**
	   * 判断两密码是否匹配

	   * @param saltLength
	   * @param inputPassStr
	   * @param savedPassStr
	   * @return
	   * @throws Exception
	   */
	  public static boolean isValidRegist(
	      int saltLength,
	      String inputPassStr,
	      String savedPassStr) throws  Exception {
	    
	    if (saltLength < 2) {
	      saltLength = 10;
	    }

	    byte[] inputPass = inputPassStr.getBytes(SysConst.DEFAULT_CODE); 
	    byte[] savedPass = new BASE64Decoder().decodeBuffer(savedPassStr);
	    
	    return isValidPassword(saltLength - 2, inputPass, savedPass);
	  }

	  /**
	   * 判断两密码是否匹配

	   * @param inputPassStr
	   * @param savedPassStr
	   * @return
	   * @throws Exception
	   */
	  public static boolean isValidRegist(
	      String inputPassStr,
	      String savedPassStr) throws  Exception {
	    
	    return isValidRegist(SysProps.getInt(SysPropKey.SALT_LENGTH), inputPassStr, savedPassStr);
	  }
	  
	  /**
	   * 加密字符串，生成密文字节
	   * @param srcStr
	   * @return 密文字节
	   * @throws Exception
	   */
	  public static byte[] ciphEncryptBytes(String srcStr) throws  Exception {
	    Cipher cipher =SysSecurityTool.getPassWordCipher(Cipher.ENCRYPT_MODE);
	    byte[] bufBytes = srcStr.getBytes(SysConst.DEFAULT_CODE);
	    return cipher.doFinal(bufBytes);
	  }
	  
	  /**
	   * 解密字节数组
	   * @param bufBytes 密文字节
	   * @return 解密的字符串
	   * @throws Exception
	   */
	  public static String ciphDecryptBytes(byte[] bufBytes) throws  Exception {
	    Cipher cipher = SysSecurityTool.getPassWordCipher(Cipher.DECRYPT_MODE);
	    return new String(cipher.doFinal(bufBytes), SysConst.DEFAULT_CODE);
	  }
	  
	  /**
	   * 加密密码，生成密文

	   * @param pass
	   * @return
	   * @throws Exception
	   */
	  public static String ciphEncryptStr(String srcPass) throws  Exception {
	    Cipher cipher =SysSecurityTool.getPassWordCipher(Cipher.ENCRYPT_MODE);
	    byte[] passBytes = srcPass.getBytes(SysConst.DEFAULT_CODE);
	    passBytes = cipher.doFinal(passBytes);
	    return new BASE64Encoder().encode(passBytes);
	  }
	  
	  /**
	   * 解密密码
	   * @param encryptPass
	   * @return
	   * @throws Exception
	   */
	  public static String ciphDecryptStr(String encryptPass) throws  Exception {
	    Cipher cipher =SysSecurityTool.getPassWordCipher(Cipher.DECRYPT_MODE);
	    byte[] passBytes = new BASE64Decoder().decodeBuffer(encryptPass);
	    return new String(cipher.doFinal(passBytes), SysConst.DEFAULT_CODE);
	  }
}
