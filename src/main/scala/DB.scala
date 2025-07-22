import java.sql.{Connection, DriverManager, Statement}

object DB {
  val url = "jdbc:mysql://127.0.0.1:3306/scaladb"
  val username = "root"
  val password = "mysql"
  var connection: Connection = null
  try {
    connection = DriverManager.getConnection(url, username, password)
    println("Connexion réussie")
  } catch {
    case e: Exception => e.printStackTrace()
  }
}
