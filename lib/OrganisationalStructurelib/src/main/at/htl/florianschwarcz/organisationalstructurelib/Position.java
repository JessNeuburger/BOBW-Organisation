/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Florian Schwarcz
 */
public class Position {
    private Person person;
    private Job job;
    private List<Position> subordinates;
    private List<Staff> staff;
    private Position superordinate;

    public Position() {
    }
    public Position(Person person){
        this.person = person;
    }
    public Position(Job job){
        this.job = job;
    }
    public Position(Person person, Job job){
        this.person = person;
        this.job = job;
    }

    public Position getSuperordinate(){
        return superordinate;
    }

    public void setSuperordinate(Position superordinate){
        this.superordinate = superordinate;
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
        staff.setSuperordinate(this);
        this.staff.add(staff);
    }

    /**
     * Adds a list of staff to the list.
     * @param staff
     */
    public void addStaff(List<Staff> staff){
        if(this.staff == null){
            this.staff = new LinkedList<>();
        }
        staff.forEach(s -> s.setSuperordinate(this));
        this.staff.addAll(staff);
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
        subordinate.setSuperordinate(this);
        subordinates.add(subordinate);
    }

    /**
     * Adds a list of subordinates to the list.
     * @param subordinates
     */
    public void addSubordinates(List<Position> subordinates){
        if(subordinates == null){
            subordinates = new LinkedList<>();
        }
        subordinates.forEach(s -> s.setSuperordinate(this));
        this.subordinates.addAll(subordinates);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return Objects.equals(getPerson(), position.getPerson()) &&
                Objects.equals(getJob(), position.getJob()) &&
                Objects.equals(getSuperordinate(), position.getSuperordinate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPerson(), getJob(), getSuperordinate());
    }
}
