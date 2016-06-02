package testInsignia;

import static org.junit.Assert.*;

import org.junit.Test;

import stackOverflow.InsigniaCompuesta;
import stackOverflow.InsigniaPorEtiqueta;
import stackOverflow.InsigniaPorRespuestas;
import stackOverflow.Sistema;
import stackOverflow.UsuarioSimple;
import static org.mockito.Mockito.*;

public class TestInsigniaCompuesta {
	private InsigniaCompuesta insigniaCompuesta;
	private InsigniaPorRespuestas insigniaRespuestasYDias;
	private UsuarioSimple simple = mock(UsuarioSimple.class);
	private InsigniaPorEtiqueta insignia;

	private Sistema sist;

	@Test
	public void testCumpleCondicion() {
		insigniaRespuestasYDias = mock(InsigniaPorRespuestas.class);
		insignia = mock(InsigniaPorEtiqueta.class);

		sist = mock(Sistema.class);

		when(insigniaRespuestasYDias.cumpleCondicion(simple, sist)).thenReturn(
				true);
		when(insignia.cumpleCondicion(simple, sist)).thenReturn(true);
		insigniaCompuesta = new InsigniaCompuesta("Especialista",
				insigniaRespuestasYDias, insignia);

		assertTrue(insigniaCompuesta.cumpleCondicion(simple, sist));
	}

	@Test
	public void testUpdate() {
		insigniaRespuestasYDias = mock(InsigniaPorRespuestas.class);
		insignia = mock(InsigniaPorEtiqueta.class);

		sist = mock(Sistema.class);

		when(insigniaRespuestasYDias.cumpleCondicion(simple, sist)).thenReturn(
				true);
		when(insignia.cumpleCondicion(simple, sist)).thenReturn(true);
		insigniaCompuesta = new InsigniaCompuesta("Especialista",
				insigniaRespuestasYDias, insignia);
		insigniaCompuesta.update(sist, simple);
		verify(sist).entregarInsignia(insigniaCompuesta, simple);

	}
}
