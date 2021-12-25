package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Facture;


@Repository
public interface FactureRepository extends CrudRepository<Facture, Long>{
	
	/**
	@Query("SELECT f FROM Facture f WHERE f.montantFacture= :montantFacture")
	List<Facture> findFacturesByMontant(@Param("montantFacture") float  montantFacture);
	
	@Query(value = "SELECT SUM(df.prixtotal) FROM DetailFacture df WHERE df.facture.client.categorieClient =: categorie and df.facture.dateFacture between :startD and :endD and df.facture.active = true")
	public float getChiffreAffaireParCategorieClient(@Param("categorie") CategorieClient categorieClient, @Param("startD") Date startDate, @Param("endD") Date endDate);
	
	@Modifying
	@Query("DELETE FROM Facture f WHERE f.active= FALSE AND f.idFacture= :id")
	void deleteFactureByActive(@Param("id") Long id);
	
	@Query("SELECT f FROM Facture f WHERE f.client.idClient= :idClient")
	List<Facture> findFacturesByClient(@Param("idClient") Long  idClient);
	*/
	
	@Query("SELECT f FROM Facture f WHERE f.client =: clientId")
	List<Facture> findByClientId(@Param("clientId") Long clientId);
	
	
	List<Facture> findByClient(Client c);

}
