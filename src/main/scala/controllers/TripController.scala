package controllers

import javafx.fxml.FXML
import javafx.scene.control.TableView

import model.Trip

import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */

@sfxml
class TripController(@FXML private val tripsTable: TableView[Trip]) {

  loadTrips();

  def loadTrips() = {
    //      val trips = TripService.getAll(AuthenticationService.token.get)
    //      val tripsList: ObservableList[Trip] = FXCollections.observableArrayList();
    //      trips.get.foreach(u => tripsList.add(u))
    //      tripsTable.setItems(tripsList);
  }
}
