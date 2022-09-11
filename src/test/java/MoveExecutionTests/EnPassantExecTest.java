package MoveExecutionTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Game;

public class EnPassantExecTest {

    private Game game;

    // the position used can be viewed here :
    // https://lichess.org/analysis/fromPosition/k7/8/8/4pP2/8/2K5/8/8_w_-_e6_0_1
    @Test
    @DisplayName("Manually setting up en passant (in FEN), then testing capture")
    void manualEnPassant() {
        
        game = new Game("k7/8/8/4pP2/8/2K5/8/8 w - e6 0 1");

        game.clicked("65");
        game.clicked("56");

        Assertions.assertEquals("k7/8/4P3/8/8/2K5/8/8 b - - 0 1", game.getFenstring());
    }

    // https://lichess.org/analysis/fromPosition/k7/4p3/8/5P2/8/2K5/8/8_b_-_-_0_1
    @Test
    @DisplayName("Moving the pawn in position to be captured en passant")
    void autoEnPassant() {
        
        game = new Game("k7/4p3/8/5P2/8/2K5/8/8 b - - 0 1");

        // moving black pawn
        game.clicked("57");
        game.clicked("55");

        Assertions.assertEquals("k7/8/8/4pP2/8/2K5/8/8 w - e6 0 2", game.getFenstring());

        // capturing en passant
        game.clicked("65");
        game.clicked("56");

        Assertions.assertEquals("k7/8/4P3/8/8/2K5/8/8 b - - 0 2", game.getFenstring());
    }
    
}




