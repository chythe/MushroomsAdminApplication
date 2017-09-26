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
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.{CheckBoxTableCell, PropertyValueFactory}
import javafx.util.Callback
import javax.accessibility.AccessibleRole

import components.{TablesContainer}
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
import scalafx.scene.control._
import scalafxml.core.macros.sfxml
import scalaj.http.{Http, HttpOptions}

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */

@sfxml
class UserController(
                      @FXML private val usersTable: TableView[User],
                      @FXML private val userTab: Tab) {

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

  }

}
