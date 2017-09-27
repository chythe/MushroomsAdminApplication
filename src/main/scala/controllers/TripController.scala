package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import commands.DeleteTripCommand
import components.TablesContainer
import model.Trip
import services.{AuthenticationService, TripService}

import scalafx.scene.control._
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */

@sfxml
class TripController(@FXML private val tripsTable: TableView[Trip],
                     @FXML private val createTripButton: Button) {

  loadTrips();
  TablesContainer.tripsTable = Option(tripsTable);

  def loadTrips() = {
    tripsTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteTrip();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    tripsTable.setContextMenu(contextMenu);
    val trips = TripService.getAll(AuthenticationService.token.get)
    val tripsList: ObservableList[Trip] = FXCollections.observableArrayList();
    trips.get.foreach(u => tripsList.add(u))
    tripsTable.setItems(tripsList);
  }

  def updateDateTime(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.dateTime = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updatePlace(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.place = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updateCoordinateX(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.coordinateX = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updateCoordinateY(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.coordinateY = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updateRadius(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.radius = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def deleteTrip() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    tripsTable.getSelectionModel().getSelectedItems().forEach(trip => {
      TripService.delete(
        AuthenticationService.token.get,
        new DeleteTripCommand(trip.id))
    })
    tripsTable.getItems().removeAll(tripsTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createTrip() = {
    //    //  Create the custom dialog.
    //    val dialog = new Dialog[CreateCommand]();
    //    dialog.setTitle("Create Trip");
    //    dialog.setHeaderText("Create Trip");
    //
    //    // Set the button types.
    //    val createButtonType: ButtonType = new ButtonType("Create", ButtonData.OKDone);
    //    dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.Cancel);
    //
    //    // Create the username and password labels and fields.
    //    val grid: GridPane = new GridPane();
    //    grid.setHgap(10);
    //    grid.setVgap(10);
    //    grid.setPadding(new Insets(20, 150, 10, 10));
    //
    //    val field: TextField = new TextField();
    //    field.setPromptText("");
    //
    //    grid.add(new Label("Field:"), 0, 0);
    //    grid.add(field, 1, 0);
    //
    //    dialog.getDialogPane().setContent(grid);
    //
    //    dialog.resultConverter = dialogButton =>
    //      if (dialogButton == createButtonType)
    //        CreateCommand()
    //      else
    //        null
    //
    //    val result = dialog.showAndWait()
    //
    //    result match {
    //      case Some(CreateUserCommand()) =>
    //        TripService.create(
    //          AuthenticationService.token.get,
    //          new CreateCommand())
    //      case None               => println("Dialog returned: None")
    //    }
  }
}
