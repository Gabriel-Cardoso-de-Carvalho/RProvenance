/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rprov;

import java.util.LinkedList;

/**
 *
 * @author Gaburieru
 */
public class Entity {
    public String id;
    public String name;
    public String value;
    public String valType;
    public String type;

    public Entity(String id, String name, String value, String valType, String type) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.valType = valType;
        this.type = type;
    }
    
    
    public Entity(String id, String name) {
        this(id,name,null,"function","function");
    }
    
    public Entity(String id){
        this.id = id;
    }
    
    @Override
   public String toString(){
        if(value != null) return "id = "+id+"\n\tname = " + name + "\n\tvalue = " + value + "\n\tvaltype = " + valType + "\n\ttype = " + type;
        return "id = "+id+"\n\tname = " + name + "\n\tvaltype = " + valType + "\n\ttype = " + type;
   }
    
   @Override
    public boolean equals(Object o){
        return this.id.equals(((Entity)o).id);
    }
    
    public static LinkedList<Entity> geradosNaoUsados(LinkedList<Entity> geradosPorFuncao, LinkedList<Used> usados){
        LinkedList<Entity> result = new LinkedList<>();
        boolean gerado;
        
        for (int i = 0; i < geradosPorFuncao.size(); i++) {
            gerado = false;
            for (int j = 0; j < usados.size(); j++) {
                if (geradosPorFuncao.get(i).equals(usados.get(j).entity)) {
                    gerado = true;
                    break;
                }
            }
            if (!gerado && geradosPorFuncao.get(i).isFirst(result)) result.add(geradosPorFuncao.get(i));
        }
        //System.out.println("geradosPorFuncao");
        //for (int i = 0; i < result.size(); i++) {;
        //    System.out.println(result.get(i));
        //}
        return result;
    }
        
    public static LinkedList<Entity> usadosNaoGerados(LinkedList<Entity> usadosPorFuncao, LinkedList<WasGeneratedBy> generatedBy){
        LinkedList<Entity> result = new LinkedList<>();
        boolean usado;
        
        for (int i = 0; i < usadosPorFuncao.size(); i++) {
            usado = false;
            for (int j = 0; j < generatedBy.size(); j++) {
                if (usadosPorFuncao.get(i).equals(generatedBy.get(j).entity)) {
                    usado = true;
                    break;
                }
            }
            if (!usado && !usadosPorFuncao.get(i).type.equals("function") && usadosPorFuncao.get(i).isFirst(result)) result.add(usadosPorFuncao.get(i));
        }
        //System.out.println("usadosPorFuncao");
        //for (int i = 0; i < result.size(); i++) {
        //    System.out.println(result.get(i));
        //}
        return result;
    }
    
    public boolean isFirst(LinkedList<Entity> e){
        for (int i = 0; i < e.size(); i++) if(e.get(i).name.equals(this.name)) return false;
        return true;
    }
    
}
