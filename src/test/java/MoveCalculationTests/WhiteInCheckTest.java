package MoveCalculationTests;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;
import chess.MoveFamily.MoveCalculator;

// analysing the potential moves for every piece, in a position where white is in check
// the position used can be viewed here :
// https://lichess.org/analysis/standard/3K2bk/1bP3pp/8/R7/Q3N1B1/8/8/3r4_w_-_-_0_1

public class WhiteInCheckTest {
    
    @Test
    @DisplayName("Pieces can block or capture the rook")
    void avoidCheck() {
        Board board = new Board("3K2bk/1bP3pp/8/R7/Q3N1B1/8/8/3r4", true);
        MoveCalculator moveCalc = new MoveCalculator(board);

        // King : can move out of the check (2/4 moves are legal)
        Assertions.assertEquals( Arrays.asList("57", "58"), moveCalc.calculatePotMoves("48"));

        // Rook : can block the check on one square
        Assertions.assertEquals( Arrays.asList("45"), moveCalc.calculatePotMoves("15"));

        // knight : can block the check two ways
        Assertions.assertEquals( Arrays.asList("42", "46"), moveCalc.calculatePotMoves("54"));

        // bishop : can block or capture rook
        Assertions.assertEquals( Arrays.asList("41", "47"), moveCalc.calculatePotMoves("74"));

        // queen : can block two ways, or capture rook
        Assertions.assertEquals( Arrays.asList("47", "44", "41"), moveCalc.calculatePotMoves("14"));

        // pawn : no legal moves
        Assertions.assertEquals( new ArrayList<>(), moveCalc.calculatePotMoves("37"));
    }
}
