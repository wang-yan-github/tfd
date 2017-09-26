package com.system.tool;

import java.util.Arrays;
import java.util.regex.Pattern;

public class RegexUtil {
	private String regex;
	
	private void setRegex(String regex){
		this.regex=regex;
	}
	public String getRegex() {
		return regex;
	}
	private RegexUtil(){}
	public static RegexUtil getInstance(String regex){
		RegexUtil regexUtil=new RegexUtil();
		regexUtil.setRegex(regex);
		return regexUtil;
	}
	
	/**
	 * 正则表达式：座机电话、传真号码
	 */
	public final static String REGEX_TELEPHONE="((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
	/**
	 * 正则表达式：电子邮箱
	 */
	public final static String REGEX_EMAIL="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
	/**
	 * 正则表达式：手机号码
	 */
	public final static String REGEX_MOBILE_PHONE="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
	/**
	 * 正则表达式：大于等于0的整数
	 */
	public final static String REGEX_INT_0_="^(0|([1-9]\\d*))$";
	/**
	 * 模拟javascript regex.test(str) 方法
	 * @param str
	 * @param regexStr
	 * @return
	 */
	public boolean test(String str){
		return Pattern.compile(this.getRegex()).matcher(str).find();
	}
	
	public static void main(String[] args) {
		for(String str:Arrays.asList("-1","0","1","10","101","111","010")){
			
			boolean aa=RegexUtil.getInstance(RegexUtil.REGEX_INT_0_).test(str);
			System.out.println(str+":"+aa);
		}
	}
}
