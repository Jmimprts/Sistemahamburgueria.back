package deliveryhamburgueriabk.model;

import deliveryhamburgueriabk.enums.FormaPagamento;
import deliveryhamburgueriabk.enums.StatusPedido;
import deliveryhamburgueriabk.enums.TipoPedido;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_pedido", nullable = false)
    private int numeroPedido;

    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pedido")
    private TipoPedido tipoPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(){}

    public Pedido(int numeroPedido, FormaPagamento formaPagamento) {
        this.numeroPedido = numeroPedido;
        this.formaPagamento = formaPagamento;
        this.statusPedido = StatusPedido.PENDENTE;
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        item.setPedido(this);
        valorTotal += item.getSubtotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double calcularTotal(){
        return valorTotal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

}