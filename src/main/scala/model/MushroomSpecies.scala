package model

import net.liftweb.json.JString

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */
case class MushroomSpecies(
                       @BeanProperty val id: Long,
                       @BeanProperty var name: String,
                       @BeanProperty var genusId: String
//                       examplePhoto: Array[String]
                     ) {
}
