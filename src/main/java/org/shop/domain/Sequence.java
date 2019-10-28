package org.shop.domain;

import java.io.Serializable;

public class Sequence implements Serializable {
    private String name;
    private int nextid;

    public Sequence() {

    }

    public int getNextid() {
        return nextid;
    }

    public void setNextid(int nextid) {
        this.nextid = nextid;
    }

    public Sequence(String name, int nextid) {
        this.name = name;
        this.nextid = nextid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextId() {
        return nextid;
    }

    public void setNextId(int nextid) {
        this.nextid = nextid;
    }
}
