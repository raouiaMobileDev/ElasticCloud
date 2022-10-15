package org.cloud.federation.nginx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartNgnix {
	static Process process = null;
	public static boolean state;
	public static void main(String[] args) {
		System.out.println( start());
	}

	public static boolean start() {
		
		return runServer("sudo /etc/init.d/nginx start");

	}

	public static boolean stop() {
		return runServer("sudo /etc/init.d/nginx stop");

	}
	
	public static boolean restart() {
		return runServer("sudo /etc/init.d/ngin restart");

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