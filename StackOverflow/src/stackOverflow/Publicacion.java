package stackOverflow;

import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;

public abstract class Publicacion {

	private String title;
	private String body;
	private Usuario autor;
	private DateTime fechaDePublicacion;
	private Map<String, String> votos;

	public Publicacion(String title, String body, Usuario autor2) {
		this.title = title;
		this.body = body;
		this.autor = autor2;
		this.fechaDePublicacion = DateTime.now();
		this.votos = new TreeMap<String, String>();
	}

	public void agregarVoto(String usrName, String voto) {
		this.votos.put(usrName, voto);

	}

	public int cantidadDeVotos() {
		int cantidad = 0;
		for (String positivo : this.votos.values()) {
			if (positivo.equals("positiva")) {
				cantidad = cantidad + 1;
			}
		}
		return cantidad;
	}

	public String getTitle() {
		return title;
	}

	public void setBody(String cuerpo) {
		this.body = cuerpo;
	}

	public String getBody() {
		return this.body;
	}

	public Usuario autor() {
		return this.autor;
	}

	public DateTime fechaDePublicacion() {
		return this.fechaDePublicacion;
	}

	public abstract boolean sonIguales(Publicacion publicacion);

}
