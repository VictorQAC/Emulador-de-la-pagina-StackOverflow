package stackOverflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BusquedaCompuesta extends FiltroDeBusqueda {
	/*
	 * Filtro que aplica una busqueda sobre preguntas, segunr los filtros que
	 * esta clase contenga
	 */

	private List<FiltroDeBusqueda> filtros;

	public BusquedaCompuesta(List<FiltroDeBusqueda> filtros) {
		this.setFiltros(filtros);
	}

	public List<Pregunta> buscar() {
		Set<Pregunta> resultado = new HashSet<Pregunta>();
		for (FiltroDeBusqueda filtro : this.getFiltros()) {
			filtro.setSistema(this.getSistema());
			resultado.addAll(filtro.buscar());
		}

		return new ArrayList<Pregunta>(resultado);
	}

	public List<FiltroDeBusqueda> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<FiltroDeBusqueda> filtros) {
		this.filtros = filtros;
	}

}
