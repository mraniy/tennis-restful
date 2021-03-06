package com.kata.tennis.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Optional;


@Data
@AllArgsConstructor
public class ScorePlayer {



    private int numberPointsOfGameWonByPlayer;
    private LinkedList<GamesAndMaybeTieBreakPoints> numberGamesWonByPlayerBySet;

    private int numberSetWonByPlayer;

    public ScorePlayer() {
        numberPointsOfGameWonByPlayer = 0;
        numberGamesWonByPlayerBySet = new LinkedList<>();
        GamesAndMaybeTieBreakPoints gamesAndMaybeTieBreakPoints = new GamesAndMaybeTieBreakPoints(0, Optional.empty());
        numberGamesWonByPlayerBySet.add(gamesAndMaybeTieBreakPoints);
        numberSetWonByPlayer = 0;
    }

    public Integer getNumberOfGamesWonsByPlayerForCurrentSet(int currentSet) {
        return numberGamesWonByPlayerBySet.get(currentSet-1).getGames();
    }

}
