package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import tn.esprit.spring.entities.detailFacture;

public interface DetailFactureRepository extends CrudRepository<detailFacture, Long>{

/**
	@Query(value = "SELECT SUM(df.prixtotal) FROM detailfacture df WHERE df.facture.active = true")
	public float getChiffreAffaireMagasin();
	*/
	
}
