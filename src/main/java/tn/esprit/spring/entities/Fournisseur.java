package tn.esprit.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Fournisseur implements Serializable{
	
	
	private static final long serialVersionUID = 1L ;

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idFournisseur")
	private Long idFournisseur;
	@Column(name="code")
	private String code;
	@Column(name="libelle")
	private String libelle;
	
	
}