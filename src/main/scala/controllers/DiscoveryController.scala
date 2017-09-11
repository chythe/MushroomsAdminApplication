package controllers

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML

import model.Discovery
import services.{AuthenticationService, DiscoveryService}

import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */
@sfxml
class DiscoveryController(@FXML private val discoveriesTable: TableView[Discovery]) {


  loadDiscoveries();

  def loadDiscoveries() = {
          val discoveries = DiscoveryService.getAll(AuthenticationService.token.get)
          val discoveriesList: ObservableList[Discovery] = FXCollections.observableArrayList();
          discoveries.get.foreach(u => discoveriesList.add(u))
          discoveriesTable.setItems(discoveriesList);
          discoveriesTable.items.getValue.forEach(x => System.out.println(x));
  }


}
