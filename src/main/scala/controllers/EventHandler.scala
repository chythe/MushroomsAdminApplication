package controllers

import javafx.{event => jfxe, stage => jfxs}
/**
  * Created by Mateusz on 06.05.2017.
  */
object EventHandler {

    /**
      * The method returns event handler to display confirm dialog window.
      * @return event handler to display confirm dialog window
      */
    def GetCloseRequestHandler(): jfxe.EventHandler[jfxs.WindowEvent] = {
      return new jfxe.EventHandler[jfxs.WindowEvent]() {

        override def handle(windowEvent: jfxs.WindowEvent) = {
  //        dialogs: Dialog = new Dialog();
  //        Optional<ButtonType> result =
  //          dialogs.getExitConfirmDialogAlert().showAndWait();
  //        if (result.get() == ButtonType.OK){
  //          Platform.exit();
  //          System.exit(0);
  //        } else
  //          windowEvent.consume();
        }
      };
    }
}
