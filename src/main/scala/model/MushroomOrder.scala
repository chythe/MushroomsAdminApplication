package model

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */
case class MushroomOrder(
                     @BeanProperty val id: Long,
                     @BeanProperty var mushroomClassId: String,
                     @BeanProperty var name: String
                   ) {
}
