package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.MushroomOrder
import services.{AuthenticationService, MushroomOrderService}

import scalafx.scene.control._
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 24.09.2017.
  */
@sfxml
class MushroomOrderController(@FXML private val mushroomOrderTable: TableView[MushroomOrder],
                              @FXML private val createMushroomOrderButton: Button) {

  loadMushroomOrder();

  def loadMushroomOrder() = {
    mushroomOrderTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteMushroomOrder();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    mushroomOrderTable.setContextMenu(contextMenu);
    val maybeMushroomOrder = MushroomOrderService.getAll(AuthenticationService.token.get)
    val mushroomOrderList: ObservableList[MushroomOrder] = FXCollections.observableArrayList();
    maybeMushroomOrder.get.foreach(u => mushroomOrderList.add(u))
    mushroomOrderTable.setItems(mushroomOrderList);
    mushroomOrderTable.items.getValue.forEach(x => System.out.println(x));
  }

  def updateName(event: CellEditEvent[MushroomOrder, String]) = {
    val mushroomOrder = event.getRowValue();
    mushroomOrder.name = event.getNewValue();
    MushroomOrderService.update(AuthenticationService.token.get, mushroomOrder);
  }

  def updateClassId(event: CellEditEvent[MushroomOrder, Number]) = {
    val mushroomOrder = event.getRowValue();
    mushroomOrder.mushroomClassId = String.valueOf(event.getNewValue());
    MushroomOrderService.update(AuthenticationService.token.get, mushroomOrder);
  }

  def deleteMushroomOrder() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    mushroomOrderTable.getSelectionModel().getSelectedItems().forEach(mushroomOrder => {
      MushroomOrderService.delete(AuthenticationService.token.get, mushroomOrder)
    })
    mushroomOrderTable.getItems().removeAll(mushroomOrderTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createMushroomOrder() = {
    //    //  Create the custom dialog.
    //    val dialog = new Dialog[CreateCommand]();
    //    dialog.setTitle("Create Mushroom Order");
    //    dialog.setHeaderText("Create Mushroom Order");
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

