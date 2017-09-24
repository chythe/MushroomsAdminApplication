package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.MushroomClass
import services.{AuthenticationService, MushroomClassService}

import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, TableView}
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 24.09.2017.
  */
@sfxml
class MushroomClassController(@FXML private val mushroomClassTable: TableView[MushroomClass]) {

    loadMushroomSpecies();

    def loadMushroomSpecies() = {
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

}
