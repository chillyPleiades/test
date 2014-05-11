package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
	static HashMap<String, String> hm=new HashMap<String, String>();
	static String filePath="";
	static boolean onceTime;
	public static void main(String[] args) throws IOException, InterruptedException {
		onceTime=false;
		if(args.length>0){
			if(args[1]=="true"){
				onceTime=true;
			}
			filePath=args[1];
		}else{
			filePath="./settings.txt";
		}

		hm=new HashMap<String, String>();
		try{
			ArrayList<String> al = IOLibrary.FileInput(filePath);
		for(String s:al){
			s.replaceAll(" ", "");
			s.replaceAll("Å@", "");
			s.replaceAll("	", "");
			if(s.contains("=")){
				hm.put(s.split("=")[0], s.split("=")[1]);
			}
		}
		update();
		}catch(FileNotFoundException e){
			hm=new HashMap<String, String>();
			if(!hm.containsKey("path")){
				hm.put("path", "null");
			}

			if(!hm.containsKey("updated")){
				hm.put("updated", String.valueOf(getUnixMinute()-721));
			}

			if(!hm.containsKey("updateMin")){
				hm.put("updateMin", "720");
			}
			ArrayList<String> write=new ArrayList<String>();
			for(String k:hm.keySet()){
				write.add(k+"="+hm.get(k));
			}
			IOLibrary.FileOutput(filePath, write);
		}

		if(onceTime==true){
			System.exit(0);
		}

		while(true){
			hm=new HashMap<String, String>();
			ArrayList<String> al = IOLibrary.FileInput(filePath);
			for(String s:al){
				s.replaceAll(" ", "");
				s.replaceAll("Å@", "");
				s.replaceAll("	", "");
				if(s.contains("=")){
					hm.put(s.split("=")[0], s.split("=")[1]);
				}
			}

			if(Integer.parseInt(hm.get("updated"))+Integer.parseInt(hm.get("updateMin"))<getUnixMinute()){
				update();
			}
			
			Thread.sleep(60*1000);
		}
	}

	private static void update() throws IOException {
		Runtime.getRuntime().exec("TortoiseProc.exe /command:update /path:\""+hm.get("path")+"\" /closeonend:2");
		hm.remove("updated");
		hm.put("updated", String.valueOf(getUnixMinute()));

		if(!hm.containsKey("path")){
			hm.put("path", "null");
		}

		if(!hm.containsKey("updated")){
			hm.put("updated", String.valueOf(getUnixMinute()));
		}

		if(!hm.containsKey("updateMin")){
			hm.put("updateMin", "720");
		}

		ArrayList<String> write=new ArrayList<String>();
		for(String k:hm.keySet()){
			write.add(k+"="+hm.get(k));
		}
		IOLibrary.FileOutput(filePath, write);

	}

	private static int getUnixMinute() {
		return (int) (System.currentTimeMillis()/1000/60);
	}
}
