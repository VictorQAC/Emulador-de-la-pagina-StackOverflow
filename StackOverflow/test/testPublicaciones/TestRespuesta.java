package testPublicaciones;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.Pregunta;
import stackOverflow.Respuesta;
import stackOverflow.Sistema;
import stackOverflow.Usuario;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestRespuesta {

	private Sistema sistema;
	private Respuesta respuesta;
	private Respuesta respuesta2;
	private Pregunta pregunta;
	private Usuario usuario;
	private Usuario usuario2;

	@Before
	public void setUp() {
		usuario = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		pregunta = mock(Pregunta.class);
		sistema = mock(Sistema.class);
		respuesta = new Respuesta("respuesta a publicacion",
				"cuerpo respuesta", usuario);
		respuesta2 = new Respuesta("respuesta a publicacion2",
				"cuerpo respuesta2", usuario2);
	}
	
	@Test
	public void DadaUnaRespuestaComprobarQueEsIgualASiMisma(){
		assertTrue(respuesta.sonIguales(respuesta));
	}
	
	@Test
	public void DadaUnaRespuestaComprobarQueNoEsIgualAOtra(){
		assertFalse(respuesta2.sonIguales(respuesta));
	}
	
	

	
}
