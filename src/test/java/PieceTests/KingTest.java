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
import chess.PieceFamily.King;

public class KingTest {

    private Piece king;

    @BeforeEach
    public void setup() {
        king = new King("K");
    }

    @Test
    void tryWrongType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new King("r"));
    }
    

    @Test
    @DisplayName("Construction")
    void createBlackking() {
        king = new King("k");
        assertEquals(false, king.isWhite());
        assertEquals("k", king.getType());
        assertEquals("blackk", king.toString());
    }

    @Test
    @DisplayName("Construction")
    void createWhitekking() {

        assertEquals(true, king.isWhite());
        assertEquals("K", king.getType());
        assertEquals("whiteK", king.toString());

        Assertions.assertThrows(IllegalStateException.class, () -> king.getForwardMoves("11"));
        assertThrows(IllegalStateException.class, () -> king.getQueenRank());
    }



    @Test
    @DisplayName("Movement from centre")
    void testCoverCentre() {

        List<String> innerList = Arrays.asList("56","57", "58", "66", "68", "76", "77", "78");

        List<List<String>> result = new ArrayList<>();
        result.add(innerList);
        
        Assertions.assertEquals(king.calculateCover(6, 7),result);
    }

    @Test
    @DisplayName("Movement from corner")
    void testCoverCorner() {

        List<String> innerList = Arrays.asList("71", "72", "82");

        List<List<String>> result = new ArrayList<>();
        result.add(innerList);
        
        Assertions.assertEquals(king.calculateCover(8, 1),result);
    }
}


