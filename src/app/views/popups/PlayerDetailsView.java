package app.views.popups;

import app.engine.agent.Player;
import app.engine.space.ColorProperty;
import app.engine.space.Property;
import app.engine.space.Railroad;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Properties;

public class PlayerDetailsView extends PopUpView {
    VBox myRoot;
    Scene myScene;
    Player myPlayer;
    VBox myVBox;

    public PlayerDetailsView(Player player) {
        super("player details");
        myPlayer = player;
        initialize();
    }

    private void initialize(){
        myVBox = new VBox();
        setBasicInfo();
        setColorProperties();
        setRailroads();
        setUtilities();
    }

    private void setRailroads() {
        HBox newHBox = new HBox();
        newHBox.getChildren().add(new Text("Railroads: "));
        for (Property railroad: myPlayer.getProperties()) {
            if (railroad instanceof Railroad) {
                newHBox.getChildren().add(new Text(railroad.getName()+", "));
            }
        }
        myRoot.getChildren().add(newHBox);
    }

    private void setUtilities() {
        HBox newHBox = new HBox();
        newHBox.getChildren().add(new Text("Utilities: "));
        for (Property utility: myPlayer.getProperties()) {
            if (utility instanceof Railroad) {
                newHBox.getChildren().add(new Text(utility.getName()+", "));
            }
        }
        myRoot.getChildren().add(newHBox);
    }

    private void setBasicInfo() {
        HBox horizontal = new HBox();
        horizontal.getChildren().add(new Text("Name: "+ myPlayer.getName()));
        horizontal.getChildren().add(new Text("$: "+ myPlayer.getWallet()));
        myVBox.getChildren().add(horizontal);
        myRoot.getChildren().add(myVBox);
    }

    private void setColorProperties(){
        VBox newVBox = new VBox();
        newVBox.getChildren().add(new Text("Color Properties"));
        for (Property property: myPlayer.getProperties()) {
            if (property instanceof ColorProperty) {
                newVBox.getChildren().add(new Text(property.getName()+": Number houses- "+((ColorProperty) property).getHouses()+", Number hotels- "+((ColorProperty) property).getHotels()));
            }
        }
        myRoot.getChildren().add(newVBox);
    }


    @Override
    protected Scene myScene() {
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        return myScene;
    }
}
