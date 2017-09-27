package commands

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 27.09.2017.
  */
case class DeleteTripCommand(
                                         @BeanProperty var id: Long
                                       ) {
}