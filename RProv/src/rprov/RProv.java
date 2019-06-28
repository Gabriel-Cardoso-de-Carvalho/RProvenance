/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rprov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Gaburieru
 */
public class RProv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args == null || args.length == 1) {
            System.out.println("The call to this should be:\njava -jar RProv.jar <prov_path> <script_path>");
            System.exit(0);
        }
        boolean ctrl = false;
        
        if(args.length>2) for (int i = 2; i < args.length; i++) if (args[i].equals("-ctrl")) ctrl = true;
            
        new ParseJson(args[0],args[1],ctrl);
    }
    
}
