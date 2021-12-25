package tn.esprit.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Facture implements Serializable{
	
	
	private static final long serialVersionUID = 1L ;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Long idFacture;
	@Column(name="montantRemise")
	private float montantRemise ;
	@Column(name="montantFacture")
	private float montantFacture;
	@Temporal (TemporalType.DATE)
	private Date dateFacture;
	@Column(name="active")
	private Boolean active;
	
	//relation client et facture
	 @ManyToOne
	  private Client client;
	 
	 //relation facture  et details facture
	 @OneToMany(mappedBy = "facture")
		private  List <detailFacture> detailFactures;
	 
	 
	
	
	
	
}
