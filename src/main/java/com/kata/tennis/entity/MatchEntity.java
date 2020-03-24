package com.kata.tennis.entity;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class MatchEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String player1;

    @Property
    private String player2;

    @Property
    @Lob
    private String matchContent;
}
