package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

public static void main(String[]args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("model-Main.fxml"));
		CucharitaControllerGUI controller= new CucharitaControllerGUI();
		fxmlLoader.setController(controller);
		Parent root=fxmlLoader.load();
		Scene scene= new Scene(root);
		
		controller.setMainStage(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.setTitle("La Cucharita");
		primaryStage.show();
		
		controller.firstWindow();
		
	}
}
