package app.engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class GameFileWriter {

    private String[] playerNames;
    private String[] gamePieces;
    private String propFile;
    private String rulesFile;


    public GameFileWriter(String[] playerNames, String[] playerPieces, String propFile, String rulesFile){
        this.playerNames = playerNames;
        this.gamePieces = playerPieces;
        this.propFile = propFile;

        this.rulesFile = "vanillaRules.properties";
        // eventually - this.rulesFile = rulesFile;

        writePropertiesFile();
    }

    private void writePropertiesFile(){
        try {
            File file = new File("data/" + propFile);
            file.createNewFile();

            var output = new BufferedWriter(new FileWriter(file));

            for(int i=0; i<playerNames.length; i++){
                String currentPlayerString = "player" + i + "=" + playerNames[i];
                String currentPlayerPiece = "piece" + i + "=" + gamePieces[i];
                output.write(currentPlayerString + "\n");
            }

            output.write("prop_file=" + propFile);
            output.write("rules_file=" + rulesFile);

        } catch( IOException e) {
            e.printStackTrace();
        }

    }

}
