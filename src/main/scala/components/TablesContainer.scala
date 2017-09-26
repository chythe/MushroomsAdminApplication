package components

import model.{Discovery, Trip, User}

import scalafx.scene.control.TableView

/**
  * Created by pawel_zaqkxkn on 26.09.2017.
  */
object TablesContainer {

  var tripsTable: Option[TableView[Trip]] = None

  var discoveriesTable: Option[TableView[Discovery]] = None

  var usersTable: Option[TableView[User]] = None

}
