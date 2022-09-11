package chess.PieceFamily;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(String type) {
        super(type);
        if (!type.equalsIgnoreCase("k")) {throw new IllegalArgumentException("illegal piece type");}
    }
    
    @Override
    public List<List<String>> calculateCover(int x, int y) {
        List<String> moves = new ArrayList<>();

        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if (0 < i && i < 9 && 0 < j && j < 9 && !(x == i && y == j)) {
                    moves.add(i+""+j);
                }

            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(moves);
        return result;          // looks stupid, but haven't found a better way to be consistent with other pieces
    }
}
