package services

import client.SprayHttpClient
import exceptions.LoginFailedException

import scala.collection.immutable.Map

/**
  * Created by Mateusz on 06.05.2017.
  */
object AuthenticationService {

  /**
    *
    */
  @throws(classOf[LoginFailedException])
  def login(address: String, email: String, password: String) = {
    def sprayHttpClient = new SprayHttpClient
    def url = address + "/login"
    def body = "email=" + email + "&password=" + password
    def params = Map[String, String]()
    def headers = Map("Content-Type" -> "application/x-www-form-urlencoded")
    sprayHttpClient.post(url, body, params, headers)
  }
}
