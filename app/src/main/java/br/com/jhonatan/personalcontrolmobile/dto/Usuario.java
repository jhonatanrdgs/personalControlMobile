package br.com.jhonatan.personalcontrolmobile.dto;


public class Usuario {

	private Long id;
	private String nomeUsuario;
	private String login;
	private String senha;
	private boolean ativo;
	private String permissao;
	private String senhaDescriptografada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

    public String getSenhaDescriptografada() {
        return senhaDescriptografada;
    }

    public void setSenhaDescriptografada(String senhaDescriptografada) {
        this.senhaDescriptografada = senhaDescriptografada;
    }
}
