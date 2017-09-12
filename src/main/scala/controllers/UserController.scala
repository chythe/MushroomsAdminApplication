package controllers

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.{CheckBoxTableCell, PropertyValueFactory}
import javax.accessibility.AccessibleRole

import enum.Gender
import model.User
import services.{AuthenticationService, PdfService, UserService}

import scala.xml.{Elem, XML}
import scalafx.event
import scalafx.scene.control.{Tab, TableView}
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */

@sfxml
class UserController(
                      @FXML private val usersTable: TableView[User],
                      @FXML private val userTab: Tab) {

  loadUsers();
  exportToPdf();


  def loadUsers() = {
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

  def toRow(u: User): String = {
    return "<tr>" +
      "<td>" + u.id + "</td>" +
      "<td>" + u.username + "</td>" +
      "<td>" + u.email + "</td>" +
      "<td>" + u.role + "</td>" +
      "<td>" + u.firstName + "</td>" +
      "<td>" + u.lastName + "</td>" +
      "<td>" + u.birthDate + "</td>" +
      "<td>" + u.country + "</td>" +
      "<td>" + u.city + "</td>" +
      "<td>" + u.gender + "</td>" +
      "<td>" + u.level + "</td>" +
      "</tr>"
  }

  def toHtmlRows(users: ObservableList[User]) : String = {
    var content = "";
    users.forEach(u => content += toRow(u))
    return mainRow() + content;
  }

  def mainRow() : String = {
    return "<thead><tr>" +
      "<td>ID</td>" +
      "<td>Username</td>" +
      "<td>Email</td>" +
      "<td>Role</td>" +
      "<td>First name</td>" +
      "<td>Last name</td>" +
      "<td>Birth date</td>" +
      "<td>Country</td>" +
      "<td>City</td>" +
      "<td>Gender</td>" +
      "<td>Level</td>" +
    "</tr></thead><tbody>"
  }

  def exportToPdf() = {
    val tableContent = PdfService.createTableContent(userTab.text.value, toHtmlRows(usersTable.getItems))
    PdfService.exportToPdf(XML.loadString(tableContent));
  }
}