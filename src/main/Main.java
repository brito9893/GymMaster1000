package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/AuthenticationForm.fxml"));
        primaryStage.setTitle("Gym Master 1000");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        root.getChildrenUnmodifiable();
    }

    public void setStageHomepage(Stage s) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/AuthenticationForm.fxml"));
        s.setTitle("Gym Master 1000");
        s.setScene(new Scene(root));
        s.show();
        s.setResizable(false);
        s.centerOnScreen();
    }

    public void setStageMainwindow(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.setMaximized(true);
    }
}
