package controllers

import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.User

import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */

@sfxml
class UserController(@FXML private val usersTable: TableView[User]) {

  loadUsers();

  def loadUsers() = {
    //      val users = UserService.getAll(AuthenticationService.token.get)
    //      val usersList: ObservableList[User] = FXCollections.observableArrayList();
    //      users.get.foreach(u => usersList.add(u))
    //      usersTable.setItems(usersList);
  }

  def updateUser(event: CellEditEvent[User, String]) = {
    // TODO pobrac zedytowanego usera
    //      UserService.update(AuthenticationService.token.get, event.getRowValue())
  }
}
