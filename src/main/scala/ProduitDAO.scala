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
  }
