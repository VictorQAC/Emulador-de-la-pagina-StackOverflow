package testPublicaciones;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.Pregunta;
import stackOverflow.Respuesta;
import stackOverflow.Usuario;

public class TestPregunta {

	private Pregunta pregunta;
	private Respuesta respuesta;
	private Usuario usuario;

	@Before
	public void setUp() {
		usuario = mock(Usuario.class);
		pregunta = new Pregunta("variable en java",
				"¿Como se declara una variable de int en java?",
				"variable-Java", usuario);
		respuesta = new Respuesta("respuesta a publicacion",
				"se declara asi: int x; ", usuario);

	}

	@Test
	public void DadaUnaPreguntaYUnaRespuestaADichaPreguntaComprobarQueSeGuardo() {
		assertSame(pregunta.cantidadDeRespuestas(), 0);
		pregunta.agregarRespuesta(respuesta);
		assertSame(pregunta.cantidadDeRespuestas(), 1);
	}

	@Test
	public void DadaUnaPreguntaObtenerLaRespuesta() {
		pregunta.agregarRespuesta(respuesta);
		assertEquals(pregunta.obtenerRespuesta(respuesta), respuesta);
	}

	@Test
	public void DadaUnaPreguntaObtenerRespuestaDelUsuario() {
		pregunta.agregarRespuesta(respuesta);
		when(usuario.sonIguales(usuario)).thenReturn(true);
		assertSame(pregunta.obtenerRespuestasDelUsuario(usuario).size(), 1);
	}

	@Test
	public void DadaUnaPreguntaObtenerSuEtiqueta() {
		assertSame(pregunta.getEtiqueta(), "variable-Java");
	}

	@Test
	public void DadaUnaPreguntaObtenerSuCuerpo() {
		assertSame(pregunta.getBody(),
				"¿Como se declara una variable de int en java?");
	}

}
