package tn.esprit.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.DetailProduit;
import tn.esprit.spring.repository.IDetailProduitRepository;
import tn.esprit.spring.service.IDetailProduitService;

import javax.management.relation.RelationNotFoundException;
import java.util.Date;
import java.util.List;


@Service
public class DetailProduitServiceImp implements IDetailProduitService {
	
	@Autowired
	IDetailProduitRepository dpRepo;

	@Override
	public void saveDetailProduit(DetailProduit dp) {

		
	if (dp.getDateCreation()==null){
		dp.setDateCreation(new Date());
		dp.setDateDerniereModification(new Date());
	}
	else{
		dp.setDateDerniereModification(new Date());
	}
	
		  dpRepo.save(dp);
		
	}
	
	

	@Override
	public DetailProduit retrieveDetailProduit(Long id) {
	return	dpRepo.findById(id).get();
		
		
	}

	@Override
	public DetailProduit updateDetailProduit(DetailProduit dp) throws RelationNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public boolean deleteDetailProduitById(Long id) {
		DetailProduit dp = dpRepo.getById(id);

		dpRepo.delete(dp);
		return true;
	}



	@Override
	public List<DetailProduit> getsAlldP() {

		return dpRepo.findAll();
	}



	@Override
	public DetailProduit getDetailProduitByProduit(Long idProduit) {
		return null;
	}



	/*@Override
	public DetailProduit getDetailProduitsByLibelleProduit(String libelle) {
	
		return dpRepo.getDetailProduitsByLibelleProduit(libelle);
	}*/


}
