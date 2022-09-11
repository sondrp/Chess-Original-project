package chess.MoveFamily;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chess.GameFamily.Board;
import chess.GameFamily.BoardIterator;
import chess.PieceFamily.King;
import chess.PieceFamily.Pawn;
import chess.PieceFamily.Piece;

abstract class MoveSimulator extends MoveAnalyser{

    private Predicate<String> emptySquare = coord -> placements.get(coord) == null;
    
    protected MoveSimulator(Board board) {
        super(board);
    }
    
    // level 2 calculation
    protected List<String> calculateMoves(String coordinate) {
        
        Piece movingPiece = placements.get(coordinate);
        
        Predicate<String> captureEnemy = coord -> placements.get(coord).isWhite() != movingPiece.isWhite();
        Predicate<String> enPassant = coord -> board.getEnPassantSquare().equals(coord);


        // need to check for empty square first, to allow the filter to short-ciruit. Otherwise it might try to evaluate null.isWhite()
        Predicate<String> combinedFilter = (movingPiece instanceof Pawn) ? enPassant.or(emptySquare.negate().and(captureEnemy)) : emptySquare.or(captureEnemy);

        List<String> moves = calculateCover(coordinate).stream()
                        .filter(combinedFilter)
                        .collect(Collectors.toList());

        
        if ( movingPiece instanceof Pawn || movingPiece instanceof King) {
            addBonusMoves(moves, movingPiece, coordinate);
        }            
        return moves;
    }


    public boolean noMoveIsLegal(boolean checkForWhite) {
        
        // finds details about the king
        String moveFrom = board.getKingCoord(checkForWhite);
        List<String> moves = calculateMoves(moveFrom);


        for (String moveTo : moves) {
            if(isMoveLegal(moveFrom, moveTo)) {return false;}          // checks first if the king can move out of check: which is the case in most check positions
        }

        // admittedly somewhat overengineered: this makes an iterator for all the friendly pieces, except the king.
        // A stream and filter would do the job just as well, but in that case I wouldn't have been able to show off my knowledge of iterators
        BoardIterator friendlyPieces = board.makeBoardIterator((map, coordinate) -> 
            map.get(coordinate).isWhite() == checkForWhite &&
            !map.get(coordinate).getType().equalsIgnoreCase("k"));

        while (friendlyPieces.hasNext()) {
            moveFrom = friendlyPieces.next();
            moves = calculateMoves(moveFrom);
            for (String moveTo : moves) {
                if(isMoveLegal(moveFrom, moveTo)) {return false;}          // checks every move to see if any can block
            }
        }       
        return true;        // returns true if every possible move still leaves king in check
    }

    // returns true if you can do specified move without putting your king in check
    protected boolean isMoveLegal(String moveFrom, String moveTo) {
        Piece enPasantCapture = null;
        String enPassantSquare = null;

        // Saving details, makes it possible to reset after check
        Piece movingPiece = placements.get(moveFrom);
        Piece capturedPiece = placements.get(moveTo);
        boolean isWhitePlaying = movingPiece.isWhite();
        
        // Making the move
        placements.put(moveFrom, null);
        placements.put(moveTo, movingPiece);

        // deletes captured pawn when en passant : to cover at least two special cases
        if (board.getEnPassantSquare().equals(moveTo) && movingPiece instanceof Pawn) {
            int x = moveTo.charAt(0) - 48;
            int y = moveFrom.charAt(1) - 48; 
            enPassantSquare = x+""+y;

            enPasantCapture = placements.get(enPassantSquare);
            placements.put(enPassantSquare, null);
        }
        
        // Checking if move results in check
        boolean isMoveSjakk = isPositionSjakk(isWhitePlaying);

        // resetting
        placements.put(moveFrom, movingPiece);
        placements.put(moveTo, capturedPiece);

        if (enPasantCapture != null) {
            placements.put(enPassantSquare, enPasantCapture);
        }

        return !isMoveSjakk;
    }
}
