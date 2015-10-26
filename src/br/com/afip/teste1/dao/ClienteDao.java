package br.com.afip.teste1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.afip.teste1.jdbc.ConnectionFactory;
import br.com.afip.teste1.modelo.Cliente;

public class ClienteDao {
	private Connection connection;
	public ClienteDao() {
		try{
			connection = new ConnectionFactory().getConnection();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void adiciona(Cliente cliente){
		String sql = "insert into clientes (nome,cpf,email) values (?,?,?)";
		PreparedStatement stmt;
		try{
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.execute();
			connection.close();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Cliente cliente){
		
		if(cliente.getId() == null){
			throw new IllegalArgumentException("O id da conta é nulo, impossível remover");
		}
		
		String sql = "delete from clientes where id = ?";
		
		PreparedStatement stmt;
		try{
			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, cliente.getId());
			stmt.execute();
			connection.close();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void altera(Cliente cliente){
		
		String sql = "update clientes set nome=?, cpf= ?, email=? where id=? ";
		
		PreparedStatement stmt;
		
		try{
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setLong(4, cliente.getId());
			stmt.execute();
			connection.close();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	public List<Cliente> lista(){
		String sql = "select * from clientes";
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				lista.add(criaCliente(rs));
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return lista;		
	}
	public Cliente buscaPorId(Long id){
		if(id == null){
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}
		
		String sql = "select * from clientes where id = ?";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				Cliente cliente = new Cliente(); 
				cliente = criaCliente(rs);
				rs.close();
				connection.close();
				return cliente;
			}
			rs.close();
			stmt.close();
			return null;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public boolean verificaCpf(String parametro){
		try{
			String sql="Select * from clientes where cpf = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, parametro);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				rs.close();
				connection.close();
				return true;
			}
			rs.close();
			stmt.close();
			return false;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean verificaEmail(String parametro){
		try{
			String sql="Select * from clientes where email = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, parametro);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				rs.close();
				connection.close();
				return true;
			}
			rs.close();
			stmt.close();
			return false;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * 
	 * @param rs resultado da Query
	 * @return Objeto cliente para ser adicionado a Lista
	 * @throws SQLException
	 */
	private Cliente criaCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();

		cliente.setId(rs.getLong("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setCpf(rs.getString("cpf"));
		cliente.setEmail(rs.getString("email"));
		
		return cliente;
	}
}
