package model


import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */

case class User(
                 @BeanProperty val id: Long,
                 @BeanProperty var username: String,
                 @BeanProperty var email: String,
                 @BeanProperty var role: String,
                 @BeanProperty var firstName: String,
                 @BeanProperty var lastName: String,
                 @BeanProperty var birthDate: String,
                 @BeanProperty var country: String,
                 @BeanProperty var city: String,
                 @BeanProperty var gender: String,
                 @BeanProperty var level: String,
                 @BeanProperty var photo: String
               ) {
}



