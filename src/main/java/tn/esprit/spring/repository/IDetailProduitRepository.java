package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.DetailProduit;

public interface IDetailProduitRepository extends JpaRepository<DetailProduit,Long> {

	
	
	
	/*@Query("select dp from DetailProduit dp right join produit p where p.libelle= :lib")
	DetailProduit getDetailProduitsByLibelleProduit(@Param("lib") String libelle );*/
	
}
