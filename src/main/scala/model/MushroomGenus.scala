package model

import scala.beans.BeanProperty

case class MushroomGenus(
                     @BeanProperty val id: Long,
                     @BeanProperty var familyId: String,
                     @BeanProperty var name: String
                   ) {
}
