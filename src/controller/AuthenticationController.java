package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.GymCenter;
import model.User;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class AuthenticationController {
    public Button loginButton;
    public Button registerButton;
    public Button cancelButton;
    public Button submitButton;
    public DatePicker birthdayInput;
    public PasswordField passwordInput;
    public TextField userInput;
    public TextField nameInput;
    public TextField emailInput;
    public TextField emailVerificationInput;
    public TextField usernameInput;
    public TextField addressInput;
    public TextField zipCode1;
    public TextField zipCode2;
    public PasswordField passConfirm;
    public Button specs;

    int min = 8;
    int max = 64;
    int digit = 0;
    int special = 0;
    int upCount = 0;
    int loCount = 0;

    public void loginAction(ActionEvent actionEvent) throws IOException {
        ArrayList<String> userInfo = new ArrayList<>();
        userInfo.add(userInput.getText());
        userInfo.add(passwordInput.getText());

        if (verifyUser(userInfo)) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Username/Password wrong!");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        }
    }

    public void registerAction(ActionEvent actionEvent) throws IOException {
        //get reference to the button's stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setResizable(false);
        //load up OTHER fxml document
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/RegistrationForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void handleSubmitAction(ActionEvent actionEvent) throws IOException {
        String name = verifyName(nameInput);

        Date birthday = verifyBirthday(birthdayInput);

        String password = verifyPassword(passwordInput.getText(), passConfirm.getText());

        String address = verifyAddress(addressInput);

        String email = verifyEmail(emailInput, emailVerificationInput);

        String username = verifyUsername(usernameInput);

        String zipCode = verifyZipcode(zipCode1, zipCode2);

        if (username != null && email != null && name != null && password != null && birthday != null) {
            GymCenter.addUnconfirmedUser(new User(name, email, username, password, zipCode, birthday, address));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("User registered with success!");
            alert.setContentText("Please confirm your registration on your profile.");
            alert.showAndWait();

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/AuthenticationForm.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setContentText("Failed to register user!");
            alert.showAndWait();
        }
    }

    public void backToLoginPage(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setContentText("All information WILL be lost!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("../fxml/AuthenticationForm.fxml"));
            stage.setTitle("Gym Master 1000");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }
    }

    public void showSpecs(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password minimum requirements");
        alert.setHeaderText("Password must have:");
        alert.setContentText("Must have at least " + min + " characters" + "\nNeeds at least one digit" + "\nNeeds at least one special character");
        alert.showAndWait();
    }

    private boolean verifyUser(ArrayList<String> userInfo) {
        return GymCenter.isUser(userInfo.get(0), userInfo.get(1));
    }

    private String verifyName(TextField nameInput) {
        String name = null;
        if (nameInput.getText() != null) {
            name = nameInput.getText();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You must provide your name!");
        }
        return name;
    }

    private String verifyPassword(String p1, String p2) {
        if (p1.length() >= min && p1.length() <= max) {
            if (Objects.equals(p1, p2)) {
                for (int i = 0; i < p1.length(); i++) {
                    char c = p1.charAt(i);
                    if (Character.isUpperCase(c)) {
                        upCount++;
                    }
                    if (Character.isLowerCase(c)) {
                        loCount++;
                    }
                    if (Character.isDigit(c)) {
                        digit++;
                    }
                    if (c >= 33 && c <= 46 || c == 64) {
                        special++;
                    }
                }
                if (special >= 1 && loCount >= 1 && upCount >= 1 && digit >= 1) {
                    return passwordInput.getText();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Passwords do not match!");
                a.showAndWait();
            }

            if (p1.length() < min) {
                for (int i = 0; i < p1.length(); i++) {
                    char c = p1.charAt(i);
                    if (Character.isLowerCase(c)) {
                        loCount++;
                    }
                }

                if (loCount > 0) {
                    System.out.println(" Password must be at least " + min + " characters:");
                    System.out.println(" You need at least one upper case character:");
                    System.out.println(" You need at least one digit:");
                    System.out.println(" You need at least one special character:");


                }
            } else if (p1.length() < min && upCount > 1) {
                for (int i = 0; i < p1.length(); i++) {
                    char c = p1.charAt(i);
                    if (Character.isLowerCase(c)) {
                        loCount++;
                    }
                    if (Character.isUpperCase(c)) {
                        upCount++;
                    }
                }
                if (loCount > 0 && upCount > 0) {
                    System.out.println(" Password must be at least " + min + " characters");
                    System.out.println(" You need at least one digit:");
                    System.out.println(" You need at least one special character");
                }
            }
            if (p1.length() > max || p1.length() >= max && upCount > 1 && loCount > 1 && digit > 1) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error!");
                a.setContentText("Password too long!" + "\n\n Must have UP TO 64 characters.");
                a.showAndWait();

            }
            if (p1.length() >= min && p1.length() <= max && loCount > 0 && upCount > 0 && digit > 0 && special == 0) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error!");
                a.setContentText("You need at least a special character");
                a.showAndWait();
            }
            if (p1.length() >= min && p1.length() <= max && loCount > 0 && upCount > 0 && digit == 0 && special == 0) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error!");
                a.setContentText("You need at least one digit" + "\nYou need at least one special character");
                a.showAndWait();
            }
        }
        return null;
    }

    private Date verifyBirthday(DatePicker birthdayInput) {
        if (birthdayInput != null) {
            Date d = null;
            LocalDate localDate = birthdayInput.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            d = Date.from(instant);

            if (d.before(new Date(System.currentTimeMillis()))) {
                return d;
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error!");
                a.setContentText("Hey pal. You're not alive yet. You cant't workout. Check your birthday.");
                a.showAndWait();
            }
        }
        return null;
    }

    private String verifyAddress(TextField addressInput) {
        if (addressInput.getText() != null) {
            return addressInput.getText();
        } else {
            return "no address";
        }
    }

    private String verifyUsername(TextField usernameInput) {
        if (usernameInput.getText().length() >= 6) {
            return usernameInput.getText();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Username is too short");
            a.showAndWait();
        }
        return null;
    }

    private String verifyEmail(TextField emailInput, TextField emailVerificationInput) {
        if (Objects.equals(emailInput.getText(), emailVerificationInput.getText())) {
            if (emailInput.getText().contains("@")) {
                int i = emailInput.getText().indexOf('@');
                String domain = emailInput.getText().substring(i);
                if (domain.contains(".")) {
                    return emailInput.getText();
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Invalid email.");
            a.showAndWait();
        }
        return null;
    }

    private String verifyZipcode(TextField zipCode1, TextField zipCode2) {
        String s1 = zipCode1.getText();
        String s2 = zipCode2.getText();

        if (s1.length() == 4 && s2.length() == 3) {
            return s1 + "-" + s2;
        } else {
            return null;
        }
    }
}
