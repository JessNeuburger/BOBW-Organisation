/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Florian Schwarcz
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
    public List<Position> getAllSubordinatePositions(){
        if(subordinates == null){
            return new LinkedList<Position>();
        }
        List<Position> allSubordinatePositions = new LinkedList<>();
        for(Position subordinate : subordinates){
            allSubordinatePositions.add(subordinate);
            allSubordinatePositions.addAll(subordinate.getAllSubordinatePositions());
        }
        return allSubordinatePositions;
    }

    public List<Person> getAllSubordinatePersons(){
        if(subordinates == null){
            return new LinkedList<Person>();
        }
        List<Person> allSubordinatePersons = new LinkedList<>();
        for(Position subordinate : subordinates){
            allSubordinatePersons.add(subordinate.getPerson());
            allSubordinatePersons.addAll(subordinate.getAllSubordinatePersons());
        }
        return allSubordinatePersons;
    }

    /**
     * Adds a subordinate to the list.
     * @param subordinate
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
