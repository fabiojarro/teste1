package br.com.afip.teste1.util;

import org.springframework.validation.BindingResult;

import br.com.afip.teste1.dao.ClienteDao;
import br.com.afip.teste1.modelo.Cliente;

public class ValidaCliente {
	
	Cliente cliente;	
	BindingResult bindingResult;
	
	public ValidaCliente(Cliente cliente,BindingResult bindingResult) {
		this.cliente = cliente;
		this.bindingResult= bindingResult;
		this.cliente.setNome(cliente.getNome().trim());
		this.cliente.setEmail(cliente.getEmail().trim());
		this.cliente.setCpf(cliente.getCpf().trim());
	}
	
	private boolean verificaEmailexistente(){
		boolean retorno = new ClienteDao().verificaEmail(cliente.getEmail()); 
		if(retorno){
			bindingResult.rejectValue("email",null, "Email já cadastrado por outro usuário");
		}
		return retorno;
		
	}
	
	private boolean verificaNome(){		
		if(this.cliente.getNome().isEmpty()){
			bindingResult.rejectValue("nome",null, "O campo NOME não pode ser vazio.");
			return false;
		}
		return true;
	}
	
	private boolean verificaCPFexistente(){
		boolean retorno = new ClienteDao().verificaCpf(cliente.getCpf());
		if(retorno){
			bindingResult.rejectValue("cpf",null, "CPF já cadastrado");
		}
		return retorno;
	}	
	
	/** Como se trata de um teste, apenas realizei a validação de números do campo CPF
	  Caberia neste metodo criar um validação real de CPF.
	  Não implementei a validação do CPF propositalmente, pois no momento dos testes inserir um 
	  CPF valido talvez fosse um inconveniente.
	 **/
	private boolean validaCPF(){		
		try {
			Long.parseLong(cliente.getCpf());
			return true;
		} catch (Exception e) {
			bindingResult.rejectValue("cpf",null, "Apenas caractreres númericos");
			return false;
		}
	}
	
	public boolean validacao(){
		boolean nome = verificaNome();
		boolean cpf = validaCPF();
		boolean cpfExiste = verificaCPFexistente();
		boolean emailExiste = verificaEmailexistente();
		
		return (nome && cpf && !cpfExiste && !emailExiste);
		
	}

	public boolean validacaoAlteracao() {
		boolean nome = true;
		boolean cpf = true;
		boolean cpfExiste = false;
		boolean emailExiste = false;
		
		Cliente cadastroAnterior = new ClienteDao().buscaPorId(cliente.getId());
		
		if(!cliente.getNome().equals(cadastroAnterior.getNome())){
			nome = verificaNome();	
		}
		
		if(!cliente.getEmail().equals(cadastroAnterior.getEmail())){
			emailExiste = verificaEmailexistente();	
		}
		
		if(!cliente.getCpf().equals(cadastroAnterior.getCpf())){
			cpf = validaCPF();
			cpfExiste = verificaCPFexistente();
		}
		return (nome && cpf && !cpfExiste && !emailExiste);
	}
}
