package services

import model.{Discovery, Trip}
import net.liftweb.json.{DefaultFormats, parse}

import scalaj.http.{Http, HttpOptions}

/**
  * Created by pawel_zaqkxkn on 14.06.2017.
  */
object DiscoveryService {
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
}
