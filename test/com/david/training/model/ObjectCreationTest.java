package com.david.training.model;

import java.util.Date;

public class ObjectCreationTest {
	
	public static void main(String args[]) {
		
		
		
		
		Pedido u1 = new Pedido();
		u1.setIdPedido(1);
		u1.setEmail("AA@A.COM");
		
		u1.setFechaPedido(new Date());
		
		//System.out.println("Pedido= "+u1);
		
		
		
		//System.out.println("El pedido "+u1.getIdPedido()+" realizado por "+u1.getEmail()+" se pidió en la fecha"+u1.getFechaPedido());
		
		
		
		Pedido u2 = new Pedido();
		u2.setIdPedido(2);
		u2.setEmail("BB@B.COM");
		u2.setFechaPedido(new Date());
		
		//System.out.println("Pedido= "+u2);
		
		//System.out.println("El pedido "+u2.getIdPedido()+" realizado por "+u2.getEmail()+" se pidió en la fecha"+u2.getFechaPedido());
		
		Pedido[] pedidos = new Pedido[] {u1,u2};
		
		
		for (Pedido u : pedidos) {
			System.out.println( "   ¡Bienvenidos a David Training!");
		System.out.println(u.toString());
		}
		
		
		
		
	}

}
