package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.MushroomGenus
import services.{AuthenticationService, MushroomGenusService}

import scalafx.scene.control._
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
@sfxml
class MushroomGenusController(@FXML private val mushroomGenusTable: TableView[MushroomGenus],
                              @FXML private val createMushroomGenusButton: Button) {

  loadMushroomGenus();

  def loadMushroomGenus() = {
    mushroomGenusTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteMushroomGenus();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    mushroomGenusTable.setContextMenu(contextMenu);
    val maybeMushroomGenus = MushroomGenusService.getAll(AuthenticationService.token.get)
    val mushroomGenusList: ObservableList[MushroomGenus] = FXCollections.observableArrayList();
    maybeMushroomGenus.get.foreach(u => mushroomGenusList.add(u))
    mushroomGenusTable.setItems(mushroomGenusList);
    mushroomGenusTable.items.getValue.forEach(x => System.out.println(x));
  }

  def updateName(event: CellEditEvent[MushroomGenus, String]) = {
    val mushroomGenus = event.getRowValue();
    mushroomGenus.name = event.getNewValue();
    MushroomGenusService.update(AuthenticationService.token.get, mushroomGenus);
  }

  def updateFamilyId(event: CellEditEvent[MushroomGenus, Number]) = {
    val mushroomGenus = event.getRowValue();
    mushroomGenus.familyId = String.valueOf(event.getNewValue());
    MushroomGenusService.update(AuthenticationService.token.get, mushroomGenus);
  }

  def deleteMushroomGenus() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    mushroomGenusTable.getSelectionModel().getSelectedItems().forEach(mushroomGenus => {
      MushroomGenusService.delete(AuthenticationService.token.get, mushroomGenus)
    })
    mushroomGenusTable.getItems().removeAll(mushroomGenusTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createMushroomGenus() = {
    //    //  Create the custom dialog.
    //    val dialog = new Dialog[CreateCommand]();
    //    dialog.setTitle("Create Mushroom Genus");
    //    dialog.setHeaderText("Create Mushroom Genus");
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
