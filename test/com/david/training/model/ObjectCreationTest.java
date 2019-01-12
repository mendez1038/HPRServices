package com.david.training.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.david.training.model.utils.ContenidoByTituloComparator;

public class ObjectCreationTest {
	
	public static void main(String args[]) {
		
		
		
		
		Pedido u1 = new Pedido();
		
		u1.setIdPedido(1);
		u1.setFechaPedido(new Date());
		u1.setPrecioTotal(2.0);
		u1.setEmail("AA@A.COM");
		
		
		
		//System.out.println("Pedido= "+u1);
		
		
		
		//System.out.println("El pedido "+u1.getIdPedido()+" realizado por "+u1.getEmail()+" se pidió en la fecha"+u1.getFechaPedido());
		
		
		
		Pedido u2 = new Pedido();
		u2.setIdPedido(2);
		u2.setFechaPedido(new Date());
		u2.setPrecioTotal(4.0);
		u2.setEmail("BB@B.COM");
		//System.out.println("Pedido= "+u2);
		
		//System.out.println("El pedido "+u2.getIdPedido()+" realizado por "+u2.getEmail()+" se pidió en la fecha"+u2.getFechaPedido());
		
		Pedido[] pedidos = new Pedido[] {u1,u2};
		
		
		for (Pedido u : pedidos) {
			System.out.println( "   ¡Bienvenidos a David Training!");
		System.out.println(u.toString());
		}
		
		List<Contenido> contenidos = new ArrayList<Contenido>();
		
		Contenido c1 = new Contenido();
		c1.setIdContenido(1);
		c1.setTitulo("abc");
		contenidos.add(c1);
		
		Contenido c2 = new Contenido();
		c2.setIdContenido(2);
		c2.setTitulo("bado");
		contenidos.add(c2);
		
		Contenido c3= new Contenido();
		c3.setIdContenido(3);
		c3.setTitulo("asa");
		contenidos.add(c3);
		
		Collections.sort(contenidos, new ContenidoByTituloComparator());
		
		
		System.out.println("\nOrdenado por titulo:");
		for (Contenido c: contenidos) {
			System.out.println(c);
		}
		
		
	}
}
