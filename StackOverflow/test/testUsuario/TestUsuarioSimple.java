package testUsuario;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.Insignia;
import stackOverflow.InsigniaPorEtiqueta;
import stackOverflow.Pregunta;
import stackOverflow.Ranking;
import stackOverflow.Respuesta;
import stackOverflow.Sistema;
import stackOverflow.SistemaAdministradorDePreguntas;
import stackOverflow.SistemaAdministradorDeUsuarios;
import stackOverflow.UsuarioSimple;

public class TestUsuarioSimple {

	private UsuarioSimple usuarioSimple1;
	private UsuarioSimple usuarioSimple2;
	private UsuarioSimple usuarioSimple3;
	private Sistema sistema;
	private DateTime fechaDeRegistracion;
	private DateTime fechaUltimaDePublicacion;
	private InsigniaPorEtiqueta insignia1;
	private InsigniaPorEtiqueta insignia2;
	private SistemaAdministradorDeUsuarios sistU;
	private SistemaAdministradorDePreguntas sistP;
	private Pregunta pregunta;
	private Ranking ranking;

	@Before
	public void setUp() {
		sistU = new SistemaAdministradorDeUsuarios();
		sistP = new SistemaAdministradorDePreguntas();
		sistema = new Sistema(sistP, sistU);
		ranking= new Ranking();
		fechaDeRegistracion = new DateTime(DateTime.now().getDayOfYear()
				- DateTime.now().getMonthOfYear() - DateTime.now().getYear());

		usuarioSimple1 = new UsuarioSimple(sistema, "JuanPerez10",
				"jperez@gmail.com", "Juan Perez");
		usuarioSimple2 = new UsuarioSimple(sistema, "diegoz12",
				"diegoz12@gmail.com", "Diego goz");
		insignia1 = new InsigniaPorEtiqueta("InsigniaDePrueba", 1);
		insignia2 = new InsigniaPorEtiqueta("InsigniaDePrueba2", 2);
		usuarioSimple1.setRanking(ranking);
		usuarioSimple2.setRanking(ranking);


	}

