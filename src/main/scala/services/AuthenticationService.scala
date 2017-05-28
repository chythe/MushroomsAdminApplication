package services

import java.net.MalformedURLException

import exceptions.LoginFailedException
import model.User
import model.containers.UsersContainer
import net.liftweb.json._
import scalaj.http._

/**
  * Created by Mateusz on 06.05.2017.
  */
object AuthenticationService {

  /**
    *
    */
  implicit val formats = DefaultFormats

  /**
    *
    */
  var token: Option[String] = None

  /**
    *
    */
  var url: Option[String] = None

  /**
    *
    */
  @throws(classOf[LoginFailedException])
  def login(address: String, email: String, password: String) = {
    val urlString = address + "/login"
    val params = "email=" + email + "&password=" + password
    try {
      val response = Http(urlString).postData(params)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .option(HttpOptions.readTimeout(10000)).asString
      if (response.code != 200)
        throw new LoginFailedException("Bad email or password.")
      token = response.header("Authorization") match {
        case Some(s) => Option(s.replace("Bearer ", ""))
      }
      UsersContainer.currentUser = Option(parse(response.body).extract[User])
//      UsersContainer.currentUser match {
//        case Some(u) => if (!u.role.equals("ADMIN")) throw new LoginFailedException("No permissions.")
//      }
      url = Option(address)
    } catch {
      case e @ (_: MalformedURLException | _: IllegalArgumentException) =>
        throw new LoginFailedException("Bad address.")
    }
  }

  def logout() = {
    url match {
      case Some(u) => {
        val urlString = u + "/logout"
        val response = Http(urlString).asString
      }
    }
  }
}
