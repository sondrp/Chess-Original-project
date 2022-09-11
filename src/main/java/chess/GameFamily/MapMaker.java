package chess.GameFamily;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.PieceFamily.Bishop;
import chess.PieceFamily.King;
import chess.PieceFamily.Knight;
import chess.PieceFamily.Pawn;
import chess.PieceFamily.Piece;
import chess.PieceFamily.Queen;
import chess.PieceFamily.Rook;

public class MapMaker {
    // Serves one purpose: turn a fenstring into a map of strings and pieces.
    // This function could be defined in the fen, but delegating
    // makes it easier to use for testing.


    private MapMaker() {}


    public static Map<String, Piece> makeMap(String gamestring) {
        
        Map<String, Piece> placements = new LinkedHashMap<>();
            List<String> coordinates = makeCoordinates();
            List<Piece> pieces =         
            Stream.of(gamestring.split(""))
                  .filter(letter -> !letter.equals("/"))
                  .flatMap(letter -> (!Character.isDigit(letter.charAt(0))) ? Stream.of(letter) : Collections.nCopies(Integer.parseInt(letter), "").stream())
                  .map(MapMaker::pieceMaker)
                  .collect(Collectors.toList());
    
            for (int i = 0; i < 64; i++) {
                Piece piece = pieces.get(i);
                String coord = coordinates.get(i);
    
                placements.put(coord, piece);
            }
            return placements;
        }
    
    private static List<String> makeCoordinates() {
        List<String> coords = new ArrayList<>();
        for (int y = 8; y > 0; y--) {
            for (int x = 1; x < 9; x++) {
                coords.add(x+""+y);
            }
        }
        return coords;
    }

    private static Piece pieceMaker(String type) {
        if (type.equals("")) {return null;}
        if (type.equalsIgnoreCase("p")) {return new Pawn(type);}
        if (type.equalsIgnoreCase("r")) {return new Rook(type);}
        if (type.equalsIgnoreCase("b")) {return new Bishop(type);}
        if (type.equalsIgnoreCase("n")) {return new Knight(type);}
        if (type.equalsIgnoreCase("k")) {return new King(type);}
        if (type.equalsIgnoreCase("q")) {return new Queen(type);}
        throw new IllegalArgumentException("Piecemaker couldn't make piece");
    }
}
