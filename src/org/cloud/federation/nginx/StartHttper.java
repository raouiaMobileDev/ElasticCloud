package org.cloud.federation.nginx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartHttper {
	
	static Process process = null;
	public static boolean state;
	public static void main(String[] args) {
		
		System.out.println("***********t1***********\n");
		System.out.println( start());	
		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		System.out.println("***********t2***********\n");
		System.out.println( runServer("sudo httperf --server 127.0.0.1 --port 80 --uri /examples/jsp/jsp2/simpletag/hello.jsp    --rate 10 --num-conn 600    --num-call 1 --timeout 1"));
		
	}

	public static boolean start() {
		//return runServer("sudo httperf --client=0/1 --server=127.0.0.1 --port=80 --uri=/ --send-buffer=4096 --recv-buffer=16384 --num-conns=5000 --num-calls=6000");
		return runServer("sudo httperf --server 127.0.0.1 --port 80 --uri /examples/jsp/jsp2/simpletag/hello.jsp    --rate 10 --num-conn 600    --num-call 1 --timeout 1");
	}

	

	public static boolean runServer(String command) {

		
		Runtime runtime = Runtime.getRuntime();
		
		
		try {
			process = runtime.exec(command);

			// Consommation de la sortie standard de l'application externe dans
			// un Thread separe
			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(process.getInputStream()));
						String line = "";
						try {
							while ((line = reader.readLine()) != null) {
								System.out.print("thread1= "+line);
								StartNgnix.state= true;
							}
						} finally {
							reader.close();
							StartNgnix.state= true;
							
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();
						StartNgnix.state= false;
					}
				}
				
			}.start();

			// Consommation de la sortie d'erreur de l'application externe dans
			// un Thread separe
			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(process.getErrorStream()));
						String line = "";
						try {
							while ((line = reader.readLine()) != null) {
								System.out.print("thread2= "+line);
							}
						} finally {
							reader.close();
							StartNgnix.state= false;
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();
						StartNgnix.state= false;
					}
				}
			}.start();
				Thread.sleep(2000);
				return StartNgnix.state;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
	

}
