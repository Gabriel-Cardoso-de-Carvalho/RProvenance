/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rprov;

import java.io.*;
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
    public Activity[] activities;
    public Entity[] entities;
    public WasInformedBy[] informedBy;
    public WasGeneratedBy[] generatedBy;
    public Used[] used;

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
            while(token != StreamTokenizer.TT_EOF){
                if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println(tokenizer.nval);
                }
                else if (tokenizer.ttype == StreamTokenizer.TT_WORD){
                            System.out.println(tokenizer.sval);
                    switch(tokenizer.sval){
                        case "prefix":
                            /* Instanciar this.prefix*/ 
                            break;
                        case "activity":
                            /* Popular this.activities e instanciar this.environment*/ 
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
                }else if(token == '"'){
                    System.out.println(tokenizer.sval);
                }else
                    System.out.println((char)token);
                
                token = tokenizer.nextToken();
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
