package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.MushroomFamily
import services.{AuthenticationService, MushroomFamilyService}

import scalafx.scene.control._
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
@sfxml
class MushroomFamilyController(@FXML private val mushroomFamilyTable: TableView[MushroomFamily],
                               @FXML private val createMushroomFamilyButton: Button) {

  loadMushroomFamily();

  def loadMushroomFamily() = {
    mushroomFamilyTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteMushroomFamily();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    mushroomFamilyTable.setContextMenu(contextMenu);
    val maybeMushroomFamily = MushroomFamilyService.getAll(AuthenticationService.token.get)
    val mushroomFamilyList: ObservableList[MushroomFamily] = FXCollections.observableArrayList();
    maybeMushroomFamily.get.foreach(u => mushroomFamilyList.add(u))
    mushroomFamilyTable.setItems(mushroomFamilyList);
    mushroomFamilyTable.items.getValue.forEach(x => System.out.println(x));
  }

  def updateName(event: CellEditEvent[MushroomFamily, String]) = {
    val mushroomFamily = event.getRowValue();
    mushroomFamily.name = event.getNewValue();
    MushroomFamilyService.update(AuthenticationService.token.get, mushroomFamily);
  }

  def updateOrderId(event: CellEditEvent[MushroomFamily, Number]) = {
    val mushroomFamily = event.getRowValue();
    mushroomFamily.orderId = String.valueOf(event.getNewValue());
    MushroomFamilyService.update(AuthenticationService.token.get, mushroomFamily);
  }

  def deleteMushroomFamily() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    mushroomFamilyTable.getSelectionModel().getSelectedItems().forEach(mushroomFamily => {
      MushroomFamilyService.delete(AuthenticationService.token.get, mushroomFamily)
    })
    mushroomFamilyTable.getItems().removeAll(mushroomFamilyTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createMushroomFamily() = {
    //    //  Create the custom dialog.
    //    val dialog = new Dialog[CreateCommand]();
    //    dialog.setTitle("Create Mushroom Family");
    //    dialog.setHeaderText("Create Mushroom Family");
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
