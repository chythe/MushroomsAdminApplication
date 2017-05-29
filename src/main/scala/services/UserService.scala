package services

import java.net.MalformedURLException

import exceptions.LoginFailedException
import model.User
import model.containers.UsersContainer
import net.liftweb.json.{DefaultFormats, parse}

import scalafx.util.converter.FormatStringConverter
import scalaj.http.{Http, HttpOptions}

/**
  * Created by Mateusz on 06.05.2017.
  */
object UserService {

  def getAll(token: String): Option[Array[User]] = {
    val urlString = "http://localhost:8080/api/users"

    val response = Http.apply(urlString)
      .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Authorization", token)
      .option(HttpOptions.readTimeout(10000)).asString
    if (response.code != 200)
      throw new RuntimeException("Błąd połączenia z serwerem")

    implicit val formats = DefaultFormats
    return Option(parse(response.body).extract[Array[User]])
  }
}
