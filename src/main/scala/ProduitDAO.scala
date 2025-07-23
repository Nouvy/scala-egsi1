import java.sql.PreparedStatement
import java.sql._
object ProduitDAO {
  def insert(produit: Produit): Int = {
      val requete = "INSERT INTO produits " +
        "(nom, description, stock)" +
        "VALUES (?, ?, ?)"
      val statement = DB.connection.prepareStatement(requete, java.sql.Statement.RETURN_GENERATED_KEYS)
      statement.setString(1, produit.nom)
      statement.setString(2, produit.description)
      statement.setInt(3, produit.stock)
      statement.executeUpdate()

      val ids = statement.getGeneratedKeys
      if (ids.next()) {
        val idProduit = ids.getInt(1)
        val requeteLiaison = "INSERT INTO produits_categories" +
          "(produit_id, categorie_id) " +
          "VALUES (?, ?)"
        val statementLiason = DB.connection.prepareStatement(requeteLiaison)
        for (categorie <- produit.categories) {
          statementLiason.setInt(1, idProduit)
          statementLiason.setInt(2, categorie.id)
          statementLiason.executeUpdate()
        }

        idProduit
      } else {
        throw new Exception("Erreur de récupération de l'id du produit")
      }
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
