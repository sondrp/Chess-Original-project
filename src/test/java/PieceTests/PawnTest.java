package PieceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.PieceFamily.Piece;
import chess.PieceFamily.Pawn;

public class PawnTest {

    private Piece pawn;

    @BeforeEach
    public void setup() {
        pawn = new Pawn("P");
    }

    @Test
    void tryWrongType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Pawn("r"));
    }

    @Test
    @DisplayName("Construction")
    void createBlackpawn() {
        pawn = new Pawn("p");
        assertEquals(false, pawn.isWhite());
        assertEquals("p", pawn.getType());
        assertEquals("blackp", pawn.toString());
        assertEquals(1, pawn.getQueenRank());

    }

    @Test
    @DisplayName("Construction")
    void createWhitekpawn() {

        assertEquals(true, pawn.isWhite());
        assertEquals("P", pawn.getType());
        assertEquals("whiteP", pawn.toString());
        assertEquals(8, pawn.getQueenRank());
 
    }

    @Test
    @DisplayName("Cover from centre for white pawn")
    void testCoverCentre() {

        List<String> innerList = Arrays.asList("65", "85");

        List<List<String>> result = new ArrayList<>();
        result.add(innerList);
        
        Assertions.assertEquals(pawn.calculateCover(7, 4),result);
    }

    @Test
    @DisplayName("Cover from corner for black pawn")
    void testCoverCentreForBlack() {

        pawn = new Pawn("p");

        List<String> innerList = Arrays.asList("63", "83");

        List<List<String>> result = new ArrayList<>();
        result.add(innerList);
        
        Assertions.assertEquals(pawn.calculateCover(7, 4),result);
    }

    @Test
    @DisplayName("Cover edge of board") 
    void testCoverEdge() {

        List<String> innerList = Arrays.asList("77");

        List<List<String>> result = new ArrayList<>();
        result.add(innerList);
        
        Assertions.assertEquals(pawn.calculateCover(8, 6),result);
    }

    @Test
    @DisplayName("Illegal to have pawn on 1st or 8th row")
    void shouldThrow() {

        Assertions.assertThrows(IllegalStateException.class, () -> pawn.calculateCover(1, 1));
        Assertions.assertThrows(IllegalStateException.class, () -> pawn.calculateCover(5, 8));
    }

    @Test
    @DisplayName("Get forward moves")
    void oneForwardWhite() {

        List<String> result = Arrays.asList("25");

        Assertions.assertEquals(result, pawn.getForwardMoves("24"));
    }

    @Test
    @DisplayName("Get forward moves")
    void oneForwardBlack() {
        pawn = new Pawn("p");
        
        List<String> result = Arrays.asList("25");
        
        Assertions.assertEquals(result, pawn.getForwardMoves("26"));
    }

    @Test
    @DisplayName("Get two forward moves from own startrow")
    void twoForwardWhite() {

        List<String> result = Arrays.asList("43", "44");
        Assertions.assertEquals(result, pawn.getForwardMoves("42"));
        
        result = Arrays.asList("48");
        Assertions.assertEquals(result, pawn.getForwardMoves("47"));
    }

    @Test
    @DisplayName("Get two forward moves from own startrow")
    void twoForwardBlack() {

        pawn = new Pawn("p");

        List<String> result = Arrays.asList("76", "75");
        Assertions.assertEquals(result, pawn.getForwardMoves("77"));
        
        result = Arrays.asList("71");
        Assertions.assertEquals(result, pawn.getForwardMoves("72"));
    }


}

