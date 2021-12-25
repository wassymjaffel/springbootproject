package tn.esprit.spring.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.ERole;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.categorieClient;
import tn.esprit.spring.repository.ClientRepository;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public List<Client> retrieveAllClients() {
		
		return (List<Client>) clientRepository.findAll() ;
	}

	@Override
	public Client addClient(Client c) {
		log.info("client ajouté"+c.toString());
		ERole eRole;
		eRole=ERole.ROLE_USER;
	    Role role=new Role(eRole);
	    Set<Role>r=new HashSet<>();
	    r.add(role);
	    c.setRoles(r);
	    c.setActive(true);
		return clientRepository.save(c) ;
	}

	@Override
	public String deleteClient(Long id) {
		log.info("client deleted ,ID:"+id);
		clientRepository.deleteById(id); 
		return ("client deleted"+id);
	}

	@Override
	
	public Client updateClient(Client c) {
		  Client existingClient = clientRepository.findById(c.getIdClient()).orElse(null);
		  if(c.getNom()!=null){
		  existingClient.setNom(c.getNom());}
		  if(c.getPrenom()!=null){
		  existingClient.setPrenom(c.getPrenom());}
		  if(c.getCategorieClient()!=null){
		  existingClient.setCategorieClient(c.getCategorieClient());}
		  if(c.getDateNaissance()!=null){
		  existingClient.setDateNaissance(c.getDateNaissance());}
		  if(c.getEmail()!=null){
		  existingClient.setEmail(c.getEmail());}
		  if(c.getPassword()!=null){
		  existingClient.setPassword(c.getPassword());}
		  if(c.getProfession()!=null){
		  existingClient.setProfession(c.getProfession());}
		  log.info("client updated"+c.toString());
	        return clientRepository.save(existingClient);
	        
	}

	@Override
	public Client retrieveClient(Long id) {
		
		return clientRepository.findById(id).get();
	}

	@Override
	public List<Client> addClients(List<Client> Clients) {
	
		return clientRepository.saveAll(Clients);
	}

	@Override
	public List<Client> FindAllClientsByCategorie(categorieClient categorieClient) {
	
		return clientRepository.findByCategorieClient(categorieClient);
	}
	
	@Override
	public float getChiffreAffaireParCategorieClient(categorieClient categorieClient, Date startDate, Date endDate) {
	float chiffreAffaire=0.0f;
		List<Client> listeClient=FindAllClientsByCategorie(categorieClient);
	for(int i=0;i<listeClient.size();i++)
	{
		chiffreAffaire=chiffreAffaire+chiffreAffaireDuneListeFacture(clientFacturesBetweenTwoDates(listeClient.get(i), startDate, endDate));
	}	
		return 0;
	}

	@Override
	public List<Facture> clientFacturesBetweenTwoDates(Client c, Date startDate, Date endDate) {
		List<Facture> listFacture= c.getFactures();
		List<Facture> listFactureBetweenDates = new ArrayList<>();
		for(int i=0;i<listFacture.size();i++)
		{
			if (listFacture.get(i).getDateFacture().getTime() >= startDate.getTime() && listFacture.get(i).getDateFacture().getTime() <= endDate.getTime() ) {
			listFactureBetweenDates.add(listFacture.get(i));	
			}
		}
			
		
		return listFactureBetweenDates;
	}

	@Override
	public float chiffreAffaireDuneListeFacture(List<Facture> listFacture) {
		float chiffreAffaire=0.0f;
		for(int i=0;i<listFacture.size();i++)
		{
			chiffreAffaire=chiffreAffaire+(listFacture.get(i).getMontantFacture()-listFacture.get(i).getMontantRemise());
		}
		return chiffreAffaire;
	}

	@Override
	public float pourcentageCategorieClient(categorieClient categ) {
		int allclients=retrieveAllClients().size();
		int clientsbycateg=FindAllClientsByCategorie(categ).size();
		float pourcentage=((float)clientsbycateg/allclients)*100;
		return pourcentage;
	}

	@Override
	public Client signin(String username, String password) {
		Client client=clientRepository.findByNom(username);
		
		if (password.equals(client.getPassword())) {
		
			return client;
		}else{
			return null;
		}
		
	}

	@Override
	public String findUserrole(String username) {
		Set<Role>r=new HashSet<>();	
		r=clientRepository.findByNom(username).getRoles();
		Role role=new Role(ERole.ROLE_USER);
		for (Role element :r)
		{ role=element;
		
		}
		
		return role.getName().name();
		
	}

	@Override
	public void makeAdmin(long clientId) {
		Client c= clientRepository.getById(clientId);
		ERole eRole;
		eRole=ERole.ROLE_ADMIN;
	    Role role=new Role(eRole);
	    Set<Role>r=new HashSet<>();
	    r.add(role);
	    c.getRoles().clear();
	   
	    String role2 =findUserrole(c.getNom());
	    
	    if(role2.equals("ROLE_USER")){
	    	 c.setRoles(r);
	    	c.setNom("_"+c.getNom());
	    clientRepository.save(c);
	    }
	}

	

}
