package testSistema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.Insignia;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;
import stackOverflow.SistemaAdministradorDePreguntas;
import stackOverflow.SistemaAdministradorDeUsuarios;
import stackOverflow.Usuario;
import stackOverflow.UsuarioModerador;
import stackOverflow.UsuarioSimple;
import stackOverflow.UsuarioVisitante;
import static org.mockito.Mockito.*;
public class TestSistema {

	
		private Sistema sist;
		private Usuario userMock1;
		private Pregunta preguntaMock;
		
		private Insignia insigniaMock;
		private List<Insignia> listaDeInsignias;
		private SistemaAdministradorDeUsuarios sistU;
		private SistemaAdministradorDePreguntas sistP;
		
	@Before
	public void update()
	{
		sistP= new SistemaAdministradorDePreguntas();
		sistU= new SistemaAdministradorDeUsuarios();
		sist= new Sistema(sistP,sistU);
		userMock1= mock(Usuario.class);
		preguntaMock= mock(Pregunta.class);
		
		insigniaMock=mock(Insignia.class);
		listaDeInsignias=new ArrayList<Insignia>();
		
	}
	@Test
	public void testAlInicializarUnSistemaArrancaConUnaListaDeUsuariosDeTamanhoIgualACero()
	{
		assertTrue(sist.usuarios().size()==0);
		
	}
	@Test
	public void testAlInicializarUnSistemaArrancaConUnaListaDePreguntasDeTamanhoIgualACero()
	{
		assertTrue(sist.preguntas().size()==0);
		
	}
	
	
	@Test
	public void testRegistrarUsuarioVisitante() throws Exception
	{
		sist.registrarUsuario(new UsuarioVisitante(sist, "179.254.234"));
		assertTrue(sist.usuarios().size()==1);
		
	}
	
	@Test
	public void registrarUsuarioModerador() throws Exception
	{
		sist.registrarUsuario(new UsuarioModerador(sist, "diego", "diego@websocial"));
		sistU.existeNombreDeUsuario(sist.usuarios(), "diego");
		assertTrue(sist.usuarios().size()==1);
		assertFalse(sist.usuarios().size()==2);
	}
	
	
	
	@Test(expected=Exception.class)
	public void testAlIntentarRegistrarDosUsuariosModeradoresConELMismoNombreDeUsuarioDebeLanzarUnaExcepcion() throws Exception 
	{
	
			sist.registrarUsuario(new UsuarioModerador(sist, "cloneTrooper", "diego@websocial"));
			assertTrue(sistU.existeNombreDeUsuario(sist.usuarios(), "cloneTrooper"));
			sist.registrarUsuario(new UsuarioModerador(sist, "cloneTrooper", "diego@wikipedia"));
			
	}
	@Test
	public void testRegistrarUsuarioSimple() throws Exception
	{
		sist.registrarUsuario(new UsuarioSimple(sist, "nacho", "nacho@man", "ignacio"));
		
	}
	
	@Test(expected=Exception.class)
	public void testAlIntentarRegistrarDosUsuariosSimplesConELMismoNombreDeUsuarioDebeLanzarUnaExcepcion() throws Exception 
	{
		
			sist.registrarUsuario(new UsuarioSimple(sist, "cloneTrooper", "diego@websocial", "diego"));
			sist.registrarUsuario(new UsuarioSimple(sist, "cloneTrooper", "diego@wikipedia", "diego"));
	}
	
	@Test 
	public void testRegistrarPublicacion()
	{
		
	}
	@Test
	public void testObtenerPregunta() throws Exception
	{
		when(preguntaMock.sonIguales(preguntaMock)).thenReturn(true);
		sist.registrarPublicacion(userMock1, preguntaMock);
		
		assertEquals(sist.obtenerPregunta(preguntaMock),preguntaMock);
	}
	
	@Test
	public void testBorrarPregunta() throws Exception
	{
		when(preguntaMock.sonIguales(preguntaMock)).thenReturn(true);
		sist.registrarPublicacion(userMock1, preguntaMock);
		sist.borrarPregunta(preguntaMock);
		assertFalse(sistP.existePregunta(sist.preguntas(), preguntaMock));
	}
	
	
	
	
	@Test
	public void testEntregarInsignia() throws Exception
	{
		when(userMock1.getUsrName()).thenReturn("diego");
		when(userMock1.sonIguales(userMock1)).thenReturn(true);
		sist.registrarUsuario(userMock1);
		sist.entregarInsignia(insigniaMock, userMock1);
		verify(userMock1).aceptarInsignia(insigniaMock);
	}

	@Test
	public void testVerificarEntrega() throws Exception
	{
		when(userMock1.getUsrName()).thenReturn("diego");
		when(userMock1.sonIguales(userMock1)).thenReturn(true);

		when(userMock1.insigniaGanadas()).thenReturn(listaDeInsignias);
		sist.registrarUsuario(userMock1);
		assertEquals(sist.insigniasDelUsuario(userMock1),listaDeInsignias); 
		
	}
	@Test
	public void testVerificaQueAlAgregarUnaInsigniaElSistemaSeGuardaSuNombre()
	{
		when(insigniaMock.nombre()).thenReturn("insignia");
		sist.agregarInsignia(insigniaMock);
		assertEquals(sist.insignias().get(0),insigniaMock);
	}
}
