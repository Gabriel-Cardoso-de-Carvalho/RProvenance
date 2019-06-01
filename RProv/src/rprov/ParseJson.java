/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rprov;

import java.io.*;

/**
 *
 * @author Gaburieru
 */
public class ParseJson {
    public File json;
    public Prefix prefix;
    public Environment env;
    public Activity[] activities;
    public Entity[] entities;
    public WasInformedBy[] informedBy;
    public WasGeneratedBy[] generatedBy;
    public Used[] used;

    /**
     *
     * @param fileName
     */
    public ParseJson(String fileName) {
        json = new File(fileName);
    }
    
    
}
