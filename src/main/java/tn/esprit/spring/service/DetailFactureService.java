package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.detailFacture;

public interface DetailFactureService {

	List<detailFacture> retrieveAllDetailFactures();
	detailFacture addDetailFacture(detailFacture f);
	void deleteDetailFacture(Long id);
	detailFacture updateDetailFacture(detailFacture f);
	detailFacture retrieveDetailFacture(Long id);
}
