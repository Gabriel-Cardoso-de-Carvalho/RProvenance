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
    public String valType;

    public Entity(String id, String name, String value, String valType) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.valType = valType;
    }
    
    
    public Entity(String id, String name) {
        this(id,name,null,"function");
    }
    
}
