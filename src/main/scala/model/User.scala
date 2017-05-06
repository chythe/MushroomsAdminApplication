package model

/**
  * Created by Mateusz on 06.05.2017.
  */
case class User(
                 id: Long,
                 username: String,
                 email: String,
                 password: String,
                 role: String,
                 firstName: String,
                 lastName: String,
                 birthDate: String,
                 gender: String
               ) {
}
