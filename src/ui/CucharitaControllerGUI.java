package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.FormatStyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.ControllerModel;
import model.Employee;
import model.Ingredient;

public class CucharitaControllerGUI {

	private Stage mainStage;
	
	//Employee section
	@FXML
	private TableView<Employee> tvEmployeesList;

	@FXML
	private TableColumn<Employee,String> tcName;

	@FXML
	private TableColumn<Employee,String> tcId;

	@FXML
	private TableColumn<Employee,String> tcBirth;

	@FXML
	private TableColumn<Employee,String> tcPassword;
	
	@FXML
    private TextField tfId;

    @FXML
    private TextField tfOldPassword;

    @FXML
    private TextField tfNewPassword;
    
    @FXML
    private VBox mainPane;
    
    @FXML
    private TextField txtIdInside;

    @FXML
    private TextField txtPasswordInside;
    
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtId;

    @FXML
    private DatePicker dpBirth;
	
	private ObservableList<Employee> observableListEmployee;
	
	private ControllerModel employeesManager;
	
	
	//Ingredient section
    @FXML
    private TableView<Ingredient> tableIngredients;

    @FXML
    private TableColumn<Ingredient, String> tabIng;

    @FXML
    private TableColumn<Ingredient, String> tabUnit;

	private ObservableList<Ingredient> observableListInv;
	
    @FXML
    private Label labList;

    @FXML
    private Button btnAdd;
    
    private ControllerModel ingredientsManager;
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	public CucharitaControllerGUI() {
		
		dpBirth= new DatePicker();
		dpBirth.setEditable(false);
	}
	
    @FXML
    void addIngredient(ActionEvent event) {
    	
    }
	
	@FXML
    public void signIn(ActionEvent event) throws IOException {
		
		int check=checkUsers(txtIdInside.getText(),txtPasswordInside.getText());
		
		if(check!=999999) {
			//FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Change-Password.fxml"));
			fxmlLoader.setController(this);
			Parent changeP=fxmlLoader.load();
			mainPane.getChildren().setAll(changeP);
		}
		else {
			advertisement("El id o la contraseña es incorrecta");
		}
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Registration.fxml"));
		fxmlLoader.setController(this);
		Parent changeR=fxmlLoader.load();
		mainPane.getChildren().setAll(changeR);
    }
    
    @FXML
    public void efectiveSignUp(ActionEvent event) {
    	LocalDate date=dpBirth.getValue();
    	String birth=date.setConverter(new LocalDateStringConverter(FormatStyle.SHORT));
    	
    }
	
	public void itializeTableViewEmployee(){
		
    	observableListEmployee= FXCollections.observableArrayList(employeesManager.getEmployees());
    	
    	tvEmployeesList.setItems(observableListEmployee);
    	tcName.setCellValueFactory(new PropertyValueFactory<Employee,String>("Name"));
    	tcId.setCellValueFactory(new PropertyValueFactory<Employee,String>("Id"));
    	tcBirth.setCellValueFactory(new PropertyValueFactory<Employee,String>("Birth"));
    	tcPassword.setCellValueFactory(new PropertyValueFactory<Employee,String>("Password"));
	} 
	
	public void itializeTableViewInv(){
		
    	observableListInv= FXCollections.observableArrayList(ingredientsManager.getIngredients());
    	
    	tableIngredients.setItems(observableListInv);
    	tabIng.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("Ingredient name"));
    	tabUnit.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("Unit"));
	} 
	
	@FXML
    public void changePassword(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Change-Password.fxml"));
		fxmlLoader.setController(this);
		Parent changeP=fxmlLoader.load();
		mainPane.getChildren().setAll(changeP);
		
	}
	
	@FXML
    public void changeAction(ActionEvent event) {
		
		int check=checkUsers(tfId.getText(),tfOldPassword.getText());
		
		if(check!=999999) {
			employeesManager.getEmployees().get(check).setPassword(tfNewPassword.getText());
			advertisement("La contraseña del empleado "+employeesManager.getEmployees().get(check).getName()+" ha sido cambiada exitosamente");
		}
		else {
			advertisement("El id o la contraseña es incorrecta");
		}
		
    }
	
	public void firstWindow() throws IOException {
		
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("model-Autentication.fxml"));
		fxmlLoader.setController(this);
		Parent firstW=fxmlLoader.load();
		mainPane.getChildren().setAll(firstW);
	}
	
	public int checkUsers(String idc, String passw) {
		int ok=999999;
		
		for(int i=0;i<employeesManager.getEmployees().size();i++) {
			if(employeesManager.getEmployees().get(i).getId().equals(idc)) {
				if(employeesManager.getEmployees().get(i).getPassword().equals(passw)) {
					ok=i;
				}
			}
		}
		
		return ok;
	}
	
	public void advertisement(String info) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(info);
		alert.showAndWait();
	}
	
	
}