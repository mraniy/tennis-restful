package com.kata.tennis.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private String name;

    private ScorePlayer scorePlayer;

    private boolean serve;


}
