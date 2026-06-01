package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;

public class PedidoRetirada implements IPedido{

    private Pedido pedido;
    public PedidoRetirada(Pedido pedido){
        this.pedido = pedido;
        this.pedido.setTipoPedido(TipoPedido.RETIRADA);
    }

    @Override
    public void processar() {
        System.out.println("Pedido #" + pedido.getNumeroPedido() + " para RETIRADA processado.");
        System.out.println("Pedido para retirada. Pronto em: " + calcularTempoDePreparo() + " minutos");
    }

    @Override
    public double calcularFrete() {
        return 0;
    }

    @Override
    public int calcularTempoDePreparo() {
        return 50;
    }

    @Override
    public String descTipo() {
        return "Retirada";
    }
}