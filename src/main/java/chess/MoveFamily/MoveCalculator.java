package chess.MoveFamily;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import chess.GameFamily.Board;
import chess.PieceFamily.Piece;


public class MoveCalculator extends MoveSimulator {

    public MoveCalculator(Board board) {
        super(board);
    }
    
    // level 3 calculation : finds all moves, and simulates each one. Allows only the moves that won't leave their own king in check
    public List<String> calculatePotMoves(String coordinate) {
        if (!Pattern.matches("[1-8]{1}[1-8]{1}", coordinate)) {throw new IllegalArgumentException("Tried to calculate move outside board");}

        Piece movingPiece = placements.get(coordinate);
        
        if (movingPiece == null) {return new ArrayList<>();}
        if (movingPiece.isWhite() != board.isWhitePlaying()) {return new ArrayList<>();}

        // calculation levels 0 -> 2
        List<String> moves = calculateMoves(coordinate);

        return moves.stream()
                        .filter(moveTo -> isMoveLegal(coordinate, moveTo))
                        .collect(Collectors.toList());
    }
}
