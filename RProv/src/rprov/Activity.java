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
    
    public static int NA = Integer.MIN_VALUE;
    
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
        this(id,name,type,elapsedTime,Activity.NA,Activity.NA,Activity.NA,Activity.NA,Activity.NA);
    }
    
    @Override
    public String toString(){
        return "id = "+id+"\n\tname = " + name + "\n\ttype = " + type + "\n\telapsedTime = " + elapsedTime + "\n\tscriptNum = " + scriptNum +
                "\n\tstartLine = " + startLine + "\n\tstartCol = " + startCol + "\n\tendCol = " + endCol + "\n\tendLine = " + endLine;
    }
    
}
