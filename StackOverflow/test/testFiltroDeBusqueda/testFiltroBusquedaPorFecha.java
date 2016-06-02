package testFiltroDeBusqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.BusquedaPorFecha;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;

public class testFiltroBusquedaPorFecha {

	private BusquedaPorFecha filtroPorFecha;
	private Sistema mockStack;
	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;
	private ArrayList<Pregunta> listaPreguntas;

	@Before
	public void setUp() {
		filtroPorFecha = new BusquedaPorFecha("2014-01-1", "2014-11-14");
		mockStack = mock(Sistema.class);
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);
		listaPreguntas = new ArrayList<Pregunta>();

		filtroPorFecha.setSistema(mockStack);
	}

	@Test
	public void DadoUnSistemaConSoloUnaPublicacionFiltroConUnaFechaIntermediaYverificoSiLaContiene() {
		listaPreguntas.add(mockPregunta1);

		DateTime time1 = new DateTime("2014-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);

		assertEquals(filtroPorFecha.buscar().size(), 1);
		assertTrue(filtroPorFecha.buscar().contains(mockPregunta1));
	}

	@Test
	public void DadoUnSistemaConSoloUnaPublicacionFiltroConUnaFechaFueraDelRangoYverificoSiNoLaContiene() {
		listaPreguntas.add(mockPregunta1);

		DateTime time1 = new DateTime("2000-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);

		assertTrue(filtroPorFecha.buscar().size() == 0);
		assertFalse(filtroPorFecha.buscar().contains(mockPregunta1));
	}

	@Test
	public void DadoUnSistemaConSoloDosPublicacionesFiltroCOnUnaPublicacionFueraDeLRangoYOtraDentro() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);

		DateTime time1 = new DateTime("2000-11-8");
		DateTime time2 = new DateTime("2014-11-7");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta2.fechaDePublicacion()).thenReturn(time2);

		assertEquals(filtroPorFecha.buscar().size(), 1);
		assertTrue(filtroPorFecha.buscar().contains(mockPregunta2));
		assertFalse(filtroPorFecha.buscar().contains(mockPregunta1));
	}

	@Test
	public void DadoUnSistemaConSoloDosPublicacionesFiltroConLasDosPublicacionesFueraDeLRango() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);

		DateTime time1 = new DateTime("2000-11-8");
		DateTime time2 = new DateTime("2004-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta2.fechaDePublicacion()).thenReturn(time2);

		assertTrue(filtroPorFecha.buscar().size() == 0);
		assertFalse(filtroPorFecha.buscar().contains(mockPregunta2));
		assertFalse(filtroPorFecha.buscar().contains(mockPregunta1));
	}

	@Test
	public void DadoUnSistemaConSoloDosPublicacionesFiltroConLasDosPublicacionesDentroDelRango() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);

		DateTime time1 = new DateTime("2014-01-8");
		DateTime time2 = new DateTime("2014-11-7");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta2.fechaDePublicacion()).thenReturn(time2);

		assertEquals(filtroPorFecha.buscar().size(), 2);
		assertTrue(filtroPorFecha.buscar().contains(mockPregunta1));
		assertTrue(filtroPorFecha.buscar().contains(mockPregunta2));
	}
}
