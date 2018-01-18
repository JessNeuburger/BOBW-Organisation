/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Florian Schwarcz
 */
public class Job{
    private String name;
    private Profile profile;
    
    public Job(String name, Profile profile){
        this.name = name;
        this.profile = profile;
    }

    public Job() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job job = (Job) o;
        return Objects.equals(getName(), job.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName());
    }
}
