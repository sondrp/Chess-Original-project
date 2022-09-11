package chess.PieceFamily;
public class Bishop extends Piece {
    
    private int[][] directionPairs = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public Bishop(String type) {
        super(type);
        if (!type.equalsIgnoreCase("b")) {throw new IllegalArgumentException("illegal piece type");}
    }

    @Override
    protected int[][] getDirectionPair() {return directionPairs;}
}
