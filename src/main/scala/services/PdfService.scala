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
  def exportToPdf(page: Elem) = {
    val pdf = Pdf(".\\wkhtmltopdf\\wkhtmltopdf.exe",new PdfConfig {
      orientation := Landscape
      pageSize := "Letter"
      marginTop := "1in"
      marginBottom := "1in"
      marginLeft := "1in"
      marginRight := "1in"
    });

    pdf.run(page, new File(".\\users report.pdf"))

  }

  def createTableContent(tabName: String, elem: String): String = {
    val title = createTitle(tabName, LocalDateTime.now());
    val table = createTable(elem);
    return title + table;
  }

  def createTitle(tabName: String, now: LocalDateTime): String = {
    val notFormatter = "<html><h1 style=\"color: #5e9ca0;\">" +
      "Mushrooms report</h1>" +
      "\n<h2 style=\"color: #2e6c80;\">${tabName} report from ${now}</h2>";
    val formatted = notFormatter
      .replace("${tabName}", tabName)
      .replace("${now}", now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    return formatted;
  }

  def createTable(table: String): String = {
    val start = "<table class=\"editorDemoTable\">";
    val content = table;
    val end = "</tbody></table></html>";
    return start + content + end;
  }
}
