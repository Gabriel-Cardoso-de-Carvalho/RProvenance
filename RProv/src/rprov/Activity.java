/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rprov;

/**
 *
 * @author Gaburieru
 */
public class Activity {
    
    public static long NA = Integer.MIN_VALUE;
    
    public String id;
    public String name;
    public String type;
    public double elapsedTime;
    public long scriptNum;
    public long startLine;
    public long startCol;
    public long endCol;
    public long endLine;

    public Activity(String id, String name, String type, double elapsedTime, long scriptNum, long startLine, long startCol, long endCol, long endLine) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.elapsedTime = elapsedTime;
        this.scriptNum = scriptNum;
        this.startLine = startLine;
        this.startCol = startCol;
        this.endCol = endCol;
        this.endLine = endLine;
    }
        
    public Activity(String id, String name, String type, double elapsedTime, String scriptNum, String startLine, String startCol, String endCol, String endLine) {
        
        this.id = id;
        this.name = name;
        this.type = type;     
        this.elapsedTime = elapsedTime;
        this.scriptNum = Activity.NA;
        this.startLine = Activity.NA;
        this.startCol = Activity.NA;
        this.endCol = Activity.NA;
        this.endLine = Activity.NA;  
        
        if (!scriptNum.equals("NA")) this.scriptNum = Math.round(Double.parseDouble(scriptNum));
        if (!startLine.equals("NA")) this.startLine = Math.round(Double.parseDouble(startLine));
        if (!startCol.equals("NA")) this.startCol = Math.round(Double.parseDouble(startCol));
        if (!endLine.equals("NA")) this.endLine = Math.round(Double.parseDouble(endLine));
        if (!endCol.equals("NA")) this.endCol = Math.round(Double.parseDouble(endCol));
        
        
    }
    
    public Activity(String id){
        this.id = id;
    }
    
    @Override
    public String toString(){
        return "id = "+id+"\n\tname = " + name + "\n\ttype = " + type + "\n\telapsedTime = " + elapsedTime + "\n\tscriptNum = " + scriptNum +
                "\n\tstartLine = " + startLine + "\n\tstartCol = " + startCol + "\n\tendLine = " + endLine + "\n\tendCol = " + endCol;
    }
    
    @Override
    public boolean equals(Object o){
        return this.id.equals(((Activity)o).id);
    }
    
}
