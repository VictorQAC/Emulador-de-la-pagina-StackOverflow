package stackOverflow;
import java.util.ArrayList;


public class SistemaAdministradorDeUsuarios {

	public boolean existeNombreDeUsuario(ArrayList<Usuario> usrs,String usrName){
		boolean existe=false;
		int indice=0;
		while(indice != usrs.size() && existe==false)
		{
			existe=existe || usrs.get(indice).usrName==usrName;
			indice=indice+1;
		}
		return existe;
	}
	
	public boolean existeUsuario(ArrayList<Usuario> usrs,Usuario simple) {
		int indice =0;
		boolean existe = false;
		while(indice!= usrs.size()&& existe==false){
			existe=existe||usrs.get(indice).sonIguales(simple);
			indice=indice+1;
		}
		return existe;
	}
public Usuario obtenerUsuario(Usuario usr, ArrayList<Usuario> usrs) {
		
		for(Usuario u : usrs){
			if(u.sonIguales(usr)){
				return u;
			}
		}
		return null;
	}

public void agregarUsuario(Usuario usuario, ArrayList<Usuario> usrs) throws Exception
{
	if(this.existeNombreDeUsuario(usrs, usuario.getUsrName()))
	{
		throw new Exception("ese usuario ya existe, pruebe con otro");
	}
	else
	{
		usrs.add(usuario);
	}
}


}
