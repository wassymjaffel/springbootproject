package tn.esprit.spring.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.service.ClientServiceImpl;
import tn.esprit.spring.service.FactureService;




@Api(tags = "Facture management")
@RestController
@RequestMapping("/facture")
@CrossOrigin()
public class FactureRestController {

	@Autowired
	FactureService factureService;
	
	@Autowired
	FactureRepository factureRepository;
	
	@Autowired
	ClientServiceImpl clientServiceImpl;

	// http://localhost:8089/SpringMVC/facture/get-factures
	@ApiOperation(value = "Récupérer la liste des factures")
	@GetMapping("/get-factures")
	@ResponseBody
	List<Facture> retrieveAllFactures() {
		return factureService.retrieveAllFactures();
	}
	
	// http://localhost:8089/SpringMVC/facture/get-facture/{id}
	@ApiOperation(value = "Récupérer Facture par ID")
	@GetMapping("/get-facture/{idFacture}")
	@ResponseBody
	Facture retrieveFacture(@PathVariable("idFacture") Long idFacture) {
		return factureService.retrieveFacture(idFacture);
	}

	// http://localhost:8089/SpringMVC/facture/add-facture
	@ApiOperation(value = "Ajouter facture")
	@PostMapping("/add-facture/{idClient}")
	@ResponseBody
	Facture addFactureClient(@RequestBody Facture f, @PathVariable Long idClient) {
		return factureService.addFactureClient(f, idClient);
	}

	// http://localhost:8089/SpringMVC/facture/add-facture
	@ApiOperation(value = "Ajouter facture")
	@PostMapping("/add-facture")
	@ResponseBody
	Facture addFacture(@RequestBody Facture f) {
		return factureService.addFacture(f);
	}
	
	
	// http://localhost:8089/SpringMVC/facture/remove-facture/{idFacture}
	@ApiOperation(value = "Supprimer facture")
	@DeleteMapping("/remove-facture/{idFacture}")
	@ResponseBody
	public void removeFacture(@PathVariable("idFacture") long factureId) {
	factureService.deleteFacture(factureId);
	}
	
	// http://localhost:8089/SpringMVC/facture/modify-facture
	@ApiOperation(value = "Modifier facture")
	@PutMapping("/modify-facture")
	@ResponseBody
	public Facture modifyFacture(@RequestBody Facture facture) {
	return factureService.updateFacture(facture);
	}
	
	// http://localhost:8089/SpringMVC/facture/cancel-facture
	@ApiOperation(value = "Annuler Facture par ID")
	@PutMapping("/cancel-facture")
	@ResponseBody
	void cancelFacture(@PathVariable("idFacture") Long idFacture) {
		factureService.cancelFacture(idFacture);
	}
	
	@ApiOperation(value = "Récupérer Facture par clientID")
	@GetMapping("/get-factures/{idClient}")
	@ResponseBody
	List<Facture> retrieveFactureByClient(@PathVariable("idClient") Long idClient) {
		Client c = clientServiceImpl.retrieveClient(idClient);
		return factureRepository.findByClient(c);
	}
	
	@Scheduled(cron = "0 0 4 1 1 ? *")
	public void cronMethod() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		float ca = 0;
		Calendar calendar = Calendar.getInstance();
		int dateYear;

		for (Facture i : retrieveAllFactures()) {
			// Calendar.getInstance().get(Calendar.DAY_OF_YEAR)!=1

			calendar.setTime(i.getDateFacture());
			dateYear = calendar.get(Calendar.YEAR);
			

			if (dateYear == year) {
				ca = ca + (i.getMontantFacture() - i.getMontantRemise());
			}
		}
		System.out.println("CA " + ca);
		System.out.println("Facture CRON");
	}
	
}

