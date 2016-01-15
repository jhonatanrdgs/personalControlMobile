package br.com.jhonatan.personalcontrolmobile.dto;

import java.util.Date;

/**
 * Created by Jhonatan on 14/01/2016.
 */
public class Despesa {

    private Long id;
    private Categoria categoria;
    private MetodoPagamento metodoPagamento;
    //TODO private Usuario usuario;
    private Double valorTotal;
    private String descricao;
    private Integer totalParcelas;
    private Date data;
    private boolean fixa;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(Integer totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isFixa() {
        return fixa;
    }

    public void setFixa(boolean fixa) {
        this.fixa = fixa;
    }
}
