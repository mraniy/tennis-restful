package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.TieBreakHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.jupiter.api.Test;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestTieBreakHandler {



    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_without_advantage() {
        // given
        int numberPointsOfGameWonByFederer = 3;
        int numberPointsOfGameWonByNadal = 7;
        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByFederer, 1, 6);
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByNadal, 1, 6);
        Player federer = new Player("Federer",scoreFederer,true);
        Player nadal = new Player("Nadal",scoreNadal,false);

        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        gameHandler.refreshScore(match, nadal);
        // then
        assertThat(match.getPlayer2().getScorePlayer().getNumberGamesWonByPlayerBySet().get(0).getGames(), is(7));
        assertThat(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getPlayer2().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getPlayer1().isServe(), is(false));
        assertThat(match.getPlayer2().isServe(), is(true));
    }

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_with_advantage() {
        // given
        int numberPointsOfGameWonByPlayer = 10;
        int numberPointsOfGameWonByPlayer1 = 8;
        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByPlayer, 1, 6);
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByPlayer1, 1, 6);
        Player federer = new Player("Federer",scoreFederer,false);
        Player nadal = new Player("Nadal",scoreNadal,true);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        gameHandler.refreshScore(match, federer);
        // then
        assertThat(match.getPlayer1().getScorePlayer().getNumberGamesWonByPlayerBySet().get(0).getGames(), is(7));
        assertThat(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getPlayer2().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
    }

    /**
     * players swicth serve when the sum of the points between the two players is impair 1-0 3-2 7-6
     */
    @Test
    public void should_switch_serve_when_they_play_an_impair_point_of_the_tie_break() {
        // given
        int numberPointsOfGameWonByPlayer1 = 3;
        int numberPointsOfGameWonByPlayer2 = 2;
        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByPlayer1, 1, 6);
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByPlayer2, 1, 6);
        Player federer = new Player("Federer",scoreFederer,false);
        Player nadal = new Player("Nadal",scoreNadal,true);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        gameHandler.refreshScore(match, federer);
        // then
        assertThat(match.getPlayer1().isServe(), is(true));
        assertThat(match.getPlayer2().isServe(), is(false));
    }


}
