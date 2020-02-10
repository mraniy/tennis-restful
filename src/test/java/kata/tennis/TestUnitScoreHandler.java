package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.MatchHandler;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class TestUnitScoreHandler {

    UnitScoreHandler pointHandler = new PointHander();

    @BeforeEach
    public void setUp() {
        MatchHandler.NUMBER_OF_SETS_TO_WIN_THE_TIE = 2;
    }

    @Test
    public void should_determine_match_winner_correctly() {
        // given
        ScorePlayer scoreFederer = aScore(3, 1, 5);
        ScorePlayer scoreNadal = aScore(2, 1, 4);

        Player federer = new Player("Federer",scoreFederer,true);
        Player nadal = new Player("Nadal",scoreNadal,false);

        Match match = new Match(federer, nadal);
        // when

        pointHandler.proceed(match, federer);
        // then
        assertThat(match.getWinner() , is(Optional.of("Federer")));
    }

    @Test
    public void should_determine_set_winner_correctly() {
        // given
        ScorePlayer scoreFederer = aScore(7, 0, 4);
        ScorePlayer scoreNadal = aScore(8, 0, 5);
        Player federer = new Player("Federer",scoreFederer,true);
        Player nadal = new Player("Nadal",scoreNadal,true);
        Match match = new Match(federer, nadal);
        // when

        pointHandler.proceed(match, nadal);
        // then
        assertThat(match.getPlayer2().getScorePlayer().getNumberSetWonByPlayer() , is(1));
    }
}
