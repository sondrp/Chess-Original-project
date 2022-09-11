package chess.GameFamily;
import java.util.Map;
import java.util.function.BiPredicate;

import chess.PieceFamily.Piece;



public class Board {

    private Map<String, Piece> placements;
    private String[] castleCoords = new String[4];
    private String enPassant = "-";
    private boolean isCastleLegal = true;
    private boolean isWhitePlaying;

    private boolean resetHalfmoveClock = false;          // used by fen to see if the game is draw after 50 moves without capture or pawnmove


    public Board(String gamestring, boolean isWhitePlaying) {
        placements = MapMaker.makeMap(gamestring);
        this.isWhitePlaying = isWhitePlaying;
    }
    public String getKingCoord(boolean whiteKing) {
        return placements.entrySet().stream()
                         .filter(e -> e.getValue() != null)
                         .filter(e -> e.getValue().isWhite() == whiteKing)
                         .filter(e -> e.getValue().getType().equalsIgnoreCase("k"))
                         .map(Map.Entry::getKey)
                         .findFirst()
                         .orElseThrow(() -> {throw new IllegalStateException("The king is missing from the game (from getKingCoord)");});
    }


    public BoardIterator makeBoardIterator(BiPredicate<Map<String, Piece>, String> condition) {
        return new BoardIterator(placements, condition);
    }

    
    // getters
    public Map<String, Piece> getPlacements() {return placements;}
    public String[] getCastleCoords() {return castleCoords;}
    public String getEnPassantSquare() {return enPassant;}
    public boolean isWhitePlaying() {return isWhitePlaying;}
    public boolean isCastleLegal() {return isCastleLegal;}
    public boolean getResetHalfmove() {return resetHalfmoveClock;}

    // setters 
    public void changePlaying() {isWhitePlaying = !isWhitePlaying;}
    public void setCastleCoords(String[] castleCoords) {this.castleCoords = castleCoords;}
    public void setEnPassantSquare(String coord) {enPassant = coord;}
    public void makeCastleIllegal() {isCastleLegal = false;}
    public void resetHalfmoveClock(boolean reset) {this.resetHalfmoveClock = reset;}
}
