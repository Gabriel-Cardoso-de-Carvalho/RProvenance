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
public class Prefix {
    public String prov;
    public String rdt;

    public Prefix(String prov, String rdt) {
        this.prov = prov;
        this.rdt = rdt;
    }
    
    @Override
    public String toString(){
        return "Prefix:\nprov = " + prov + "\nrdt = " + rdt;
    }
}
