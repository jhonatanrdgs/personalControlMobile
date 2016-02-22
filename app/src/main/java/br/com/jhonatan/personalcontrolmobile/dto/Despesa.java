package br.com.jhonatan.personalcontrolmobile.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Date inicio;
    private Date fim;

    public Despesa() {
        descricao = "";//null dá erro na consulta
    }

    public Despesa(String descricao, Double valorTotal, Categoria categoria, MetodoPagamento metodoPagamento, Date data, boolean fixa, Integer totalParcelas) {
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.categoria = categoria;
        this.metodoPagamento = metodoPagamento;
        this.data = data;
        this.fixa = fixa;
        this.totalParcelas = totalParcelas;
    }


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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

}
