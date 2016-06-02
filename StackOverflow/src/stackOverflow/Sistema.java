package stackOverflow;


import java.util.ArrayList;
import java.util.Observable;



public class Sistema extends Observable {
	
	private ArrayList<Usuario> usrs;/*el nombre de usuario no se puede repetir*/
	private ArrayList<Pregunta> preguntas;/*el titulo de la pregunta no se puede repetir*/;
	
	private ArrayList<Insignia> insignias;
	private SistemaAdministradorDePreguntas sistP;
	private SistemaAdministradorDeUsuarios sistU;
	
	public Sistema(SistemaAdministradorDePreguntas sistemaAdministradorDePreguntas,SistemaAdministradorDeUsuarios sistemaAdministradorDeUsuarios)
	{
		this.usrs= new ArrayList<Usuario>();
		this.preguntas= new ArrayList<Pregunta>();
		this.insignias = new ArrayList<Insignia>();
		this.sistP = sistemaAdministradorDePreguntas;
		this.sistU = sistemaAdministradorDeUsuarios ;
	}
	
	public void registrarPublicacion(Usuario usuario,Pregunta pregunta) throws Exception{
		sistP.agregarPregunta(this.preguntas, pregunta);
		setChanged();
		notifyObservers(usuario);
		
	}
	
	public void borrarPregunta(Pregunta pregunta){
		
		this.preguntas.remove(obtenerPregunta(pregunta));
	}
	
	public Pregunta obtenerPregunta(Pregunta pregunta) {
		 
		for(Pregunta preg : this.preguntas)
		{
			if(pregunta.sonIguales(preg))
			{
				return preg;
			}
			
		}
		return null;
	}

	
	
	public void registrarUsuario(Usuario usuario) throws Exception{
		
		this.sistU.agregarUsuario(usuario, usrs);
	}
	
	public void registrarUsuarioVisitante(Usuario usuario){
		if(! (this.sistU.existeNombreDeUsuario(this.usrs, usuario.getUsrName()))){
			this.usrs.add(usuario);
		}
	}
		
	public ArrayList<Pregunta> preguntas() {
		return this.preguntas;
	}
	
	//insignias
	public void entregarInsignia(Insignia insignia,Usuario usuario) {
		 this.sistU.obtenerUsuario(usuario,this.usrs).aceptarInsignia(insignia);
	}
	
	//agregar observers
	public void agregarInsignia(Insignia insignia){
		super.addObserver(insignia);
		this.insignias.add(insignia);
	}
	
	public ArrayList<Respuesta> obtenerRespuestasDelUsuario(Usuario usuario) {
		ArrayList<Respuesta> respuestas= new ArrayList<Respuesta>();
		for (Pregunta pregunta : this.preguntas)
		{
			respuestas.addAll(pregunta.obtenerRespuestasDelUsuario(usuario));
		}
		return respuestas;
	}

	public ArrayList<Insignia> insigniasDelUsuario(Usuario usuario) {
		return (ArrayList<Insignia>) this.sistU.obtenerUsuario(usuario,this.usrs).insigniaGanadas();
	}


	public ArrayList<Usuario> usuarios()
	{
		return this.usrs;
	}

	public ArrayList<Insignia> insignias() {
		return insignias;
	}


	public void setInsignias(ArrayList<Insignia> insignias) {
		this.insignias = insignias;
	}

	public void reemplazarPregunta(Pregunta pregunta,
			Pregunta preguntaModificada) {
		if(this.preguntas.remove(pregunta)){
		this.preguntas.add(preguntaModificada);
		}
		
	}
	
	public ArrayList<Pregunta> buscar(FiltroDeBusqueda filtro)
	{
		filtro.setSistema(this);
		return (ArrayList<Pregunta>) filtro.buscar();
	}

	


	

	
}	
	
	