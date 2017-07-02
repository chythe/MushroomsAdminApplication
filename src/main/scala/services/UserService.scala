package services

import java.net.MalformedURLException

import com.google.gson.Gson
import exceptions.LoginFailedException
import model.User
import net.liftweb.json.{DefaultFormats, parse}

import scalafx.util.converter.FormatStringConverter
import scalaj.http.{Http, HttpOptions}

/**
  * Created by Mateusz on 06.05.2017.
  */
object UserService {
  def update(get: String, user: User) = {
    val urlString = "http://localhost:8080/api/users"

    implicit val formats = DefaultFormats

    val gson = new Gson();

    val json = gson.toJson(user);
    try {
      val response = Http(urlString).postData(json)
        .header("Content-Type", "application/json")
        .option(HttpOptions.readTimeout(10000)).asString
      if (response.code != 200)
        throw new NotImplementedError();
    }
  }



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
