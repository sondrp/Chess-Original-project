package chess.PieceFamily;
public class Queen extends Piece {
    
    protected int[][] directionPairs = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    public Queen(String type) {
        super(type);
        if (!type.equalsIgnoreCase("q")) {throw new IllegalArgumentException("illegal piece type");}
    }

    @Override
    protected int[][] getDirectionPair() {return directionPairs;}
}
