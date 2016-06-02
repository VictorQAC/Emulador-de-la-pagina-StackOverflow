package testComparadorPorCriterio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.ComparadorPorCantidadDeVotos;
import stackOverflow.ComparadorPorCriterio;
import stackOverflow.Pregunta;

public class testComparadorPorCantidadDeVotos {

	private ComparadorPorCriterio comparadorPorCantidadDeVotosDeLaPregunta;

	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;

	@Before
	public void setUp() {
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);

		comparadorPorCantidadDeVotosDeLaPregunta = new ComparadorPorCantidadDeVotos();

	}

	@Test
	public void testConUnComparadorPorCantidadDeVotosComparaDosPublicacionesQueTienenDirefenteCantidadDeVotos() {

		when(mockPregunta1.cantidadDeVotos()).thenReturn(2);
		when(mockPregunta2.cantidadDeVotos()).thenReturn(5);

		assertFalse(this.comparadorPorCantidadDeVotosDeLaPregunta
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));
		assertTrue(this.comparadorPorCantidadDeVotosDeLaPregunta
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));
	}

	@Test
	public void testConUnComparadorPorCantidadDeVotosComparaDosPublicacionesQueTienenIgualCantidadDeVotos() {

		when(mockPregunta1.cantidadDeVotos()).thenReturn(5);
		when(mockPregunta2.cantidadDeVotos()).thenReturn(5);

		assertFalse(this.comparadorPorCantidadDeVotosDeLaPregunta
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));
		assertFalse(this.comparadorPorCantidadDeVotosDeLaPregunta
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));
	}

}
