package com.project.fms.user.data;

public class FilesData {
	private static FileRecord[] records;  
	  
    public static FileRecord[] getRecords() {  
        if (records == null) {  
            records = getNewRecords();  
        }  
        return records;  
    }  
  
    public static FileRecord[] getNewRecords() {  
        return new FileRecord[]{
        		new FileRecord("File 8", "F001", "0"),//Open for Transcriber
        		new FileRecord("File 7", "F002", "1"),//Closed for transcriber, open for Editor
        		new FileRecord("File 6", "F003", "2"),//Open for transcriber(returned by editor),closed for Editor
        		new FileRecord("File 5", "F004", "3"),//Closed for transcriber, Closed for Editor, Open for Proofer.
        		new FileRecord("File 4", "F005", "4"),//Closed for Transcriber, Open for Editor(returned by Proofer),closed for Proofer
        		new FileRecord("File 3", "F006", "5"),//Closed for Transcriber, Closed for Editor, Closed for Proofer, Open for QA
        		new FileRecord("File 2", "F007", "6"),//Closed for Transcriber, Closed for Editor, Open for Proofer(returned by QA), Closed for QA 
        		new FileRecord("File 1", "F008", "7"),//File Processing Finished
        		
        };  
    }  

}
