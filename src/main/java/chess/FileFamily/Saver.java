package chess.FileFamily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// saver:
// move game to last,
// or add new
public class Saver implements ISaver{

    private List<String> savedGames = new ArrayList<>();    

    private static final String filename = "src/main/java/chess/FileFamily/Games.txt";
    private static final String defaultFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public Saver() {

        load();

    }

    // used to select game in main menu
    public void selectAndSave(String activeGame) {
        if (activeGame.equals(defaultFen)) {
            addAndSave();
            return;
        }
        if (!savedGames.contains(activeGame)) {throw new IllegalStateException("tried to use non existent game");}

        savedGames.remove(activeGame);
        savedGames.add(activeGame);
        save();
    }

    // used for new game in main menu
    public void addAndSave() {
        savedGames.add(defaultFen);
        save();
    }

    // used for forking during game
    public void fork(String fenstring) {
        savedGames.remove(savedGames.size() - 1);
        savedGames.add(fenstring);
        savedGames.add(fenstring);
        save();
    }

    public void replaceLast(String fenstring) {
        savedGames.remove(savedGames.size() - 1);
        savedGames.add(fenstring);
        save();
    }

    public String getChosenGame() {
        if (savedGames.isEmpty()) {return defaultFen;}
        int index = savedGames.size() - 1;
        return savedGames.get(index);
    }

     public Map<String, String> getGamesMap() {
        Map<String, String> savedGamesMap = new LinkedHashMap<>();
        String gameID;
        int gameNumber = 1;

        for (String fenstring : savedGames) {
            gameID = "Game " + gameNumber;
            savedGamesMap.put(gameID, fenstring);
            gameNumber++;
        }
        return savedGamesMap;
    }

    public void remove(String fen) {
        if (!savedGames.contains(fen)) {return;}
        savedGames.remove(fen);
    }

    public List<String> getGamesList() {
        return savedGames;
    } 

    @Override
    public void load() {
        try (FileReader fr = new FileReader(filename)) {
            BufferedReader br = new BufferedReader(fr);
            
            String fenstring;
            while ((fenstring = br.readLine()) != null) {

                if (!Pattern.matches("((([prnbqkPRNBQK12345678]*/){7})([prnbqkPRNBQK12345678]*)) (w|b) ((K?Q?k?q?)|\\-) (([abcdefgh][36])|\\-) (\\d*) (\\d*)", fenstring)) {throw new IllegalStateException("illegal fen in file");}
                else {savedGames.add(fenstring);}
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        savedGames = savedGames.stream()
            .filter(game -> Pattern.matches("((([prnbqkPRNBQK12345678]*/){7})([prnbqkPRNBQK12345678]*)) (w|b) ((K?Q?k?q?)|\\-) (([abcdefgh][36])|\\-) (\\d*) (\\d*)", game))
            .collect(Collectors.toList());

        try (FileWriter fw = new FileWriter(filename)) {
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(String.join("\n", savedGames));

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
