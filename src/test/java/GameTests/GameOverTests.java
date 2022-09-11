package GameTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Game;
import chess.GameFamily.GameOverException;

public class GameOverTests {
    
    // the position used can be viewed here :
    // https://lichess.org/analysis/standard/2B1k3/2B1pnb1/5pQp/1pp5/2r1P2P/8/8/3R1R1K_w_-_-_0_1#0
    @Test
    @DisplayName("Mate in one (using a pin)")
    void mateInOne() {

        Game game = new Game("2B1k3/2B1pnb1/5pQp/1pp5/2r1P2P/8/8/3R1R1K w - - 0 1");

        game.clicked("41");
        Assertions.assertThrows(GameOverException.class, () -> game.clicked("48"));
    }


    // https://lichess.org/analysis/standard/2B1k3/2B1pnb1/5pQp/1pp5/2r1P2P/8/8/3R1R1K_w_-_-_0_1#0
    @Test
    @DisplayName("piece can block")
    void notMateInOne() {

        Game game = new Game("2rkr2b/2p1p3/8/8/8/8/K7/7R w - - 0 1");

        game.clicked("81");
        game.clicked("41");
        // nothing was thrown
    }


    // 3R2B1/8/3nr3/R1nk4/2r5/5P2/B7/4Q2K w - - 0 1
    @Test
    @DisplayName("Mate in one (with even more pins)")
    void mateInOne2() {

        Game game = new Game("3R2B1/8/3nr3/R1nk4/2r5/5P2/B7/4Q2K w - - 0 1");

        game.clicked("51");
        Assertions.assertThrows(GameOverException.class, () -> game.clicked("54"));
    }


    // https://lichess.org/analysis/standard/K5QR/8/7b/2p5/p1N5/P7/R7/7k_w_-_-_0_1#0
    @Test
    @DisplayName("no legal moves for black but not in check = stalemate")
    void stalemate() {

        Game game = new Game("K5QR/8/7b/2p5/p1N5/P7/R7/7k w - - 0 1");

        game.clicked("12");
        Assertions.assertThrows(GameOverException.class, () -> game.clicked("22"));
    }


    // https://lichess.org/analysis/fromPosition/1k6/8/2n5/8/8/5N2/8/1K6_w_-_-_49_49#96
    @Test
    @DisplayName("fifty move rule")
    void gameover() {

        Game game = new Game("1k6/8/2n5/8/8/5N2/8/1K6 w - - 49 49");

        game.clicked("63");
        Assertions.assertThrows(GameOverException.class, () -> game.clicked("71"));
    }
}
