package com.david.training.model.utils;

import java.util.Comparator;

import com.david.training.model.Contenido;


public class ContenidoByTituloComparator implements Comparator<Contenido> {

	private static ContenidoByTituloComparator instance = null;
	
	public static final ContenidoByTituloComparator getInstance() {
		if(instance == null) {
			instance = new ContenidoByTituloComparator();
		}
		return instance;
	}

	@Override
	public int compare(Contenido o1, Contenido o2) {
		// TODO Auto-generated method stub
		return o1.getTitulo().compareTo(o2.getTitulo());
	}

}
