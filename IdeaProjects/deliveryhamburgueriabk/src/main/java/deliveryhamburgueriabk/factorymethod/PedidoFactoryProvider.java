package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.TipoPedido;

public class PedidoFactoryProvider {

    public static IPedidoFactory getFactory(TipoPedido tipoPedido){
        return switch (tipoPedido){
            case ENTREGA -> new PedidoEntregaFactory();
            case RETIRADA -> new PedidoRetiradaFactory();
        };
    }
}
