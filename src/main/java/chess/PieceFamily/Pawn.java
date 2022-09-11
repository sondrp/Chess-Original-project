package chess.PieceFamily;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    
    private int rowchange;
    private int queenrank;
    private int startrank;

    public Pawn(String type) {
        super(type);
        if (!type.equalsIgnoreCase("p")) {throw new IllegalArgumentException("illegal piece type");}
        boolean isWhite = isWhite();
        rowchange = isWhite ? 1 : -1;
        queenrank = isWhite ? 8 : 1;
        startrank = isWhite ? 2 : 7;
    }

    @Override
    public List<List<String>> calculateCover(int x, int y) {
        if (y == 1 || y == 8) {throw new IllegalStateException("there is a pawn on the queenrank");}
        List<String> moves = new ArrayList<>();

        if (1 < x) {
            moves.add((x-1)+""+(y+rowchange));
        }
        if (x < 8) {
            moves.add((x+1)+""+(y+rowchange));
        }
 
        List<List<String>> result = new ArrayList<>();
        result.add(moves);
        return result;          // looks stupid, but haven't found a better way to be consistent with other pieces
  
    }

    @Override
    public List<String> getForwardMoves(String coordinate) {
        int x = coordinate.charAt(0) - 48;
        int y = coordinate.charAt(1) - 48;

        List<String> moves = new ArrayList<>();
        if (y == queenrank) {throw new IllegalStateException("there is a pawn on the queenrank");}

        moves.add(x+""+(y+rowchange));
        if (y == startrank) {
            moves.add(x+""+(y+2*rowchange));
        }
        return moves;
    }

    @Override
    public int getQueenRank() {return queenrank;}
}
