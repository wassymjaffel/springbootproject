package tn.esprit.spring.service;

import tn.esprit.spring.entities.categorieProduit;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.categorieProduit;

import javax.management.relation.RelationNotFoundException;
import java.util.Date;
import java.util.List;

public interface IProduitService {
	
	List<Produit> findAllProduits();

	
	//public Produit addProduit(Produit produit,Long id , Long ids) ;
	
	Produit addProduit(Produit produit) ;

	Produit retrieveProduitById(Long id);

	Produit updateProduit(Produit produit) throws RelationNotFoundException;
	
	void deleteProduitById(Long id);
	

	
	/*public Produit deleteProduct(Long id) ;
	*/


	void assignProduitToStock(Long idProduit, Long idStock);
	
	void assignFournisseutToProduit(Long idProduit,Long idFournisseur);
	
	List<Produit> retrieveProduitsByCategorie(categorieProduit categorieProduit);
	List<Produit> ProduitParDateCreation(Date date1,Date date2);


}

