import java.io.File
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafxml.core.{DependenciesByType, FXMLView, NoDependencyResolver}
import scalafx.Includes._

object AdminApplication extends JFXApp {

//  val resource = getClass.getResource("/../resources/fxml/Dashboard.fxml")
//  if (resource == null) {
//    throw new jio.IOException("Cannot load resource: Dashboard.fxml")
//  }

  // NOTE: ScalaFX doe not yet provide a wrapper fro FXMLLoader (2012.11.12)
  // We load here FXML content using JavaFX directly.
  // It is important to provide type for the element loaded,
  // though it can be a generic, here use `javafx.scene.parent`.
  val root = FXMLView(new File("src/main/resources/fxml/Login.fxml")
    .toURI().toURL(), NoDependencyResolver)

  stage = new JFXApp.PrimaryStage() {
    title = "Mushrooms Admin Application"
    scene = new Scene(root)
  }
}