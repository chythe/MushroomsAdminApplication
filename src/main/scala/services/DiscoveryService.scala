package services

import java.util.logging.Logger

import com.google.gson.Gson
import model.Discovery
import net.liftweb.json.{DefaultFormats, parse}

import scalaj.http.{Http, HttpOptions}

/**
  * Created by pawel_zaqkxkn on 14.06.2017.
  */
object DiscoveryService {

  private val LOGGER = Logger.getLogger("DiscoveryService");

  def update(token: String, discovery: Discovery) = {
    val urlString = "http://localhost:8080/api/discoveries"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(discovery);
    try {
      val response = Http(urlString).put(json)
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + token)
        .option(HttpOptions.readTimeout(10000)).asString
      if (response.code != 200) {
        LOGGER.warning("Error. Http status: " + response.code)
        throw new RuntimeException("Error. Http status: " + response.code + " " + response.body);
      }
      else {
        LOGGER.fine("Discovery updated: " + response.body);
      }
    }
  }

  def getAll(token: String): Option[Array[Discovery]] = {
    val urlString = "http://localhost:8080/api/discoveries"

    val response = Http.apply(urlString)
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", token)
      .option(HttpOptions.readTimeout(10000)).asString
    if (response.code != 200)
      throw new RuntimeException("Błąd połączenia z serwerem")

    implicit val formats = DefaultFormats
    return Option(parse(response.body).extract[Array[Discovery]])
  }

  def delete(token: String, discovery: Discovery) = {
    val urlString = "http://localhost:8080/api/discoveries/" + discovery.id

    implicit val formats = DefaultFormats

    try {
      val response = Http(urlString).method("DELETE")
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + token)
        .option(HttpOptions.readTimeout(10000)).asString
      if (response.code != 200) {
        LOGGER.warning("Error. Http status: " + response.code)
        throw new RuntimeException("Error. Http status: " + response.code);
      }
      else {
        LOGGER.fine("User updated: " + response.body);
      }
    }
  }

  //  def create(token: String, createCommand: CreateCommand) = {
  //    val urlString = "http://localhost:8080/api/discoveries"
  //
  //    implicit val formats = DefaultFormats
  //
  //    val gson = new Gson();
  //
  //    val json = gson.toJson(createCommand);
  //    try {
  //      val response = Http(urlString).postData(json)
  //        .header("Content-Type", "application/json")
  //        .header("Authorization", "Bearer " + token)
  //        .option(HttpOptions.readTimeout(10000)).asString
  //      if (response.code != 200) {
  //        LOGGER.warning("Error. Http status: " + response.code)
  //        throw new RuntimeException("Error. Http status: " + response.code);
  //      }
  //      else {
  //        LOGGER.fine("User updated: " + response.body);
  //      }
  //    }
  //  }

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
}
