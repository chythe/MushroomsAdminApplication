package exceptions


import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

/**
  * Created by pawel_zaqkxkn on 24.09.2017.
  */
object ExceptionHandler {

  def showError(msg: String) = {
    val alert = new Alert(AlertType.Error) {
//      initOwner(stage)
      title = "Error"
      contentText = msg
    }.showAndWait();
  }
}
