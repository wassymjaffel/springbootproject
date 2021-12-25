package tn.esprit.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Fournisseur;
import tn.esprit.spring.repository.IFournisseurRepository;
import tn.esprit.spring.service.IFournisseurService;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

@Service
public class FournisseurServiceImpl implements IFournisseurService {
	
	@Autowired
	IFournisseurRepository founisseurRepo;

	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		return founisseurRepo.findAll();
	}

	@Override
	public Fournisseur addFournisseur(Fournisseur f) {
		founisseurRepo.save(f);
		return f;
	}

	@Override
	public boolean deleteFournisseur(Long id) {
		Fournisseur f = founisseurRepo.getById(id);
		founisseurRepo.delete(f);
		return true;
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur f)throws RelationNotFoundException {
		Fournisseur existe = this.founisseurRepo.findById(f.getIdFournisseur()).orElseThrow(
				() -> new RelationNotFoundException("produit not found with id :" + f.getIdFournisseur()));
		existe.setCode(f.getCode());
		existe.setLibelle(f.getLibelle());
		return this.founisseurRepo.save(existe);
	}

	@Override
	public Fournisseur retrieveFournisseur(Long id) {
		return founisseurRepo.findById(id).orElse(null);
	}

}
