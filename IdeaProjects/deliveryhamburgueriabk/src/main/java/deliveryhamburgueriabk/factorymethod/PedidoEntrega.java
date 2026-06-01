package deliveryhamburgueriabk.factorymethod;

import deliveryhamburgueriabk.enums.TipoPedido;
import deliveryhamburgueriabk.model.Pedido;

public class PedidoEntrega implements IPedido {

    private Pedido pedido;
    private static double TAXA_FRETE_BASE = 6.00;

    public PedidoEntrega(Pedido pedido){
        this.pedido = pedido;
        this.pedido.setTipoPedido(TipoPedido.ENTREGA);
    }

    @Override
    public void processar() {
        System.out.println("Pedido #" + pedido.getNumeroPedido() + " para ENTREGA processado.");
        System.out.println("Frete: R$ " + calcularFrete());
        System.out.println("Tempo estimado: " + calcularTempoDePreparo() + " minutos");
    }

    @Override
    public double calcularFrete() {
        return TAXA_FRETE_BASE + (pedido.getValorTotal() * 0.02);
    }

    @Override
    public int calcularTempoDePreparo() {
        return 50;
    }

    @Override
    public String descTipo() {
        return "Entrega";
    }

}


