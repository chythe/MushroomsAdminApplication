package controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.Initializable

import scalafxml.core.{ControllerDependencyResolver, FxmlProxyGenerator}

/**
  *
  */
class DashboardController extends Initializable with ScreenControllerChild {

  /**
    *
    */
  private var screensController: ScreensController  = null

  /**
    *
    * @param location
    * @param resources
    */
  override def initialize(location: URL, resources: ResourceBundle) = {
    // TODO
  }

  /**
    *
    * @param controllerParent
    */
  override def setControllerParent(controllerParent: ScreensController) = {
    screensController = controllerParent;
  }


}
