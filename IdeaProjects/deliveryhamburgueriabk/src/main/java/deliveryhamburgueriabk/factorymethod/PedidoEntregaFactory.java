package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.model.Pedido;

public class PedidoEntregaFactory implements IPedidoFactory{
    @Override
    public IPedido criarPedido(Pedido pedido) {
        return new PedidoEntrega(pedido);
    }
}
