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
    public int scriptNum;
    public int startLine;
    public int startCol;
    public int endCol;
    public int endLine;

    public Activity(String id, String name, String type, double elapsedTime, int scriptNum, int startLine, int startCol, int endCol, int endLine) {
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
    
}
