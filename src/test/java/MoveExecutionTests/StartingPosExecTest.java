package MoveExecutionTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Game;

// The best way to see if a move was executed properly, is to compare FEN string calculated after move, with the FEN string we know should describe the position
// This however, involves many different parts of the program, and a failed test here might not mean that the executor did anything wrong.

// The focus of these tests will be to execute different moves, and checking if the game can calculate the new position correctly
public class StartingPosExecTest {

    private Game game;
    
    @BeforeEach
    public void setup() {
        game = new Game("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    @Test
    @DisplayName("Move pawn")
    void movePawn() {

        game.clicked("52");
        game.clicked("54");
        
        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", game.getFenstring());
    }

    @Test
    @DisplayName("Move knight")
    void moveKnight() {

        game.clicked("21");
        game.clicked("33");
        
        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1", game.getFenstring());
    }
}
