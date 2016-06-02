package stackOverflow;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.joda.time.Days;

public abstract class Insignia implements Observer {

	protected String nombre;
	protected int cantidadDeDias;
	protected int cantidadDePublicaciones;

	public Insignia(String nombre) {
		this.nombre = nombre;
		this.cantidadDeDias = 7;
		this.cantidadDePublicaciones = 0;

	}

	public Insignia(String nombre, int cantidadDePublicaciones,
			int cantidadDeDias) {
		this.nombre = nombre;
		this.cantidadDePublicaciones = cantidadDePublicaciones;
		this.cantidadDeDias = cantidadDeDias;

	}

	public String nombre() {
		return this.nombre;
	}

	public int cantidadDeDias() {
		return this.cantidadDeDias;
	}

	public int cantidadDePublicaciones() {
		return this.cantidadDePublicaciones;
	}

	public void update(Observable sistema, Object usuario) {

		Sistema sist = (Sistema) sistema;
		Usuario user = (Usuario) usuario;

		if (cumpleCondicion(user, sist)) {
			sist.entregarInsignia(this, user);
		}
	}

	public boolean cumpleCondicionDeFecha(Pregunta pregunta,
			ArrayList<Pregunta> preguntas) {
		boolean cumple = false;
		for (Pregunta preg : preguntas) {
			cumple = cumple
					|| Days.daysBetween(preg.fechaDePublicacion(),
							pregunta.fechaDePublicacion()).isLessThan(
							Days.days(this.cantidadDeDias))
					|| Days.daysBetween(preg.fechaDePublicacion(),
							pregunta.fechaDePublicacion()).equals(
							Days.days(this.cantidadDeDias));

		}
		return cumple;
	}

	public boolean cumpleCondicionDeFecha(Respuesta respuesta,
			ArrayList<Respuesta> respuestas) {
		boolean cumple = false;
		for (Respuesta resp : respuestas) {
			cumple = cumple
					|| Days.daysBetween(resp.fechaDePublicacion(),
							resp.fechaDePublicacion()).isLessThan(
							Days.days(this.cantidadDeDias))
					|| Days.daysBetween(resp.fechaDePublicacion(),
							respuesta.fechaDePublicacion()).equals(
							Days.days(this.cantidadDeDias));

		}
		return cumple;
	}

	public abstract boolean cumpleCondicion(Usuario usuario, Sistema sist);

	public boolean sonIguales(Insignia insignia)
	// dos insignias son iguales si tienen la mismas propiedades
	{
		return this.nombre == insignia.nombre()
				&& this.cantidadDePublicaciones == insignia
						.cantidadDePublicaciones()
				&& this.cantidadDeDias == insignia.cantidadDeDias();
	}
}
