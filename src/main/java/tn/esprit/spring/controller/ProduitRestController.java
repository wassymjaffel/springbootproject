package tn.esprit.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.categorieProduit;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.categorieProduit;
import tn.esprit.spring.service.IDetailProduitService;
import tn.esprit.spring.service.IProduitService;
import tn.esprit.spring.service.ImageService;

import javax.management.relation.RelationNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/produit")
@Api(tags = "Product management")
public class ProduitRestController {
	
	@Autowired
	IProduitService ips;

	@Autowired
	IDetailProduitService dps;

	@Autowired
	ImageService imageService;

	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	@GetMapping(value="/getAllProduits")
	@ResponseBody
	public List<Produit> getProduits() {
	List<Produit> listProduits = ips.findAllProduits();
	return listProduits;

}
	
	
	/*@PostMapping(value ="/addProduit/{idr}/{ids}")
	@ResponseBody
	@ApiOperation(value = "Ajouter un produit")
	public Produit ajoutProduit(@RequestBody Produit p,@PathVariable("idr") Long id ,@PathVariable("ids") Long ids ){
		      //  log.info("test {}", p);
				dps.saveDetailProduit(p.getDetailProduit());
		   return ips.addProduit(p,(long)id,(long)ids);
		   
	}
	*/
	
	@PostMapping(value ="/addProduit", consumes = { "multipart/form-data" })
	@ResponseBody
	@ApiOperation(value = "Ajouter un produit")
	public Produit ajoutProduit(@RequestPart("produit") Produit p,  @RequestPart("files") MultipartFile[] files){
		List<String> file_s = new ArrayList<String>();
		dps.saveDetailProduit(p.getDetailProduit());
		Produit produit = ips.addProduit(p);
		for(int i =0 ; i< files.length; i++){
			imageService.store(files[i], p);
			file_s.add("https://localhostw:8083/product/"+produit.getIdProduit()+files[i].getOriginalFilename());
		}
		return produit;
	}
	
	
	
	@GetMapping("/retrieveProduit/{idProduit}")
	@ResponseBody
	 public Produit getProduit(@PathVariable("idProduit")Long idPoduit){

		 return ips.retrieveProduitById(idPoduit);}
	
	
	@GetMapping("/deleteProduit/{idProduit}")
	@ResponseBody
	@ApiOperation(value = "Supprimer un produit")
	public String deleteProduitById(@PathVariable("idProduit") Long idProduit){
		//dps.deleteDetailProduitById(id)
		ips.deleteProduitById(idProduit);
		return "success";
	}
	
	@PostMapping("/updateProduit/{idProduit}")
	@ResponseBody
	public void updateProduit(@RequestBody Produit p, @PathVariable("idProduit") Long idProduit) throws RelationNotFoundException{
		 //p.getDetailProduit().setDateDerniereModification(new Date());
			ips.updateProduit(p);

	}
	
	

	
	
	@GetMapping(value="/getProduitsByCategorie/{cp}")
	@ResponseBody
	public List<Produit> retrieveProduitsByCat(@PathVariable("cp") categorieProduit categorieProduit){
		return ips.retrieveProduitsByCategorie(categorieProduit) ;
	}
	
	@PutMapping("/assignProduitToStock")
	@ResponseBody
	public void assignProduitToStock(@RequestBody Produit p){
		ips.assignProduitToStock(p.getIdProduit(),p.getStock().getIdStock());
       // log.info("test {}", idProduit,idStock);
	}
	
	
	  @PutMapping("/assignFournisseurToProduit/{p}/{f}")
	  @ResponseBody public void assignFournisseurtToProduit(@RequestBody @PathVariable("p")
	 Long  idProduit , @PathVariable("f") Long idFournisseur){
		  ips.assignFournisseutToProduit(idProduit, idFournisseur);
	  }
	 

	@GetMapping(value="/getProduitsByDateCreation/{d1}/{d2}")
	@ResponseBody
	public List<Produit> produitParDateCreation(@PathVariable("d1") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date d1,
			@PathVariable("d2") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date d2 ) {
		return ips.ProduitParDateCreation(d1, d2);
		
	}
	


}