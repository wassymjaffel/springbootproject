package tp.springboot;

public class PaytmRequestBody {

	private String nomutilisatuer;
	private String numerodetelephone;
	private String typedecompte;
	private String numerodecompte;
	public String getNomutilisatuer() {
		return nomutilisatuer;
	}
	public void setNomutilisatuer(String nomutilisatuer) {
		this.nomutilisatuer = nomutilisatuer;
	}
	public String getNumerodetelephone() {
		return numerodetelephone;
	}
	public void setNumerodetelephone(String numerodetelephone) {
		this.numerodetelephone = numerodetelephone;
	}
	public String getTypedecompte() {
		return typedecompte;
	}
	public void setTypedecompte(String typedecompte) {
		this.typedecompte = typedecompte;
	}
	public String getNumerodecompte() {
		return numerodecompte;
	}
	public void setNumerodecompte(String numerodecompte) {
		this.numerodecompte = numerodecompte;
	}
	public PaytmRequestBody(String nomutilisatuer, String numerodetelephone, String typedecompte,
			String numerodecompte) {
		super();
		this.nomutilisatuer = nomutilisatuer;
		this.numerodetelephone = numerodetelephone;
		this.typedecompte = typedecompte;
		this.numerodecompte = numerodecompte;
	}
	public PaytmRequestBody() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaytmRequestBody [nomutilisatuer=" + nomutilisatuer + ", numerodetelephone=" + numerodetelephone
				+ ", typedecompte=" + typedecompte + ", numerodecompte=" + numerodecompte + "]";
	}

	
}
