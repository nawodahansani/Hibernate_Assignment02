
package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

    @Id
    private int Aid;
    @ManyToOne /*owners-end*/@JoinColumn(name = "king")
    private Student Cid;
    private String address1;
    private String address2;

    public Address() {
    }

    public Address(int aid, Student cid, String address1, String address2) {
        Aid = aid;
        Cid = cid;
        this.address1 = address1;
        this.address2 = address2;
    }

    public int getAid() {
        return Aid;
    }

    public void setAid(int aid) {
        Aid = aid;
    }

    public Student getCid() {
        return Cid;
    }

    public void setCid(Student cid) {
        Cid = cid;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
