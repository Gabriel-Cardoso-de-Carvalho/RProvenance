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
    
    
}
