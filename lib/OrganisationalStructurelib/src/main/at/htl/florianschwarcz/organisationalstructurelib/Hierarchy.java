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
public class Hierarchy {
    private static Hierarchy instance;
    public static Hierarchy getInstance(){
        if(instance == null){
            instance = new Hierarchy();
        }
        return instance;
    }

    private Position head;

    public Hierarchy() {
    }
    
    public Position getHead() {
        return head;
    }

    public void setHead(Position head) {
        this.head = head;
    }

    /**
     * Creates a list of all positions in the hierarchy.
     * @return
     */
    public List<Position> positionList(){
        if(head == null){
            return new LinkedList<>();
        }
        List<Position> positionList = new LinkedList<>();
        positionList.add(head);
        positionList.addAll(head.getAllSubordinatePositions());
        return positionList;
    }

    /**
     * Creates a list of all persons in the hierarchy.
     * @return
     */
    public List<Person> personList(){
        if(head == null){
            return new LinkedList<>();
        }
        List<Person> personList = new LinkedList<>();
        if(head.getPerson() != null) {
            personList.add(head.getPerson());
        }
        personList.addAll(head.getAllSubordinatePersons());
        return personList;
    }

    /**
     * Returns the person which profile matches
     * the given one best.
     * @param profile
     * @return
     */
    public Person getBestPerson(Profile profile){
        Person bestPerson = null;
        int bestPoints = 0;
        for(Person person : personList()){
            if(bestPerson == null || Profile.compareProfiles(person.getProfile(), profile) > bestPoints) {
                bestPoints = Profile.compareProfiles(person.getProfile(), profile);
                bestPerson = person;
            }
        }
        return bestPerson;
    }

    /**
     * Returns the Job which profile matches
     * the given one best.
     * @param profile
     * @return
     */
    public Job getBestJob(Profile profile){
        Job bestJob = null;
        boolean bestJobIsAllLower = false;
        int bestPoints = 0;
        for(Position position : positionList()){
            if(bestJob == null || Profile.compareProfiles(profile, position.getJob().getProfile()) > bestPoints) {
                bestPoints = Profile.compareProfiles(position.getJob().getProfile(), profile);
                if(!(bestJobIsAllLower && Profile.compareProfiles(position.getJob().getProfile(), bestJob.getProfile()) > 0)) {
                    bestJob = position.getJob();
                }
                if(Profile.compareAllHigherOrEqual(profile, bestJob.getProfile()) == 1){
                    bestJobIsAllLower = true;
                }
            }
        }
        return bestJob;
    }
}
