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
    private List<Staff> staff;

    public Position() {
    }

    public List<Staff> getStaff(){
        return staff;
    }

    /**
     * Adds a staff to the list.
     * @param staff
     */
    public void addStaff(Staff staff){
        if(this.staff == null){
            this.staff = new LinkedList<>();
        }
        this.staff.add(staff);
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
            return new LinkedList<>();
        }
        List<Position> allSubordinatePositions = new LinkedList<>();
        for(Position subordinate : subordinates){
            allSubordinatePositions.add(subordinate);
            allSubordinatePositions.addAll(subordinate.getAllSubordinatePositions());
        }
        return allSubordinatePositions;
    }

    /**
     * Returns all subordinate persons and subordinate persons
     * from subordinates.
     * @return List of all subordinate persons
     */
    public List<Person> getAllSubordinatePersons(){
        if(subordinates == null){
            return new LinkedList<>();
        }
        List<Person> allSubordinatePersons = new LinkedList<>();
        for(Position subordinate : subordinates){
            if(subordinate.getPerson() != null){
                allSubordinatePersons.add(subordinate.getPerson());
            }
            allSubordinatePersons.addAll(subordinate.getAllSubordinatePersons());
        }
        return allSubordinatePersons;
    }

    public String getAllSubordinateTables(int level){
        if(subordinates == null){
            return "";
        }
        String table = "";
        for(Position position : subordinates){
            table += level + " " + position.getTableLine() + position.getAllSubordinateTables(level + 1);
        }
        return table;
    }

    public String getTableLine(){
        if(person == null){
            return "Frei\n";
        }
        return person.getLastName() + '\n';
    }

    /**
     * Adds a subordinate to the list.
     * @param subordinate
     */
    public void addSubordinate(Position subordinate){
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
