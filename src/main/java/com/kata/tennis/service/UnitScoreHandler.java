package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
@Component
public abstract class UnitScoreHandler {

    protected Optional<UnitScoreHandler> next;

    public abstract void refreshScore(Match match, Player player);

    public void proceed(Match match, Player pointWinner) {
        refreshScore(match, pointWinner);
        next.ifPresent(unitScoreHandler -> unitScoreHandler.proceed(match,pointWinner));
    }
}
