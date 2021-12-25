package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.ApiOperation;

import tn.esprit.spring.entities.detailFacture;
import tn.esprit.spring.service.DetailFactureService;



@RestController
@RequestMapping("/DetailFacture")
@CrossOrigin()
public class DetailFactureRestController {

	@Autowired
	DetailFactureService detailFactureService;
	
	@Autowired
	DetailFactureRestController detailFactureRestController;

	// http://localhost:8089/SpringMVC/DetailFacture/get-all-DetailFactures
		@GetMapping("/get-all-DetailFactures")
		@ResponseBody
		public List<detailFacture> getDetailFactures() {
		List<detailFacture> listDetailFactures = detailFactureService.retrieveAllDetailFactures();
		return listDetailFactures;
		}

		// http://localhost:8089/SpringMVC/DetailFacture/get-DetailFacture/{id}
		@ApiOperation(value = "Récupérer Detail Facture par ID")
		@GetMapping("/get-DetailFacture/{idDetailFacture}")
		@ResponseBody
		detailFacture retrieveDetailFacture(@PathVariable("idDetailFacture") Long idDetailFacture) {
			return detailFactureService.retrieveDetailFacture(idDetailFacture);
		}
		
		// http://localhost:8089/SpringMVC/DetailFacture/add-DetailFacture
		@PostMapping("/add-DetailFacture")
		@ResponseBody
		public detailFacture addDetailFacture(@RequestBody detailFacture f)
		{
		detailFacture detailFacture = detailFactureService.addDetailFacture(f);
		return detailFacture;
		}
		
		
		// http://localhost:8089/SpringMVC/DetailFacture/remove-DetailFacture/{DetailFacture-id}
		@DeleteMapping("/remove-DetailFacture/{DetailFacture-id}")
		@ResponseBody
		public void removeDetailFacture(@PathVariable("DetailFacture-id") long DetailFactureId) {
		detailFactureService.deleteDetailFacture(DetailFactureId);
		}
		
		// http://localhost:8089/SpringMVC/DetailFacture/modify-DetailFacture
		@PutMapping("/modify-DetailFacture")
		@ResponseBody
		public detailFacture modifyDetailFacture(@RequestBody detailFacture facture) {
		return detailFactureService.updateDetailFacture(facture);
		}
		
	}

