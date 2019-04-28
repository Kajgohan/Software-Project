package bishopfish.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AboutPageController extends Controller {
    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }

    public void creditButton(ActionEvent event) throws IOException {
        loadFxml("creditsPage.fxml");
    }
}
