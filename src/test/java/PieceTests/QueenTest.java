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
import chess.PieceFamily.Queen;

public class QueenTest {

    private Piece queen;

    @BeforeEach
    public void setup() {
        queen = new Queen("Q");
    }

    @Test
    void tryWrongType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Queen("wgewegweg"));
    }
    

    @Test
    @DisplayName("Construction")
    void createBlackqueen() {
        queen = new Queen("q");
        assertEquals(false, queen.isWhite());
        assertEquals("q", queen.getType());
        assertEquals("blackq", queen.toString());
    }

    @Test
    @DisplayName("Construction")
    void createWhitekqueen() {

        assertEquals(true, queen.isWhite());
        assertEquals("Q", queen.getType());
        assertEquals("whiteQ", queen.toString());

        Assertions.assertThrows(IllegalStateException.class, () -> queen.getForwardMoves("11"));
        assertThrows(IllegalStateException.class, () -> queen.getQueenRank());
    }



    @Test
    @DisplayName("Movement from centre")
    void testCoverCentre() {

        List<String> up = Arrays.asList("45", "46", "47", "48");
        List<String> upright = Arrays.asList("55", "66", "77", "88");
        List<String> right = Arrays.asList("54", "64", "74", "84");
        List<String> downright = Arrays.asList("53", "62", "71");
        List<String> down = Arrays.asList("43", "42", "41");
        List<String> downleft = Arrays.asList("33", "22", "11");
        List<String> left = Arrays.asList("34", "24", "14");
        List<String> upleft = Arrays.asList("35", "26", "17");

        List<List<String>> result = new ArrayList<>();
        result.add(up);
        result.add(upright);
        result.add(right);
        result.add(downright);
        result.add(down);
        result.add(downleft);
        result.add(left);
        result.add(upleft);
        
        Assertions.assertEquals(queen.calculateCover(4, 4),result);
    }

    @Test
    @DisplayName("Movement from corner")
    void testCoverCorner() {

        List<String> right = Arrays.asList("28", "38", "48", "58", "68", "78", "88");
        List<String> downright = Arrays.asList("27", "36", "45", "54", "63", "72", "81");
        List<String> left = Arrays.asList("17", "16", "15", "14", "13", "12", "11");

        List<List<String>> result = new ArrayList<>();
        result.add(right);
        result.add(downright);
        result.add(left);
        
        Assertions.assertEquals(queen.calculateCover(1, 8),result);
    }
}

