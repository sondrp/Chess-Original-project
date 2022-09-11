package chess.PieceFamily;

public class Rook extends Piece {
    
    protected int[][] directionPairs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public Rook(String type) {
        super(type);
        if (!type.equalsIgnoreCase("r")) {throw new IllegalArgumentException("illegal piece type");}
    }

    @Override
    protected int[][] getDirectionPair() {return directionPairs;}
}




