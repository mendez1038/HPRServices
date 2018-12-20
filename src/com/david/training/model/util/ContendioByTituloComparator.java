package com.david.training.model.util;

import java.util.Comparator;

import com.david.training.model.Contenido;

public class ContendioByTituloComparator implements Comparator<Contenido> {

	@Override
	public int compare(Contenido c1, Contenido c2) {
		// TODO Auto-generated method stub
		System.out.println("comparo "+c1.getTitulo()+" con "+c2.getTitulo());
		return c1.getTitulo().compareTo(c2.getTitulo());
	}

	
	
}
