package FileTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chess.FileFamily.Fen;

public class FenTest {
    

    // testing if Fen can make the fenstring properly
    // (note that it's being made, and not just returned as recieved)



    @Test
    void createFen() {

        Fen fen = new Fen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", fen.makeFenstring());

        fen = new Fen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1");
        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", fen.makeFenstring());

        fen = new Fen("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2 w - - 0 1");
        Assertions.assertEquals("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2 w - - 0 1", fen.makeFenstring());
    }

} 
