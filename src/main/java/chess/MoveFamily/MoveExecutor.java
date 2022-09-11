package chess.MoveFamily;
import java.util.Arrays;
import java.util.List;

import chess.GameFamily.Board;
import chess.GameFamily.GameOverException;
import chess.PieceFamily.King;
import chess.PieceFamily.Pawn;
import chess.PieceFamily.Piece;
import chess.PieceFamily.Queen;
import chess.PieceFamily.Rook;

public class MoveExecutor extends MoveSimulator {

    private final List<String> rookstart = Arrays.asList("81", "11", "88", "18");
    private final List<String> kingend = Arrays.asList("71", "31", "78", "38");        
    private final List<String> rookend = Arrays.asList("61", "41", "68", "48");
    
    public MoveExecutor(Board board) {
        super(board);
    }


    public void execute(String moveFrom, String moveTo) {
        
        Piece movingPiece = placements.get(moveFrom);

        makeMove(movingPiece, moveFrom, moveTo);

        // change into a queen when pawn reach end of board
        if (movingPiece instanceof Pawn && moveTo.charAt(1) - 48 == movingPiece.getQueenRank()) {
            String type = movingPiece.isWhite() ? "Q" : "q";
            placements.put(moveTo, new Queen(type)); 
        }
        
        board.changePlaying();

        // Determines checkmate or stalemate:
        // if it's check and no move is legal -> checkmate
        // if it's not check but no move legal -> stalemate
        if (isPositionSjakk(board.isWhitePlaying())) {
            if (noMoveIsLegal(board.isWhitePlaying())) {
                throw new GameOverException("Checkmate. Winner is " + (board.isWhitePlaying() ? "black" : "white"));
            } 
        } else if (noMoveIsLegal(board.isWhitePlaying())) {
            throw new GameOverException("Stalemate");
        }
    }

    private void makeMove(Piece movingPiece, String moveFrom, String moveTo) {
        
        Piece capturedPiece = placements.get(moveTo);
        if (capturedPiece != null && capturedPiece.getType().equalsIgnoreCase("k")) {throw new IllegalStateException("The king was captured");}
        
        board.resetHalfmoveClock(capturedPiece != null);       // halfmove clock is reset if piece is captured

        // The actual move change
        placements.put(moveFrom, null);               
        placements.put(moveTo, movingPiece);

        // the rest is just to handle special cases


        // delete captured pawn in en passant capture
        if (board.getEnPassantSquare().equals(moveTo) && movingPiece instanceof Pawn) {
            int x = moveTo.charAt(0) - 48;
            int y = moveFrom.charAt(1) - 48; 
            placements.put(x+""+y, null);
        }

        // removes castle options if king move. If it's a castle move, it also moves the rook
        if (board.isCastleLegal() && movingPiece instanceof King) {

            String[] remainingCastleCoords = board.getCastleCoords();
            int index = movingPiece.isWhite() ? 0 : 2;

            
            // moving the rook
            if (Arrays.asList(remainingCastleCoords).contains(moveTo)) {
                
                int i = kingend.indexOf(moveTo);
                String rookStart = rookstart.get(i);
                String rookLanding = rookend.get(i);
                Piece rook = placements.get(rookStart);
                
                placements.put(rookStart, null);
                placements.put(rookLanding, rook);
            }
            remainingCastleCoords[index] = null;
            remainingCastleCoords[index + 1] = null;
            board.setCastleCoords(remainingCastleCoords);
        }

        // removes castle option if rook move
        if (board.isCastleLegal() && movingPiece instanceof Rook) {
            String[] remainingCastleCoords = board.getCastleCoords();
            int index;

            if ((index = rookstart.indexOf(moveFrom)) == -1) {return;}

            index = rookstart.indexOf(moveFrom);
            remainingCastleCoords[index] = null;

            board.setCastleCoords(remainingCastleCoords);    
        }

        // update en passant for next move (if a pawn moves two forward)
        board.setEnPassantSquare("-");
        if (movingPiece instanceof Pawn) {
            board.resetHalfmoveClock(true);
            int x = moveFrom.charAt(0) - 48;
            int y0 = moveFrom.charAt(1) - 48;
            int y1 = moveTo.charAt(1) - 48;

            if (Math.abs(y1 - y0) == 2) {
                int y = (y1 + y0) / 2;
                board.setEnPassantSquare(x+""+y);
            } 
        }
    }
}
