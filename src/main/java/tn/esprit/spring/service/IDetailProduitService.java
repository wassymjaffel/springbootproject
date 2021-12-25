package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.DetailProduit;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

@Service
public interface IDetailProduitService {
	
	
   List<DetailProduit> getsAlldP() ;
	
   void saveDetailProduit(DetailProduit dp );
	
	
	DetailProduit retrieveDetailProduit(Long id);

	DetailProduit updateDetailProduit(DetailProduit dp) throws RelationNotFoundException;
	
	
	boolean deleteDetailProduitById(Long id);
	//DetailProduit getDetailProduitsByLibelleProduit(String libelle );
	DetailProduit getDetailProduitByProduit(Long idProduit);



}
