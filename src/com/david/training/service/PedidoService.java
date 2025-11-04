package com.david.training.service;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Pedido;
import com.david.training.model.PedidoCriteria;
import com.david.training.model.LineaPedido;
import java.util.List;

public interface PedidoService {

    /** Lista paginada de pedidos de un usuario (sin líneas) */
    Results<Pedido> historial(String email, int startIndex, int count)
        throws DataException;

    /** (Opcional) Lista paginada con filtros por fecha/orden */
    Results<Pedido> historial(String email, PedidoCriteria criteria, int startIndex, int count)
        throws DataException;

    /** Detalle seguro: devuelve el pedido + líneas solo si pertenece al email */
    Pedido detalle(Integer idPedido, String email)
        throws InstanceNotFoundException, DataException;

    /** (Si de verdad lo necesitas) Solo las líneas, pero mantén el filtro por dueño */
    List<LineaPedido> lineasDePedido(Integer idPedido, String email)
        throws InstanceNotFoundException, DataException;
    
    boolean comprado(String email, Integer idContenido) 
    		throws DataException;

}
