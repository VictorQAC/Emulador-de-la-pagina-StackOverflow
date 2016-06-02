package testFiltroDeBusqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.BusquedaPorEtiqueta;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;

public class testFiltroBusquedaPorEtiqueta {

	private BusquedaPorEtiqueta filtroPorEtiqueta;
	private Sistema mockStack;
	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;
	private ArrayList<Pregunta> listaPreguntas;
	private String etiquetas1;
	private String etiquetas2;

	@Before
	public void setUp() {
		filtroPorEtiqueta = new BusquedaPorEtiqueta("Java");
		mockStack = mock(Sistema.class);
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);
		listaPreguntas = new ArrayList<Pregunta>();

		etiquetas1 = "Java SmallTalk";
		etiquetas2 = "Cocina facil";

		filtroPorEtiqueta.setSistema(mockStack);
	}

	@Test
	public void DadoUnSistemaConUnaSolaPublicacionFiltroPorEtiquetaQueLaContiene() {
		listaPreguntas.add(mockPregunta1);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.getEtiqueta()).thenReturn(etiquetas1);

		assertEquals(filtroPorEtiqueta.buscar().size(), 1);
		assertTrue(filtroPorEtiqueta.buscar().contains(mockPregunta1));

	}

	@Test
	public void DadoUnSistemaConDosPublicacionesFiltroPorEtiquetaQueContieneSoloAUna() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.getEtiqueta()).thenReturn(etiquetas1);
		when(mockPregunta2.getEtiqueta()).thenReturn(etiquetas2);

		assertTrue(filtroPorEtiqueta.buscar().size() == 1);
		assertTrue(filtroPorEtiqueta.buscar().contains(mockPregunta1));
		assertFalse(filtroPorEtiqueta.buscar().contains(mockPregunta2));
	}

	@Test
	public void DadoUnSistemaConDosPublicacionesFiltroPorEtiquetaQueNoLaTiene() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.getEtiqueta()).thenReturn(etiquetas2);
		when(mockPregunta2.getEtiqueta()).thenReturn(etiquetas2);

		assertEquals(filtroPorEtiqueta.buscar().size(), 0);
		assertFalse(filtroPorEtiqueta.buscar().contains(mockPregunta2));
		assertFalse(filtroPorEtiqueta.buscar().contains(mockPregunta1));
	}

	@Test
	public void DadoUnSistemaConDosPublicacionesFiltroPorEtiquetaQueContieneAAmbas() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.getEtiqueta()).thenReturn(etiquetas1);
		when(mockPregunta2.getEtiqueta()).thenReturn(etiquetas1);

		assertEquals(filtroPorEtiqueta.buscar().size(), 2);
		assertTrue(filtroPorEtiqueta.buscar().contains(mockPregunta1));
		assertTrue(filtroPorEtiqueta.buscar().contains(mockPregunta2));

	}

}
