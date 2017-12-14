/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import java.util.HashMap;

/**
 *
 * @author Florian Schwarcz
 */
public class Profile {
    private HashMap<String, Integer> attributes;
    
    public Profile(){
    }

    public HashMap<String, Integer> getAttributes(){
        return attributes;
    }

    /**
     * Adds a new attribute to the list.
     * @param name
     * @param value
     * @return True if successfully added, false if already in list.
     */
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

    /**
     * Changes the value of an existing attribute.
     * @param name
     * @param value
     * @return True if successfully changed, false if not in list.
     */
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

    /**
     * Returns the value of an existing attribute.
     * @param name
     * @return Value of the attribute, -1 if not in list.
     */
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

    /**
     * Compares two profiles using the difference of
     * all attributes.
     * @param p1
     * @param p2
     * @return
     */
    public static int compareProfiles(Profile p1, Profile p2){
        int score = 0;
        for(String key : p1.getAttributes().keySet()){
            if(p2.getValue(key) != -1){
                score += p1.getValue(key) - p2.getValue(key);
            }
        }
        return score;
    }
}
