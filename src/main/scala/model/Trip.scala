package model

/**
  * Created by Mateusz on 06.05.2017.
  */
class Trip(
          id: String,
          dateTime: String,
          place: String
          ) {

  def getId(): String = {
    return id
  }

  def getDateTime(): String = {
    return dateTime
  }

  def getPlace(): String = {
    return place
  }
}
