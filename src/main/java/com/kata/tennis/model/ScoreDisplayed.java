package com.kata.tennis.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@Data
@NoArgsConstructor
public class ScoreDisplayed {

    public ScoreDisplayed deepCopy() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(objectMapper.writeValueAsString(this), ScoreDisplayed.class);
    }


    private LinkedList<GamesAndMaybeTieBreakPoints> gamesWonByPlayer1;

    private LinkedList<GamesAndMaybeTieBreakPoints> gamesWonByPlayer2;

    private LinkedList<Integer> setsWonByPlayer1;

    private LinkedList<Integer> setsWonByPlayer2;

    private String pointsWonByPlayer1;

    private String pointsWonByPlayer2;


}
