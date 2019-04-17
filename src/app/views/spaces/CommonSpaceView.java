package app.views.spaces;

import app.engine.space.CommonSpace;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CommonSpaceView extends SpaceView<CommonSpace> {
    private ImageView myCommonImageView;


    public CommonSpaceView(CommonSpace model) {
        super(model.getName(), model);
    }

    @Override
    public void initialize(){
        System.out.println(this.getMyName());
        myRoot = new StackPane();
        String name = this.getMyName();

        //horrible coding right here, please refactor
        myCommonImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(name + ".png")));

//        if(name.equals("Go")){
//            myCommonImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Go.png")));
//        }else if(name.equals("Income Tax")){
//            myCommonImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Super Tax.png")));
//        }else if(name.equals("Go To Jail")){
//            myCommonImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Go To Jail.png")));
//        }else if(name.equals("Free Parking")){
//            myCommonImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Free Parking.png")));
//        }else if(name.equals("Super Tax")){
//            myCommonImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Super Tax.png")));
//        }else if(name.equals("In Jail")){
//            myCommonImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("In Jail.png")));
//        }
        myCommonImageView.setFitHeight(40);
        myCommonImageView.setFitWidth(40);
        myRoot.getChildren().add(myCommonImageView);
        myRoot.setStyle("-fx-background-color: white");

    }

    @Override
    public void adjustSize() {

    }

    @Override
    public void offerPopUp() {

    }
}
