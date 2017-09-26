package com.system.tool;

import java.util.Random;


public class RandomUtil {
	public static String getRandomNum(){
		int min = 100000;
		int max = 999999;

		Random rand= new Random();

		int tmp = Math.abs(rand.nextInt());

		String returnData = tmp % (max - min + 1) + min+"";
		return returnData;

	}
}
