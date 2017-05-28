package model

/**
  * Created by Mateusz on 06.05.2017.
  */
case class User(
                 id: String,
                 username: String,
                 firstName: String,
                 lastName: String,
                 email: String,
                 birthDate: String,
                 gender: String,
                 role: String,
                 level: String
               ) {
}
