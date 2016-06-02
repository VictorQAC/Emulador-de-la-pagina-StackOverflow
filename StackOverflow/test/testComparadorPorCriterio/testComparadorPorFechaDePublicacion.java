package testComparadorPorCriterio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.ComparadorPorCriterio;
import stackOverflow.ComparadorPorFechaDePublicacion;
import stackOverflow.Pregunta;

public class testComparadorPorFechaDePublicacion {

	private ComparadorPorCriterio comparadorPorFechaDePublicacion;

	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;

	@Before
	public void setUp() {
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);

		comparadorPorFechaDePublicacion = new ComparadorPorFechaDePublicacion();

	}

	@Test
	public void testComparoConUnComparadorDPorFechaDePublicacionDosPublicacionesConDiferenteFEchaDEPublicacion() {

		DateTime time1 = new DateTime("2000-11-8");
		DateTime time2 = new DateTime("2014-11-7");

		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta2.fechaDePublicacion()).thenReturn(time2);

		assertFalse(this.comparadorPorFechaDePublicacion
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));
		assertTrue(this.comparadorPorFechaDePublicacion
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));
	}

	@Test
	public void testComparoConUnComparadorDPorFechaDePublicacionDosPublicacionesConIgualFechaDePublicacion() {

		DateTime time1 = new DateTime("2000-11-8");

		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta2.fechaDePublicacion()).thenReturn(time1);

		assertFalse(this.comparadorPorFechaDePublicacion
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));
		assertFalse(this.comparadorPorFechaDePublicacion
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));
	}

}
