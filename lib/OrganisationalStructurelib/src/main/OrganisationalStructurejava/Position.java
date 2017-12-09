/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganisationalStructureLibrary;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author flori
 */
public class Position {
    private Person person;
    private Job job;
    private List<Position> subordinates;
    private List<Position> staff;

    public Position() {
    }

    public List<Position> getStaff(){
        return staff;
    }

    /**
     * Adds a staff to the list.
     * @param newStaff
     */
    public void addStaff(Position newStaff){
        if(staff == null){
            staff = new LinkedList<>();
        }
        staff.add(newStaff);
    }

    public List<Position> getSubordinates() {
        return subordinates;
    }

    /**
     * Returns all subordinates and subordinates from subordinates.
     * @return List of all subordinates
     */
    public List<Position> getAllSubordinates(){
        if(subordinates == null){
            return new LinkedList<Position>();
        }
        List<Position> allSubordinates = new LinkedList<>();
        for(Position subordinate : subordinates){
            addSubortinates.add(subordinate);
            allSubordinates.addAll(subordinate.getAllSubordinates());
        }
        return allSubordinates;
    }

    /**
     * Adds a subordinate to the list.
     * @param newSubordinate
     */
    public void addSubortinate(Position subordinate){
        if(subordinates == null){
            subordinates = new LinkedList<>();
        }
        subordinates.add(subordinate);
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Job getJob(){
        return job;
    }

    public void setJob(Job job){
        this.job = job;
    }
}
