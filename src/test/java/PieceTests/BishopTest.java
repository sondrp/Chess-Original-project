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

import chess.PieceFamily.Bishop;
import chess.PieceFamily.Piece;
public class BishopTest {
    
    private Piece bishop;

    @BeforeEach
    public void setup() {
        bishop = new Bishop("B");
    }

    @Test
    void tryWrongType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Bishop("r"));
    }
    

    @Test
    @DisplayName("Construction")
    void createBlackBishop() {
        bishop = new Bishop("b");
        assertEquals(false, bishop.isWhite());
        assertEquals("b", bishop.getType());
        assertEquals("blackb", bishop.toString());
    }

    @Test
    @DisplayName("Construction")
    void createWhitekbishop() {

        assertEquals(true, bishop.isWhite());
        assertEquals("B", bishop.getType());
        assertEquals("whiteB", bishop.toString());

        Assertions.assertThrows(IllegalStateException.class, () -> bishop.getForwardMoves("11"));
        assertThrows(IllegalStateException.class, () -> bishop.getQueenRank());
    }



    @Test
    @DisplayName("Movement from centre")
    void testCoverCentre() {


        List<String> upright = Arrays.asList("56", "67", "78");
        List<String> downright = Arrays.asList("54", "63", "72", "81");
        List<String> downleft = Arrays.asList("34", "23", "12");
        List<String> upleft = Arrays.asList("36", "27", "18");

        List<List<String>> result = new ArrayList<>();
        result.add(upright);
        result.add(downright);
        result.add(downleft);
        result.add(upleft);
        
        Assertions.assertEquals(bishop.calculateCover(4, 5), result);
    }

    @Test
    @DisplayName("Movement from corner")
    void testCoverCorner() {
        List<String> upright = Arrays.asList("22", "33", "44", "55", "66", "77", "88");

        List<List<String>> result = new ArrayList<>();
        result.add(upright);
  
        
        Assertions.assertEquals(bishop.calculateCover(1, 1),result);
    }
}
