package com.kodigos_challenge.api.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;



    @ElementCollection
    @CollectionTable(name = "order_checklist", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "item", length = 500)
    private List<String> checklist;

    @Lob
    @Column(name = "photo_data", columnDefinition = "TEXT")
    private String photoData;

    public Order(int id, String description, List<String> checklist, String photoUrl) {
        this.id = id;
        this.description = description;
        this.checklist = checklist;
        this.photoData = photoUrl;

    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getChecklist() {
        return checklist;
    }

    public String getPhotoUrl() {
        return photoData;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setChecklist(List<String> checklist) {
        this.checklist = checklist;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoData = photoUrl;
    }
}
