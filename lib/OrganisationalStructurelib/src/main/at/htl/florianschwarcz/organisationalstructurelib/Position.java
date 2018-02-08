/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Florian Schwarcz
 */
public class Position {
    private ObjectProperty<Person> person;
    private ObjectProperty<Job> job;
    private ObservableList<Position> subordinates;
    private ObservableList<Staff> staff;
    private ObjectProperty<Position> superordinate;

    public Position() {
        person = new SimpleObjectProperty<>();
        job = new SimpleObjectProperty<>();
        subordinates = FXCollections.observableArrayList();
        staff = FXCollections.observableArrayList();
        superordinate = new SimpleObjectProperty<>();
    }
    public Position(Person person){
        this();
        setPerson(person);
    }
    public Position(Job job){
        this();
        setJob(job);
    }
    public Position(Person person, Job job){
        this();
        setPerson(person);
        setJob(job);
    }

    public Position getSuperordinate(){
        return superordinate.get();
    }

    public void setSuperordinate(Position superordinate){
        this.superordinate.set(superordinate);
    }

    public ObjectProperty<Position> superordinateProperty() {
        return superordinate;
    }

    public ObservableList<Staff> getStaff(){
        return staff;
    }

    /**
     * Adds a staff to the list.
     * @param staff
     */
    public void addStaff(Staff staff){
        staff.setSuperordinate(this);
        this.getStaff().add(staff);
    }

    /**
     * Adds a list of staff to the list.
     * @param staff
     */
    public void addStaff(List<Staff> staff){
        staff.forEach(s -> s.setSuperordinate(this));
        this.getStaff().addAll(staff);
    }

    public List<Position> getSubordinates() {
        return subordinates;
    }

    /**
     * Returns all subordinates and subordinates from subordinates.
     * @return List of all subordinates
     */
    public List<Position> getAllSubordinatePositions(){
        if(getSubordinates().size() == 0){
            return new LinkedList<>();
        }
        List<Position> allSubordinatePositions = new LinkedList<>();
        for(Position subordinate : getSubordinates()){
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
        if(getSubordinates().size() == 0){
            return new LinkedList<>();
        }
        List<Person> allSubordinatePersons = new LinkedList<>();
        for(Position subordinate : getSubordinates()){
            if(subordinate.getPerson() != null){
                allSubordinatePersons.add(subordinate.getPerson());
            }
            allSubordinatePersons.addAll(subordinate.getAllSubordinatePersons());
        }
        return allSubordinatePersons;
    }

    public String getAllSubordinateTables(int level){
        if(getSubordinates() == null){
            return "";
        }
        String table = "";
        for(Position position : getSubordinates()){
            table += level + " " + position.getTableLine() + position.getAllSubordinateTables(level + 1);
        }
        return table;
    }

    public String getTableLine(){
        if(getPerson() == null){
            return "Frei\n";
        }
        return getPerson().getLastName() + '\n';
    }

    /**
     * Adds a subordinate to the list.
     * @param subordinate
     */
    public void addSubordinate(Position subordinate){
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
        return person.get();
    }
    public void setPerson(Person person) {
        if(this.person.getValue()!=null)this.person.getValue().setPosition(null);
        this.person.set(person);
        if(person!=null){
            person.setPosition(this);
        }
    }

    public ObjectProperty<Person> personProperty() {
        return person;
    }

    public Job getJob(){
        return job.get();
    }
    public void setJob(Job job){
        this.job.set(job);
    }

    public ObjectProperty<Job> jobProperty() {
        return job;
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
