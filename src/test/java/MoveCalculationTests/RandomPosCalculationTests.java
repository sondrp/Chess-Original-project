package MoveCalculationTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;
import chess.MoveFamily.MoveCalculator;

// analysing the potential moves for every piece, in a random position
// the position used can be viewed here :
// https://lichess.org/analysis/standard/nk3b2/1bn1PP1P/1P2P1q1/RB2Kpr1/1pp5/2p1QP1r/NpPP1ppp/3NR1B1_w_-_-_0_1

// moveCalculator is given a coordinate, and it gives a list of posible moves for the piece standing on it. Sometimes it has no moves, 
// and an empty list is returned. Otherwise, a list of coordinates is returned
public class RandomPosCalculationTests {
    
    @Test
    @DisplayName("Calculating potential moves for every white piece")
    void whiteMoves() {
        
        Board board = new Board("nk3b2/1bn1PP1P/1P2P1q1/RB2Kpr1/1pp5/2p1QP1r/NpPP1ppp/3NR1B1", true);
        MoveCalculator moveCalc = new MoveCalculator(board);
        List<String> emptyList = new ArrayList<>();

        // pawns
        Assertions.assertEquals(Arrays.asList("37"), moveCalc.calculatePotMoves("26"));
        Assertions.assertEquals(emptyList, moveCalc.calculatePotMoves("32"));
        Assertions.assertEquals(Arrays.asList("33", "43", "44"), moveCalc.calculatePotMoves("42"));
        Assertions.assertEquals(emptyList, moveCalc.calculatePotMoves("56"));
        Assertions.assertEquals(Arrays.asList("68", "58"), moveCalc.calculatePotMoves("57"));
        Assertions.assertEquals(Arrays.asList("64"), moveCalc.calculatePotMoves("63"));
        Assertions.assertEquals(emptyList, moveCalc.calculatePotMoves("67"));
        Assertions.assertEquals(Arrays.asList("88"), moveCalc.calculatePotMoves("87"));
        
        // rooks
        Assertions.assertEquals(Arrays.asList("16", "17", "18", "14", "13"), moveCalc.calculatePotMoves("15"));
        Assertions.assertEquals(Arrays.asList("52", "61"), moveCalc.calculatePotMoves("51"));
        
        // knights
        Assertions.assertEquals(Arrays.asList("24", "31", "33"), moveCalc.calculatePotMoves("12"));
        Assertions.assertEquals(Arrays.asList("22", "33", "62"), moveCalc.calculatePotMoves("41"));

        // bishops
        Assertions.assertEquals(Arrays.asList("36", "47", "58", "34", "14", "16"), moveCalc.calculatePotMoves("25"));
        Assertions.assertEquals(Arrays.asList("82", "62"), moveCalc.calculatePotMoves("71"));

        // queen
        Assertions.assertEquals(Arrays.asList("54", "64", "75", "62", "52", "43", "33", "44", "35"), moveCalc.calculatePotMoves("53"));

        // king
        Assertions.assertEquals(Arrays.asList("44", "46", "64"), moveCalc.calculatePotMoves("55"));    
    }

    @Test
    @DisplayName("Calculating potential moves for every black piece")
    void blackMoves() {
        
        Board board = new Board("nk3b2/1bn1PP1P/1P2P1q1/RB2Kpr1/1pp5/2p1QP1r/NpPP1ppp/3NR1B1", false);
        MoveCalculator moveCalc = new MoveCalculator(board);
        List<String> emptyList = new ArrayList<>();

        // pawns
        Assertions.assertEquals(Arrays.asList("21"), moveCalc.calculatePotMoves("22"));
        Assertions.assertEquals(Arrays.asList("23"), moveCalc.calculatePotMoves("24"));
        Assertions.assertEquals(Arrays.asList("42"), moveCalc.calculatePotMoves("33"));
        Assertions.assertEquals(emptyList, moveCalc.calculatePotMoves("34"));
        Assertions.assertEquals(Arrays.asList("51", "71", "61"), moveCalc.calculatePotMoves("62"));
        Assertions.assertEquals(emptyList, moveCalc.calculatePotMoves("64"));
        Assertions.assertEquals(emptyList, moveCalc.calculatePotMoves("72"));
        Assertions.assertEquals(Arrays.asList("71", "81"), moveCalc.calculatePotMoves("82"));
        
        // rooks
        Assertions.assertEquals(Arrays.asList("85", "74", "73"), moveCalc.calculatePotMoves("75"));
        Assertions.assertEquals(Arrays.asList("84", "85", "86", "87", "73", "63"), moveCalc.calculatePotMoves("83"));

        // knights
        Assertions.assertEquals(Arrays.asList("26"), moveCalc.calculatePotMoves("18"));
        Assertions.assertEquals(Arrays.asList("16", "25", "45", "56", "58"), moveCalc.calculatePotMoves("37"));

        // bishops
        Assertions.assertEquals(Arrays.asList("38", "36", "45", "54", "63", "16"), moveCalc.calculatePotMoves("27"));
        Assertions.assertEquals(Arrays.asList("77", "86", "57"), moveCalc.calculatePotMoves("68"));

        // queen
        Assertions.assertEquals(Arrays.asList("77", "78", "87", "86", "85", "66", "56", "67"), moveCalc.calculatePotMoves("76"));

        // king 
        Assertions.assertEquals(Arrays.asList("38"), moveCalc.calculatePotMoves("28"));
        

    }
}
