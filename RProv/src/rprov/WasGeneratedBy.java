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
public class WasGeneratedBy {
    public String id;
    public Activity activity;
    public Entity entity;

    public WasGeneratedBy(String id, Activity activity, Entity entity) {
        this.id = id;
        this.activity = activity;
        this.entity = entity;
    }
    
    @Override
    public String toString(){
        String a,b;
        if(activity == null) a = "NA";
        else a = activity.id;
        if(entity == null) b = "NA";
        else b = entity.id;
        return "id = "+id+"\n\tactivity = " + a + "\n\tentity = " + b;
    } 
        
    public static LinkedList<Entity> gerados(LinkedList<WasGeneratedBy> generatedBy, Activity a ){
        LinkedList<Entity> result = new LinkedList<>();
        for (int i = 0; i < generatedBy.size(); i++) {
            if (generatedBy.get(i).activity.equals(a)) result.add(generatedBy.get(i).entity);
        }
        return result;
    }
    
}
