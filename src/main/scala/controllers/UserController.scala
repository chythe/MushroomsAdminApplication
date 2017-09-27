package controllers
import collection.mutable._
import javafx.{scene => jfxsc, stage => jfxst}
import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import java.util
import java.util.stream.Collectors
import javafx.collections.{FXCollections, ObservableList}
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.{CheckBoxTableCell, PropertyValueFactory}
import javafx.util.Callback
import javax.accessibility.AccessibleRole

import components.TablesContainer
import commands.DeleteUsersCommand
import enum.Gender
import exceptions.LoginFailedException
import model.User
import net.liftweb.json.DefaultFormats
import pdf.PdfBuilder
import services.{AuthenticationService, PdfService, UserService}

import scala.compat.java8.JFunction
import scala.xml.{Elem, XML}
import scalafx.beans.binding.Bindings
import scalafx.event
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.control._
import scalafx.scene.layout.GridPane
import scalafxml.core.macros.sfxml
import scalaj.http.{Http, HttpOptions}

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */

@sfxml
class UserController(
                      @FXML private val usersTable: TableView[User],
                      @FXML private val userTab: Tab,
                      @FXML private val createUserButton: Button) {

  loadUsers();
  TablesContainer.usersTable = Option(usersTable);

  def loadUsers() = {
    usersTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteUser();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    usersTable.setContextMenu(contextMenu);
    val users = UserService.getAll(AuthenticationService.token.get)
    val usersList: ObservableList[User] = FXCollections.observableArrayList();
    users.get.foreach(u => usersList.add(u))
    usersTable.setItems(usersList);
  }

  def updateUsername(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.username = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateEmail(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.email = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateRole(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.role = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateFirstName(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.firstName = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateLastName(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.lastName = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateBirthDate(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.birthDate = LocalDate.parse(event.getNewValue(), DateTimeFormatter.ISO_LOCAL_DATE).toString();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateCountry(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.country = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateCity(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.city = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateGender(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.gender = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def updateLevel(event: CellEditEvent[User, String]) = {
    val user = event.getRowValue();
    user.role = event.getNewValue();
    UserService.update(AuthenticationService.token.get, user);
  }

  def deleteUser() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    val userIdsList = new ArrayBuffer[Long]();
    usersTable.getSelectionModel().getSelectedItems().forEach(user => {
      userIdsList.append(user.id)
    })
    val userIdsArray = userIdsList.toArray[Long]
    UserService.delete(
      AuthenticationService.token.get,
      new DeleteUsersCommand(userIdsArray))
    usersTable.getItems().removeAll(usersTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createUser() = {
    // Create the custom dialog.
    val dialog = new Dialog[User]();
    dialog.setTitle("Create User");
    dialog.setHeaderText("Create User");

    // Set the button types.
    val createButtonType: ButtonType = new ButtonType("Create", ButtonData.OKDone);
    dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.Cancel);

    // Create the username and password labels and fields.
    val grid: GridPane = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    val email: TextField = new TextField();
    email.setPromptText("email@email.com");
    val username: TextField = new TextField();
    username.setPromptText("username");
    val password: PasswordField = new PasswordField();
    password.setPromptText("****");
    val role: TextField = new TextField();
    role.setPromptText("ROLE");
    val firstName: TextField = new TextField();
    firstName.setPromptText("First Name");
    val lastName: TextField = new TextField();
    lastName.setPromptText("Last Name");
    val birthDate: TextField = new TextField();
    birthDate.setPromptText("YYYY-MM-DD");
    val country: TextField = new TextField();
    country.setPromptText("Country");
    val city: TextField = new TextField();
    city.setPromptText("City");
    val gender: TextField = new TextField();
    gender.setPromptText("GENDER");
    val level: TextField = new TextField();
    level.setPromptText("LEVEL");

    grid.add(new Label("Email:"), 0, 0);
    grid.add(email, 1, 0);
    grid.add(new Label("Username:"), 0, 1);
    grid.add(username, 1, 1);
    grid.add(new Label("Password:"), 0, 2);
    grid.add(password, 1, 2);
    grid.add(new Label("Role:"), 0, 3);
    grid.add(role, 1, 3);
    grid.add(new Label("First Name:"), 0, 4);
    grid.add(firstName, 1, 4);
    grid.add(new Label("Last Name:"), 0, 5);
    grid.add(lastName, 1, 5);
    grid.add(new Label("Birth Date:"), 0, 6);
    grid.add(birthDate, 1, 6);
    grid.add(new Label("Country:"), 0, 7);
    grid.add(country, 1, 7);
    grid.add(new Label("City:"), 0, 8);
    grid.add(city, 1, 8);
    grid.add(new Label("Gender:"), 0, 9);
    grid.add(gender, 1, 9);
    grid.add(new Label("Level:"), 0, 10);
    grid.add(level, 1, 10);

    // Enable/Disable login button depending on whether a username was entered.
//    val createButton: Node = dialog.getDialogPane().lookupButton(createButtonType);
//    createButton.setDisable(true);

    // Do some validation (using the Java 8 lambda syntax).
//    username.textProperty().addListener((observable, oldValue, newValue) -> {
//      loginButton.setDisable(newValue.trim().isEmpty());
//    });

    dialog.getDialogPane().setContent(grid);

    dialog.resultConverter = dialogButton =>
      if (dialogButton == createButtonType)
        User(0,
          username.text(),
          email.text(),
          role.text(),
          firstName.text(),
          lastName.text(),
          birthDate.text(),
          country.text(),
          city.text(),
          gender.text(),
          level.text(),
          "")
      else
        null

    val result = dialog.showAndWait()

    result match {
      case Some(User(i, u, e, r, f, l, b, c, c1, g, l1, f1)) =>
        UserService.create(
          AuthenticationService.token.get,
          new User(i, u, e, r, f, l, b, c, c1, g, l1, f1))
      case None               => println("Dialog returned: None")
    }
  }

}
