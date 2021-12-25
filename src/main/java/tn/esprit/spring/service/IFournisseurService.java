package tn.esprit.spring.service;

import tn.esprit.spring.entities.Fournisseur;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

public interface IFournisseurService {
	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(Fournisseur f);

	boolean deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur f ) throws RelationNotFoundException;

	Fournisseur retrieveFournisseur(Long id);
	


}
