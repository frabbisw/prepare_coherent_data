package explore_dir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import entity.DataObject;

public class FileExplorer
{
    public FileExplorer(String rootFileName)
    {
    	File rootFile = new File(rootFileName);
        browseClasses(rootFile);
    }
    public void browseClasses(File rootFile) {
    	new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
    		ASTParser parser = ASTParser.newParser(AST.JLS3);
    		
    		DataObject dataObject = new DataObject();
    		dataObject.setFileName(path);
    		
    		String str = readUsingBufferedReaderCharArray(file);
    		parser.setSource(str.toCharArray());
    		parser.setKind(ASTParser.K_COMPILATION_UNIT);
    		
    		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
    		cu.accept(new ASTVisitor() {
    			@Override
    			public boolean visit(MethodDeclaration node) {
    				dataObject.setMethod(node.toString());    				
    				dataObject.setMethodName(node.getName().toString());
    				
    				//System.out.println(parseComment(node.getJavadoc()));
    				
    				dataObject.setComment(parseComment(node.getJavadoc()));
    				return super.visit(node);
    			}
    		});
    		
    		//System.out.println(dataObject.getFileName());
    		System.out.println(dataObject.getComment());
    		
    		//System.out.println("\n\n");
    		
        }).explore(rootFile);
    }
    private static String readUsingBufferedReaderCharArray(File file) {
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[10];
		try {
			reader = new BufferedReader(new FileReader(file));
			while (reader.read(buffer) != -1) {
				stringBuilder.append(new String(buffer));
				buffer = new char[10];
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return stringBuilder.toString();
	}
    public static String parseComment(Javadoc doc) {
    	String ret = "";
    	if(doc==null)	return "";

    	String javadoc = doc.toString();
    	
    	String [] args = javadoc.split("\n");
    	for(int i=1; i<args.length-1; i++)
    	{
    		if(args[i].length()>1) {
    			if(args[i].contains("@"))
    				break;
    			
    			String tmp = " "+args[i];
    			
    			String smargs [] = tmp.split("\\*");
    			ret+=smargs[1];
    		}
    	}
    	return ret;
    }
}