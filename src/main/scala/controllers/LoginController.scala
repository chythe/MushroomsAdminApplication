package controllers

import java.io.File

import javafx.{scene => jfxsc, stage => jfxst}
import scalafx.event.{ActionEvent}
import scalafx.scene.{Parent, Scene}
import scalafx.scene.control.{Button, TextField}
import scalafx.stage.Stage
import scalafxml.core.{FXMLView, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import scalafx.Includes._

/**
  * Created by Mateusz on 05.05.2017.
  */
@sfxml
class LoginController(
                       private val emailTextField: TextField,
                       private val passwordTextField: TextField,
                       private val loginButton: Button) {
  /**
    *
    * @param event
    */
  def loginButtonOnAction(event: ActionEvent) = {
    val dashboardParent: Parent = FXMLView(new File("src/main/resources/fxml/Dashboard.fxml")
      .toURI().toURL(), NoDependencyResolver)
    val dashboardScene: Scene = new Scene(dashboardParent)
    val appStage: Stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    appStage.hide(); //optional
    appStage.setScene(dashboardScene);
    appStage.show();
  }
}
