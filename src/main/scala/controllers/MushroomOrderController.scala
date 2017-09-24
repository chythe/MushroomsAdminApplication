package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.MushroomOrder
import services.{AuthenticationService, MushroomOrderService}

import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 24.09.2017.
  */
@sfxml
class MushroomOrderController(@FXML private val mushroomOrderTable: TableView[MushroomOrder]) {

  loadMushroomSpecies();

  def loadMushroomSpecies() = {
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
}

