import Utils.DbConnector;
import Utils.IOExecutor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewsControllers/OuterView.fxml"));
        primaryStage.setTitle("C195 Appointment Scheduler by Kris Bitney");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(e -> close());
        primaryStage.show();
    }

    public void close() {
        DbConnector.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
