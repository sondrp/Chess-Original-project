package MoveExecutionTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Game;

public class CastleExecTest {

    private Game game;

    // the position used can be viewed here :
    // https://lichess.org/analysis/standard/r3k2r/8/8/8/8/8/8/R3K2R_w_KQkq_-_0_1
    @BeforeEach
    public void setup() {
        game = new Game("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1");
    }

    @Test
    void whiteCastleKingside() {

        game.clicked("51");
        game.clicked("71");

        Assertions.assertEquals("r3k2r/8/8/8/8/8/8/R4RK1 b kq - 1 1", game.getFenstring());
    }

    @Test
    void whiteCastleQueenside() {

        game.clicked("51");
        game.clicked("31");

        Assertions.assertEquals("r3k2r/8/8/8/8/8/8/2KR3R b kq - 1 1", game.getFenstring());
    }

    @Test
    @DisplayName("moving white king up, then black castle")
    void blackCastleKingside() {

        // moving white king
        game.clicked("51");
        game.clicked("52");

        Assertions.assertEquals("r3k2r/8/8/8/8/8/4K3/R6R b kq - 1 1", game.getFenstring());
        
        // black castle
        game.clicked("58");
        game.clicked("78");
        
        Assertions.assertEquals("r4rk1/8/8/8/8/8/4K3/R6R w - - 2 2", game.getFenstring());
    }

    @Test
    @DisplayName("moving white king up, then black castle")
    void blackCastleQueenside() {

        // moving white king
        game.clicked("51");
        game.clicked("52");

        Assertions.assertEquals("r3k2r/8/8/8/8/8/4K3/R6R b kq - 1 1", game.getFenstring());
        
        // black castle
        game.clicked("58");
        game.clicked("38");
        
        Assertions.assertEquals("2kr3r/8/8/8/8/8/4K3/R6R w - - 2 2", game.getFenstring());
    }
    
}
