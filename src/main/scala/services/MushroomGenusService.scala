package services

import java.util.logging.Logger

import com.google.gson.Gson
import model.MushroomGenus
import net.liftweb.json.{DefaultFormats, parse}

import scalaj.http.{Http, HttpOptions}

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
object MushroomGenusService {

  private val LOGGER = Logger.getLogger("MushroomGenusService");

  def update(token: String, mushroomGenus: MushroomGenus) = {
    val urlString = "http://localhost:8080/api/mushroom-genus"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(mushroomGenus);
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
        LOGGER.fine("Mushroom genus updated: " + response.body);
      }
    }
  }

  def getAll(token: String): Option[Array[MushroomGenus]] = {
    val urlString = "http://localhost:8080/api/mushroom-genus"

    val response = Http.apply(urlString)
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", token)
      .option(HttpOptions.readTimeout(10000)).asString
    if (response.code != 200)
      throw new RuntimeException("Error. Http status: " + response.code + " " + response.body);

    implicit val formats = DefaultFormats
    return Option(parse(response.body).extract[Array[MushroomGenus]])
  }

  def delete(token: String, mushroomGenus: MushroomGenus) = {
    val urlString = "http://localhost:8080/api/mushroom-genus/" + mushroomGenus.id

    implicit val formats = DefaultFormats

    try {
      val response = Http(urlString).method("DELETE")
        .header("Content-Type", "application/x-www-form-urlencoded")
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
  //    val urlString = "http://localhost:8080/api/mushroom-genus"
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
}
