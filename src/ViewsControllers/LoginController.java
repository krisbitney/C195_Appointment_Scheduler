package ViewsControllers;

import Dao.UserDao;
import Dao.UserDaoImp;
import Utils.InputValidator;
import Utils.UserSettings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginController extends InnerController {

    @FXML private Label userNameLabel;
    @FXML private Label passwordLabel;
    @FXML private TextField userNameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label loginMessageLabel;

    private ResourceBundle rb;

    public void initialize() {
        rb = ResourceBundle.getBundle("Resources.Login", UserSettings.locale);
        populateText();
    }

    private void populateText() {
        userNameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
    }

    public void login() {
        // validate text in login fields
        String userName = userNameTextField.getText().trim();
        String password = passwordField.getText().trim();
        if (InputValidator.invalidLoginText(userName) || InputValidator.invalidLoginText(password)) {
            displayErrorText(rb.getString("error_invalid"));
            return;
        }
        // verify username and password
        UserDao userDao = new UserDaoImp();
        Integer userId = userDao.getUserIdByUserNameAndPassword(userName, password);
        if (userId == null) {
            displayErrorText(rb.getString("error_invalid"));
            logLogin(false, userName);
            return;
        }
        logLogin(true, userName);
        UserSettings.userId = userId;
        UserSettings.userName = userName;
        this.outerController.toggleUiAccessOn();
        ScheduleController scheduleController = this.outerController.launchScheduleScene();
        scheduleController.checkForUpcomingAppointments(Duration.ofMinutes(15));
    }

    private void displayErrorText(String errorMessage) {
        loginMessageLabel.setText(errorMessage);
        loginMessageLabel.setTextFill(Color.RED);
    }

    private ZonedDateTime logLogin(boolean succeeded, String userName) {
        ZonedDateTime loginDateTime = ZonedDateTime.now(UserSettings.USER_TIME_ZONE);
        Path logPath = Paths.get("log.txt");
        // create log text file if it does not exist
        if (!Files.exists(logPath)) {
            try {
                Files.createFile(logPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // create log entry
        String entry;
        String loggedDateTime = loginDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        if (succeeded) {
            entry = "User " + userName + " successfully logged in at " + loggedDateTime + "\n";
        } else {
            entry = "User " + userName + " unsuccessfully attempted to log in at " + loggedDateTime + "\n";
        }
        // write entry to log
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginDateTime;
    }
}
