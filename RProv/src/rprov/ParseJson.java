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
            String id="",name="",valType="",type="",sn="",sl="",sc="",el="",ec="";
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
                                        valType = tokenizer.sval;
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
                                //System.out.println(id+" "+ name+" "+valType+" "+et+" "+sn+" "+sl+" "+sc+" "+el+" "+ec);
                                a = new Activity(id,name,valType,et,sn,sl,sc,ec,el);
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
                                            case "rdt:valType":
                                                token = tokenizer.nextToken();
                                                valType = tokenizer.sval;
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
                                    //System.out.println(id+" "+ name+" "+valType+" "+et+" "+sn+" "+sl+" "+sc+" "+el+" "+ec);
                                    if((id.substring(0, 1)).equals("f")) e = new Entity(id,name);
                                    else e = new Entity(id,name,val,valType,type);
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
            System.out.println("File of provenance was not found");
            System.exit(-1);
        } catch (IOException ex) {
            System.out.println("Other Problem");
            System.exit(-1);
        }
        
        try{
            Scanner scanner = new Scanner(script);
            while(scanner.hasNext()){
                this.lines.add(scanner.nextLine());
            }
            
        }catch (FileNotFoundException ex) {
            System.out.println("Path for the Script is not correct");
        }
        
        //Process the parsed provenance
        
        this.manage();
                
        try{
            PrintWriter fw = new PrintWriter(new FileWriter(json.getPath().substring(0, json.getPath().length()-5)+".YW"));
            for (int i = 0; i < this.lines.size(); i++) {
                fw.print(lines.get(i)+"\n");
            }
            fw.close();
            
        }catch (FileNotFoundException ex) {
            System.out.println("Path for the Script is not correct");
            System.exit(-1);
        }catch (IOException ex) {
            System.out.println("Other problem");
            System.exit(-1);
        }
        
        if(false) printJson();
    }
    
    //Managing all the atributions and uses
    public void manage(){
        
        LinkedList<Entity> geradosPorFuncao = new LinkedList<>();
        LinkedList<Entity> usadosPorFuncao = new LinkedList<>();
        LinkedList<String> funcoes = new LinkedList<>();
        LinkedList<String> nomes = new LinkedList<>();
        
        int controleEntrada = 1;
        int controleSaida = 1;
        
        lines.add(0, "#@BEGIN "+activities.get(0).name.substring(0, activities.get(0).name.length()-2).replace(" ", "_"));
        incrementOffset(0);
        lines.add(lines.size(), "#@END "+activities.get(activities.size()-1).name.substring(0, activities.get(activities.size()-1).name.length()-2).replace(" ", "_"));
        LinkedList<String> pilha = new LinkedList<>();
        for (int i = 1; i < activities.size()-1; i++) {
            //Caso esteja anotada a função
            if ("Start".equals(activities.get(i).type)) {
                if(activities.get(i).startLine!=Activity.NA){
                    //Coloca todos os #@IN <variável de entrada>
                    LinkedList<Entity> usados = Used.usados(this.used, activities.get(i));
                    //Coloca anotação #@BEGIN <nome da função>
                    if (usados.get(0).valType.equals("function")){
                        lines.add((int)(activities.get(i).startLine+activities.get(i).offset-1), "\n#@BEGIN "+usados.get(0).name);
                        funcoes.add(activities.get(i).name);
                        nomes.add(usados.get(0).name);
                    }else lines.add((int)(activities.get(i).startLine+activities.get(i).offset-1), "\n#@BEGIN "+activities.get(i).name.replace(" ", "_"));
                    
                    incrementOffset(i-1);
                    
                    for (int j = 0; j < usados.size(); j++) {
                        //System.out.println(usados.get(j));
                        if(!"function".equals(usados.get(j).valType)){
                            lines.add((int)(activities.get(i).startLine+activities.get(i).offset-1), "#@IN "+usados.get(j).name);
                            incrementOffset(i-1);
                        }
                    }
                //Caso seja um "Start" gerado por .ddg.start()
                }else if("Operation".equals(activities.get(i+1).type)){
                    lines.add((int)(activities.get(i+1).startLine+activities.get(i+1).offset-1), "\n#@BEGIN "+activities.get(i).name.replace(" ", "_"));
                    incrementOffset(i-1);
                    pilha.push(activities.get(i).name);
                }
            } else if ("Finish".equals(activities.get(i).type)){
                //Caso esteja anotada a função
                if(activities.get(i).startLine!=Activity.NA){
                    //Coloca anotação #@END <nome da função>
                    if(funcoes.lastIndexOf(activities.get(i).name)!=-1) lines.add((int)(activities.get(i).endLine+activities.get(i).offset), "#@END "+nomes.get(funcoes.lastIndexOf(activities.get(i).name)));
                    else lines.add((int)(activities.get(i).endLine+activities.get(i).offset), "#@END "+activities.get(i).name.replace(" ", "_"));
                    //Coloca todos os #@OUT <variável de saída>
                    LinkedList<Entity> gerados = WasGeneratedBy.gerados(this.generatedBy, activities.get(i));
                    incrementOffset(i);
                    for (int j = 0; j < gerados.size(); j++) {
                        lines.add((int)(activities.get(i).startLine+activities.get(i).offset-1), "#@OUT "+gerados.get(j).name);
                        incrementOffset(i-1);
                    }
                //Caso seja um "Start" gerado por .ddg.start()
                }else if (!pilha.isEmpty() && pilha.peek().equals(activities.get(i).name)) {
                    lines.add((int)(activities.get(i-1).endLine+activities.get(i-1).offset), "#@END "+activities.get(i).name.replace(" ", "_"));
                    incrementOffset(i);
                }
            }
        }
        LinkedList<Used> atividadesFuncoes = Used.atividadesFuncoes(this.used);
        for (int i = 0; i < atividadesFuncoes.size(); i++) {
            //System.out.println(atividadesFuncoes.get(i).activity);
            //Adiciona #@BEGIN
            lines.add((int)(atividadesFuncoes.get(i).activity.startLine+atividadesFuncoes.get(i).activity.offset-1), 
                    "\n#@BEGIN "+atividadesFuncoes.get(i).entity.name);
            incrementOffset(Integer.parseInt(atividadesFuncoes.get(i).activity.id.substring(1))-2);
            //Coloca todos os #@IN <variável de entrada>
            LinkedList<Entity> usados = Used.usados(this.used, atividadesFuncoes.get(i).activity);
            for (int j = 0; j < usados.size(); j++) {
                usadosPorFuncao.add(usados.get(j));
                //System.out.println(usados.get(j));
                if(!"function".equals(usados.get(j).valType)){
                    if("Device".equals(usados.get(j).valType)){
                        lines.add((int)(atividadesFuncoes.get(i).activity.startLine+atividadesFuncoes.get(i).activity.offset-1),
                            "#@IN ctrl"+controleEntrada++);
                        incrementOffset(Integer.parseInt(atividadesFuncoes.get(i).activity.id.substring(1))-2);
                        
                    }else{
                        lines.add((int)(atividadesFuncoes.get(i).activity.startLine+atividadesFuncoes.get(i).activity.offset-1),
                            "#@IN "+usados.get(j).name);
                        incrementOffset(Integer.parseInt(atividadesFuncoes.get(i).activity.id.substring(1))-2);
                    }
                }
            }
            //Coloca todos os #@OUT <variável de saída>
            LinkedList<Entity> gerados = WasGeneratedBy.gerados(this.generatedBy, atividadesFuncoes.get(i).activity);//Entidades geradas pela atividade     
            for (int j = 0; j < gerados.size(); j++) {
                geradosPorFuncao.add(gerados.get(j));
                if("Device".equals(gerados.get(j).valType)){
                    lines.add((int)(atividadesFuncoes.get(i).activity.startLine+atividadesFuncoes.get(i).activity.offset-1), 
                        "#@OUT ctrl"+controleSaida++);
                    incrementOffset(Integer.parseInt(atividadesFuncoes.get(i).activity.id.substring(1))-2);
                    
                }else{
                    lines.add((int)(atividadesFuncoes.get(i).activity.startLine+atividadesFuncoes.get(i).activity.offset-1), 
                        "#@OUT "+gerados.get(j).name);
                    incrementOffset(Integer.parseInt(atividadesFuncoes.get(i).activity.id.substring(1))-2);
                }
            }
            //Adiciona #@END
            lines.add((int)(atividadesFuncoes.get(i).activity.startLine+atividadesFuncoes.get(i).activity.offset), 
                    "#@END "+atividadesFuncoes.get(i).entity.name);
            incrementOffset(Integer.parseInt(atividadesFuncoes.get(i).activity.id.substring(1))-1);
        }
        
        //Entidades de entrada: os #@IN anotados no inicio do programa
        LinkedList<Entity> entrada = Entity.usadosNaoGerados(usadosPorFuncao, generatedBy);
        //Entidades de saida: os #@OUT anotados no inicio do programa
        LinkedList<Entity> saida = Entity.geradosNaoUsados(geradosPorFuncao, used);
        int offset = 1;
        for (int i = 0; i < entrada.size(); i++) lines.add(offset++, "#@IN "+entrada.get(i).name);
        
        for (int i = 0; i < saida.size(); i++) lines.add(offset++, "#@OUT "+saida.get(i).name);
    }
    
    /**
     * This function increments the offset of all activities after the one with id 'id'.
     * This is useful since after adding a annotation, all lines in the activities after that line have to be incremented.
     */
    private void incrementOffset(int id){
        for (int i = id+1; i < activities.size(); i++){
            if(activities.get(i).startLine!=Activity.NA) activities.get(i).offset++;
        }
    }
    
        
    public void printJson(){
            System.out.println(this.prefix);
            System.out.println("Activities:");
            for (int i = 0; i < this.activities.size(); i++) {
                System.out.println(this.activities.get(i));
            }
            System.out.println("Entities:");
            for (int i = 0; i < this.entities.size(); i++) {
                System.out.println(this.entities.get(i));
            }
            System.out.println("WasInformedBy:");
            for (int i = 0; i < this.informedBy.size(); i++) {
                System.out.println(this.informedBy.get(i));
            }
            System.out.println("WasGeneratedBy:");
            for (int i = 0; i < this.generatedBy.size(); i++) {
                System.out.println(this.generatedBy.get(i));
            }
            System.out.println("UsedBy:");
            for (int i = 0; i < this.used.size(); i++) {
                System.out.println(this.used.get(i));
            }
            System.out.println("Output Script:");
            for (int i = 0; i < this.lines.size(); i++) {
                System.out.println(this.lines.get(i));
            }
    }
}
