package main;

import java.util.ArrayList;
import java.util.HashMap;

public class XMLElement {
	private XMLElement parent;
	private HashMap<String, String> attribute;
	private ArrayList<XMLElement> child;
	private boolean strFlag;
	private String value="";
	
	public boolean isStr() {
		return strFlag;
	}

	public void setStr(boolean str) {
		this.strFlag = str;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public XMLElement() {
		attribute=new HashMap<String, String>();
		child=new ArrayList<XMLElement>();
		strFlag=false;
	}
	
	public XMLElement(XMLElement parent,HashMap<String, String> attribuite,ArrayList<XMLElement> child) {
		if(parent==null){
			this.parent=null;
		}else{
			this.parent=parent;
		}
		
		if(attribuite==null){
			this.attribute=new HashMap<String, String>();
		}else{
			this.attribute=attribuite;
		}
		
		if(child==null){
			this.child=new ArrayList<XMLElement>();
		}else{
			this.child=child;
		}
		
		this.strFlag=false;
	}
	
	public XMLElement(XMLElement parent,String str){
		this.attribute=new HashMap<String, String>();
		this.child=new ArrayList<XMLElement>();
		this.parent=parent;
		this.value=str;
		this.strFlag=true;
	}
	
	public XMLElement getParent() {
		return parent;
	}
	public void setParent(XMLElement parent) {
		this.parent = parent;
	}
	public HashMap<String, String> getAttribute() {
		return attribute;
	}
	public void setAttribute(HashMap<String, String> attribute) {
		this.attribute = attribute;
	}
	public ArrayList<XMLElement> getChild() {
		return child;
	}
	public void setChild(ArrayList<XMLElement> child) {
		this.child = child;
	}
	
	public void childAdd(XMLElement ele) {
		child.add(ele);
	}
	
	
}
