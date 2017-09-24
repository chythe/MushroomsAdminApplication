package model

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */
case class MushroomClass(
                     @BeanProperty val id: Long,
                     @BeanProperty var name: String
                   ) {
}
