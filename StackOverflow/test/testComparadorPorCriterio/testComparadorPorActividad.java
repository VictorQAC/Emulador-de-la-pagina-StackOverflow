package testComparadorPorCriterio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.ComparadorPorActividad;
import stackOverflow.ComparadorPorCriterio;
import stackOverflow.Pregunta;
import stackOverflow.Usuario;

public class testComparadorPorActividad {

	private ComparadorPorCriterio comparadorPorActividadDeUsuario;

	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;

	private Usuario mockUsuario1;
	private Usuario mockUsuario2;

	@Before
	public void setUp() {
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);

		mockUsuario1 = mock(Usuario.class);
		mockUsuario2 = mock(Usuario.class);

		comparadorPorActividadDeUsuario = new ComparadorPorActividad();

	}

	@Test
	public void testComparoConUnComparadorPorActividadDosPublicacionesConPuntajeDeActividadDistinto() {

		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);

		when(mockUsuario1.posicionEnELRanking()).thenReturn(1);
		when(mockUsuario2.posicionEnELRanking()).thenReturn(4);

		assertFalse(this.comparadorPorActividadDeUsuario
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));
		assertTrue(this.comparadorPorActividadDeUsuario
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));

	}

	@Test
	public void testComparoConUnComparadorPorActividadDosPublicacionesConPuntajeDeActividadIgual() {

		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);

		when(mockUsuario1.posicionEnELRanking()).thenReturn(1);
		when(mockUsuario2.posicionEnELRanking()).thenReturn(1);

		assertFalse(this.comparadorPorActividadDeUsuario
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));
		assertFalse(this.comparadorPorActividadDeUsuario
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));

	}

}
