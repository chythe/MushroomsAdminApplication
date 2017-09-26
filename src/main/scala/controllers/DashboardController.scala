package controllers

import java.io.File
import java.net.URL
import java.time.LocalDateTime
import java.util
import java.util.ResourceBundle
import javafx.collections.{FXCollections, ObservableList}
import javafx.{scene => jfxsc, stage => jfxst}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.PropertyValueFactory

import components.TablesContainer
import services._

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
import pdf.PdfBuilder

import scala.xml.Elem

/**
  *
  */
@sfxml
class DashboardController (
                            @FXML private val borderPane: BorderPane,
                            @FXML private val dashboardMenuBar: MenuBar,
                            @FXML private val exportToPdfMenuItem: MenuItem,
                            @FXML private val exportUsers: MenuItem,
                            @FXML private val exitMenuItem: MenuItem,
                            @FXML private val logoutMenuItem: MenuItem,
                            @FXML private val helpButton: Button
                         )
  {


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

    def exportToPdf[?](tableName: String,
                       pdf: Elem,
                       filePath: String) = {

      PdfService.exportToPdf(filePath, pdf);

      val stage: Stage = dashboardMenuBar.getScene().getWindow().asInstanceOf[jfxst.Stage]
      val alert = new Alert(AlertType.Information) {
        initOwner(stage)
        title = "Export"
        headerText = tableName + " was exported to the pdf"
      }
      alert.showAndWait();
    }

    def exportUsers(event: ActionEvent) = {

      val pdf = new PdfBuilder()
        .title("Users", LocalDateTime.now())
        .openTable()
        .mainRow("ID", "Username", "Email", "Role",
          "First name", "Last name", "Birth date",
          "Country", "City", "Gender", "Level")
        .rows(TablesContainer.usersTable.get.getItems(), UserService.toRow)
        .closeTable()
        .build();


      exportToPdf(
        "Users",
        pdf,
        ".\\users report.pdf"
      )
    }

    def exportTrips(event: ActionEvent) = {
      val pdf = new PdfBuilder()
        .title("Trips", LocalDateTime.now())
        .openTable()
        .mainRow("ID", "Place", "Date time", "Coordinate X", "Coordinate Y", "Radius")
        .rows(TablesContainer.tripsTable.get.getItems(), TripService.toRow)
        .closeTable()
        .build();


      exportToPdf(
        "Trips",
        pdf,
        ".\\trips report.pdf"
      );
    }

    def exportDiscoveries(event: ActionEvent) = {
      val pdf = new PdfBuilder()
        .title("Discoveries", LocalDateTime.now())
        .openTable()
        .mainRow("ID", "Trip ID", "Date time",
          "Coordinate X", "Coordinate Y",
          "Mushroomer ID", "Mushroom species ID")
        .rows(TablesContainer.discoveriesTable.get.getItems(), DiscoveryService.toRow)
        .closeTable()
        .build();


      exportToPdf(
        "Discoveries",
        pdf,
        ".\\discoveries report.pdf"
      );
    }

}