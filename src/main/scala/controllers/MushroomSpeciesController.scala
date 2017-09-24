package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.{Discovery, MushroomSpecies}
import services.{AuthenticationService, DiscoveryService, MushroomSpeciesService}

import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
@sfxml
class MushroomSpeciesController(@FXML private val mushroomSpeciesTable: TableView[MushroomSpecies]) {

  loadMushroomSpecies();

  def loadMushroomSpecies() = {
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
}
