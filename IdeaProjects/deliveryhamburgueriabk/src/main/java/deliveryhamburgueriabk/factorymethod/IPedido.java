package deliveryhamburgueriabk.factorymethod;

public interface IPedido {

    void processar();
    double calcularFrete();
    int calcularTempoDePreparo();
    String descTipo();
}
