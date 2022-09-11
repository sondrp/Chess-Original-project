package MoveExecutionTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Game;

public class pawnQueeningTest {

    private Game game;
    
    // the position used can be viewed here :
    // https://lichess.org/analysis/fromPosition/8/k4P2/8/8/8/8/8/1K6_w_-_-_0_1
    @Test
    @DisplayName("pawn turning into a queen when reaching last row")
    void whiteQueening() {

        game = new Game("8/k4P2/8/8/8/8/8/1K6 w - - 0 1");

        game.clicked("67");
        game.clicked("68");

        Assertions.assertEquals("5Q2/k7/8/8/8/8/8/1K6 b - - 0 1", game.getFenstring());
    }

    // https://lichess.org/analysis/fromPosition/8/k4P2/8/8/8/8/7p/1K6_b_-_-_0_1
    @Test
    @DisplayName("pawn turning into a queen when reaching last row")
    void blackQueening() {

        game = new Game("8/k4P2/8/8/8/8/7p/1K6 b - - 0 1");

        game.clicked("82");
        game.clicked("81");

        Assertions.assertEquals("8/k4P2/8/8/8/8/8/1K5q w - - 0 2", game.getFenstring());
    }
}
