package demo2api.joins.partof;

import java.io.Serializable;

public class Parto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public long conv;
	
	public long person;

	public Parto() {
	}
	
	public Parto(Partof p) {
		conv = p.conv;
		person = p.person;
	}
}
