package MoveCalculationTests;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;
import chess.MoveFamily.MoveCalculator;

// analysing the potential moves for the queen, whose moves are limited by a bishop pinning the queen to the king
// the position used can be viewed here :
// https://lichess.org/analysis/fromPosition/7k/8/5b2/8/8/2Q5/8/K7_w_-_-_0_1

public class PinTests {
    

    @Test
    @DisplayName("Queen can only move along diagonal")
    void notMoveOutOfPinWhite() {
        
        Board board = new Board("7k/8/5b2/8/8/2Q5/8/K7", true);
        MoveCalculator moveCalc = new MoveCalculator(board);

        Assertions.assertEquals( Arrays.asList("44", "55", "66", "22"), moveCalc.calculatePotMoves("33"));
    }

    @Test
    @DisplayName("Bishop can only move along diagonal") 
    void notMoveOutOfPinBlack() {
        Board board = new Board("7k/8/5b2/8/8/2Q5/8/K7", false);
        MoveCalculator moveCalc = new MoveCalculator(board);

        Assertions.assertEquals( Arrays.asList("77", "55", "44", "33"), moveCalc.calculatePotMoves("66"));

    }
}
