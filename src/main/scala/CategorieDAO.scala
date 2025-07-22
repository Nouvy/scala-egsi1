import java.sql.PreparedStatement
import java.sql._

object CategorieDAO {

  def createTable(): Unit = {
    val requete =
      """CREATE TABLE IF NOT EXISTS categories (
        |  id INT AUTO_INCREMENT PRIMARY KEY,
        |  nom VARCHAR(100) NOT NULL
        |)
        |""".stripMargin

    val statement = DB.connection.prepareStatement(requete)
    statement.execute()
  }

  def insert(categorie: Categorie): Unit = {
    val requete = "INSERT INTO categories (nom) VALUES (?)"
    val statement = DB.connection.prepareStatement(requete)
    statement.setString(1, categorie.nom)
    statement.executeUpdate()
  }

  def findAll(): List[Categorie] = {
    val requete = "SELECT * FROM categories"
    val statement = DB.connection.prepareStatement(requete)
    val result = statement.executeQuery()
    var categories = List[Categorie]()

    while (result.next()) {
      val categorie = Categorie(
        id = result.getInt("id"),
        nom = result.getString("nom")
      )
      categories = categorie :: categories
    }

    categories.reverse
  }
  
  def  find(id: Int): Option[Categorie] = {
    val requete = "SELECT * FROM categories WHERE id = ?"
    val statement = DB.connection.prepareStatement(requete)
    statement.setInt(1, id)
    val result = statement.executeQuery()
    Option.when(result.next()) {
      Categorie(id = result.getInt("id"), nom = result.getString("nom"))
    }
    
    
  }
}
