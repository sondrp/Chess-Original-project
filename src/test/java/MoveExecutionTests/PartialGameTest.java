package MoveExecutionTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Game;

public class PartialGameTest {
    
    // testing if the start of a game can run without mistakes
    // the game used can be viewed here :
    // https://lichess.org/study/agESaWbF/cScPsrC4
    @Test
    @DisplayName("Immortal game")
    void testFullGame() {
        
        Game game = new Game();

        game.clicked("52");
        game.clicked("54");

        game.clicked("57");
        game.clicked("55");

        game.clicked("62");
        game.clicked("64");
        
        game.clicked("55");
        game.clicked("64");

        Assertions.assertEquals("rnbqkbnr/pppp1ppp/8/8/4Pp2/8/PPPP2PP/RNBQKBNR w KQkq - 0 3", game.getFenstring());

        game.clicked("61");
        game.clicked("34");

        game.clicked("48");
        game.clicked("84");

        game.clicked("51");
        game.clicked("61");
        
        game.clicked("27");
        game.clicked("25");

        Assertions.assertEquals("rnb1kbnr/p1pp1ppp/8/1p6/2B1Pp1q/8/PPPP2PP/RNBQ1KNR w kq b6 0 5", game.getFenstring());

        game.clicked("34");
        game.clicked("25");

        game.clicked("78");
        game.clicked("66");

        game.clicked("71");
        game.clicked("63");
        
        game.clicked("84");
        game.clicked("86");

        Assertions.assertEquals("rnb1kb1r/p1pp1ppp/5n1q/1B6/4Pp2/5N2/PPPP2PP/RNBQ1K1R w kq - 3 7", game.getFenstring());

        game.clicked("42");
        game.clicked("43");

        game.clicked("66");
        game.clicked("85");

        game.clicked("63");
        game.clicked("84");
        
        game.clicked("86");
        game.clicked("75");

        Assertions.assertEquals("rnb1kb1r/p1pp1ppp/8/1B4qn/4Pp1N/3P4/PPP3PP/RNBQ1K1R w kq - 3 9", game.getFenstring());
    }
}
