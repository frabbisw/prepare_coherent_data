package file_handling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.DataObject;

public class FileParser {
	public String fileName;
	ArrayList<DataObject> dataObejcts;
	public FileParser(String fileName) {
		this.fileName=fileName;
		this.dataObejcts=new ArrayList<DataObject>();
		startParsing();
	}
	private void startParsing() {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	DataObject dataObject = new DataObject();
		    	String[] args = line.split(",");
		    	dataObject.setMethod_id(args[0]);
		    	
		    	//2nd line
		    	line = br.readLine();
		    	
		    	String comment = parseComment(br);
		    	String method = parseMethod(br);
		    	
		    	//###
		    	line = br.readLine();
		    	
		    	dataObject.setMethod(method);
		    	dataObject.setComment(comment);
		    	
		    	dataObejcts.add(dataObject);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String parseMethod(BufferedReader br) {
		String method="";
		try {
			//method lines number
			String line = br.readLine();
			int number = Integer.parseInt(line);
			
			for(int i=0; i<number; i++) {
				line = br.readLine();
				method+=(line+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return method;
	}
	private String parseComment(BufferedReader br) {
		String comment="";
		try {	
			//comment lines number
			int number = Integer.parseInt(br.readLine());
			number-=2;
			String line = br.readLine();
			
			for(int i=0; i<number; i++) {
				line = br.readLine();
				if(line.substring(5, 6).equals("@"))
					break;
				comment+=line.substring(5);
				comment+=" ";
			}
			
			line = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment;
	}
	public ArrayList<DataObject> getDataObejcts() {
		return dataObejcts;
	}
	public void setDataObejcts(ArrayList<DataObject> dataObejcts) {
		this.dataObejcts = dataObejcts;
	}
	
}
