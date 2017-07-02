package model

/**
  * Created by Mateusz on 06.05.2017.
  */
case class Comment(
                    id: Long,
                    discoveryId: Long,
                    userId: Long,
                    targetCommentId: Long,
                    content: String,
                    dateTime: String
                  ) {
}
