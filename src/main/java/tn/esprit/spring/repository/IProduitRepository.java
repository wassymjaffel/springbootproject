package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.categorieProduit;
import tn.esprit.spring.entities.Produit;

import java.util.Date;
import java.util.List;

@Repository
public interface IProduitRepository extends JpaRepository  <Produit, Long> {

	//@Modifying
	//@Query(value = "delete from Produit p where p.idProduit= :id", nativeQuery = true)
	
	
	
	/*@Modifying
	@Query(value = "INSERT INTO Produit (code, libelle,prixUnitaire,idStock,idRayon, idDetailProduit) VALUES (:code,:libelle, :prixUnitaire, :idStock, :idRayon, :idDetailProduit)",
	nativeQuery = true)
	Produit addProduit(@Param("code")String code ,@Param("libelle")String libelle,@Param("prixUnitaire")Float prixUnitaire,@Param( "idRayon")Rayon rayon,
	 @Param ("idStock") Stock stock,@Param ("idDetailProduit") DetailProduit detailProduit );*/
	
	
	
	//@Query(value = "SELECT p FROM Produit p")
	//List<Produit> findAll();

	//@Query(value = "select * from Produit p where p.idProduit =:id", nativeQuery = true)
	//Produit retrieveProduit(Long id);
	
	

	//Produit updateProduit(Produit produit);
	
	@Query("select p from Produit p  where p.detailProduit.categorieProduit= :categorie")
	List<Produit> getProduitsByCategorie(@Param("categorie") categorieProduit categorieProduit );

	/*@Modifying
	@Query("update Produit p set p.stock.idStock = :idS where p.idProduit = :idp")
	void assignProduitToStock(@Param("idp")Long idProduit, @Param("idS")Long idStock);*/
	
	
@Query("select p from Produit p where p.detailProduit.dateCreation BETWEEN :startDate AND :endDate ")
List<Produit> ProduitParDateCreation(@Param("startDate") Date date1,@Param("endDate") Date date2);
	
	
	
	
	
	
}
