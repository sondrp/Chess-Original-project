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
import chess.PieceFamily.Knight;

public class KnightTest {

    private Piece knight;

    @BeforeEach
    public void setup() {
        knight = new Knight("N");
    }

    @Test
    void tryWrongType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Knight("r"));
    }
    

    @Test
    @DisplayName("Construction")
    void createBlackknight() {
        knight = new Knight("n");
        assertEquals(false, knight.isWhite());
        assertEquals("n", knight.getType());
        assertEquals("blackn", knight.toString());
    }

    @Test
    @DisplayName("Construction")
    void createWhitekknight() {

        assertEquals(true, knight.isWhite());
        assertEquals("N", knight.getType());
        assertEquals("whiteN", knight.toString());

        Assertions.assertThrows(IllegalStateException.class, () -> knight.getForwardMoves("11"));
        assertThrows(IllegalStateException.class, () -> knight.getQueenRank());
    }



    @Test
    @DisplayName("Movement from centre")
    void testCoverCentre() {

        List<String> innerList = Arrays.asList("15","17", "24", "28", "44", "48", "55", "57");

        List<List<String>> result = new ArrayList<>();
        result.add(innerList);
        
        Assertions.assertEquals(knight.calculateCover(3, 6),result);
    }

    @Test
    @DisplayName("Movement from corner")
    void testCoverCorner() {

        List<String> innerList = Arrays.asList("23", "32");

        List<List<String>> result = new ArrayList<>();
        result.add(innerList);
        
        Assertions.assertEquals(knight.calculateCover(1, 1),result);
    }
}

