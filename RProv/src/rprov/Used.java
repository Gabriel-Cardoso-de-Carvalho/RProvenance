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
public class Used {
    public String id;
    public Entity entity;
    public Activity activity;

    public Used(String id, Entity entity, Activity activity) {
        this.id = id;
        this.entity = entity;
        this.activity = activity;
    }
    
    @Override
    public String toString(){
        String a,b;
        if(activity == null) a = "NA";
        else a = activity.id;
        if(entity == null) b = "NA";
        else b = entity.id;
        return "id = "+id+"\n\tentity = " + b + "\n\tactivity = " + a;
    }    
    
        
    public static LinkedList<Entity> usados(LinkedList<Used> used, Activity a ){
        LinkedList<Entity> result = new LinkedList<>();
        for (int i = 0; i < used.size(); i++) {
            if (used.get(i).activity.equals(a)) result.add(used.get(i).entity);
        }
        return result;
    }
    
    public static LinkedList<Used> atividadesFuncoes(LinkedList<Used> used){
        LinkedList<Used> result = new LinkedList<>();
        for (int i = 0; i < used.size(); i++) {
            if ("function".equals(used.get(i).entity.type) && !(used.get(i).activity.startLine == Activity.NA) 
                    && "Operation".equals(used.get(i).activity.type)) result.add(used.get(i));
        }
        return result;
    }
}
