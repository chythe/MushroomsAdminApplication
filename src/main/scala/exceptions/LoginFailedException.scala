package exceptions

/**
  * Created by Mateusz on 06.05.2017.
  */
case class LoginFailedException(message: String = "", cause: Throwable = None.orNull)
  extends Exception(message, cause)
