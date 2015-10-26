package br.com.afip.teste1.modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Cliente {
	
	private Long id;
	
	@NotNull(message="{cliente.formulario.nome.obrigatorio}") 
	@Size(min=1,max=100,message="{cliente.formulario.nome.obrigatorio}")
	private String nome;
	
	@NotNull(message="{cliente.formulario.cpf.obrigatorio}")
	@Size(min=11,max=11, message="{cliente.formulario.cpf.obrigatorio}")
	private String cpf;
	
	@NotNull(message="{cliente.formulario.email.obrigatorio}")
	@Size(min=1, max=100, message="{cliente.formulario.email.obrigatorio}")
	/**Pattern apenas para teste, precisa de adequações**/
	@Pattern(regexp = ".+@.+\\.[a-z]+", message="{cliente.formulario.email.invalido}")
	private String email;
	
	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
