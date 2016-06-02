package stackOverflow;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public abstract class Usuario {
	protected Sistema sistema;
	protected String usrName;
	private List<Insignia> insignias;
	protected Ranking ranking;
	protected DateTime fechaDeUltimaPublicacion;
	protected DateTime fechaDeRegistracion;
	protected int cantPuntosPorActividad;

	public Usuario(Sistema sistema, String usrName) {
		this.sistema = sistema;
		this.usrName = usrName;
		this.insignias = new ArrayList<Insignia>();
		this.ranking = new Ranking();
		this.fechaDeUltimaPublicacion = null;
		this.fechaDeRegistracion = new DateTime(DateTime.now().getDayOfYear()
				- DateTime.now().getMonthOfYear() - DateTime.now().getYear());
		;
		this.cantPuntosPorActividad = 0;
	}

	public abstract void realizarPregunta(String titulo, String cuerpo,
			String etiqueta) throws Exception;

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public double esRegular() {
		if (DateTime.now().minusMonths(6)
				.isBefore(this.fechaDeUltimaPublicacion)) {
			return 1.0;
		} else {
			return 0.75;
		}
	}

	public void votarPregunta(Pregunta pregunta, String voto) throws Exception {
		sistema.obtenerPregunta(pregunta).agregarVoto(this.getUsrName(), voto);

	}

	public void votarRespuesta(Pregunta pregunta, Respuesta respuesta,
			String voto) throws Exception {
		this.sistema.obtenerPregunta(pregunta).obtenerRespuesta(respuesta)
				.agregarVoto(voto, voto);

	}

	public double puntosParaRanking() {
		return this.puntajePorActividad() * this.factorDeRegularidad();
	}

	public double factorDeRegularidad() {
		return this.esRegular();
	}

	public List<Insignia> insigniaGanadas() {
		return insignias;
	}

	public void setInsignias(List<Insignia> insignias) {
		this.insignias = insignias;
	}

	public void aceptarInsignia(Insignia insignia) {
		if (tengoEsta(insignia) == false) {
			this.insignias.add(insignia);
		}
	}

	public boolean tengoEsta(Insignia insignia) {
		boolean posee = false;

		for (Insignia ins : this.insignias) {
			posee = posee || ins.nombre() == insignia.nombre();
		}
		return posee;
	}

	public ArrayList<Insignia> insigniasFaltantes() {
		ArrayList<Insignia> res = new ArrayList<Insignia>();

		for (Insignia insignia : this.sistema.insignias()) {
			if (!poseoInsignia(insignia)) {
				res.add(insignia);
			}
		}
		return res;
	}

	public boolean poseoInsignia(Insignia insignia) {
		boolean posee = false;

		for (Insignia ins : this.insignias) {
			posee = posee || ins.sonIguales(insignia);
		}
		return posee;
	}

	public int cantidadDeInsignias() {
		return this.insignias.size();
	}

	public String getUsrName() {
		return usrName;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public abstract boolean sonIguales(Usuario usuario);

	public abstract void responder(Pregunta pregunta, String tituloRespuesta,
			String respuesta) throws Exception;

	public int posicionEnELRanking() {
		// devuelve la posicion en el ranking del usuario
		return this.ranking.tamanhoDelRanking()
				- this.ranking.ranking().indexOf(this.puntosParaRanking());
	}

	public DateTime getFechaDeUltimaPublicacion() {
		return this.fechaDeUltimaPublicacion;
	}

	public void setFechaDeUltimaPublicacion(DateTime fechaDeUltimaPublicacion) {
		this.fechaDeUltimaPublicacion = fechaDeUltimaPublicacion;
	}

	public void setCantPuntosPorActividad(int cantPuntosPorActividad) {
		this.cantPuntosPorActividad = cantPuntosPorActividad;
	}

	public int puntajePorActividad() {
		return this.cantPuntosPorActividad;
	}

	public DateTime getFechaDeRegistracion() {
		return fechaDeRegistracion;
	}

	public void setFechaDeRegistracion(DateTime fechaDeRegistracion) {
		this.fechaDeRegistracion = fechaDeRegistracion;
	}
}
