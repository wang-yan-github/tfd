package sysmanage.regist.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import sysmanage.regist.data.Reg;

import com.system.global.DateConst;



public class RegLogic {
	String[] $=new String[]{"###","|||","!!!","~~~","@@@","$$$","%%%","^^^","***","(((",")))","+++","===","&&&"};
	int usedRandom=-1;
	
	public byte[] encodeReg(Reg reg) throws Exception{
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			@Override
			public Object processObjectValue(String name, Object value, JsonConfig arg2) {
				// TODO Auto-generated method stub
				if(value!=null){
					return new SimpleDateFormat(DateConst.VALUE_SHORT_DATE).format(value);
				}
				return null;
			}
			@Override
			public Object processArrayValue(Object value, JsonConfig arg1) {
				// TODO Auto-generated method stub
				return value;
			}
		});
		String regFileContent=JSONObject.fromObject(reg, jsonConfig).toString();
		
		byte[] regBytes=regFileContent.getBytes("utf-8");
		byte[] encodeBytes=new byte[regBytes.length];
		
		//#记录需要转码的字节索引
		List<Integer> encodeIndex=new ArrayList<Integer>();
		for (int i = 0; i < regBytes.length; i++) {
			if (regBytes[i]+2>Byte.MAX_VALUE) {
				encodeBytes[i]=regBytes[i];
				continue;
			}else{
				encodeIndex.add(i);
				//加密
				encodeBytes[i]=(byte) (regBytes[i]+2);
			}
		}
		
		
		int k=0;
		List<Byte> encode2Bytes=new ArrayList<Byte>();
		for (int i = 0; i < encodeBytes.length; i++) {
			encode2Bytes.add(encodeBytes[i]);
			if (k<encodeIndex.size()) {
				int random=getRandom();
				String $$=$[random];
				byte[] indexStrBytes=new String($$+Integer.toBinaryString(encodeIndex.get(k)+random)+$$).getBytes("utf-8");
				for (int j = 0; j < indexStrBytes.length; j++) {
					encode2Bytes.add(encode2Bytes.size(), indexStrBytes[j]);
				}
				k++;
			}
		}
		if (k<encodeIndex.size()) {
			for (; k < encodeIndex.size(); k++) {
				int random=getRandom();
				String $$=$[random];
				byte[] indexStrBytes=new String($$+Integer.toBinaryString(encodeIndex.get(k)+random)+$$).getBytes("utf-8");
				for (int j = 0; j < indexStrBytes.length; j++) {
					encode2Bytes.add(encode2Bytes.size(), indexStrBytes[j]);
				}
			}
		}
		
		byte[] encode2BytesNew=new byte[encode2Bytes.size()];
		for (int i = 0; i < encode2BytesNew.length; i++) {
			encode2BytesNew[i]=encode2Bytes.get(i);
		}
		
		return encode2BytesNew;
	}
	
	public int getRandom(){
		int random=(int)(Math.random()*$.length);
		if (random==usedRandom) {
			return getRandom();
		}
		usedRandom=random;
		return random;
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(Integer.toBinaryString(i));
		}
	}
}
