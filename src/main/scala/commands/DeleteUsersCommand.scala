package commands
import scala.beans.BeanProperty

case class DeleteUsersCommand(
                          @BeanProperty val ids: Array[Long]
                        ) {
}