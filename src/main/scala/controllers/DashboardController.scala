package controllers

import java.io.File
import java.net.URL
import java.util
import java.util.ResourceBundle
import javafx.collections.{FXCollections, ObservableList}
import javafx.{scene => jfxsc, stage => jfxst}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.PropertyValueFactory

import services.{AuthenticationService, DiscoveryService, TripService, UserService}

import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.{Parent, Scene}
import scalafx.scene.control.{Button, MenuBar, MenuItem}
import scalafx.stage.Stage
import scalafxml.core.{ControllerDependencyResolver, FXMLView, FxmlProxyGenerator, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import scalafx.Includes._
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import model._
import model.containers.UsersContainer

/**
  *
  */
@sfxml
class DashboardController (
                            private val borderPane: BorderPane,
                            private val dashboardMenuBar: MenuBar,
                            private val exitMenuItem: MenuItem,
                            private val logoutMenuItem: MenuItem,
                            private val usersTable: TableView[User],
                            private val tripsTable: TableView[Trip],
                            private val discoveriesTable: TableView[Discovery],
                            private val helpButton: Button
                         )
  {

    loadUsers();
    loadTrips();
    loadDiscoveries();

    /**
      *
      * @param event
      */
    def logoutMenuItemOnAction(event: ActionEvent) = {
      val loginParent: Parent = FXMLView(new File("src/main/resources/fxml/Login.fxml")
        .toURI().toURL(), NoDependencyResolver)
      val loginScene: Scene = new Scene(loginParent)
      val stage: Stage = dashboardMenuBar.getScene().getWindow().asInstanceOf[jfxst.Stage]
      stage.hide() //optional
      stage.setScene(loginScene)
      stage.show()
      stage.setFullScreen(false)
      AuthenticationService.logout()
    }

    /**
      *
      */
    def exitMenuItemOnAction(event: ActionEvent) = {
      val stage: Stage = dashboardMenuBar.getScene().getWindow().asInstanceOf[jfxst.Stage]
      val alert = new Alert(AlertType.Confirmation) {
        initOwner(stage)
        title = "Confirmation Exit"
        headerText = "Are you sure you want to exit?"
      }
      val result = alert.showAndWait()
      result match {
        case Some(ButtonType.OK) => {
          AuthenticationService.logout()
          Platform.exit()
        }
        case _ => alert.close()
      }
    }

    def loadUsers() = {
      val users = UserService.getAll(AuthenticationService.token.get)
      val usersList: ObservableList[User] = FXCollections.observableArrayList();
      users.get.foreach(u => usersList.add(u))
      usersTable.setItems(usersList);
    }

    def loadTrips() = {
      val trips = TripService.getAll(AuthenticationService.token.get)
      val tripsList: ObservableList[Trip] = FXCollections.observableArrayList();
      trips.get.foreach(u => tripsList.add(u))
      tripsTable.setItems(tripsList);
    }

    def loadDiscoveries() = {
      val discoveries = DiscoveryService.getAll(AuthenticationService.token.get)
      val discoveriesList: ObservableList[Discovery] = FXCollections.observableArrayList();
      discoveries.get.foreach(u => discoveriesList.add(u))
      discoveriesTable.setItems(discoveriesList);
    }

    def updateUser(event: CellEditEvent[User, String]) = {
      // TODO pobrac zedytowanego usera
      UserService.update(AuthenticationService.token.get, event.getRowValue())
    }
}