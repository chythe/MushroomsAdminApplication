package model

import net.liftweb.json.JsonAST.JString

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */
case class Discovery(
                 @BeanProperty val id: String,
                 @BeanProperty val tripId: String,
                 @BeanProperty var mushroomSpeciesId: String,
                 @BeanProperty val mushroomerId: String,
                 @BeanProperty var coordinateX: String,
                 @BeanProperty var coordinateY: String,
//                 @BeanProperty var photo: Binary,
                 @BeanProperty var dateTime: String,
                 @BeanProperty var public: Boolean
               ) {
}