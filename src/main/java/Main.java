import javaFX.controller.OrdersControlling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String fxmlFile = "/fxml/orders.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));

        OrdersControlling ordersControlling = loader.getController();
        ordersControlling.setMainStage(primaryStage);

        primaryStage.setTitle("Заказы");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(950);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        System.exit(0);
    }
}
