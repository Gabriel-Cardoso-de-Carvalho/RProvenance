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
public class Entity {
    public String id;
    public String name;
    public String value;
    public String type;

    public Entity(String id, String name, String value, String valType) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.type = valType;
    }
    
    
    public Entity(String id, String name) {
        this(id,name,null,"function");
    }
    
    public Entity(String id){
        this.id = id;
    }
    
    @Override
   public String toString(){
        if(value != null) return "id = "+id+"\n\tname = " + name + "\n\tvalue = " + value + "\n\tvaltype = " + type;
        return "id = "+id+"\n\tname = " + name + "\n\tvaltype = " + type;
   }
    
   @Override
    public boolean equals(Object o){
        return this.id.equals(((Entity)o).id);
    }
    
}
