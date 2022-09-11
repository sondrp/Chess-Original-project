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
import chess.PieceFamily.Rook;

public class RookTest {

    private Piece rook;

    @BeforeEach
    public void setup() {
        rook = new Rook("R");
    }

    @Test
    void tryWrongType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rook("yooooo"));
    }
    

    @Test
    @DisplayName("Construction")
    void createBlackRook() {
        Rook rook = new Rook("r");
        assertEquals(false, rook.isWhite());
        assertEquals("r", rook.getType());
        assertEquals("blackr", rook.toString());
    }

    @Test
    @DisplayName("Construction")
    void createWhitekRook() {
        Rook rook = new Rook("R");
        assertEquals(true, rook.isWhite());
        assertEquals("R", rook.getType());
        assertEquals("whiteR", rook.toString());

        Assertions.assertThrows(IllegalStateException.class, () -> rook.getForwardMoves("11"));
        assertThrows(IllegalStateException.class, () -> rook.getQueenRank());
    }



    @Test
    @DisplayName("Movement from centre")
    void testCoverCentre() {
        rook = new Rook("R");

        List<String> up = Arrays.asList("45", "46", "47", "48");
        List<String> right = Arrays.asList("54", "64", "74", "84");
        List<String> down = Arrays.asList("43", "42", "41");
        List<String> left = Arrays.asList("34", "24", "14");

        List<List<String>> result = new ArrayList<>();
        result.add(up);
        result.add(right);
        result.add(down);
        result.add(left);
        
        Assertions.assertEquals(rook.calculateCover(4, 4),result);
    }

    @Test
    @DisplayName("Movement from corner")
    void testCoverCorner() {
        rook = new Rook("R");

        List<String> up = Arrays.asList("82", "83", "84", "85", "86", "87", "88");
        List<String> left = Arrays.asList("71", "61", "51", "41", "31", "21", "11");

        List<List<String>> result = new ArrayList<>();
        result.add(up);
        result.add(left);
        
        Assertions.assertEquals(rook.calculateCover(8, 1),result);
    }
}
