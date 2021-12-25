package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Rayon implements Serializable{
	
	
	private static final long serialVersionUID = 1L ;
   
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idRayon")
	private Long idRayon;
	@Column(name="code")
	private String code;
	@Column(name="libelle")
	private String libelle;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "rayon")
	private  List <Produit> Produits;

	public Rayon(Long idRayon) {
		super();
		this.idRayon = idRayon;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rayon other = (Rayon) obj;
		if (Produits == null) {
			if (other.Produits != null)
				return false;
		} else if (!Produits.equals(other.Produits))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (idRayon == null) {
			if (other.idRayon != null)
				return false;
		} else if (!idRayon.equals(other.idRayon))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Produits == null) ? 0 : Produits.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((idRayon == null) ? 0 : idRayon.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}
	
	
	
	
	
}
