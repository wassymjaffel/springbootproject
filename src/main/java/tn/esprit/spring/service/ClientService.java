package tn.esprit.spring.service;
import java.util.Date;
import java.util.List;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.categorieClient;

public interface ClientService {
	
	List<Client> retrieveAllClients();
	Client addClient(Client c);
	String deleteClient(Long id);
	Client updateClient(Client c);
	Client retrieveClient(Long id);
	 public List<Client> addClients(List<Client> Clients);
	 public float getChiffreAffaireParCategorieClient(categorieClient categorieClient,Date startDate, Date endDate);
	 List<Client> FindAllClientsByCategorie(categorieClient categorieClient);
	 List<Facture> clientFacturesBetweenTwoDates(Client c,Date startDate,Date endDate);
	 float chiffreAffaireDuneListeFacture(List<Facture>listFacture);
	 float pourcentageCategorieClient(categorieClient categ);
	 Client signin(String username,String password);
	 String findUserrole(String username);
	 void makeAdmin(long clientId);
}
