package org.cloud.federation.utils;

public class StringUtil {
	
	
	public static boolean contain( String ch1, String ch2)
	{
		
		String [] strings= ch1.split(" ");
		for(int i=0; i<ch1.length(); i++)
		{
			if(ch2.contains(ch1))
				return true;
		}
		return false;
	}

}
