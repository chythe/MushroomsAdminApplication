package controllers

import java.io.File
import javafx.{scene => jfxsc, stage => jfxst}

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, PasswordField, TextField}
import scalafx.scene.{Parent, Scene}
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml
import scalafxml.core.{FXMLView, NoDependencyResolver}

/**
  * Created by Mateusz on 05.05.2017.
  */
@sfxml
class LoginController(
                       private val addressTextField: TextField,
                       private val emailTextField: TextField,
                       private val passwordPasswordField: PasswordField,
                       private val loginButton: Button
                     ) {

  /**
    *
    * @param event
    */
  private def changeScreen(event: ActionEvent) = {
    val dashboardParent: Parent = FXMLView(new File("src/main/resources/fxml/Dashboard.fxml")
      .toURI().toURL(), NoDependencyResolver)
    val dashboardScene: Scene = new Scene(dashboardParent)
    val appStage: Stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    appStage.hide() //optional
    appStage.setScene(dashboardScene)
    appStage.show()
  }
  /**
    *
    * @param event
    */
  def loginButtonOnAction(event: ActionEvent) = {
//    AuthenticationService.login(addressTextField.getText(), emailTextField.getText(), passwordPasswordField.getText())
    changeScreen(event)
  }
}
