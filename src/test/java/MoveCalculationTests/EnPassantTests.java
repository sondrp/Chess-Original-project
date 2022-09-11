package MoveCalculationTests;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;
import chess.MoveFamily.MoveCalculator;

// analysing situations regarding en passant
// a new position will be tested for each test. A link to the tested position is provided
// Setting the en passant square is handled by other parts of the program, and will have to be done manually here
public class EnPassantTests {
    
    
    // the position used can be viewed here :
    // https://lichess.org/analysis/standard/1k6/8/8/4pP2/2K5/8/8/8_w_-_-_0_1
    @Test
    @DisplayName("En Passant before/after making it legal")
    void forwardOnly() {
        Board board = new Board("1k6/8/8/4pP2/2K5/8/8/8", true);
        board.setEnPassantSquare("-");
        MoveCalculator moveCalc = new MoveCalculator(board);

        Assertions.assertEquals(Arrays.asList("66"), moveCalc.calculatePotMoves("65"));
        
        // now setting en passant to the square behind the pawn (as if it moved two forward)
        board.setEnPassantSquare("56");

        Assertions.assertEquals(Arrays.asList("56", "66"), moveCalc.calculatePotMoves("65"));
    }

    // https://lichess.org/analysis/fromPosition/8/8/8/2k5/3Pp3/8/7K/8_w_-_-_0_1
    @Test
    @DisplayName("King in check by pawn, and en passant should be legal to avoid it")
    void enPassantToStopCheck() {

        Board board = new Board("8/8/8/2k5/3Pp3/8/7K/8", false);
        board.setEnPassantSquare("43");
        MoveCalculator moveCalc = new MoveCalculator(board);

        Assertions.assertEquals(Arrays.asList("43"), moveCalc.calculatePotMoves("54"));
    }

    // https://lichess.org/analysis/fromPosition/k7/8/8/r2pP2K/8/8/8/8_w_-_-_0_1
    @Test
    @DisplayName("En Passant should not be legal, because then both pawns would be removed, and king left in check")
    void enPassantIllegal() {

        Board board = new Board("k7/8/8/r2pP2K/8/8/8/8", true);
        board.setEnPassantSquare("46");
        MoveCalculator moveCalc = new MoveCalculator(board);

        Assertions.assertEquals(Arrays.asList("56"), moveCalc.calculatePotMoves("55"));
    }
}
