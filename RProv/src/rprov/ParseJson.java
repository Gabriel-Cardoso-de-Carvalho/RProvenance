/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rprov;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gaburieru
 */
public class ParseJson {
    public File json;
    public File script;
    public Prefix prefix;
    public Environment env;
    public LinkedList<Activity> activities;
    public LinkedList<Entity> entities;
    public LinkedList<WasInformedBy> informedBy;
    public LinkedList<WasGeneratedBy> generatedBy;
    public LinkedList<Used> used;
    public LinkedList<String> lines;

    /**
     *
     * @param provFileName
     * @param scriptFileName
     * 
     */
    public ParseJson(String provFileName, String scriptFileName) {
        json = new File(provFileName);
        script = new File(scriptFileName);
        activities = new LinkedList<>();
        entities = new LinkedList<>();
        informedBy = new LinkedList<>();
        generatedBy = new LinkedList<>();
        used = new LinkedList<>();
        lines = new LinkedList<>();
        try{
            StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new FileReader(json)));
            
            tokenizer.parseNumbers();
 
            tokenizer.wordChars('_', '_');
            tokenizer.whitespaceChars(':', ':');
            tokenizer.whitespaceChars(',', ',');

            tokenizer.eolIsSignificant(false);          

            tokenizer.slashSlashComments(true);

            tokenizer.slashStarComments(true);
            
            int token = tokenizer.nextToken();
            String id="",name="",type="",sn="",sl="",sc="",el="",ec="";
            String val="";
            double et=-1;
            Activity a=null,b=null;
            Entity e=null;
            
