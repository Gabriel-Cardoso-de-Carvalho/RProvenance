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
    
}
