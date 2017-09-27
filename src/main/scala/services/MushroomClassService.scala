package services

import java.util.logging.Logger

import com.google.gson.Gson
import commands.DeleteMushroomClassCommand
import exceptions.ExceptionHandler
import model.MushroomClass
import net.liftweb.json.{DefaultFormats, parse}

import scalaj.http.{Http, HttpOptions}

/**
  * Created by pawel_zaqkxkn on 24.09.2017.
  */
object MushroomClassService {

  private val LOGGER = Logger.getLogger("MushroomClassService");


  def update(token: String, mushroomClass: MushroomClass) = {
    val urlString = "http://localhost:8080/api/mushroom-class"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(mushroomClass);
    try {
      val response = Http(urlString).put(json)
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + token)
        .option(HttpOptions.readTimeout(10000)).asString
      if (response.code != 200) {
        LOGGER.warning("Error. Http status: " + response.code + " " + response.body)
        ExceptionHandler.showError("Error. Http status: " + response.code);
        throw new RuntimeException("Error. Http status: " + response.code + " " + response.body)
      }
      else {
        LOGGER.fine("Mushroom class updated: " + response.body);
      }
    }
  }

  def getAll(token: String): Option[Array[MushroomClass]] = {
    val urlString = "http://localhost:8080/api/mushroom-class"

    val response = Http.apply(urlString)
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", token)
      .option(HttpOptions.readTimeout(10000)).asString
    if (response.code != 200)
      throw new RuntimeException("Error. Http status: " + response.code + " " + response.body);

    implicit val formats = DefaultFormats
    return Option(parse(response.body).extract[Array[MushroomClass]])
  }

  def delete(token: String, deleteMushroomClassCommand: DeleteMushroomClassCommand) = {
    val urlString = "http://localhost:8080/api/mushroom-class"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(deleteMushroomClassCommand);

    try {
      val response = Http(urlString).postData(json).method("DELETE")
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
  //    val urlString = "http://localhost:8080/api/mushroom-class"
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
