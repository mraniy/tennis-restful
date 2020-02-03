package com.kata.tennis.rest;


import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.UnitScoreHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class RandomMatchRetriever {




    private PointHander pointHandler;

    @Autowired
    public RandomMatchRetriever(PointHander pointHandler) {
        this.pointHandler = pointHandler;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/random" , method = RequestMethod.GET)
    public Match retrieveRandomMatch() {

        Player federer = new Player("Federer", new ScorePlayer());
        Player nadal = new Player("Nadal", new ScorePlayer());
        Match match = new Match(federer, nadal);
        while(!match.getWinner().isPresent()) {
            generatePointAndGiveItToRandomPlayer(match);
        }
        return match;
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
