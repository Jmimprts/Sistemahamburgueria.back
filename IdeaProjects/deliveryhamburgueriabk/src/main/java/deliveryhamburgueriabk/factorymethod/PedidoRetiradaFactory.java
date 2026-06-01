package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.model.Pedido;

public class PedidoRetiradaFactory implements IPedidoFactory{
    @Override
    public IPedido criarPedido(Pedido pedido) {
        return new PedidoRetirada(pedido);
    }
}
