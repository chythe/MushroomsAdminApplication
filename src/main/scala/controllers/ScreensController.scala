package controllers

import java.io.{File, IOException}
import javafx.event.EventHandler

import scala.collection.mutable.HashMap
import scalafx.animation.{KeyFrame, KeyValue, Timeline}
import scalafx.beans.property.DoubleProperty
import scalafx.event.ActionEvent
import scalafx.scene.{Node, Parent}
import scalafx.scene.layout.StackPane
import scalafx.util.Duration
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

/**
  * Created by Mateusz on 05.05.2017.
  */
class ScreensController extends StackPane {

  private var screens: HashMap[String, Node] = new HashMap[String, Node]

  def addScreen(name: String, screen: Node) = {
    screens.put(name, screen)
  }

  def getScreen(name: String): Node = {
    return screens.getOrElse(name, null)
  }

  def loadScreen(name: String, resource: String) : Boolean = {
    try {
      val fxmlLoader: FXMLLoader = new FXMLLoader(new File(resource).toURI().toURL(), NoDependencyResolver)
      val loadedScreen: Parent = fxmlLoader.load()
      val loadedScreenController: ScreenControllerChild = fxmlLoader.getController()
      loadedScreenController.setControllerParent(this)
      addScreen(name, loadedScreen)
      return true
    } catch {
      case e: IOException => println("Input or output exception")
      return false
    }
  }

  def setScreen(name: String) : Boolean = {
    if (screens.getOrElse(name, null) != null) {
      opacity: DoubleProperty = new Timeline(
        new KeyFrame(Duration.Zero, new KeyValue(opacity),
        new KeyFrame(new Duration(1000), new EventHandler[ActionEvent]() {
          override def handle(event: ActionEvent) = {

          }
        }, new KeyValue(opacity, 1.0)))
    }
  }
}
