package deliveryhamburgueriabk.service;
import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.factorymethod.IPedido;
import deliveryhamburgueriabk.factorymethod.IPedidoFactory;
import deliveryhamburgueriabk.factorymethod.PedidoFactoryProvider;
import deliveryhamburgueriabk.model.Pedido;

public class PedidoService {

    public IPedido criarPedido(Pedido pedido, TipoPedido tipoPedido){
        IPedidoFactory factory = PedidoFactoryProvider.getFactory(tipoPedido);
        return factory.criarPedido(pedido);
    }

    public void finalizarPedido(Pedido pedido, TipoPedido tipoPedido){
        IPedido pedidoCriado = criarPedido(pedido, tipoPedido);
        pedidoCriado.processar();
        System.out.println("Tipo: " + pedidoCriado.descTipo());
        System.out.println("Subtotal: R$ " + pedido.getValorTotal());
        System.out.println("Frete: R$ " + pedidoCriado.calcularFrete());
        System.out.println("Total: R$ " + (pedido.getValorTotal() + pedidoCriado.calcularFrete()));
    }
}