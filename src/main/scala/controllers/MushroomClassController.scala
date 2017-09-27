package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.MushroomClass
import services.{AuthenticationService, MushroomClassService}

import scalafx.scene.control._
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 24.09.2017.
  */
@sfxml
class MushroomClassController(@FXML private val mushroomClassTable: TableView[MushroomClass],
                              @FXML private val createMushroomClassButton: Button) {

  loadMushroomClass();

  def loadMushroomClass() = {
    mushroomClassTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteMushroomClass();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    mushroomClassTable.setContextMenu(contextMenu);
    val maybeMushroomClass = MushroomClassService.getAll(AuthenticationService.token.get)
    val mushroomClassList: ObservableList[MushroomClass] = FXCollections.observableArrayList();
    maybeMushroomClass.get.foreach(u => mushroomClassList.add(u))
    mushroomClassTable.setItems(mushroomClassList);
    mushroomClassTable.items.getValue.forEach(x => System.out.println(x));
  }

  def updateName(event: CellEditEvent[MushroomClass, String]) = {
    val mushroomClass = event.getRowValue();
    mushroomClass.name = event.getNewValue();
    MushroomClassService.update(AuthenticationService.token.get, mushroomClass);
  }

  def deleteMushroomClass() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    mushroomClassTable.getSelectionModel().getSelectedItems().forEach(mushroomClass => {
      MushroomClassService.delete(AuthenticationService.token.get, mushroomClass)
    })
    mushroomClassTable.getItems().removeAll(mushroomClassTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createMushroomClass() = {
    //    //  Create the custom dialog.
    //    val dialog = new Dialog[CreateCommand]();
    //    dialog.setTitle("Create Mushroom Class");
    //    dialog.setHeaderText("Create Mushroom Class");
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
