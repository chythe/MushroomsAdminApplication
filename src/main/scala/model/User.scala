package model

/**
  * Created by Mateusz on 06.05.2017.
  */
case class User(
                 id: String,
                 username: String,
                 email: String,
                 password: String,
                 role: String,
                 firstName: String,
                 lastName: String,
                 birthDate: String,
                 country: String,
                 city: String,
                 gender: String,
                 level: String,
                 photo: Any
               ) {
}
