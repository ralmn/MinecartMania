package com.afforess.minecartmaniacore.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;

import com.afforess.minecartmaniacore.MinecartManiaCore;

public class LogWriter extends Thread{
	private LinkedList<String> queued;
	public LogWriter(LinkedList<String> queued) {
		this.queued = queued;
	}
	
	@Override
	public void run() {
		try {
			File logger = new File(MinecartManiaCore.getDataDirectoryRelativePath() + File.separator + "MinecartMania.log");
			if (logger.exists() && logger.length() > 3100000L) {
				logger.delete(); //clear log if > 3MB
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(MinecartManiaCore.getDataDirectoryRelativePath() + File.separator + "MinecartMania.log", true));
			Iterator<String> i = queued.iterator();
			while(i.hasNext()) {
				String log = i.next();
				output.write(log + '\n');
			}
			output.close();
		} catch (Exception e) {
			MinecartManiaLogger.getInstance().info("Failed to update log!");
			return;
		}
		
	}

}
