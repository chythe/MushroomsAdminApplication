package commands

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 27.09.2017.
  */
case class DeleteMushroomFamilyCommand(
                                        @BeanProperty var id: Long
                                      ) {
}