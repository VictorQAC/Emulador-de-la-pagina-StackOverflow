package stackOverflow;
import java.util.ArrayList;


public class SistemaAdministradorDePreguntas {
	
	public void agregarPregunta(ArrayList<Pregunta> preguntas,Pregunta pregunta) throws Exception{
		if(this.yaExisteLaPreguntaConElTitulo(preguntas,pregunta.getTitle()))
		{
			throw new Exception("Esta pregunta ya existe, prueba con otro titulo");
			
		}
		else {
			preguntas.add(pregunta);	
		}
	}
	
	public boolean yaExisteLaPreguntaConElTitulo(ArrayList<Pregunta> preguntas,String title) {
		boolean existe=false;
			for(Pregunta pregunta : preguntas)
			{
				existe=existe || pregunta.getTitle()==title;
			}
		
		return existe;
	}
	
	public boolean existePregunta(ArrayList<Pregunta> preguntas, Pregunta pregunta){
		int indice =0;
		boolean existe = false;
		while(indice!= preguntas.size()&& existe==false)
		{
			existe=existe||preguntas.get(indice).sonIguales(pregunta);
			indice=indice+1;
		}
		return existe;
	}
	
	
}
