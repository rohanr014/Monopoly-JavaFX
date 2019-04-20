package app.engine.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class GameFileHandler {
    /**
     * @param directory ex) Vanilla
     * @param filename ex) Game1 (without .properties)
     */
    public static ResourceBundle createPropertiesFile(
            String directory,
            String filename,
            ArrayList<String> playerNames,
            ArrayList<String> playerPieces,
            String propFile,
            String rulesFile
    ){
        try {
            File file = new File("gamedata/" + directory + "/" + filename + ".properties");
            if(!file.exists() || (file.exists() && file.delete() && file.createNewFile())) {
                var output = new BufferedWriter(new FileWriter(file));

                for (int i = 0; i < playerNames.size(); i++) {
                    String currentPlayerString = "player" + i + "=" + playerNames.get(i) + "," + "data/" + directory + "/" + "Pieces/" + playerPieces.get(i);
                    output.write(currentPlayerString + "\n");
                }

                output.write("prop_file=" + directory + "/" + propFile + "\n");
                output.write("rules_file=" + directory + "/" + rulesFile);
                output.close();
            }

            return getGamedata(directory, filename);
        } catch( IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResourceBundle getGamedata(String directory, String filename) throws IOException {
        var fis = new FileInputStream(new File("gamedata" + "/" + directory + "/" + filename + ".properties"));
        return new PropertyResourceBundle(fis);
    }
}
