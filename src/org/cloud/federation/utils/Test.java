package org.cloud.federation.utils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			 
			System.out.println("fff");
			String x= EventUtil.eventToString (30.0, 50.6);
			System.out.println(EventUtil.getCpuUsage(x));
			System.out.println(EventUtil.getMemUsage(x));
	}

}
