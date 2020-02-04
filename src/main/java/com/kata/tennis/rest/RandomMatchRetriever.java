package com.kata.tennis.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScoreDisplayed;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.ScoreDisplayHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RandomMatchRetriever {




    private PointHander pointHandler;

    @Autowired
    private ScoreDisplayHandler scoreDisplayHandler;
    @Autowired
    public RandomMatchRetriever(PointHander pointHandler) {
        this.pointHandler = pointHandler;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/random" , method = RequestMethod.GET)
    public Match retrieveRandomMatch() {

        Match match = getMatch();
        while(!match.getWinner().isPresent()) {
            generatePointAndGiveItToRandomPlayer(match);
        }
        return match;
    }

    private Match getMatch() {
        Player federer = new Player("Federer", new ScorePlayer());
        Player nadal = new Player("Nadal", new ScorePlayer());
        return new Match(federer, nadal);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/randomWithHistory" , method = RequestMethod.GET)
    public List<ScoreDisplayed> retrieveRandomMatchWithItsHistory() throws JsonProcessingException {
        List<ScoreDisplayed> scoreDisplayeds = new ArrayList<>();
        Match match = getMatch();
        while(!match.getWinner().isPresent()) {
            generatePointAndGiveItToRandomPlayer(match);
            ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
            scoreDisplayeds.add(scoreDisplayed.deepCopy());
        }
        return scoreDisplayeds;

    }




    private  void generatePointAndGiveItToRandomPlayer(Match match) {
        long randomwinner = Math.round(Math.random());
        switch (String.valueOf(randomwinner)) {
            case "0":
                pointHandler.proceed(match, match.getPlayer1());
                break;
            default:
                pointHandler.proceed(match, match.getPlayer2());
                break;
        }
    }
}
