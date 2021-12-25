package tn.esprit.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.Rayon;


public interface RayonService {
	
	
	List<Rayon> getsAllRayons();
	Rayon addRayon(Rayon r);
	Rayon updateRayon(Rayon u);
	Rayon findRayon(Long id);
	Rayon patchRayon (Rayon p);
	Page<Rayon> findRayonWithPagination( int offset, int pageSize);
	Page<Rayon> findRayonWithPaginationAndSorting( int offset, int pageSize, String field);
	
	void deleteRayon(int i);
	void deleteRayon(Long rayonId);
	List<Produit> findProduit(Long id);
}
