package chess.GameFamily;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.FileFamily.Fen;
import chess.MoveFamily.MoveCalculator;
import chess.MoveFamily.MoveExecutor;
import chess.PieceFamily.Piece;


public class Game {

    private MoveCalculator moveCalculator;
    private MoveExecutor moveExecutor;
    private Fen fen;
    private List<String> potMoves = new ArrayList<>();
    private String moveFrom;


    public Game(String fenstring) {

        fen = new Fen(fenstring);
        Board board = fen.getBoard();

        moveCalculator = new MoveCalculator(board);
        moveExecutor = new MoveExecutor(board);
        
    }
    public Game() {
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    public List<String> clicked(String coordinate) {

        if (!potMoves.contains(coordinate)) {
            
            moveFrom = coordinate;
            potMoves = moveCalculator.calculatePotMoves(coordinate);

        } else {

            moveExecutor.execute(moveFrom, coordinate);
            fen.moveComplete();
            potMoves.clear();

        }
        return potMoves;
    }

    public Map<String, Piece> getPlacements() {
        return fen.getBoard().getPlacements();
    }

    public String getFenstring() {
        return fen.makeFenstring();
    }

    public List<String> getKing() {
        Board board = fen.getBoard();
        List<String> kinglist = new ArrayList<>();
        String kingCoord = board.getKingCoord(board.isWhitePlaying());
        kinglist.add(kingCoord);
        return kinglist;
    }    
}
