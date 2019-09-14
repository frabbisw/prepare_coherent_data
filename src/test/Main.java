package test;

import java.util.ArrayList;

import entity.DataObject;
import explore_dir.FileExplorer;
import file_handling.FileParser;

public class Main {
	public static void main(String [] args) {
		FileExplorer fileExplorer = new FileExplorer("/home/rabbi/bin/code-comments-dataset");
		ArrayList<DataObject>dataObjects=fileExplorer.getDataObjects();
		
		System.out.println(dataObjects.size());
		
		for(DataObject dataObject : dataObjects) {
			//System.out.println(dataObject.getComment());
		}
	}
}