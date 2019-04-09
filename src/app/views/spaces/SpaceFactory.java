package app.views.spaces;

import javafx.scene.layout.Pane;

import javax.swing.text.html.ImageView;



public class SpaceFactory {
    private ImageView piece1;
    public SpaceFactory() {

    }

    public SpaceView gen(Model model) {
        if(model.qwef) {
            return makeCorner();
        }
    }

    private SpaceView makeCorner() {
        return new SpaceView() {
            @Override
            public Pane initialize() {
                piece1.view();
                return null;
            }

            @Override
            public void spaceUpdate() {

            }
        };
    }
}
