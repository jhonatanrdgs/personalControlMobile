package br.com.jhonatan.personalcontrolmobile.dto;

public class RelatorioResumoDTO {

	private Double totalGastosVariaveisPeriodo;
	private Double totalGastosFixos; 
	private Double rendimentos;
	private Double sobra;
	private Double totalGastos;
	private Double percentualComprometido;

	public RelatorioResumoDTO() {}


	public Double getTotalGastosVariaveisPeriodo() {
		return totalGastosVariaveisPeriodo;
	}

	public void setTotalGastosVariaveisPeriodo(Double totalGastosVariaveisPeriodo) {
		this.totalGastosVariaveisPeriodo = totalGastosVariaveisPeriodo;
	}

	public Double getTotalGastosFixos() {
		return totalGastosFixos;
	}

	public void setTotalGastosFixos(Double totalGastosFixos) {
		this.totalGastosFixos = totalGastosFixos;
	}

	public Double getRendimentos() {
		return rendimentos;
	}

	public void setRendimentos(Double rendimentos) {
		this.rendimentos = rendimentos;
	}

	public Double getSobra() {
		return sobra;
	}

	public void setSobra(Double sobra) {
		this.sobra = sobra;
	}

	public Double getTotalGastos() {
		return totalGastos;
	}

	public void setTotalGastos(Double totalGastos) {
		this.totalGastos = totalGastos;
	}

	public Double getPercentualComprometido() {
		return percentualComprometido;
	}

	public void setPercentualComprometido(Double percentualComprometido) {
		this.percentualComprometido = percentualComprometido;
	}
}
