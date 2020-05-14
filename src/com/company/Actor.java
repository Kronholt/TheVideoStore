package com.company;

public class Actor {

    private int actor_id;
    private String stage_name, fname, lname, birth_date;

    public Actor() {
    }

    public Actor(int actor_id, String stage_name, String fname, String lname, String birth_date) {
        this.actor_id = actor_id;
        this.stage_name = stage_name;
        this.fname = fname;
        this.lname = lname;
        this.birth_date = birth_date;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }



}
