package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vitor on 10/07/2017.
 */
public class MainWindow {
    public Button logOffButton;
    public Button settingsButton;
    public Label currentTimeLabel;
    public Button userButton;
    public GridPane topGridPane;

    public void initialize() {
        bindToTime();
        topGridPane.setAlignment(Pos.CENTER_RIGHT);
    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        actionEvent -> {
                            Calendar time = Calendar.getInstance();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/MM/YYYY - HH:mm:ss");
                            currentTimeLabel.setText(simpleDateFormat.format(time.getTime()));
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void logOffAction(ActionEvent actionEvent) throws IOException {
        Stage s = (Stage) logOffButton.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("../fxml/AuthenticationForm.fxml"));
        s.setTitle("Gym Master 1000");
        s.setScene(new Scene(root));
        s.show();
        s.setResizable(false);
        s.centerOnScreen();
    }

    public void userInfoAction(ActionEvent actionEvent) {

    }

    public void settingsAction(ActionEvent actionEvent) {
    }

    public void openSettingsPage(ActionEvent actionEvent) {
    }
}
