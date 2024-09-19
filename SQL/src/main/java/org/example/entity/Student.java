package org.example.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Student {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "Cid",fetch = FetchType.LAZY,cascade = CascadeType.ALL) /*invers-end*/ /*fetchtype.eager*/
    private List<Address> address;

    public Student() {
    }

    public Student(int id, String name, List<Address> address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}