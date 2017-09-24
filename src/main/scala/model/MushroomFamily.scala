package model

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */
case class MushroomFamily(
                      @BeanProperty val id: Long,
                      @BeanProperty var orderId: String,
                      @BeanProperty var name: String
                    ) {
}
