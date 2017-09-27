package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import commands.DeleteDiscoveryCommand
import components.TablesContainer
import model.Discovery
import services.{AuthenticationService, DiscoveryService}

import scalafx.scene.control._
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */
@sfxml
class DiscoveryController(@FXML private val discoveriesTable: TableView[Discovery],
                          @FXML private val createDiscoveryButton: Button) {


  loadDiscoveries();
  TablesContainer.discoveriesTable = Option(discoveriesTable);

  def loadDiscoveries() = {
    discoveriesTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteDiscovery();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    discoveriesTable.setContextMenu(contextMenu);
    val discoveries = DiscoveryService.getAll(AuthenticationService.token.get)
    val discoveriesList: ObservableList[Discovery] = FXCollections.observableArrayList();
    discoveries.get.foreach(u => discoveriesList.add(u))
    discoveriesTable.setItems(discoveriesList);
    discoveriesTable.items.getValue.forEach(x => System.out.println(x));
  }

  def updateCoordinateX(event: CellEditEvent[Discovery, Number]) = {
    val discovery = event.getRowValue();
    discovery.coordinateX = String.valueOf(event.getNewValue());
    DiscoveryService.update(AuthenticationService.token.get, discovery);
  }

  def updateCoordinateY(event: CellEditEvent[Discovery, Number]) = {
    val discovery = event.getRowValue();
    discovery.coordinateY = String.valueOf(event.getNewValue());
    DiscoveryService.update(AuthenticationService.token.get, discovery);
  }

  def updateDateTime(event: CellEditEvent[Discovery, String]) = {
    val discovery = event.getRowValue();
    discovery.dateTime = event.getNewValue();
    DiscoveryService.update(AuthenticationService.token.get, discovery);
  }

  def deleteDiscovery() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    discoveriesTable.getSelectionModel().getSelectedItems().forEach(discovery => {
      DiscoveryService.delete(
        AuthenticationService.token.get,
        new DeleteDiscoveryCommand(discovery.id.toLong))
    })
    discoveriesTable.getItems().removeAll(discoveriesTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createDiscovery() = {
    //    //  Create the custom dialog.
    //    val dialog = new Dialog[CreateCommand]();
    //    dialog.setTitle("Create Discovery");
    //    dialog.setHeaderText("Create Discovery");
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
