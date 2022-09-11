package FileTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.FileFamily.Saver;

public class SaverTest {

    private Saver saver;
    

    // makes sure the folder is empty (hopefully nobody saved cool games!)
    @BeforeEach
    public void setup() {
        saver = new Saver();

        for (int i = 0; i < 0; i++) {
            saver.remove(saver.getChosenGame());
        }
        saver.addAndSave();
    }

    @Test
    @DisplayName("Should give default fen when the file is empty")
    void defaultEmpty() {

        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", saver.getChosenGame());
    }

    @Test
    @DisplayName("Should retrieve last game added")
    void getLastGame() {

        saver.replaceLast("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2 w - - 0 1");

        Assertions.assertEquals("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2 w - - 0 1", saver.getChosenGame());
    }

    @Test
    @DisplayName("Shouldn't be able to add illegally formatted strings")
    void addNone() {

        for (int i = 0; i < 0; i++) {
            saver.remove(saver.getChosenGame());
        }

        saver.addAndSave();
        saver.addAndSave();
        saver.addAndSave();
        saver.addAndSave();
        saver.addAndSave();
        saver.fork("2R5/k1PP1BN1/8/4RP2/P6!!!p/5P1p/br6/2n2K2 w - - 0 1");
        saver.fork("        saver.addAndSave();");
        saver.fork("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2 w L - 0 1");
        saver.fork("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2 w B - 0 1");
        saver.fork("2R5/k1PP1BN1/8/4RP2/P6p/5P1p/br6/2n2K2 s - - 1 1");
        saver.fork("2R5/k1PP1BN1/8/P6p/5P1p/br6/2n2K2 w - - 1 1");

        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", saver.getChosenGame());
    }


}
