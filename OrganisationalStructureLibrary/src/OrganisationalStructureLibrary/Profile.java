/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganisationalStructureLibrary;

import java.util.HashMap;

/**
 *
 * @author flori
 */
public class Profile {
    private HashMap<String, Integer> attributes;
    
    public Profile(){
    }

    public HashMap<String, Integer> getAttributes(){
        return attributes;
    }

    public boolean addAttribute(String name, int value){
        if(attributes == null){
            attributes = new HashMap<>();
        }
        else if(attributes.containsKey(name)){
            return false;
        }
        attributes.put(name, value);
        return true;
    }
    
    public boolean changeValue(String name, int value){
        if(attributes == null){
            return false;
        }
        Integer currValue = attributes.get(name);
        if(currValue == null){
            return false;
        }
        currValue = value;
        return true;
    }
    
    public int getValue(String name){
        if(attributes == null){
            return -1;
        }
        Integer value = attributes.get(name);
        if(value == null){
            return -1;
        }
        return value;
    }
}
