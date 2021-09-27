package ui;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ControllerModel;
import model.Employee;

public class CucharitaControllerGUI {

	private Stage mainStage;
	
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
    private Pane mainPane;
    
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
	
	private ObservableList<Employee> observableList;
	
	private ControllerModel employeesManager;
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	public CucharitaControllerGUI() {
		
		dpBirth= new DatePicker();
		dpBirth.setEditable(false);
		employeesManager=new ControllerModel();
	}
	
	@FXML
    public void signIn(ActionEvent event) throws IOException {
		
		int check=employeesManager.checkUsers(txtIdInside.getText(),txtPasswordInside.getText());
		
		if(check!=999999) {
			changeWindows("model-Menu.fxml");
		}
		else {
			advertisement("El id o la contraseña es incorrecta");
		}
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
    	changeWindows("Registration.fxml");
    }
    
    @FXML
    public void efectiveSignUp(ActionEvent event) throws IOException {
    	LocalDate date=dpBirth.getValue();
    	String birth=date.toString();
    	String name=txtName.getText();
    	String id=txtId.getText();
    	String password=txtPassword.getText();
    	
    	boolean check=employeesManager.confirmNewUser(id);
    	boolean correctly=employeesManager.addEmployee(new Employee(name,id,birth,password));
    	
    	if(check==true) {
    		advertisement("Ya exite un usuario con esta cédula");
    	}
    	else if(correctly==true) {
    		advertisement("El empleado ha sido agregado");
    		changeWindows("model-Autentication.fxml");
    	}
    	else {
    		advertisement("No se pudo agregar al empleado");
    	}
    	
    }
    
    public void firstWindow() throws IOException {
		
		changeWindows("model-Autentication.fxml");
	}
    
	public void itializeTableView(){
		
    	observableList= FXCollections.observableArrayList(employeesManager.getEmployees());
    	
    	tvEmployeesList.setItems(observableList);
    	tcName.setCellValueFactory(new PropertyValueFactory<Employee,String>("Name"));
    	tcId.setCellValueFactory(new PropertyValueFactory<Employee,String>("Id"));
    	tcBirth.setCellValueFactory(new PropertyValueFactory<Employee,String>("Birth"));
    	tcPassword.setCellValueFactory(new PropertyValueFactory<Employee,String>("Password"));
	}
	
	@FXML
    public void changePassword(ActionEvent event) throws IOException {
		
		changeWindows("Change-Password.fxml");
	}
	
	@FXML
    public void changeAction(ActionEvent event) throws IOException {
		
		int check=employeesManager.checkUsers(tfId.getText(),tfOldPassword.getText());
		
		if(check!=999999) {
			employeesManager.getEmployees().get(check).setPassword(tfNewPassword.getText());
			advertisement("La contraseña del empleado "+employeesManager.getEmployees().get(check).getName()+" ha sido cambiada exitosamente");
			showEmployeeList(event);
		}
		else {
			advertisement("El id o la contraseña es incorrecta");
		}
		
    }
	
	@FXML
	public void changePassToMenu(ActionEvent event) throws IOException {
		changeWindows("model-Menu.fxml");
	}
	
	
	
	public void advertisement(String info) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(info);
		alert.showAndWait();
	}
	
	@FXML
	public void showEmployeeList(ActionEvent event) throws IOException {
		changeWindows("model-EmployeesList.fxml");
		
		itializeTableView();
	}
	
	//Por hacer
	@FXML
	public void showInvetory(ActionEvent event)throws IOException {
		changeWindows("");
	}
	
	public void changeWindows(String jvfxml) throws IOException {
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(jvfxml));
		fxmlLoader.setController(this);
		Parent change=fxmlLoader.load();
		mainPane.getChildren().setAll(change);
	}
}
