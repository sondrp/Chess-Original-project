package chess.PieceFamily;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private String type;
    private boolean isWhite;

    protected Piece(String type) {
        this.type = type;
        isWhite = type.toUpperCase().equals(type);
    }

    @Override
    public String toString() {
        return (isWhite ? "white" : "black") + type;
    }

    // level 0 calculation
    public List<List<String>> calculateCover(int x, int y) {
        List<List<String>> allDirections = new ArrayList<>();
        List<String> oneDirection;

        for (int[] pair : getDirectionPair()) {
            oneDirection = oneDirectionCover(x, y, pair[0], pair[1]);
            if (!oneDirection.isEmpty()) {
                allDirections.add(oneDirection);
            }
        }
        return allDirections;
    }

    private List<String> oneDirectionCover(int x, int y, int dx, int dy) {
        List<String> oneDirection = new ArrayList<>();
        x += dx;
        y += dy;

        while(0 < x && x < 9 && 0 < y && y < 9) {
            oneDirection.add(x+""+y);
            x += dx;
            y += dy;
        }
        return oneDirection;
    }


    // getters 
    public List<String> getForwardMoves(String coordinate) {throw new IllegalStateException("non pawn tried to get forward pawn");}     // for pawn only
    public int getQueenRank() {throw new IllegalStateException("non pawn tried to get queen rank");}
    public String getType() {return type;}
    public boolean isWhite() {return isWhite;}
    
        // for queen, bishop, rook only
    protected int[][] getDirectionPair() {throw new IllegalStateException("undirectional piece tried to get directionpairs");}             
}
