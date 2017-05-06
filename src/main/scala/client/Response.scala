package client

case class Response(statusCode: Int, body: String, headers: Map[String, String] = Map.empty)
