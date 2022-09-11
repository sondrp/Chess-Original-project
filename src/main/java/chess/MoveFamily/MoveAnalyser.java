package chess.MoveFamily;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.GameFamily.Board;
import chess.GameFamily.BoardIterator;
import chess.PieceFamily.Pawn;
import chess.PieceFamily.Piece;



abstract class MoveAnalyser {

    Board board;
    protected Map<String, Piece> placements;


    protected MoveAnalyser(Board board) {
        this.board = board;
        this.placements = board.getPlacements();
    }


    protected boolean isPositionSjakk(boolean checkForWhite) {
        
        BoardIterator it = board.makeBoardIterator((map, coordinate) -> map.get(coordinate).isWhite() != checkForWhite);      // iterator that gives the squares of enemy pieces
        String kingCoord = board.getKingCoord(checkForWhite);

        while (it.hasNext()) {
            List<String> enemyCover = calculateCover(it.next());
            if (enemyCover.contains(kingCoord)) {return true;}
        }
        return false;
    }

    // level 1 calculation
    protected List<String> calculateCover(String coordinate) {
        Piece movingPiece = placements.get(coordinate);
        int x = coordinate.charAt(0) - 48;
        int y = coordinate.charAt(1) - 48;

        // cover of directional pieces (meaning they can't "jump over" other pieces) is trimmed differently than others.
        String type = movingPiece.getType().toLowerCase();
        boolean isDirectional = (type.equals("q") || type.equals("r") || type.equals("b"));

        List<List<String>> untrimmedCover = movingPiece.calculateCover(x, y);
        return trimCover(untrimmedCover, isDirectional);
    }

    private List<String> trimCover(List<List<String>> allDirections, boolean isDirectional) {
        if (!isDirectional) {return allDirections.get(0);}
        List<String> trimmedCover = new ArrayList<>();

        for (List<String> oneDirection : allDirections) {
             for (String coord : oneDirection) {
                trimmedCover.add(coord);
                if (placements.get(coord) != null) {break;}
            }
        }
        return trimmedCover;
    }



    // used in level 2 calculation
    protected void addBonusMoves(List<String> moves, Piece movingPiece, String coordinate) {

        if (movingPiece instanceof Pawn) {
            movingPiece.getForwardMoves(coordinate).stream()
                .takeWhile(coord -> placements.get(coord) == null)
                .forEach(moves::add);
            return;
        }

        boolean whiteKing = movingPiece.isWhite();
        boolean enemyBlack = movingPiece.isWhite();
        String kingStartCoord = whiteKing ? "51" : "58";

        // adding castle squares for king if it's allowed
        // this is the case when king/rook hasn't moved, king is not in check, squares where rook land is not covered by enemy + are empty.
        if (coordinate.equals(kingStartCoord) && board.isCastleLegal()) {

            if (isPositionSjakk(whiteKing)) {return;}           // can't castle if it's check

            int index = whiteKing ? 0 : 2;
            String[] remainingCastleCoords = board.getCastleCoords();
            String[] squaresToCheck = {"61", "41", "68", "48"};
            String[] squaresToAdd = {"71", "31", "78", "38"};

            if (remainingCastleCoords[index] != null && placements.get(squaresToCheck[index]) == null && placements.get(squaresToAdd[index]) == null && squareNotCoveredBy(squaresToCheck[index], enemyBlack)) {
                moves.add(squaresToAdd[index]);
            }
            if (remainingCastleCoords[index+1] != null && placements.get(squaresToCheck[index+1]) == null && placements.get(squaresToAdd[index+1]) == null && squareNotCoveredBy(squaresToCheck[index+1], enemyBlack)) {
                moves.add(squaresToAdd[index + 1]);
            } 
        }        
    }

    private boolean squareNotCoveredBy(String squareToCheck, boolean enemyBlack) {

        List<String> coveredSquares;

        BoardIterator enemyPieces = board.makeBoardIterator((map, coord) -> map.get(coord).isWhite() != enemyBlack);

        while (enemyPieces.hasNext()) {
            coveredSquares = calculateCover(enemyPieces.next());
            if (coveredSquares.contains(squareToCheck)) {return false;}
        }
        return true;
    }
}
