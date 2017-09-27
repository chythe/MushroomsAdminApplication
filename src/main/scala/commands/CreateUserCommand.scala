package commands

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 27.09.2017.
  */
case class CreateUserCommand(
                 @BeanProperty var username: String,
                 @BeanProperty var email: String,
                 @BeanProperty var firstName: String,
                 @BeanProperty var lastName: String,
                 @BeanProperty var birthDate: String,
                 @BeanProperty var gender: String,
                 @BeanProperty var password: String
               ) {
}