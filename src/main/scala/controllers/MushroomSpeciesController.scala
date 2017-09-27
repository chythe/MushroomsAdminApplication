package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import commands.DeleteMushroomSpeciesCommand
import model.MushroomSpecies
import services.{AuthenticationService, MushroomSpeciesService}

import scalafx.scene.control._
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
@sfxml
class MushroomSpeciesController(@FXML private val mushroomSpeciesTable: TableView[MushroomSpecies],
                                @FXML private val createMushroomSpeciesButton: Button) {

  loadMushroomSpecies();

  def loadMushroomSpecies() = {
    mushroomSpeciesTable.getSelectionModel().setSelectionMode(SelectionMode.Multiple);
    val contextMenu = new ContextMenu()
    val deleteAllSelectedItem = new MenuItem("Delete all selected rows")
    deleteAllSelectedItem.setOnAction((event: ActionEvent) => {
      deleteMushroomSpecies();
    })
    contextMenu.getItems().addAll(deleteAllSelectedItem)
    mushroomSpeciesTable.setContextMenu(contextMenu);
    val maybeMushroomSpecies = MushroomSpeciesService.getAll(AuthenticationService.token.get)
    val mushroomSpeciesList: ObservableList[MushroomSpecies] = FXCollections.observableArrayList();
    maybeMushroomSpecies.get.foreach(u => mushroomSpeciesList.add(u))
    mushroomSpeciesTable.setItems(mushroomSpeciesList);
    mushroomSpeciesTable.items.getValue.forEach(x => System.out.println(x));
  }

  def updateName(event: CellEditEvent[MushroomSpecies, String]) = {
    val mushroomSpecies = event.getRowValue();
    mushroomSpecies.name = event.getNewValue();
    MushroomSpeciesService.update(AuthenticationService.token.get, mushroomSpecies);
  }

  def updateGenusId(event: CellEditEvent[MushroomSpecies, Number]) = {
    val mushroomSpecies = event.getRowValue();
    mushroomSpecies.genusId = String.valueOf(event.getNewValue());
    MushroomSpeciesService.update(AuthenticationService.token.get, mushroomSpecies);
  }

  def deleteMushroomSpecies() = {
    //      val stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    //      try {
    mushroomSpeciesTable.getSelectionModel().getSelectedItems().forEach(mushroomSpecies => {
      MushroomSpeciesService.delete(
        AuthenticationService.token.get,
        new DeleteMushroomSpeciesCommand(mushroomSpecies.id))
    })
    mushroomSpeciesTable.getItems().removeAll(mushroomSpeciesTable.getSelectionModel().getSelectedItems())
    //      } catch {
    //        case e: Exception => new Alert(AlertType.Error) {
    ////          initOwner(stage)
    //          title = "Error"
    //          headerText = "Delete failed."
    //        }.showAndWait()
    //      }
  }

  def createMushroomSpecies() = {
    //    //  Create the custom dialog.
    //    val dialog = new Dialog[CreateCommand]();
    //    dialog.setTitle("Create Mushroom Species");
    //    dialog.setHeaderText("Create Mushroom Species");
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
