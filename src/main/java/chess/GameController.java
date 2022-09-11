package chess;

import java.io.IOException;
import java.util.List;

import chess.FileFamily.Saver;
import chess.GameFamily.Game;
import chess.GameFamily.GameOverException;
import chess.PieceFamily.Piece;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class GameController {

    private Game game = new Game();
    
    private String style = "default";
    private boolean gameOver = false;


    @FXML private ImageView boardView;
    @FXML private GridPane sq;
    @FXML private GridPane dottss;


    @FXML public void initialize() {
        try {
            String fen = new Saver().getChosenGame();
            game = new Game(fen);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("gamecontroller init went wrong");
        }
        drawBoard();
    }

    @FXML
    private void updateImages() {
        ImageView temp;
        Image image;
        Piece piece;

        for (Node node : sq.getChildren()) {

            int x = GridPane.getColumnIndex(node) + 1;
            int y = 8 - GridPane.getRowIndex(node);
            String coord = x+""+y;
            piece = game.getPlacements().get(coord);

            temp = (ImageView)node;
            image = (piece == null) ? null : new Image(getClass().getResourceAsStream("images/pieces/"+style+"/"+ piece.toString()+".png"));
            temp.setImage(image);
        }
    }

    // fork
    @FXML public void fork() {
        new Saver().fork(game.getFenstring());
    }

    @FXML public void backToMainmenu() throws IOException {
        if (!gameOver) {
            new Saver().replaceLast(game.getFenstring());
        }
        App.setRoot("Mainmenu");
    }

    @FXML
    public void mouseEntered(MouseEvent e) {
        if (gameOver) {return;}
        
        Node source = (Node)e.getSource() ;
        int x = GridPane.getColumnIndex(source) + 1;
        int y = 8 - GridPane.getRowIndex(source);
        String coord = x+""+y;

        try {
            
            List<String> dots = game.clicked(coord);
            updateImages();
            drawDots(dots);

        } catch (GameOverException f) {
            
            updateImages();
            drawDots(game.getKing());
            System.out.println(f.getMessage());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Game over");
            alert.setContentText(f.getMessage());
            alert.showAndWait();
            gameOver = true;
            
        }
    }

    @FXML private void drawDots(List<String> dots) {
        ImageView temp;

        for (Node node : dottss.getChildren()) {
            temp = (ImageView)node;
            int x = GridPane.getColumnIndex(node) + 1;
            int y = 8 - GridPane.getRowIndex(node);
            String coord = x+""+y;

            if (dots.contains(coord)) {
                temp.setImage(new Image(getClass().getResourceAsStream("images/other/dot.png")));
            } else {temp.setImage(null);}
        }
    }



    @FXML private void changeStyle() {
        style = style.equals("default") ? "pixel" : "default";
        drawBoard();
    }




    @FXML private void drawBoard() {
        
        boardView.setImage(new Image(getClass().getResourceAsStream("images/boards/"+style+"/board.png")));
        updateImages();
    }
}