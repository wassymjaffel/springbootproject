package tn.esprit.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.service.IDetailProduitService;

@Slf4j
@RestController
@RequestMapping("/detailProduit")
public class DetailProduitRestController {
	
	 @Autowired
	 IDetailProduitService  dps;
	 
	/*@GetMapping(value="/getDPByLibelle/{lib}")
	@ResponseBody
	public DetailProduit retrieveProduitsByCat(@PathVariable("lib") String lib){
		 DetailProduit dp = dps.getDetailProduitsByLibelleProduit(lib);
		return dp ;
		
	}*/
	

}
