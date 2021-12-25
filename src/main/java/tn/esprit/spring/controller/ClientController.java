package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.categorieClient;
import tn.esprit.spring.payload.request.LoginRequest;
import tn.esprit.spring.payload.response.JwtResponse;
import tn.esprit.spring.service.ClientServiceImpl;
import tn.esprit.spring.service.CustomUserDetails;
import tn.esprit.spring.service.CustomUserDetailsService;

@RequestMapping("/client")
@RestController
@CrossOrigin
@Api(tags = "Client management")
public class ClientController {
	
	@Autowired
   ClientServiceImpl clientServiceImpl;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@ApiOperation(value = "Ajout Client")
	
	@PostMapping("/addClient")
	public Client addClient(@RequestBody Client client)
	{   String pwd=client.getPassword();
	    String encryptPwd=passwordEncoder.encode(pwd);
	    client.setPassword(encryptPwd);
		return clientServiceImpl.addClient(client);
	}

	@PostMapping("/addClients")
	public List<Client> addClients(@RequestBody List<Client> clients)
	{
		return clientServiceImpl.addClients(clients);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/clients")
    public List<Client> findAllClients() {
        return clientServiceImpl.retrieveAllClients();
    }
	
	@GetMapping("/clientById/{id}")
    public Client findClientById(@PathVariable long id) {
        return clientServiceImpl.retrieveClient(id);
    }
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/updateClient")
    public Client updateClient(@RequestBody Client client) {
        return clientServiceImpl.updateClient(client);
    }
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable long id) {
        return clientServiceImpl.deleteClient(id);
    }
	
	@GetMapping("/clientsByCategory/{category}")
    public List<Client> ClientBycategory(@PathVariable categorieClient category) {
        return clientServiceImpl.FindAllClientsByCategorie(category);
    }
	
	@GetMapping("/chiffreAffaireParCategorieClient/{categorieClient}/{startDate}/{endDate}")
	 public float ChiffreAffaireParCategorieClient(@PathVariable categorieClient categorieClient,@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date startDate,@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date endDate)
	 {
		 return clientServiceImpl.getChiffreAffaireParCategorieClient(categorieClient, startDate, endDate);
	 }
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/clientsCategoryPourcentage/{category}")
    public float ClientcategoryPourcentage(@PathVariable categorieClient category) {
        return clientServiceImpl.pourcentageCategorieClient(category);
    }

	@PostMapping("/Login")
	public String login(){
		return "authenticated successfully" ;
	}
	
	
	@GetMapping("/signin/{username}/{password}")
	public Client signin(@PathVariable String username,@PathVariable String password){
		
		return clientServiceImpl.signin(username, password);
	}
	@GetMapping("/userRole/{username}")
	public String userRole(@PathVariable String username){
		
			return clientServiceImpl.findUserrole(username);
		
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/MakeAdmin/{id}")
    public String MakeAdmin(@PathVariable long id) {
        clientServiceImpl.makeAdmin(id) ;
        return "Admin made";
    }
	
	
	@PostMapping("/sign")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
long a=5000;
List<String> l=new ArrayList<>();
		return ResponseEntity.ok(new JwtResponse("", 
												 a, 
												 "", 
												 "", 
												 l));
	}

}
