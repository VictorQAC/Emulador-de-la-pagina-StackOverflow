package testFiltroDeBusqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.BusquedaPorAutor;
import stackOverflow.ComparadorPorActividad;
import stackOverflow.ComparadorPorCantidadDeVotos;
import stackOverflow.ComparadorPorCriterio;
import stackOverflow.ComparadorPorFechaDePublicacion;
import stackOverflow.ComparadorPorNombreDeUsuario;
import stackOverflow.FiltroDeBusqueda;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;
import stackOverflow.Usuario;

public class testOrdenamientoDeBusquedas {

	private FiltroDeBusqueda filtro;

	private Sistema mockStack;

	private Usuario mockUsuario1;
	private Usuario mockUsuario2;
	private Usuario mockUsuario3;

	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;
	private Pregunta mockPregunta3;
	private Pregunta mockPregunta4;
	private ArrayList<Pregunta> listaPreguntas;

	@Before
	public void setUp() {
		filtro = new BusquedaPorAutor("Daniel");
		mockStack = mock(Sistema.class);
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);
		mockPregunta3 = mock(Pregunta.class);
		mockPregunta4 = mock(Pregunta.class);
		listaPreguntas = new ArrayList<Pregunta>();

		mockUsuario1 = mock(Usuario.class);
		mockUsuario2 = mock(Usuario.class);
		mockUsuario3 = mock(Usuario.class);

	}

	@Test
	public void DadaUnaListaConCuatroPublicacionesAtravesDeUnFiltroOrdenoDichaListaPorOrdenAlfabetico() {

		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		listaPreguntas.add(mockPregunta3);
		listaPreguntas.add(mockPregunta4);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario1);
		when(mockPregunta3.autor()).thenReturn(mockUsuario2);
		when(mockPregunta4.autor()).thenReturn(mockUsuario3);

		when(mockUsuario1.getUsrName()).thenReturn("Cesar");
		when(mockUsuario2.getUsrName()).thenReturn("Benjamin");
		when(mockUsuario3.getUsrName()).thenReturn("Andres");

		assertFalse(this.listaPreguntas.get(0) == mockPregunta3);

		ComparadorPorCriterio comparadorPorNombre = new ComparadorPorNombreDeUsuario();

		List<Pregunta> l = this.filtro.ordenarPorCriterioAscendente(
				listaPreguntas, comparadorPorNombre);

		assertTrue(l.get(0) == mockPregunta4);
		assertTrue(l.get(1) == mockPregunta3);
		assertTrue(l.get(2) == mockPregunta2);
		assertTrue(l.get(3) == mockPregunta1);

		List<Pregunta> l2 = this.filtro.ordenarPorCriterioDescendente(
				listaPreguntas, comparadorPorNombre);

		assertTrue(l2.get(0) == mockPregunta1);
		assertTrue(l2.get(1) == mockPregunta2);
		assertTrue(l2.get(2) == mockPregunta3);
		assertTrue(l2.get(3) == mockPregunta4);
	}

	@Test
	public void DadaUnaListaConCuatroPublicacionesAtravesDeUnFiltroOrdenoDichaListaSegunLaFechaQueFueronPublicadas() {

		DateTime time1 = new DateTime("2010-10-8");
		DateTime time2 = new DateTime("2010-11-8");
		DateTime time3 = new DateTime("2003-11-8");
		DateTime time4 = new DateTime("2001-11-8");

		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		listaPreguntas.add(mockPregunta3);
		listaPreguntas.add(mockPregunta4);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta2.fechaDePublicacion()).thenReturn(time2);
		when(mockPregunta3.fechaDePublicacion()).thenReturn(time3);
		when(mockPregunta4.fechaDePublicacion()).thenReturn(time4);

		assertFalse(this.listaPreguntas.get(0) == mockPregunta4);

		ComparadorPorCriterio comparadorPorFechaDePublicacion = new ComparadorPorFechaDePublicacion();

		List<Pregunta> l = this.filtro.ordenarPorCriterioAscendente(
				listaPreguntas, comparadorPorFechaDePublicacion);

		assertEquals(l.get(0), mockPregunta4);
		assertEquals(l.get(1), mockPregunta3);
		assertEquals(l.get(2), mockPregunta1);
		assertEquals(l.get(3), mockPregunta2);

		List<Pregunta> l2 = this.filtro.ordenarPorCriterioDescendente(
				listaPreguntas, comparadorPorFechaDePublicacion);

		assertEquals(l2.get(0), mockPregunta2);
		assertEquals(l2.get(1), mockPregunta1);
		assertEquals(l2.get(2), mockPregunta3);
		assertEquals(l2.get(3), mockPregunta4);

	}

	@Test
	public void DadaUnaListaConCuatroPublicacionesAtravesDeUnFiltroOrdenoDichaListaPorLaCantitdadDeVotosObtenidos() {

		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		listaPreguntas.add(mockPregunta3);
		listaPreguntas.add(mockPregunta4);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.cantidadDeVotos()).thenReturn(5);
		when(mockPregunta2.cantidadDeVotos()).thenReturn(3);
		when(mockPregunta3.cantidadDeVotos()).thenReturn(9);
		when(mockPregunta4.cantidadDeVotos()).thenReturn(1);

		assertFalse(this.listaPreguntas.get(0) == mockPregunta4);

		ComparadorPorCriterio comparadorPorCantidadDeVotos = new ComparadorPorCantidadDeVotos();

		List<Pregunta> l = this.filtro.ordenarPorCriterioAscendente(
				listaPreguntas, comparadorPorCantidadDeVotos);

		assertEquals(l.get(0), mockPregunta4);
		assertEquals(l.get(1), mockPregunta2);
		assertEquals(l.get(2), mockPregunta1);
		assertEquals(l.get(3), mockPregunta3);

		List<Pregunta> l2 = this.filtro.ordenarPorCriterioDescendente(
				listaPreguntas, comparadorPorCantidadDeVotos);

		assertEquals(l2.get(0), mockPregunta3);
		assertEquals(l2.get(1), mockPregunta1);
		assertEquals(l2.get(2), mockPregunta2);
		assertEquals(l2.get(3), mockPregunta4);
	}

	@Test
	public void DadoUnaListaConCuatroPublicacionesAtravesDeUnFiltroOrdenoDichaListaegunLaPosicionEnElRankingQueTengaSuAutor() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		listaPreguntas.add(mockPregunta3);
		listaPreguntas.add(mockPregunta4);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario1);
		when(mockPregunta3.autor()).thenReturn(mockUsuario2);
		when(mockPregunta4.autor()).thenReturn(mockUsuario3);

		when(mockUsuario1.posicionEnELRanking()).thenReturn(3);
		when(mockUsuario2.posicionEnELRanking()).thenReturn(2);
		when(mockUsuario3.posicionEnELRanking()).thenReturn(1);

		assertFalse(this.listaPreguntas.get(0) == mockPregunta3);

		ComparadorPorCriterio comparadorPorActividad = new ComparadorPorActividad();

		List<Pregunta> l = this.filtro.ordenarPorCriterioAscendente(
				listaPreguntas, comparadorPorActividad);

		assertTrue(l.get(0) == mockPregunta4);
		assertTrue(l.get(1) == mockPregunta3);
		assertTrue(l.get(2) == mockPregunta2);
		assertTrue(l.get(3) == mockPregunta1);

		List<Pregunta> l2 = this.filtro.ordenarPorCriterioDescendente(
				listaPreguntas, comparadorPorActividad);

		assertTrue(l2.get(0) == mockPregunta1);
		assertTrue(l2.get(1) == mockPregunta2);
		assertTrue(l2.get(2) == mockPregunta3);
		assertTrue(l2.get(3) == mockPregunta4);
	}

}
