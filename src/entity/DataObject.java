package entity;

public class DataObject {
//	method_id, method_name, class_name, software_system
//	filepath, start_line, end_line,
//	Length of the Head Comments
//	Head Comment
//	Length of the Implementation
//	Method Implementation
	
	String method_id;
	String comment;
	String method;
	String methodName;
	boolean coherent;
	String fileName;
	
	public DataObject() {
		
	}
	
	public DataObject(String method_id, String comment, String method) {
		this.method_id = method_id;
		this.comment = comment;
		this.method = method;
	}
	public String getMethod_id() {
		return method_id;
	}
	public void setMethod_id(String method_id) {
		this.method_id = method_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public boolean isCoherent() {
		return coherent;
	}
	public void setCoherent(boolean coherent) {
		this.coherent = coherent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
}