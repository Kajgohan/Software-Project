package bishopfish.controllers;

import bishopfish.db.DBCustom;
import bishopfish.utils.TwoFactorAuth;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TwoFactorPage extends Controller implements Initializable {

    TwoFactorAuth tfa = new TwoFactorAuth();
    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXButton authButton;

    @FXML
    private JFXTextField twofactortextbox;

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return(!(twofactortextbox.getText().isEmpty()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }



    public void onSubmitClick(ActionEvent actionEvent) throws IOException {
        if (tfa.verifyLogin(twofactortextbox.getText())) {
            String permissions = DBCustom.getPermissions(CSI.getInstance().getCurUser());
            if (!permissions.isEmpty()) {
                CSI.getInstance().setPermissions(permissions);
                CSI.getInstance().getMementoTimer().cancel();
                loadOuterFxml("homePage.fxml");
                //loadNewScene(actionEvent, "homePage.fxml");
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, twofactortextbox.getScene().getWindow(), "Error!", "Invalid Credentials, please generate a new code.");
            tfa = new TwoFactorAuth();
        }
    }

    public void get2FAClick(ActionEvent actionEvent) {
        tfa.sendVerification();
    }

}
