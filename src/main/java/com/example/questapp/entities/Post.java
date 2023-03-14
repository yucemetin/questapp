package com.example.questapp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // user silindiği zaman onun bütün postları da silinir.
    private User user;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String text;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<Like> likes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date createdOn;

    @Column
    private Date lastUpdatedOn;

    @PrePersist
    private void onCreate() {
        createdOn = new Date();
        lastUpdatedOn = createdOn;
    }

    @PreUpdate
    public void onUpdate() {
        lastUpdatedOn = new Date();
    }

}
