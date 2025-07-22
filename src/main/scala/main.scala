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
    println("5 - Ajouter une catégorie")
    println("6 - Afficher nos catégories")
    println("7 - Créer la table categorie")
    println("8 - Créer table produits_categories")
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
        println("Merci de choisir une catgéorie")
        val categories = CategorieDAO.findAll()
        categories.foreach { categorie =>
          println(s"ID: ${categorie.id}, " +
            s"Nom: ${categorie.nom}, ")
        }
        val idCategorie = StdIn.readInt()
        var categoriesChoisies = List[Categorie]()
        val categorie = CategorieDAO.find(1)
        categorie.foreach(c =>
          categoriesChoisies = c :: categoriesChoisies
        )
        val produit1 = Produit(nom = nom, description = description, stock = stock, categories = categoriesChoisies)
        ProduitDAO.insert(produit1)
      case 4 =>
        val produits = ProduitDAO.findAll()
        produits.foreach { produit =>
          println(s"ID: ${produit.id}, " +
            s"Nom: ${produit.nom}, " +
            s"Description: ${produit.description}, " +
            s"Stock: ${produit.stock}")
        }
      case 5 =>
        println("Saisir le nom")
        val nom = StdIn.readLine()

        val categorie1 = Categorie(nom = nom)
        CategorieDAO.insert(categorie1)
      case 6 =>
        val categories = CategorieDAO.findAll()
        categories.foreach { categorie =>
          println(s"ID: ${categorie.id}, " +
            s"Nom: ${categorie.nom}, ")
        }
      case 7 =>
        CategorieDAO.createTable()
      case 8 =>
        ProduitCategorieDAO.createTable()
      case 0 =>
        println("Au revoir")
      case _ =>
        println("Ce chiffre n'est pas dans le menu")
    }
  }




}
