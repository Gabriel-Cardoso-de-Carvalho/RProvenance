/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rprov;

import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gaburieru
 */
public class ParseJson {
    public File json;
    public Prefix prefix;
    public Environment env;
    public LinkedList<Activity> activities;
    public LinkedList<Entity> entities;
    public LinkedList<WasInformedBy> informedBy;
    public LinkedList<WasGeneratedBy> generatedBy;
    public LinkedList<Used> used;

    /**
     *
     * @param fileName
     */
    public ParseJson(String fileName) {
        json = new File(fileName);
        
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
            double d;
            String s;
            String id="",name="",type="",sn="",sl="",sc="",el="",ec="";
            double et=-1;
            while(token != StreamTokenizer.TT_EOF){
                if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println(tokenizer.nval);
                }
                else if (tokenizer.ttype == StreamTokenizer.TT_WORD){
                    System.out.println(tokenizer.sval);
                    
                }else if(token == '"'){
                    System.out.println("|"+tokenizer.sval+"|");
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
                                        sn = ""+Math.round(tokenizer.nval);
                                    }else if("rdt:startLine".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        sl = ""+Math.round(tokenizer.nval);
                                    }else if("rdt:startCol".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        sc = Math.round(tokenizer.nval)+"";
                                    }else if("rdt:endLine".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        el = Math.round(tokenizer.nval)+"";
                                    }else if("rdt:endCol".equals(tokenizer.sval)){
                                        token = tokenizer.nextToken();
                                        ec = Math.round(tokenizer.nval)+"";
                                    }   
                                }
                                System.out.println(id+" "+ name+" "+type+" "+et+" "+sn+" "+sl+" "+sc+" "+el+" "+ec);
                                this.activities.add(new Activity(id,name,type,et,sn,sl,sc,el,ec));
                                token = tokenizer.nextToken();
                            }
                            break;
                        case "entity":
                            /* Popular this.entities*/
                            break;
                        case "wasInformedBy":
                            /* Popular this.informedBy*/
                            break;
                        case "wasGeneratedBy":
                            /* Popular this.generatedBy*/
                            break;
                        case "used":
                            /* Popular this.used*/
                            break;
                        
                        default:
                            System.out.println(tokenizer.sval);
                    }
                }else
                    System.out.println((char)token);
                
                token = tokenizer.nextToken();
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        System.out.println(this.prefix);
        for (int i = 0; i < this.activities.size(); i++) {
            System.out.println(this.activities.get(i));
        }
    }
    
    
}
