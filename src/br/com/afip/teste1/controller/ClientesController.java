package br.com.afip.teste1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.afip.teste1.dao.ClienteDao;
import br.com.afip.teste1.modelo.Cliente;
import br.com.afip.teste1.util.ValidaCliente;

@Controller
public class ClientesController {
	
	@RequestMapping("/")
	public String exibeOpcoes(){
		return "home";
	}
	
	@RequestMapping("/adiciona")
	public String adiciona(){
		return "adiciona";
	}
	
	@RequestMapping("/adicionaCliente")
	public ModelAndView adicionaCliente(@Valid Cliente cliente, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			ModelAndView mv= new ModelAndView("adiciona");
			mv.addObject("cliente", cliente);
			return mv;
		}
		
		if(new ValidaCliente(cliente,bindingResult).validacao()){
			new ClienteDao().adiciona(cliente);
			return new ModelAndView("redirect:lista");
		}else{
			ModelAndView mv= new ModelAndView("adiciona");
			mv.addObject("cliente", cliente);
			return mv;
		}
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(){
		List<Cliente> clientes = new ClienteDao().lista();
		ModelAndView mv = new ModelAndView("lista");
		mv.addObject("clientes", clientes);
		mv.addObject("alterar", false);
		mv.addObject("remover", true);
		return mv;
	}
	
	@RequestMapping("/removeCliente")
	public String removeCliente(Long id){
		ClienteDao clienteDao = new ClienteDao();
		clienteDao.remove(new ClienteDao().buscaPorId(id));
		return "redirect:lista";
	}
	
	@RequestMapping("/altera")
	public ModelAndView altera(){
		List<Cliente> clientes = new ClienteDao().lista();
		ModelAndView mv = new ModelAndView("lista");
		mv.addObject("clientes", clientes);
		mv.addObject("alterar", true);
		mv.addObject("remover", false);
		return mv;
	}
	
	@RequestMapping("/alteraCadastro")
	public ModelAndView alteraCadastro(Long id){
		
		ModelAndView mv = new ModelAndView("altera");
		mv.addObject("cliente", new ClienteDao().buscaPorId(id));
		
		return mv;
	}
	
	@RequestMapping("/alteraCliente")
	public ModelAndView alteraCliente(@Valid Cliente cliente, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			ModelAndView mv = new ModelAndView("altera");
			mv.addObject("cliente", cliente);
			return mv;
		}
		
		if(new ValidaCliente(cliente,bindingResult).validacaoAlteracao()){
			new ClienteDao().altera(cliente);
			return new ModelAndView("redirect:lista");
		}else{
			ModelAndView mv = new ModelAndView("altera");
			mv.addObject("cliente", cliente);
			return mv;
		}

	}
	
	@RequestMapping("/lista")
	public ModelAndView listaCliente(){		
		List<Cliente> clientes = new ClienteDao().lista();		
		ModelAndView mv = new ModelAndView("lista");
		mv.addObject("clientes", clientes);
		mv.addObject("alterar", true);
		mv.addObject("remover", true);
		return mv;
	}
	
}
