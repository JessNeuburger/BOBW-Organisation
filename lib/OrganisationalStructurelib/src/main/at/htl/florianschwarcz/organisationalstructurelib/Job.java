/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Florian Schwarcz
 */
public class Job{
    private SimpleStringProperty name;
    private SimpleObjectProperty<Profile> profile;
    
    public Job(String name, Profile profile){
        this.name = new SimpleStringProperty(name);
        this.profile = new SimpleObjectProperty<>(profile);
    }

    public Job() {
    }

    public SimpleObjectProperty<Profile> profileProperty() {
        return profile;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.getName();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Profile getProfile() {
        return profile.get();
    }

    public void setProfile(Profile profile) {
        this.profile.set(profile);
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
