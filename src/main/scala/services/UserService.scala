package services

import java.net.MalformedURLException
import java.util
import java.util.logging.Logger

import com.google.gson.Gson
import commands.DeleteUsersCommand
import exceptions.LoginFailedException
import model.User
import net.liftweb.json.{DefaultFormats, parse}

import scalafx.util.converter.FormatStringConverter
import scalaj.http.{Http, HttpOptions}

/**
  * Created by Mateusz on 06.05.2017.
  */
object UserService {

  private val LOGGER = Logger.getLogger("UserService");

  def update(token: String, user: User) = {
    val urlString = "http://localhost:8080/api/users"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(user);
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
        LOGGER.fine("User updated: " + response.body);
      }
    }
  }

  def getAll(token: String): Option[Array[User]] = {
    val urlString = "http://localhost:8080/api/users/all"

    val response = Http.apply(urlString)
      .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Authorization", "Bearer " + token)
      .option(HttpOptions.readTimeout(10000)).asString
    if (response.code != 200)
      throw new RuntimeException("Error. Http status: " + response.code)

    implicit val formats = DefaultFormats
    return Option(parse(response.body).extract[Array[User]])
  }

  def delete(token: String, deleteUsersCommand: DeleteUsersCommand) = {
    val urlString = "http://localhost:8080/api/users"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(deleteUsersCommand);

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

  def create(token: String, user: User) = {
    val urlString = "http://localhost:8080/api/users"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(user);
    try {
      val response = Http(urlString).postData(json)
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
}
