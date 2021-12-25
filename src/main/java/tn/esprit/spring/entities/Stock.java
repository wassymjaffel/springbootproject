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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class Stock implements Serializable{
	
	
	private static final long serialVersionUID = 1L ;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idStock")
	private Long idStock;
	@Column(name="qte")
	private  int  qte;
	@Column(name="qteMin")
	private int qteMin;
	@Column(name="libelleStock")
	private String libelleStock;
	
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "stock")
	private  List <Produit> Produits;

	public Stock(Long idStock) {
		super();
		this.idStock = idStock;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (Produits == null) {
			if (other.Produits != null)
				return false;
		} else if (!Produits.equals(other.Produits))
			return false;
		if (idStock == null) {
			if (other.idStock != null)
				return false;
		} else if (!idStock.equals(other.idStock))
			return false;
		if (libelleStock == null) {
			if (other.libelleStock != null)
				return false;
		} else if (!libelleStock.equals(other.libelleStock))
			return false;
		if (qte != other.qte)
			return false;
		if (qteMin != other.qteMin)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Produits == null) ? 0 : Produits.hashCode());
		result = prime * result + ((idStock == null) ? 0 : idStock.hashCode());
		result = prime * result + ((libelleStock == null) ? 0 : libelleStock.hashCode());
		result = prime * result + qte;
		result = prime * result + qteMin;
		return result;
	}
	
	
	

}
