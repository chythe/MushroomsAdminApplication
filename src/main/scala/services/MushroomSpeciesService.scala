package services

import java.util.logging.Logger

import com.google.gson.Gson
import model.MushroomSpecies
import net.liftweb.json.{DefaultFormats, parse}

import scalaj.http.{Http, HttpOptions}

/**
  * Created by pawel_zaqkxkn on 23.09.2017.
  */
object MushroomSpeciesService {

  private val LOGGER = Logger.getLogger("MushroomSpeciesService");

  def update(token: String, mushroomSpecies: MushroomSpecies) = {
    val urlString = "http://localhost:8080/api/mushroom-species"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(mushroomSpecies);
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
        LOGGER.fine("Msuhroom species updated: " + response.body);
      }
    }
  }

  def getAll(token: String): Option[Array[MushroomSpecies]] = {
    val urlString = "http://localhost:8080/api/mushroom-species"

    val response = Http.apply(urlString)
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", token)
      .option(HttpOptions.readTimeout(10000)).asString
    if (response.code != 200)
      throw new RuntimeException("Error. Http status: " + response.code + " " + response.body);

    implicit val formats = DefaultFormats
    return Option(parse(response.body).extract[Array[MushroomSpecies]])
  }

  def delete(token: String, mushroomSpecies: MushroomSpecies) = {
    val urlString = "http://localhost:8080/api/mushroom-species/" + mushroomSpecies.id

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
  //    val urlString = "http://localhost:8080/api/mushroom-species"
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
