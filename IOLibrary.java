package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class IOLibrary {


	public static void HTMLGetter(URL url,String charset){
		ArrayList<String> al=new ArrayList<String>();
		// WebÉyÅ[ÉWÇì«Ç›çûÇﬁ
		try {
			// ê⁄ë±
			URLConnection uc = url.openConnection();
			// HTMLÇì«Ç›çûÇﬁ
			BufferedInputStream bis = new BufferedInputStream(uc.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(bis, charset));
			String line;
			while ((line = br.readLine()) != null) {
				al.add(line);
			}

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (UnknownHostException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		}    

	}

	public static ArrayList<String> FileInput(String pass) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(pass));

		ArrayList<String> al=new ArrayList<String>();
		String temp=br.readLine();
		while(temp!=null){
			al.add(temp);
			temp=br.readLine();
		}
		return al;
	}

	public static void FileOutput(String pass,ArrayList<String> content) throws IOException {
		FileOutputStream xyz = new FileOutputStream(pass);
		OutputStreamWriter out = new OutputStreamWriter(xyz);

		Iterator<String> it=content.iterator();
		while(it.hasNext()){
			out.write(it.next()+"\n");
		}
		out.close();
		xyz.close();
	}

	public static void DBsql(String sql) {
		// TODO Auto-generated method stub

	}


	public XMLElement XMLParser(String pass) throws IOException {
		ArrayList<String> al = FileInput(pass);
		StringBuilder sb=new StringBuilder();
		for(String s:al){
			sb.append(s);
		}
		String temp=sb.toString();
		String[] xmls = temp.split("<");
		XMLElement root=new XMLElement();
		root.childAdd(new XMLElement(root, xmls[0]));
		
		return null;
	}



}
