package controllers

import java.time.LocalDateTime
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent

import model.{Discovery, Trip}
import pdf.PdfBuilder
import services.{AuthenticationService, DiscoveryService, PdfService}

import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */
@sfxml
class DiscoveryController(@FXML private val discoveriesTable: TableView[Discovery]) {


  loadDiscoveries();
  exportToPdf();

  def loadDiscoveries() = {
          val discoveries = DiscoveryService.getAll(AuthenticationService.token.get)
          val discoveriesList: ObservableList[Discovery] = FXCollections.observableArrayList();
          discoveries.get.foreach(u => discoveriesList.add(u))
          discoveriesTable.setItems(discoveriesList);
          discoveriesTable.items.getValue.forEach(x => System.out.println(x));
  }

  def updateCoordinateX(event: CellEditEvent[Discovery, Number]) = {
    val discovery = event.getRowValue();
    discovery.coordinateX = String.valueOf(event.getNewValue());
    DiscoveryService.update(AuthenticationService.token.get, discovery);
  }

  def updateCoordinateY(event: CellEditEvent[Discovery, Number]) = {
    val discovery = event.getRowValue();
    discovery.coordinateY = String.valueOf(event.getNewValue());
    DiscoveryService.update(AuthenticationService.token.get, discovery);
  }

  def updateDateTime(event: CellEditEvent[Discovery, String]) = {
    val discovery = event.getRowValue();
    discovery.dateTime = event.getNewValue();
    DiscoveryService.update(AuthenticationService.token.get, discovery);
  }

  def toRow(u: Discovery): String = {
    return "<tr>" +
      "<td>" + u.id + "</td>" +
      "<td>" + u.tripId + "</td>" +
      "<td>" + u.dateTime + "</td>" +
      "<td>" + u.coordinateX + "</td>" +
      "<td>" + u.coordinateY + "</td>" +
      "<td>" + u.mushroomerId + "</td>" +
      "<td>" + u.mushroomSpeciesId + "</td>" +
      "</tr>"
  }

  def exportToPdf() = {
    val pdf = new PdfBuilder()
      .title("Users", LocalDateTime.now())
      .openTable()
      .mainRow("ID", "Trip ID", "Date time", "Coordinate X", "Coordinate Y", "Mushroomer ID", "Mushroom species ID")
      .rows(discoveriesTable.getItems(), toRow)
      .closeTable()
      .build();

    PdfService.exportToPdf(".\\discoveries report.pdf", pdf);
  }

}
