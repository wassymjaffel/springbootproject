package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Facture;



public interface FactureService {
	
	List<Facture> retrieveAllFactures();
	Facture addFacture(Facture f);
	void deleteFacture(Long id);
	Facture updateFacture(Facture f);
	Facture retrieveFacture(Long id);
	void cancelFacture(Long id);
	//List<Facture> retrieveFacturesByClient(Long idClient);
	//List<Facture> getAllFacturesByMontant(float montantFacture);
	List<Facture> retrieveFacturesByClient(Long idClient);
	Facture addFactureClient(Facture f, Long idClient);

}
