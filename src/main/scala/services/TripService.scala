package services

import java.util.logging.Logger

import com.google.gson.Gson
import model.{Trip, User}
import net.liftweb.json.{DefaultFormats, parse}

import scalaj.http.{Http, HttpOptions}
;

/**
 * Created by pawel_zaqkxkn on 14.06.2017.
 */
object TripService {

  private val LOGGER = Logger.getLogger("TripService");

  def update(token: String, trip: Trip) = {
    val urlString = "http://localhost:8080/api/trips"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(trip);
    try {
      val response = Http(urlString).put(json)
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + token)
        .option(HttpOptions.readTimeout(10000)).asString
      if (response.code != 200) {
        LOGGER.warning("Error. Http status: " + response.code)
        throw new RuntimeException("Error. Http status: " + response.code);
      }
      else {
        LOGGER.fine("Trip updated: " + response.body);
      }
    }
  }


  def getAll(token: String): Option[Array[Trip]] = {
    val urlString = "http://localhost:8080/api/trips"

    val response = Http.apply(urlString)
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", token)
      .option(HttpOptions.readTimeout(10000)).asString
    if (response.code != 200)
      throw new RuntimeException("Błąd połączenia z serwerem")

    implicit val formats = DefaultFormats
    return Option(parse(response.body).extract[Array[Trip]])
  }
}
