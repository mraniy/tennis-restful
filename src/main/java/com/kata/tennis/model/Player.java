package com.kata.tennis.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {

    private String name;

    private ScorePlayer scorePlayer;


}
