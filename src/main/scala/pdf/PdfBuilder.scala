package pdf

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javafx.collections.ObservableList

import model.User

import scala.xml.{Elem, XML}

/**
  * Created by pawel_zaqkxkn on 24.09.2017.
  */
class PdfBuilder {

  var content = "";

  def title(tabName: String, now: LocalDateTime): PdfBuilder = {
    val notFormatted = "<html><h1 style=\"color: #5e9ca0;\">" +
      "Mushrooms report</h1>" +
      "\n<h2 style=\"color: #2e6c80;\">${tabName} report from ${now}</h2>";
    val formatted = notFormatted
      .replace("${tabName}", tabName)
      .replace("${now}", now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    content += formatted;
    return this;
  }

  def openTable(): PdfBuilder = {
    content += "<table class=\"editorDemoTable\">";
    return this;
  }

  def mainRow(columnName: String*): PdfBuilder = {
    var mainRow = "<thead><tr>";
    columnName.foreach(row => mainRow += "<td>" + row + "</td>");
    mainRow += "</tr></thead><tbody>";
    content += mainRow;
    return this;
  }

  def rows[T](elements: ObservableList[T], toRow: (T) => String) : PdfBuilder = {
    elements.forEach(el => content += toRow(el));
    return this;
  }

  def closeTable(): PdfBuilder = {
    content += "</tbody></table></html>";
    return this;
  }

  def build(): Elem = {
    return XML.loadString(content);
  }

}
