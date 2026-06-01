package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.model.Pedido;

public interface IPedidoFactory {

    IPedido criarPedido(Pedido pedido);
}