package com.kata.tennis.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.tennis.entity.MatchEntity;
import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScoreDisplayed;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.repository.MatchRepository;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.ScoreDisplayHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RandomMatchRetriever {

    private PointHander pointHandler;

    @Autowired
    private ScoreDisplayHandler scoreDisplayHandler;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    public RandomMatchRetriever(PointHander pointHandler) {
        this.pointHandler = pointHandler;
    }


    private Match getMatch() {
        Player somePlayer1 = new Player("", new ScorePlayer(),true);
        Player somePlayer2 = new Player("", new ScorePlayer(),false);
        return new Match(somePlayer1, somePlayer2);
    }

    @RequestMapping(value = "/randomWithHistory" , method = RequestMethod.GET)
    public List<ScoreDisplayed> retrieveRandomMatchWithItsHistory(@RequestParam("player1") String player1, @RequestParam("player2") String player2) throws JsonProcessingException {
        List<ScoreDisplayed> scoreDisplayeds = new ArrayList<>();
        Match match = getMatch();
        while(!match.getWinner().isPresent()) {
            generatePointAndGiveItToRandomPlayer(match);
            ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
            scoreDisplayeds.add(scoreDisplayed.deepCopy());
        }
        saveMatch(scoreDisplayeds, player1,player2);
        return scoreDisplayeds;

    }

    private void saveMatch(List<ScoreDisplayed> scoreDisplayeds, String player1, String player2) throws JsonProcessingException {
        MatchEntity matchEntity = new MatchEntity();
        ScoreDisplayed last = scoreDisplayeds.get(scoreDisplayeds.size()-1);
        ObjectMapper objectMapper = new ObjectMapper();
        String scoreFinal = objectMapper.writeValueAsString(last);
        matchEntity.setMatchContent(scoreFinal);
        matchEntity.setPlayer1(player1);
        matchEntity.setPlayer2(player2);
        matchRepository.save(matchEntity);
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
