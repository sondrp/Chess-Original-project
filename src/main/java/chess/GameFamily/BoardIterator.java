package chess.GameFamily;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

import chess.PieceFamily.Piece;


public class BoardIterator implements Iterator<String> {

    private Map<String, Piece> placements;
    private BiPredicate<Map<String, Piece>, String> condition;
    private final BiPredicate<Map<String, Piece>, String> notEmpty = (map, coordinate) -> map.get(coordinate) != null;
    
    private String coord;
    private int index = 0;
    private int size;

    
    // Iterate through board with specified condition. index: 0 -> 63 where convertion to coordinate system I use is
    // x = index % 8 + 1
    // y = 8 - index / 8
    public BoardIterator(Map<String, Piece> placements, BiPredicate<Map<String, Piece>, String> condition) {
        
        this.placements = placements;
        this.condition = notEmpty.and(condition);

        size = placements.size();

    }

    @Override
    public boolean hasNext() {
        while (index < size) {
            coord = (index%8+1) +""+ (8-index/8);
            if (condition.test(placements, coord)) {return true;}
            index++;
        }
        return false;
    }

    @Override
    public String next() {
        if (!hasNext()) {throw new NoSuchElementException("no more of this piecetype (from boarditerator)");}
        coord = (index%8 +1) +""+ (8-index/8);
        index++;
        return coord;
    }
}