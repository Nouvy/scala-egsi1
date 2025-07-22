object ProduitCategorieDAO {
  def createTable(): Unit = {
    val requete = "CREATE TABLE produits_categories (" +
      "produit_id INT," +
      "categorie_id INT," +
      "PRIMARY KEY (produit_id, categorie_id)," +
      "FOREIGN KEY (produit_id) REFERENCES produits(id)," +
      "FOREIGN KEY (categorie_id) REFERENCES categories(id))"
    val statement = DB.connection.prepareStatement(requete)
    statement.execute()
  }
}
