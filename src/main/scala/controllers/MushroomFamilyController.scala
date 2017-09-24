package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.MushroomFamily
import services.{AuthenticationService, MushroomFamilyService}

import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
@sfxml
class MushroomFamilyController(@FXML private val mushroomFamilyTable: TableView[MushroomFamily]) {

  loadMushroomSpecies();

  def loadMushroomSpecies() = {
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
}
