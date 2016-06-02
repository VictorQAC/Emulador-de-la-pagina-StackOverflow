package stackOverflow;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.base.AbstractInterval;

public class BusquedaPorFecha extends FiltroDeBusqueda {
	/*
	 * Filtro que aplica una busqueda de preguntas segun la fecha en la q fuen
	 * publicada.
	 */

	private Interval intervalo;

	public BusquedaPorFecha(String fecha1, String fecha2) {
		DateTime f = new DateTime(fecha1);
		DateTime f2 = new DateTime(fecha2);
		this.intervalo = new Interval(f, f2);
	}

	public List<Pregunta> buscar() {
		List<Pregunta> resultado = new ArrayList<Pregunta>();
		for (Pregunta preg : this.obtenerPreguntaDelSistema()) {
			if (this.getIntervalo().contains(preg.fechaDePublicacion())) {
				resultado.add(preg);
			}
		}
		return resultado;
	}

	private AbstractInterval getIntervalo() {
		return this.intervalo;
	}
}
