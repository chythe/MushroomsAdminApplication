package controllers

import java.io.{File, IOException}
import javafx.{scene => jfxsc, stage => jfxst}

import exceptions.LoginFailedException
import services.AuthenticationService

import scalafx.Includes._
import scalafx.scene.control._
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Button, PasswordField, TextField}
import scalafx.scene.layout.GridPane
import scalafx.scene.{Parent, Scene}
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}

/**
  * Created by Mateusz on 05.05.2017.
  */
@sfxml
class LoginController(
                       private val gridPane: GridPane,
                       private val addressTextField: TextField,
                       private val emailTextField: TextField,
                       private val passwordPasswordField: PasswordField,
                       private val loginButton: Button
                     ) {

  /**
    *
    */
//  val stage: Stage = gridPane.getScene().getWindow().asInstanceOf[jfxst.Stage]
  addressTextField.setText("http://localhost:8080");

  /**
    *
    * @param event
    */
  private def changeScreen(event: ActionEvent) = {
    val dashboardParent: Parent = FXMLView(new File("src/main/resources/fxml/Dashboard.fxml")
      .toURI().toURL(), NoDependencyResolver)
    val dashboardScene: Scene = new Scene(dashboardParent)
    val stage: Stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    stage.hide() //optional
    stage.setScene(dashboardScene)
    stage.show()
//    stage.setFullScreen(true)
  }
  /**
    *
    * @param event
    */
  def loginButtonOnAction(event: ActionEvent) = {
    val stage: Stage = event.getTarget().asInstanceOf[jfxsc.Node].getScene().getWindow().asInstanceOf[jfxst.Stage]
    try {
//      AuthenticationService.login(addressTextField.getText(), emailTextField.getText(), passwordPasswordField.getText())
      changeScreen(event)
    } catch {
      case e: LoginFailedException => new Alert(AlertType.Error) {
        initOwner(stage)
        title = "Error"
        headerText = "Login failed."
        contentText = e.message
      }.showAndWait()
    }
  }
}
