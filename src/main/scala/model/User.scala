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

  def getId(): String = {
    return id
  }

  def getUsername(): String = {
    return username
  }

  def getFirstName(): String = {
    return firstName
  }

  def getLastName(): String = {
    return lastName
  }

  def getEmail(): String = {
    return email
  }

  def getBirthDate(): String = {
    return birthDate
  }

  def getGender(): String = {
    return gender
  }

  def getRole(): String = {
    return role
  }

  def getLevel(): String = {
    return level
  }
}
