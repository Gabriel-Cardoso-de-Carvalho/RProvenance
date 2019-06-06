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
public class WasInformedBy {
    public String id;
    public Activity informant;
    public Activity informed;

    public WasInformedBy(String id, Activity informant, Activity informed) {
        this.id = id;
        this.informant = informant;
        this.informed = informed;
    }
    
    @Override
    public String toString(){
        String a,b;
        if(informant == null) a = "NA";
        else a = informant.id;
        if(informed == null) b = "NA";
        else b = informed.id;
        return "id = "+id+"\n\tinformant = " + a + "\n\tinformed = " + b;
    }
    
}
