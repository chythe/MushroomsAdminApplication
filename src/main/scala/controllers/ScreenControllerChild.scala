package controllers

/**
  * Created by Mateusz on 05.05.2017.
  */
trait ScreenControllerChild {

  /**
    *
    * @param parentController
    */
  def setControllerParent(parentController: ScreensController)
}
