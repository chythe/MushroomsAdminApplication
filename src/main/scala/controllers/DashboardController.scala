package controllers

import java.io.File
import javafx.{scene => jfxsc, stage => jfxst}

import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.{Parent, Scene}
import scalafx.scene.control.{Button, MenuBar, MenuItem}
import scalafx.stage.Stage
import scalafxml.core.{FXMLView, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import scalafx.Includes._

/**
  *
  */
@sfxml
class DashboardController(
                           private val dashboardMenuBar: MenuBar,
                           private val exitMenuItem: MenuItem,
                           private val logoutMenuItem: MenuItem,
                           private val helpButton: Button) {
  /**
    *
    * @param event
    */
  def logoutMenuItemOnAction(event: ActionEvent) = {
    val loginParent: Parent = FXMLView(new File("src/main/resources/fxml/Login.fxml")
      .toURI().toURL(), NoDependencyResolver)
    val loginScene: Scene = new Scene(loginParent)
    val appStage: Stage = dashboardMenuBar.getScene().getWindow().asInstanceOf[jfxst.Stage]
    appStage.hide(); //optional
    appStage.setScene(loginScene);
    appStage.show();
  }

  /**
    *
    */
  def exitMenuItemOnAction() = {
    Platform.exit()
  }
}