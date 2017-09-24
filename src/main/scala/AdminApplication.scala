import java.io.File
import javafx.event.EventHandler
import javafx.stage.WindowEvent

import AdminApplication.stage
import services.{AuthenticationService, PdfService}

import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import scalafxml.core.{DependenciesByType, FXMLView, NoDependencyResolver}
import scalafx.Includes._
import scalafx.scene.control.{Alert, ButtonType}
import scalafx.scene.control.Alert.AlertType

object AdminApplication extends JFXApp {

    val root = FXMLView(new File("src/main/resources/fxml/Login.fxml")
      .toURI().toURL(), NoDependencyResolver)

    stage = new JFXApp.PrimaryStage() {
      title = "Mushrooms Admin Application"
      scene = new Scene(root)
    }

    stage.getScene().getWindow().setOnCloseRequest((event: WindowEvent) => {
      val alert = new Alert(AlertType.Confirmation) {
        initOwner(stage)
        title = "Confirmation Exit"
        headerText = "Are you sure you want to exit?"
      }
      val result = alert.showAndWait()
      result match {
        case Some(ButtonType.OK) => {
          AuthenticationService.logout()
          Platform.exit()
        }
        case _ => alert.close()
      }
    })
}