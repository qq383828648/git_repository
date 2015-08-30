package cn.com.poka.gzhquery.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {

	/**
	 * An example of Autoboxing and NullPointerExcpetion
	 * 
	 * @author WINDOWS 8
	 */

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws InterruptedException {
		Map<Integer,Integer> numberAndCount = new HashMap<Integer,Integer>();
		
		List arrlist = new ArrayList();
//		numberAndCount.put("name", "2");
		int[] numbers = { 10, 5, 7, 9, 11, 13, 17, 19, 2, 3, 5, 33, 12, 5 };
		Arrays.sort(numbers);
		int count = 0;
		System.out.println(numbers.length);
		for(int i = 0;i<numbers.length;i++){
			if(!arrlist.contains(numbers[i])){
				arrlist.add(numbers[i]);
			}else{
				count++;
			}
		}
		System.out.println(count);
		System.out.println(arrlist.size());
//		for (Integer i : numbers) {
//			if (null != numberAndCount.get(i)) {
//				Integer count = numberAndCount.get(i);
//				System.out.println(i);
//				numberAndCount.put(i, count++); // NullPointerException here
				
//			}
//		}
//		Iterator it = numberAndCount.keySet().iterator();
//		while(it.hasNext()){
//			String key = (String) it.next();
//			System.out.println(numberAndCount.get(key));
//			System.out.println(key);
//			
//		}
	}
}
