package stackOverflow;

import java.util.ArrayList;

public class InsigniaCompuesta extends Insignia {

	private ArrayList<Insignia> insignias;

	public InsigniaCompuesta(String nombre, Insignia insignia,
			Insignia insignia2) {
		super(nombre);
		this.insignias = new ArrayList<Insignia>();
		this.insignias.add(insignia);
		this.insignias.add(insignia2);

	}

	@Override
	public boolean cumpleCondicion(Usuario user, Sistema sist) {
		// TODO Auto-generated method stub
		boolean cumple = true;

		for (Insignia insignia : this.insignias) {
			cumple = cumple || insignia.cumpleCondicion(user, sist);
		}
		return cumple;
	}

}
