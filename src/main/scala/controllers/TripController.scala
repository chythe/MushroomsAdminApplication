package controllers

import java.time.LocalDateTime
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.TableView

import model.{Trip, User}
import pdf.PdfBuilder
import services.{AuthenticationService, PdfService, TripService, UserService}

import scalafxml.core.macros.sfxml

/**
  * Created by pawel_zaqkxkn on 04.09.2017.
  */

@sfxml
class TripController(@FXML private val tripsTable: TableView[Trip]) {

  loadTrips();
  exportToPdf();

  def loadTrips() = {
          val trips = TripService.getAll(AuthenticationService.token.get)
          val tripsList: ObservableList[Trip] = FXCollections.observableArrayList();
          trips.get.foreach(u => tripsList.add(u))
          tripsTable.setItems(tripsList);
  }

  def updateDateTime(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.dateTime = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updatePlace(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.place = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updateCoordinateX(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.coordinateX = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updateCoordinateY(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.coordinateY = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def updateRadius(event: CellEditEvent[Trip, String]) = {
    val trip = event.getRowValue();
    trip.radius = event.getNewValue();
    TripService.update(AuthenticationService.token.get, trip);
  }

  def toRow(u: Trip): String = {
    return "<tr>" +
      "<td>" + u.id + "</td>" +
      "<td>" + u.place + "</td>" +
      "<td>" + u.dateTime + "</td>" +
      "<td>" + u.coordinateX + "</td>" +
      "<td>" + u.coordinateY + "</td>" +
      "<td>" + u.radius + "</td>" +
      "</tr>"
  }

  def exportToPdf() = {
    val pdf = new PdfBuilder()
      .title("Users", LocalDateTime.now())
      .openTable()
      .mainRow("ID", "Place", "Date time", "Coordinate X", "Coordinate Y", "Radius")
      .rows(tripsTable.getItems(), toRow)
      .closeTable()
      .build();

    PdfService.exportToPdf(".\\trips report.pdf", pdf);
  }
}
