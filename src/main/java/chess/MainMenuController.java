package chess;

import java.io.IOException;
import java.util.Map;

import chess.FileFamily.Saver;
import chess.GameFamily.Game;
import chess.PieceFamily.Piece;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MainMenuController {
    
    private Saver saver;
    private Map<String, String> gamesMap;
    private String selectedFen;

    @FXML private ListView<String> listView;
    @FXML private ImageView boardView;
    @FXML private GridPane previewGrid;

    @FXML public void initialize() {
        saver = new Saver();
        loadList();
    }
    
    private void loadList() {
        selectedFen = saver.getChosenGame();
        gamesMap = saver.getGamesMap();
    
        listView.getItems().clear();
        listView.getItems().addAll(gamesMap.keySet());
        loadPreview(new Game(selectedFen));
    }

    @FXML public void handleMouseClick(MouseEvent arg0) {
        if (gamesMap.isEmpty()) {return;}
        selectedFen = gamesMap.get(listView.getSelectionModel().getSelectedItem());

        Game selectedGame = new Game(selectedFen);
        loadPreview(selectedGame);
    }

    @FXML public void newGame() throws IOException {
        saver.addAndSave();
        App.setRoot("Chess");
    }

    @FXML public void loadGame() throws IOException {
        saver.selectAndSave(selectedFen);
        App.setRoot("Chess");
    }

    @FXML public void deleteSelectedGame() {
        saver.remove(selectedFen);
        loadList();
        saver.save();

    }
   


    @FXML private void loadPreview(Game game) {
        boardView.setImage(new Image(getClass().getResourceAsStream("images/boards/default/board.png")));

        ImageView temp;
        Image image;
        Piece piece;

        for (Node node : previewGrid.getChildren()) {

            int x = GridPane.getColumnIndex(node) + 1;
            int y = 8 - GridPane.getRowIndex(node);
            String coord = x+""+y;
            piece = game.getPlacements().get(coord);

            temp = (ImageView)node;
            image = (piece == null) ? null : new Image(getClass().getResourceAsStream("images/pieces/default/"+ piece.toString()+".png"));
            temp.setImage(image);
        }
    } 
}
