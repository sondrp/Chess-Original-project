package GameTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.GameFamily.Board;
import chess.GameFamily.BoardIterator;

public class BoardIteratorTest {

    @Test
    @DisplayName("Making iterators for white pieces from standard position")
    void allWhite() {

        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", true);
        BoardIterator whitePieces = board.makeBoardIterator((map, coord) -> map.get(coord).isWhite());
        
        List<String> coordinates = new ArrayList<>();

        while (whitePieces.hasNext()) {
            coordinates.add(whitePieces.next());
        }

        List<String> result = Arrays.asList("12", "22", "32", "42", "52", "62", "72", "82", "11", "21", "31", "41", "51", "61", "71", "81");

        Assertions.assertEquals(result, coordinates);
    }

    @Test
    @DisplayName("Making iterators for white pieces (except king) from standard position")
    void allWhiteExceptKing() {

        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", true);
        BoardIterator whitePieces = board.makeBoardIterator((map, coordinate) -> 
            map.get(coordinate).isWhite() &&
            !map.get(coordinate).getType().equalsIgnoreCase("k"));
        
        List<String> coordinates = new ArrayList<>();

        while (whitePieces.hasNext()) {
            coordinates.add(whitePieces.next());
        }

        List<String> result = Arrays.asList("12", "22", "32", "42", "52", "62", "72", "82", "11", "21", "31", "41", /* "51" */ "61", "71", "81");

        Assertions.assertEquals(result, coordinates);
    }

    @Test
    @DisplayName("Finding all knights on the board")
    void allKnights() {

        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", true);
        BoardIterator whitePieces = board.makeBoardIterator((map, coordinate) -> 
            map.get(coordinate).getType().equalsIgnoreCase("n"));
        
        List<String> coordinates = new ArrayList<>();

        while (whitePieces.hasNext()) {
            coordinates.add(whitePieces.next());
        }

        List<String> result = Arrays.asList("28", "78", "21", "71");

        Assertions.assertEquals(result, coordinates);
    }
    
}
