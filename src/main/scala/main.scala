import java.sql.{Connection, DriverManager, Statement}
import scala.io.StdIn

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(): Unit = {
  var choix = 1

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

  while (choix != 0) {
    println("------MENU-------")
    println("1 - Nombre entre 10 et 20")
    println("2 - Créer table produit")
    println("3 - Ajouter un produit")
    println("4 - Afficher nos produits")
    println("0 - QUITTER")
    println("Veuillez saisir un nombre")
    choix = StdIn.readInt()



    choix match {
      case 1 =>
        var nombre = 0
        while (nombre < 10 || nombre > 20) {
          println("Entrer un nombre")
          nombre = StdIn.readInt()
          if (nombre < 10) {
            println("Plus grand !")
          } else if (nombre > 20){
            println("Plus petit !")
          } else {
            println("Succès")
          }
        }
      case 2 =>
        try {

          val requete = "CREATE TABLE produits (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "nom varchar(100) NOT NULL," +
            "description varchar(500) NOT NULL," +
            "stock INT NOT NULL" +
            ")"

          var statement: Statement = null
          statement = connection.createStatement()
          statement.executeUpdate(requete)

          println("La table a bien été crée")
        } catch {
          case e: Exception => e.printStackTrace()
        }

      case 3 =>
        println("Saisir le nom")
        val nom = StdIn.readLine()
        println("Saisir la description")
        val description = StdIn.readLine()
        println("Saisir le stock")
        val stock = StdIn.readInt()

        var statement: Statement = null
        statement = connection.createStatement()
        val requete = "INSERT INTO produits " +
          "(nom, description, stock)" +
          "VALUES ('" + nom + "', '" + description + "','" + stock + "')"

        statement.executeUpdate(requete)

      case 4 =>

      case 0 =>
        println("Au revoir")
      case _ =>
        println("Ce chiffre n'est pas dans le menu")
    }
  }




}