	@Test
	public void DadoUnUsuarioQueRealizaUnPreguntaComprobarQueSeGuardoEnElSistemaDichaPregunta()
			throws Exception {
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("TituloPregunta0", "pregunta 0",
				"etiqueta 0");
		assertEquals(usuarioSimple1.posicionEnELRanking(),1);
		assertSame(sistema.preguntas().size(), 1);
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
		usuarioSimple2.responder(pregunta, "respuesta a publicacion",
				"se declara asi: int x; ");
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 1);
		assertEquals(usuarioSimple1.posicionEnELRanking(),1);
		assertEquals(usuarioSimple2.posicionEnELRanking(),2);

	}

	@Test
	public void DadoUnUsuarioSeEncuentraInregularQueDeberiaDevolverElValorCeroComaSetentaYCinco() {
		fechaUltimaDePublicacion = new DateTime("2014-02-4");
		usuarioSimple3 = new UsuarioSimple(sistema, "PepGomez",
				"pgomez@gmail.com", "Pedro Gomez");
		usuarioSimple3.setFechaDeUltimaPublicacion(fechaDeRegistracion);
		assertTrue(usuarioSimple3.esRegular() == 0.75);
	}

	@Test
	public void DadoUnUsuarioSeEncuentraRegularQueDeberiaDevolverElValorUno()
			throws Exception {
		usuarioSimple1.realizarPregunta("TituloPregunta1", "pregunta 1",
				"etiqueta 1");
		assertTrue(usuarioSimple1.esRegular() == 1.0);
	}

	@Test
	public void DadoUnUsuarioQueRealizoDosPreguntasObtenerSuPuntajePorActividadQueEsVeinte()
			throws Exception {
		usuarioSimple1.realizarPregunta("Titulo Pregunta 2", "pregunta 2",
				"etiqueta 2");
		usuarioSimple1.realizarPregunta("Titulo Pregunta 3", "pregunta 3",
				"etiqueta 3");
		assertSame(usuarioSimple1.puntajePorActividad(), 20);
	}

	@Test
	public void DadoUnUsuarioQueRealizoDosPreguntasPeroSeEncuentraInregularObtenerSusPuntosParaRankingQueEsQuince()
			throws Exception {
		fechaUltimaDePublicacion = new DateTime("2014-04-4");
		usuarioSimple1.realizarPregunta("Patron Composite",
				"Es un patron de diseño",
				"PatronDeDiseño-Programacion-Composite");
		usuarioSimple1.realizarPregunta("Recursion",
				"¿en que consiste y como se implementa?",
				"Recursion-Programacion");
		usuarioSimple1.setFechaDeUltimaPublicacion(fechaUltimaDePublicacion);
		assertTrue(usuarioSimple1.puntosParaRanking() == 15);
	}

	@Test
	public void DadoUnUsuarioQueVotoPositivoUnaPreguntaComprobarQueSeComputoElVoto()
			throws Exception {
		Pregunta pregunta = new Pregunta("Titulo Pregunta 4", "pregunta 4",
				"etiqueta 4", usuarioSimple1);
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("Titulo Pregunta 4", "pregunta 4",
				"etiqueta 4");
		assertSame(sistema.preguntas().get(0).cantidadDeVotos(), 0);
		usuarioSimple2.votarPregunta(pregunta, "positiva");
		assertSame(sistema.preguntas().get(0).cantidadDeVotos(), 1);
	}

	@Test
	public void DadoUnUsuarioQueVotoPositivoUnaRespuestaComprobarQueSeComputoElVoto()
			throws Exception {
		Pregunta pregunta = new Pregunta("Titulo Pregunta 5", "pregunta 5",
				"etiqueta 5", usuarioSimple1);
		Respuesta respuesta = new Respuesta("respuesta a publicacion",
				"respuesta", usuarioSimple2);
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("Titulo Pregunta 5", "pregunta 5",
				"etiqueta 5");
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 0);
		usuarioSimple2.responder(pregunta, "respuesta a publicacion",
				"respuesta");
		usuarioSimple1.votarRespuesta(pregunta, respuesta, "positiva");
		assertSame(sistema.preguntas().get(0).obtenerRespuesta(respuesta)
				.cantidadDeVotos(), 1);
	}

	@Test
	public void DadoUnUsuarioComprobarQueResivioUnaInsignia() {
		assertSame(usuarioSimple1.cantidadDeInsignias(), 0);
		usuarioSimple1.aceptarInsignia(insignia1);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 1);
	}

	@Test
	public void DadoUnUsuarioComprobarQueResivioUnaInsigniaQueYaTeniaYNoLaAgrego() {
		usuarioSimple1.aceptarInsignia(insignia1);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 1);
		usuarioSimple1.aceptarInsignia(insignia2);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 2);
		usuarioSimple1.aceptarInsignia(insignia1);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 2);
	}

	@Test
	public void DadoUnUsuarioComprobarSuNombreDeUsuario() {
		assertSame(usuarioSimple1.getUsrName(), "JuanPerez10");
	}

	@Test
	public void DadoUnUsuarioComprobarSuNombreReal() {
		assertSame(usuarioSimple1.getRealName(), "Juan Perez");
	}

	@Test
	public void DadoUnUsuarioComprobarSuEmail() {
		assertSame(usuarioSimple1.getEmail(), "jperez@gmail.com");
	}

	@Test
	public void DadoUnUsuarioComprobarSuFechaDeRegistracion() {
		assertEquals(usuarioSimple1.getFechaDeRegistracion(),
				fechaDeRegistracion);
	}

	@Test
	public void DadoUnUsuarioComprobarSuFechaDeUltimaModeracion() {
		assertEquals(usuarioSimple1.getFechaDeUltimaPublicacion(), null);
	}

	@Test
	public void DadoUnUsuarioComprobrarLasInsigniaQueLeFalta() {
		assertSame(sistema.insignias().size(), 0);
		sistema.agregarInsignia(insignia1);
		assertSame(sistema.insignias().size(), 1);
		sistema.agregarInsignia(insignia2);
		assertSame(sistema.insignias().size(), 2);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 0);
		usuarioSimple1.aceptarInsignia(insignia1);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 1);
		ArrayList<Insignia> faltantes = new ArrayList<Insignia>();
		faltantes.add(insignia2);
		assertEquals(usuarioSimple1.insigniasFaltantes(), faltantes);
	}

	@Test
	public void DadoUnUsuarioComprobarQueNoLeFaltaInsigniasDelSistema() {
		assertSame(sistema.insignias().size(), 0);
		sistema.agregarInsignia(insignia1);
		assertSame(sistema.insignias().size(), 1);
		sistema.agregarInsignia(insignia2);
		assertSame(sistema.insignias().size(), 2);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 0);
		usuarioSimple1.aceptarInsignia(insignia1);
		usuarioSimple1.aceptarInsignia(insignia2);
		assertSame(usuarioSimple1.cantidadDeInsignias(), 2);
		ArrayList<Insignia> faltantes = new ArrayList<Insignia>();
		assertEquals(usuarioSimple1.insigniasFaltantes(), faltantes);
	}

}
