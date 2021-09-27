package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InvManager {

    @FXML
    private TableView<?> tableIngredients;

    @FXML
    private TableColumn<?, ?> tabIng;

    @FXML
    private TableColumn<?, ?> tabQuant;

    @FXML
    private Label labList;

    @FXML
    private Button btnAdd;

    @FXML
    void addIngredient(ActionEvent event) {

    }

}
