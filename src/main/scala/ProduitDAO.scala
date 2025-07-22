import java.sql.PreparedStatement
import java.sql._
object ProduitDAO {
  def insert(produit: Produit) = {
      val requete = "INSERT INTO produits " +
        "(nom, description, stock)" +
        "VALUES (?, ?, ?)"
      val statement = DB.connection.prepareStatement(requete)
      statement.setString(1, produit.nom)
      statement.setString(2, produit.description)
      statement.setInt(3, produit.stock)
      statement.executeUpdate()
    }

  def findAll(): List[Produit] = {
    val requete = "SELECT * FROM produits"
    val statement = DB.connection.prepareStatement(requete)
    val result = statement.executeQuery()
    var produits = List[Produit]()

    while (result.next()) {
      val produit = Produit(
        id = result.getInt("id"),
        nom = result.getString("nom"),
        description = result.getString("description"),
        stock = result.getInt("stock")
      )
      //On écrase l'ancienne liste de Produits pour inclure le nouveau
      produits = produit :: produits
    }
    produits.reverse
  }
  }
