package model

/**
  * Created by Mateusz on 06.05.2017.
  */
class Discovery(
               id: String,
               coordinateX: String,
               coordinateY: String,
               dateTime: String

               ) {

  def getId(): String = {
    return id
  }

  def getCoordinateX(): String = {
    return  coordinateX
  }

  def getCoordinateY(): String = {
    return coordinateY
  }

  def getDateTime(): String = {
    return dateTime
  }
}
