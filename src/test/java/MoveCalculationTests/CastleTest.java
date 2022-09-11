package MoveCalculationTests;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;
import chess.MoveFamily.MoveCalculator;

// analysing situations regarding castle
// a new position will be tested for each test. A link to the tested position is provided
// Setting the castle squares is handled by other parts of the program, and will have to be done manually here
public class CastleTest {
    

    // the position used can be viewed here :
    // https://lichess.org/analysis/fromPosition/rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK2R_w_KQkq_-_0_1
    @Test
    @DisplayName("Castle before/after set to illegal")
    void castleFirst() {
        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK2R", true);
        board.setCastleCoords(new String[]{"71", "31", "78", "38"});
        MoveCalculator moveCalc = new MoveCalculator(board);

        Assertions.assertEquals(Arrays.asList("61", "71"), moveCalc.calculatePotMoves("51"));
        
        board.makeCastleIllegal();
        
        Assertions.assertEquals(Arrays.asList("61"), moveCalc.calculatePotMoves("51"));
    }
    

    // https://lichess.org/analysis/fromPosition/rnbqkbnr/pppppppp/8/8/3Q4/1NB5/PPPPPPPP/R3K2R_w_KQkq_-_0_1
    @Test
    @DisplayName("Castle only kingside, because queenside disallowed")
    void kingSideOnly() {
        Board board = new Board("rnbqkbnr/pppppppp/8/8/3Q4/1NB5/PPPPPPPP/R3K2R", true);
        board.setCastleCoords(new String[]{"71", null, "78", "38"});
        MoveCalculator moveCalc = new MoveCalculator(board);
        
        
        Assertions.assertEquals(Arrays.asList("41", "61", "71"), moveCalc.calculatePotMoves("51"));
    }
    
    // https://lichess.org/analysis/fromPosition/r3k2r/8/8/8/1b6/8/8/R3K2R_w_-_-_0_1
    @Test
    @DisplayName("Castle illegal because king in check")
    void noCastle() {
        Board board = new Board("r3k2r/8/8/8/1b6/8/8/R3K2R", true);
        board.setCastleCoords(new String[]{"71", "31", "78", "38"});
        MoveCalculator moveCalc = new MoveCalculator(board);
        
        
        Assertions.assertEquals(Arrays.asList("41", "52", "61", "62"), moveCalc.calculatePotMoves("51"));
    }

    // https://lichess.org/analysis/fromPosition/r3k2r/8/8/8/2b5/8/3P1P2/R3K2R_w_-_-_0_1
    @Test
    @DisplayName("Castle only queenside, because bishop cover kingsidecastle")
    void noCastle2() {
        Board board = new Board("r3k2r/8/8/8/2b5/8/3P1P2/R3K2R", true);
        board.setCastleCoords(new String[]{"71", "31", "78", "38"});
        MoveCalculator moveCalc = new MoveCalculator(board);
        
        
        Assertions.assertEquals(Arrays.asList("41", "31"), moveCalc.calculatePotMoves("51"));
    }

    // https://lichess.org/analysis/fromPosition/r3k2r/8/8/8/2q3b1/8/3P1P2/R3K2R_w_-_-_0_1
    @Test
    @DisplayName("No castle, both ways are blocked by enemy")
    void noCastle3() {
        Board board = new Board("r3k2r/8/8/8/2q3b1/8/3P1P2/R3K2R", true);
        board.setCastleCoords(new String[]{"71", "31", "78", "38"});
        MoveCalculator moveCalc = new MoveCalculator(board);
        
        
        Assertions.assertEquals(new ArrayList<>(), moveCalc.calculatePotMoves("51"));
    }

    // https://lichess.org/analysis/fromPosition/r3k2r/8/8/8/8/8/3P1PP1/R1B1K1NR_w_-_-_0_1
    @Test
    @DisplayName("No castle, because friendly pieces block both ways")
    void noCastle4() {
        Board board = new Board("r3k2r/8/8/8/8/8/3P1PP1/R1B1K1NR", true);
        board.setCastleCoords(new String[]{"71", "31", "78", "38"});
        MoveCalculator moveCalc = new MoveCalculator(board);
        
        
        Assertions.assertEquals(Arrays.asList("41", "52", "61"), moveCalc.calculatePotMoves("51"));
    }
}