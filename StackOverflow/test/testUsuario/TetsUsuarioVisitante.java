package testUsuario;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.Insignia;
import stackOverflow.InsigniaPorEtiqueta;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;
import stackOverflow.SistemaAdministradorDePreguntas;
import stackOverflow.SistemaAdministradorDeUsuarios;
import stackOverflow.UsuarioSimple;
import stackOverflow.UsuarioVisitante;

public class TetsUsuarioVisitante {

	private UsuarioVisitante usuarioVisitante1;
	private UsuarioVisitante usuarioVisitante2;
	private Sistema sistema;
	private DateTime fechaDeUltimaPublicacion;

	private UsuarioSimple usuarioSimple1;
	private InsigniaPorEtiqueta insignia1;
	private InsigniaPorEtiqueta insignia2;
	private SistemaAdministradorDeUsuarios sistU;
	private SistemaAdministradorDePreguntas sistP;
	private Pregunta pregunta;

	@Before
	public void setUp() {
		sistU = new SistemaAdministradorDeUsuarios();
		sistP = new SistemaAdministradorDePreguntas();
		sistema = new Sistema(sistP, sistU);

		usuarioVisitante1 = new UsuarioVisitante(sistema, "56782-5");
		usuarioVisitante2 = new UsuarioVisitante(sistema, "87989-9");
		usuarioSimple1 = new UsuarioSimple(sistema, "JuanPerez10",
				"jperez@gmail.com", "Juan Perez");
		insignia1 = new InsigniaPorEtiqueta("InsigniaDePrueba", 1);
		insignia2 = new InsigniaPorEtiqueta("InsigniaDePrueba2", 2);

	}

	@Test
	public void DadoUnUsuarioVisitanteQueRealizaUnPreguntaComprobarQueNoSeGuardoEnElSistemaDichaPregunta() {
		assertSame(sistema.preguntas().size(), 0);
		usuarioVisitante1.realizarPregunta("Patron Composite",
				"Es un patron de diseño recursivo?",
				"PatronDeDiseño-Programacion-Composite");
		assertSame(sistema.preguntas().size(), 0);
	}

	@Test
	public void DadoUnUsuarioQueRealizaUnaRespuestaComprobarQueSeGuardoEnElSistema()
			throws Exception {
		pregunta = new Pregunta("variable en java",
				"¿Como se declara una variable de int en java?",
				"variable-Java", usuarioSimple1);
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("variable en java",
				"¿Como se declara una variable de int en java?",
				"variable-Java");
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 0);
		usuarioVisitante1.responder(pregunta, "respuesta a publicacion",
				"se declara asi: int x; ");
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 1);
	}

	@Test
	public void DadoUnUsuarioVisitanteModeradorSeEncuentraInregularQueDeberiaDevolverElValorCeroComaSetentaYCinco() {
		fechaDeUltimaPublicacion = new DateTime(30 - 05 - 2014);
		usuarioVisitante2.setFechaDeUltimaPublicacion(fechaDeUltimaPublicacion);
		assertTrue(usuarioVisitante2.esRegular() == 0.75);
	}

	@Test
	public void DadoUnUsuarioVisitanteSeEncuentraRegularQueDeberiaDevolverElValorUno() {
		fechaDeUltimaPublicacion = new DateTime("2014-07-30");
		usuarioVisitante2.setFechaDeUltimaPublicacion(fechaDeUltimaPublicacion);
		assertTrue(usuarioVisitante1.esRegular() == 1);
	}

	@Test
	public void DadoUnUsuarioQueEfectuoUnaRespuestaObtenerSuPuntajePorActividadQueEsCinco()
			throws Exception {
		pregunta = new Pregunta("variable en java",
				"¿Como se declara una variable de int en java?",
				"variable-Java", usuarioSimple1);
		usuarioSimple1.realizarPregunta("variable en java",
				"¿Como se declara una variable de int en java?",
				"variable-Java");
		usuarioVisitante1.responder(pregunta, "respuesta a publicacion",
				"se declara asi: int x; ");
		assertSame(usuarioVisitante1.puntajePorActividad(), 2);
	}

	@Test
	public void DadoUnUsuarioVisitanteQueEfectuoUnaRespuestaPeroSeEncuentraInregularObtenerSusPuntosParaRankingQueEsUnoComaCinco()
			throws Exception {
		pregunta = new Pregunta("variable en java",
				"¿Como se declara una variable de int en java?",
				"variable-Java", usuarioSimple1);
		fechaDeUltimaPublicacion = new DateTime("2014-05-29");
		usuarioSimple1.realizarPregunta("variable en java",
				"¿Como se declara una variable de int en java?",
				"variable-Java");
		usuarioVisitante1.responder(pregunta, "respuesta a publicacion",
				"se declara asi: int x; ");
		usuarioVisitante1.setFechaDeUltimaPublicacion(fechaDeUltimaPublicacion);
		assertTrue(usuarioVisitante1.puntosParaRanking() == 1.5);
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarQueResivioUnaInsignia() {
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 0);
		usuarioVisitante1.aceptarInsignia(insignia1);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 1);
	}

	@Test
	public void DadoUnUsuarioComprobarQueResivioUnaInsigniaQueYaTeniaYNoLaAgrego() {
		usuarioVisitante1.aceptarInsignia(insignia1);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 1);
		usuarioVisitante1.aceptarInsignia(insignia2);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 2);
		usuarioVisitante1.aceptarInsignia(insignia1);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 2);
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarSuNombre() {
		assertSame(usuarioVisitante1.getIP(), "56782-5");
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarSuFechaDeUltimaPublicacion() {
		assertEquals(usuarioVisitante1.getFechaDeUltimaPublicacion(), null);
	}

	@Test
	public void DadoUnUsuarioComprobrarLasInsigniaQueLeFalta() {
		assertSame(sistema.insignias().size(), 0);
		sistema.agregarInsignia(insignia1);
		assertSame(sistema.insignias().size(), 1);
		sistema.agregarInsignia(insignia2);
		assertSame(sistema.insignias().size(), 2);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 0);
		usuarioVisitante1.aceptarInsignia(insignia1);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 1);
		ArrayList<Insignia> faltantes = new ArrayList<Insignia>();
		faltantes.add(insignia2);
		assertEquals(usuarioVisitante1.insigniasFaltantes(), faltantes);
	}

	@Test
	public void DadoUnUsuarioComprobarQueNoLeFaltaInsigniasDelSistema() {
		assertSame(sistema.insignias().size(), 0);
		sistema.agregarInsignia(insignia1);
		assertSame(sistema.insignias().size(), 1);
		sistema.agregarInsignia(insignia2);
		assertSame(sistema.insignias().size(), 2);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 0);
		usuarioVisitante1.aceptarInsignia(insignia1);
		usuarioVisitante1.aceptarInsignia(insignia2);
		assertSame(usuarioVisitante1.cantidadDeInsignias(), 2);
		ArrayList<String> faltantes = new ArrayList<String>();
		assertEquals(usuarioVisitante1.insigniasFaltantes(), faltantes);
	}
}
