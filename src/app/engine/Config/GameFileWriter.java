package app.engine.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameFileWriter {

    private String filename;
    private String[] playerNames;
    private String[] gamePieces;
    private String propFile;
    private String rulesFile;


    public GameFileWriter(String filename, String[] playerNames, String[] playerPieces, String propFile, String rulesFile){
        this.filename = filename;
        this.playerNames = playerNames;
        this.gamePieces = playerPieces;
        this.propFile = propFile;

        this.rulesFile = "Vanilla/vanillaRules";
        // eventually - this.rulesFile = rulesFile;

        writePropertiesFile();
    }

    private void writePropertiesFile(){
        try {
            File file = new File("data/Vanilla/" + filename + ".properties");
            file.createNewFile();

            var output = new BufferedWriter(new FileWriter(file));

            for(int i=0; i<playerNames.length; i++){
                String currentPlayerString = "player" + i + "=" + playerNames[i] + "," + gamePieces[i];
                output.write(currentPlayerString + "\n");
            }

            output.write("prop_file=" + propFile);
            output.write("rules_file=" + rulesFile);

        } catch( IOException e) {
            e.printStackTrace();
        }

    }

}
