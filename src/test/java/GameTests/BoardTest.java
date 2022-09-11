package GameTests;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;

public class BoardTest {
    

    // testing if king coordinate can be found
    // position used can be viewed here :
    // https://lichess.org/analysis/standard/2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2_w_-_-_0_1
    @Test
    void findKing() {
        Board board = new Board("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2", true);

        // for white
        Assertions.assertEquals("61", board.getKingCoord(true));

        // for black
        Assertions.assertEquals("17", board.getKingCoord(false));
    }
}
