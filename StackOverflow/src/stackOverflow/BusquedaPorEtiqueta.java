package stackOverflow;

import java.util.ArrayList;
import java.util.List;

public class BusquedaPorEtiqueta extends FiltroDeBusqueda {
	/*
	 * Filtro que aplica una busqueda sobre preguntas que poseen una etiqueta
	 * determinada
	 */

	private String filtro;

	public BusquedaPorEtiqueta(String etiqueta) {
		this.setFiltro(etiqueta);
	}

	@Override
	public List<Pregunta> buscar() {
		List<Pregunta> resultado = new ArrayList<Pregunta>();
		for (Pregunta preg : this.obtenerPreguntaDelSistema()) {
			if (preg.getEtiqueta().contains(this.getFiltro())) {
				resultado.add(preg);
			}
		}

		return resultado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
