package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.detailFacture;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.DetailFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.IProduitRepository;

@Slf4j
@Service
public class FactureServiceImpl implements FactureService{

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	IProduitRepository produitRepository;
	@Autowired
	DetailFactureRepository detailFactureRepository;

	@Override
	public List<Facture> retrieveAllFactures() {
		// TODO Auto-generated method stub
		ArrayList<Facture> f=new ArrayList<Facture>();
		factureRepository.findAll().forEach(facture->f.add(facture));
		return f;
	}

	@Override
	@Transactional
	public Facture addFacture(Facture f) {
		// TODO Auto-generated method stub
		return factureRepository.save(f);
	}

	@Override
	public void deleteFacture(Long id) {
		// TODO Auto-generated method stub
		factureRepository.deleteById(id);
	}

	@Override
	public Facture updateFacture(Facture f) {
		// TODO Auto-generated method stub
		return factureRepository.save(f);
	}

	@Override
	public Facture retrieveFacture(Long id) {
		// TODO Auto-generated method stub
		//Facture fact = factureRepository.findById(id).get();
		//log.info("Facture :" + fact);
		return factureRepository.findById(id).get();
	}

	@Override
	@Modifying
	@Query("update Facture f set f.active := False where f.id_facture = : id")
	public void cancelFacture(@Param("id") Long id) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<Facture> retrieveFacturesByClient(Long idClient) {
		// TODO Auto-generated method stub
		
		List<Facture> lf = factureRepository.findByClientId(idClient);
		for (Facture fact : lf) {
			log.info("Facture :" + fact);
		}
		return lf;
	}
	
	/**
	@Override
	public void cancelFacture(Long id) {
		// TODO Auto-generated method stub
		factureRepository.deleteFactureByActive(id);
	}

	@Override
	public List<Facture> getAllFacturesByMontant(float montantFacture) {
		// TODO Auto-generated method stub
		return factureRepository.findFacturesByMontant(montantFacture);
	}

	@Override
	public List<Facture> getFacturesByClient(Long idClient) {
		// TODO Auto-generated method stub
		ArrayList<Facture> f=new ArrayList<Facture>();
		factureRepository.findFacturesByClient(idClient).forEach(facture->f.add(facture));
		return f;
	}
	*/

	@Override
	public Facture addFactureClient(Facture f, Long idClient) {
		// TODO Auto-generated method stub
		Client client = clientRepository.findById(idClient).orElse(null);
		f.setClient(client);
		f.setDateFacture(new Date());
		f.setActive(true);
		List<detailFacture> detailfactures = f.getDetailFactures();
		Facture facture = addDetailFacture(f, detailfactures);
		return factureRepository.save(facture);
	}
	
	private Facture addDetailFacture(Facture f, List<detailFacture> detailfactures){
		float montantFacture = 0;
		float montantRemise = 0;
		for (detailFacture detail : detailfactures){
			Produit produit = produitRepository.findById(detail.getProduit().getIdProduit()).orElse(null);
			float prixTotalDetail = detail.getQte() * produit.getPrixUnitaire();
			float montantRemiseDetail = (prixTotalDetail * detail.getPourcentageRemise())/100;
			float prixTotalDetailRemise = prixTotalDetail - montantRemiseDetail;
			detail.setMontanRemise(montantRemiseDetail);
			detail.setPrixTotal(prixTotalDetailRemise);
			montantFacture = montantFacture + prixTotalDetailRemise;
			montantRemise = montantRemise + montantRemiseDetail;
			detail.setProduit(produit);
			detail.setFacture(f);
			detailFactureRepository.save(detail);
		}
		f.setMontantFacture(montantFacture);
		f.setMontantRemise(montantRemise);
		return f;
	}

}
