package model

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */
case class Discovery(
                 @BeanProperty id: String,
                 @BeanProperty tripId: String,
                 mushroomSpecies: MushroomSpecies,
                 @BeanProperty mushroomerId: String,
                 @BeanProperty coordinateX: String,
                 @BeanProperty coordinateY: String,
                 photo: String,
                 @BeanProperty dateTime: String,
                 @BeanProperty public: Boolean
               ) {
//
//  def getId(): Long = {
//    return id;
//  }
//
//  def getTripId(): Long = {
//    return tripId;
//  }
//
//  def getMushroomSpeciesName(): String = {
//    return mushroomSpecies.name;
//  }
//
//  def getMushroomerId(): Long = {
//    return mushroomerId;
//  }
//
//  def getCoordinateX(): Double = {
//    return coordinateY;
//  }
//
//  def
}