            while(token != StreamTokenizer.TT_EOF){
                if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    //System.out.println(tokenizer.nval);
                }
                else if (tokenizer.ttype == StreamTokenizer.TT_WORD){
                    //System.out.println(tokenizer.sval);
                    
                }else if(token == '"'){
                    //System.out.println("|"+tokenizer.sval+"|");
                    switch(tokenizer.sval){
                        case "prefix":
                            /* Instanciar this.prefix*/
                                token = tokenizer.nextToken();
                            while(token != '}'){
                                token = tokenizer.nextToken();
                                
                                if("prov".equals(tokenizer.sval)){
                                    token = tokenizer.nextToken();
                                    id = tokenizer.sval;
                                }else if("rdt".equals(tokenizer.sval)){
                                    token = tokenizer.nextToken();
                                    name = tokenizer.sval;
                                }
                            }
                            this.prefix = new Prefix(id,name);
                            break;
                        case "activity":
                            /* Popular this.activities e instanciar this.environment*/ 
                            token = tokenizer.nextToken(); 
                            token = tokenizer.nextToken();
                            while(token != '}'){
                                id = tokenizer.sval.substring(4);
                                token = tokenizer.nextToken(); 
                                while(token != '}'){
                                    token = tokenizer.nextToken(); 
                                    if("rdt:name".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        name = tokenizer.sval;
                                    }else if("rdt:type".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        type = tokenizer.sval;
                                    }else if("rdt:elapsedTime".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        et = tokenizer.nval;
                                    }else if("rdt:scriptNum".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        if(tokenizer.ttype == StreamTokenizer.TT_NUMBER) sn = ""+Math.round(tokenizer.nval);
                                        else sn = tokenizer.sval;
                                    }else if("rdt:startLine".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        if(tokenizer.ttype == StreamTokenizer.TT_NUMBER) sl = ""+Math.round(tokenizer.nval);
                                        else sl = tokenizer.sval;
                                    }else if("rdt:startCol".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        if(tokenizer.ttype == StreamTokenizer.TT_NUMBER)  sc = Math.round(tokenizer.nval)+"";
                                        else sc = tokenizer.sval;
                                    }else if("rdt:endLine".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        if(tokenizer.ttype == StreamTokenizer.TT_NUMBER) el = Math.round(tokenizer.nval)+"";
                                        else el = tokenizer.sval;
                                    }else if("rdt:endCol".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        if(tokenizer.ttype == StreamTokenizer.TT_NUMBER) ec = Math.round(tokenizer.nval)+"";
                                        else ec = tokenizer.sval;
                                    }   
                                }
                                //System.out.println(id+" "+ name+" "+type+" "+et+" "+sn+" "+sl+" "+sc+" "+el+" "+ec);
                                a = new Activity(id,name,type,et,sn,sl,sc,el,ec);
                                //System.out.println(a);
                                this.activities.add(a);
                                token = tokenizer.nextToken();
                            }
                            break;
                        case "entity":
                            /* Popular this.entities*/
                            token = tokenizer.nextToken(); 
                            token = tokenizer.nextToken();
                            while(token != '}'){
                                id = tokenizer.sval.substring(4);
                                token = tokenizer.nextToken(); 
                                if(!id.substring(0, 1).equals("l")&&!id.equals("environment")){
                                    while(token != '}'){
                                        token = tokenizer.nextToken(); 
                                        if(null!=tokenizer.sval)switch (tokenizer.sval) {
                                            case "rdt:name":
                                            case "name":
                                                token = tokenizer.nextToken();
                                                name = tokenizer.sval;
                                                break;
                                            case "rdt:type":
                                                token = tokenizer.nextToken();
                                                type = tokenizer.sval;
                                                break;
                                            case "rdt:value":
                                                token = tokenizer.nextToken();
                                                val = tokenizer.sval;
                                                break;
                                            default:
                                                break;
                                        } 
                                    }
                                    //System.out.println(id+" "+ name+" "+type+" "+et+" "+sn+" "+sl+" "+sc+" "+el+" "+ec);
                                    if((id.substring(0, 1)).equals("f")) e = new Entity(id,name);
                                    else e = new Entity(id,name,val,type);
                                    //System.out.println(a);
                                    this.entities.add(e);
                                }else{
                                    int stack = 1;
                                    token = tokenizer.nextToken(); 
                                    while(stack != 0){
                                        token = tokenizer.nextToken(); 
                                        if(token == '{') stack++;
                                        else if (token == '}') stack--;
                                    }
                                }
                                token = tokenizer.nextToken();
                            }
                            break;
                        case "wasInformedBy":
                            /* Popular this.informedBy*/
                            token = tokenizer.nextToken(); 
                            token = tokenizer.nextToken();
                            while(token != '}'){
                                id = tokenizer.sval.substring(4);
                                token = tokenizer.nextToken(); 
                                while(token != '}'){
                                    token = tokenizer.nextToken(); 
                                    if("prov:informant".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        a = this.activities.get(this.activities.indexOf(new Activity(tokenizer.sval.substring(4))));
                                    }else if("prov:informed".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        b = this.activities.get(this.activities.indexOf(new Activity(tokenizer.sval.substring(4))));
                                    }  
                                }
                                this.informedBy.add(new WasInformedBy(id,a,b));
                                token = tokenizer.nextToken();
                            }
                            break;
                        case "wasGeneratedBy":
                            /* Popular this.generatedBy*/
                            token = tokenizer.nextToken(); 
                            token = tokenizer.nextToken();
                            while(token != '}'){
                                id = tokenizer.sval.substring(4);
                                token = tokenizer.nextToken(); 
                                while(token != '}'){
                                    token = tokenizer.nextToken(); 
                                    if("prov:activity".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        a = this.activities.get(this.activities.indexOf(new Activity(tokenizer.sval.substring(4))));
                                    }else if("prov:entity".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        e = this.entities.get(this.entities.indexOf(new Entity(tokenizer.sval.substring(4))));
                                    }  
                                }
                                this.generatedBy.add(new WasGeneratedBy(id,a,e));
                                token = tokenizer.nextToken();
                            }
                            break;
                        case "used":
                            /* Popular this.used*/
                            token = tokenizer.nextToken(); 
                            token = tokenizer.nextToken();
                            while(token != '}'){
                                id = tokenizer.sval.substring(4);
                                token = tokenizer.nextToken(); 
                                while(token != '}'){
                                    token = tokenizer.nextToken(); 
                                    if("prov:activity".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        a = this.activities.get(this.activities.indexOf(new Activity(tokenizer.sval.substring(4))));
                                    }else if("prov:entity".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        e = this.entities.get(this.entities.indexOf(new Entity(tokenizer.sval.substring(4))));
                                    }  
                                }
                                this.used.add(new Used(id,e,a));
                                token = tokenizer.nextToken();
                            }
                            break;
                        
                        default:
                            //System.out.println(tokenizer.sval);
                    }
                }else{
                    //System.out.println((char)token);
                }
                token = tokenizer.nextToken();
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
        
        try{
            Scanner scanner = new Scanner(script);
            while(scanner.hasNext()){
                this.lines.add(scanner.nextLine());
            }
            
        }catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        }
        
        
        if(true) printJson();
    }
    
    public void printJson(){
            System.out.println(this.prefix);
            for (int i = 0; i < this.activities.size(); i++) {
                System.out.println(this.activities.get(i));
            }
            for (int i = 0; i < this.entities.size(); i++) {
                System.out.println(this.entities.get(i));
            }
            for (int i = 0; i < this.informedBy.size(); i++) {
                System.out.println(this.informedBy.get(i));
            }
            for (int i = 0; i < this.generatedBy.size(); i++) {
                System.out.println(this.generatedBy.get(i));
            }
            for (int i = 0; i < this.used.size(); i++) {
                System.out.println(this.used.get(i));
            }
            for (int i = 0; i < this.lines.size(); i++) {
                System.out.println(this.lines.get(i));
            }
    }
    
}
