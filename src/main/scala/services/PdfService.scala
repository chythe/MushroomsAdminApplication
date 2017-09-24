package services

import java.io.{ByteArrayOutputStream, File, FileOutputStream, OutputStream}
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import io.github.cloudify.scala.spdf.{Landscape, Pdf, PdfConfig, SourceDocumentLike}

import scala.xml.{Elem, NodeBuffer, XML}

/**
  * Created by pawel_zaqkxkn on 12.09.2017.
  */
object PdfService {
  def exportToPdf(filePath: String, page: Elem) = {
    val pdf = Pdf(".\\wkhtmltopdf\\wkhtmltopdf.exe",new PdfConfig {
      orientation := Landscape
      pageSize := "Letter"
      marginTop := "1in"
      marginBottom := "1in"
      marginLeft := "1in"
      marginRight := "1in"
    });

    pdf.run(page, new File(filePath))

  }
}
