package com.david.training.model;

public class EqualsToString {

	public static void main(String[] args) {
		
		Matchup u = new Matchup();
		
		u.setIdEquipo1(2);
		u.setIdEquipo2(3);
		
		String idEquipo1 = u.getIdEquipo1().toString();
		String idEquipo2 = u.getIdEquipo2().toString();
		
		System.out.println(idEquipo1.equals(idEquipo2));
	}

}
