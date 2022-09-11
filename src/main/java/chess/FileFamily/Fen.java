package chess.FileFamily;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.GameFamily.Board;
import chess.GameFamily.GameOverException;
import chess.PieceFamily.Piece;

public class Fen {

    private Board board;

    private String gamestring;
    private String playing;
    private String castle;
    private String enPassant;
    private String halfmove;
    private String fullmove;

    public Fen(String fen) {
        String[] g = fen.split(" ");
        gamestring = g[0];
        playing = g[1];
        castle = g[2];
        enPassant = g[3];
        halfmove = g[4];
        fullmove = g[5];

        boolean isWhitePlaying = playing.equals("w");        
        
        board = new Board(gamestring, isWhitePlaying);
        setCastleCoordsAndEnPassant();
    }

    public String makeFenstring() {
        return gamestring + " " + playing + " " + castle + " " + enPassant + " " + halfmove + " " + fullmove;
    }

    public void moveComplete() {
        updateGameString();
        updatePlaying();
        castle = updateCastle();
        enPassant = updateEnPassant();
        updateHalfmove();
        updateFullmove();
    }

    public Board getBoard() {
        return board;
    }

    private void updateGameString() {
        Map<String, Piece> placements = board.getPlacements();

        List<String> occupations = placements.values().stream().map(p -> (p != null) ? p.getType() : "").collect(Collectors.toList());
        this.gamestring = IntStream.range(0, 8)
            .mapToObj(n -> occupations.subList(n * 8, (n+1)*8))
            .map(rowCompressor)
            .collect(Collectors.joining("/"));
    }

    private final Function<List<String>, String> rowCompressor = row -> {
        String total = "";
        int i = 0;
        for (int n = 0; n < 8; n++) {
            if (row.get(n).equals("")) {continue;}
            total += (n - i > 0) ? (n - i) + row.get(n) : row.get(n);
            i = n + 1;
        }
        return ((8 - i) > 0) ? total + (8 - i) : total;
    };
    
    private void updatePlaying() {
       this.playing = board.isWhitePlaying() ? "w" : "b";
    }

    private String updateCastle() {
        if (!board.isCastleLegal()) {return "-";}
        String[] castleCoords = board.getCastleCoords();
        String castleString = "";

        if (castleCoords[0] != null) {castleString += "K";}
        if (castleCoords[1] != null) {castleString += "Q";}
        if (castleCoords[2] != null) {castleString += "k";}
        if (castleCoords[3] != null) {castleString += "q";}

        if (castleString.equals("")) {
            board.makeCastleIllegal();
            return "-";
        }
        return castleString;
    }
    private String updateEnPassant() {
        String enPassantString = board.getEnPassantSquare();
        if (enPassantString.equals("-")) {return enPassantString;}

        char x = (char)(enPassantString.charAt(0) + 48);
        char y = enPassantString.charAt(1);
        return x+""+y;
    }

    private void updateHalfmove() {
        int halfmoveInt = Integer.parseInt(halfmove);
        halfmoveInt = board.getResetHalfmove() ? 0 : halfmoveInt+1;
        String halfmoveString = ""+halfmoveInt;
        if (halfmoveInt == 50) {throw new GameOverException("Draw by fifty-move rule");}
        this.halfmove = halfmoveString;
    }

    private void updateFullmove() {

        int fullmoveInt = Integer.parseInt(fullmove);
        this.fullmove = board.isWhitePlaying() ? ""+(fullmoveInt + 1) : fullmove;
    }
    
    // Used once when initialising
    private void setCastleCoordsAndEnPassant() {        
        if (castle.equals("-")) {
            board.makeCastleIllegal();

        } else {
            String[] remainingCastleCoords = new String[4];
            if (castle.contains("K")) {remainingCastleCoords[0] = "71";}
            if (castle.contains("Q")) {remainingCastleCoords[1] = "31";}
            if (castle.contains("k")) {remainingCastleCoords[2] = "78";}
            if (castle.contains("q")) {remainingCastleCoords[3] = "38";}    
            
            board.setCastleCoords(remainingCastleCoords);
        }
    
        if (enPassant.equals("-")) {
            board.setEnPassantSquare(enPassant);
        } else {
            int x = enPassant.charAt(0) - 96;
            int y = enPassant.charAt(1) - 48;
            board.setEnPassantSquare(x+""+y);
        }
    }
}
