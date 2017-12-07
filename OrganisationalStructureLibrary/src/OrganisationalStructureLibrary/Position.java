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
    private List<Position> subordinate;
    private List<Position> staff;

    public Position() {
    }

    public List<Position> getStaff(){
        return staff;
    }
    
    public void addStaff(Position newStaff){
        if(staff == null){
            staff = new LinkedList<>();
        }
        staff.add(newStaff);
    }
        
    public List<Position> getSubordinate() {
        return subordinate;
    }
    
    public void addSubortinate(Position newSubordinate){
        if(subordinate == null){
            subordinate = new LinkedList<>();
        }
        subordinate.add(newSubordinate);
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
