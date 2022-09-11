package MoveCalculationTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;
import chess.MoveFamily.MoveCalculator;

// Quote from instructor's answer on piazza-question on testing private methods:

// "Private metoder (hvis de er brukt riktig) er å regne som implementasjonsdetaljer, og da er de ikke nødvendigvis like interessante å teste.
// Fokuser heller på å teste de offentlige metodene dine gjennomgående."

// The private methods used in movesimulator and moveanalyser are extensive, but will not be tested directly.
// Instead, they will all be tested indirectly when used in move calculation and execution
public class StartingPosCalculationTests {

    private MoveCalculator moveCalc;

    @BeforeEach
    public void setup() {
        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", true);
        moveCalc = new MoveCalculator(board);
    }

    @Test
    @DisplayName("Testing every single legal move from starting position")
    void whiteMovesFromStart() {

        // pawns
        Assertions.assertEquals( Arrays.asList("13", "14"), moveCalc.calculatePotMoves("12"));
        Assertions.assertEquals( Arrays.asList("23", "24"), moveCalc.calculatePotMoves("22"));
        Assertions.assertEquals( Arrays.asList("33", "34"), moveCalc.calculatePotMoves("32"));
        Assertions.assertEquals( Arrays.asList("43", "44"), moveCalc.calculatePotMoves("42"));
        Assertions.assertEquals( Arrays.asList("53", "54"), moveCalc.calculatePotMoves("52"));
        Assertions.assertEquals( Arrays.asList("63", "64"), moveCalc.calculatePotMoves("62"));
        Assertions.assertEquals( Arrays.asList("73", "74"), moveCalc.calculatePotMoves("72"));
        Assertions.assertEquals( Arrays.asList("83", "84"), moveCalc.calculatePotMoves("82"));

        // knights
        Assertions.assertEquals( Arrays.asList("13", "33"), moveCalc.calculatePotMoves("21"));
        Assertions.assertEquals( Arrays.asList("63", "83"), moveCalc.calculatePotMoves("71"));
    }

    @Test
    @DisplayName("Testing if other squares are clicked")
    void otherMovesFromStart() {
        List<String> result = new ArrayList<>();

        // remaining white pieces
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("11"));
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("31"));
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("41"));
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("51"));
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("61"));
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("81"));

        // empty squares and black pieces
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("44"));
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("88"));
        Assertions.assertEquals( result, moveCalc.calculatePotMoves("77"));
    }

    @Test
    @DisplayName("illegal arguments")
    void shouldThrow() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> moveCalc.calculatePotMoves("00"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> moveCalc.calculatePotMoves("99"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> moveCalc.calculatePotMoves("59"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> moveCalc.calculatePotMoves("111"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> moveCalc.calculatePotMoves(""));
    }
    
}
