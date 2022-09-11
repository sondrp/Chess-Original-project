package chess.PieceFamily;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    
    public Knight(String type) {
        super(type);
        if (!type.equalsIgnoreCase("n")) {throw new IllegalArgumentException("illegal piece type");}
    }
    
    @Override
    public List<List<String>> calculateCover(int x, int y) {
        List<String> moves = new ArrayList<>();

        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if (Math.abs(i) + Math.abs(j) == 3 && (0 < i + x && i + x < 9 && 0 < y + j && y + j < 9)) {
                    moves.add((x + i)+""+(y + j));
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(moves);
        return result;          // looks stupid, but haven't found a better way to be consistent with other pieces
    }
}
