package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.{MushroomGenus, MushroomSpecies}
import services.{AuthenticationService, MushroomGenusService, MushroomSpeciesService}

import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
@sfxml
class MushroomGenusController(@FXML private val mushroomGenusTable: TableView[MushroomGenus]) {

  loadMushroomSpecies();

  def loadMushroomSpecies() = {
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
}
