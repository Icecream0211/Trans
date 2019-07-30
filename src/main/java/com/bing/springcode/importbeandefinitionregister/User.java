package com.bing.springcode.importbeandefinitionregister;

import org.springframework.beans.factory.annotation.Autowired;

@MyAnnoation
public class User {
    private String name;

    @Autowired
    private Residence resident;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Residence getResident() {
        return resident;
    }

    public void setResident(Residence resident) {
        this.resident = resident;
    }
}
