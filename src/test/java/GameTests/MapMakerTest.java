package GameTests;

import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.MapMaker;
import chess.PieceFamily.Piece;

public class MapMakerTest {
    
    private Map<String, Piece> placements;

    @BeforeEach
    public void setup() {
        placements = MapMaker.makeMap("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    @Test
    @DisplayName("Making map for startposition, then turning it back to a string manually")
    void turnMapToString() {

        String setup = "";
        String type;

        for (Piece piece : placements.values()) {
            type = piece == null ? "" : piece.getType();
            setup += type;
        }

        Assertions.assertEquals("rnbqkbnrppppppppPPPPPPPPRNBQKBNR", setup);
    }

    @Test
    @DisplayName("Getting values from map")
    void getValues() {

        Assertions.assertEquals("R", placements.get("81").getType());
        Assertions.assertEquals("K", placements.get("51").getType());
        Assertions.assertEquals(null, placements.get("44"));
        
        Assertions.assertEquals(null, placements.get("89"));
    }
}
