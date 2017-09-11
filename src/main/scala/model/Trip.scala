package model

import scala.beans.BeanProperty

/**
  * Created by Mateusz on 06.05.2017.
  */
class Trip(
    @BeanProperty val id: Long,
    @BeanProperty var dateTime: String,
    @BeanProperty var place: String,
    @BeanProperty var coordinateX: String,
    @BeanProperty var coordinateY: String,
    @BeanProperty var radius: String
          ) {
}
