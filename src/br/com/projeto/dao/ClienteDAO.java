/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import java.sql.Connection;
import br.com.projeto.model.Clientes;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ClienteDAO {

	// Variáveis Globais
	private Connection con;
	ResultSet rs;
	PreparedStatement stmt;
	ArrayList<Clientes> lista = new ArrayList<>();

	//criando a conexao com o banco de dado por meio da ConnectionFactory
	public ClienteDAO() {
		this.con = new ConnectionFactory().getConnection();
	}

// Criando um método para cadastrar clientes no BD.
	public void cadastrarCliente(Clientes obj) {
		try {
// 1 passo - criar o comando sql; obs.: pode estar dentro ou fora do try.
			String sql = "INSERT INTO tb_clientes (nome, rg, cpf, email, cep, estado, endereco,  bairro, numero, complemento, celular,  telefone, cidade ) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
// 2 passo - connectar o banco de dados e organizar o comando sql
			stmt = con.prepareStatement(sql);

			stmt.setString(1, obj.getNomeCliente());
			stmt.setString(2, obj.getRgCliente());
			stmt.setString(3, obj.getCpfCliente());
			stmt.setString(4, obj.getEmailCliente());
			stmt.setString(5, obj.getCepCliente());
			stmt.setString(6, obj.getUfCliente());
			stmt.setString(7, obj.getEnderecoCliente());
			stmt.setString(8, obj.getBairroCliente());
			stmt.setString(9, obj.getNumeroCasaCliente());
			stmt.setString(10, obj.getComplementoCliente());
			stmt.setString(11, obj.getCelCliente());
			stmt.setString(12, obj.getTelFixo());
			stmt.setString(13, obj.getCidadeCliente());
//executa a açao. 
//O executeQuery() você utiliza para retornar um resultado (SELECT), o retorno dele é um ResultSet, aonde contem todos os dados da sua seleção.
//O execute() você utiliza para fazer uma ação dentro do banco (delete, insert, update), o retorno dele é boolean.
			stmt.execute();
// fecha a conexao.
			stmt.close();
// envia uma mensagem de confirmaçao.
			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso! :)");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no cadastrar módulo DAO: " + e);
		}

	}

	public void alterarCliente(Clientes obj) {

		try {
			String sql = "UPDATE tb_clientes SET nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? WHERE id=?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, obj.getNomeCliente());
			stmt.setString(2, obj.getRgCliente());
			stmt.setString(3, obj.getCpfCliente());
			stmt.setString(4, obj.getEmailCliente());
			stmt.setString(5, obj.getTelFixo());
			stmt.setString(6, obj.getCelCliente());
			stmt.setString(7, obj.getCepCliente());
			stmt.setString(8, obj.getEnderecoCliente());
			stmt.setString(9, obj.getNumeroCasaCliente());
			stmt.setString(10, obj.getComplementoCliente());
			stmt.setString(11, obj.getBairroCliente());
			stmt.setString(12, obj.getCidadeCliente());
			stmt.setString(13, obj.getUfCliente());

			stmt.setInt(14, obj.getId());

			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Alterado com Sucesso! :)");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}

	public void excluirCliente(Clientes obj) {
		try {
			// 1 passo - criar o comando sql; obs.: pode estar dentro ou fora do try.
			String sql = "DELETE FROM tb_clientes WHERE id = ?";
			// 2 passo - connectar o banco de dados e organizar o comando sql
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, obj.getId());

			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Excluido com Sucesso! :)");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}

	public ArrayList<Clientes> listarClientes() {

		try {
			String sql = "SELECT * FROM tb_clientes";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Clientes obj = new Clientes();
				obj.setId(rs.getInt("id"));
				obj.setNomeCliente(rs.getString("nome"));
				obj.setRgCliente(rs.getString("rg"));
				obj.setCpfCliente(rs.getString("cpf"));
				obj.setEmailCliente(rs.getString("email"));
				obj.setTelFixo(rs.getString("telefone"));
				obj.setCelCliente(rs.getString("celular"));
				obj.setCepCliente(rs.getString("cep"));
				obj.setEnderecoCliente(rs.getString("endereco"));
				obj.setNumeroCasaCliente(rs.getString("numero"));
				obj.setComplementoCliente(rs.getString("complemento"));
				obj.setBairroCliente(rs.getString("bairro"));
				obj.setCidadeCliente(rs.getString("cidade"));
				obj.setUfCliente(rs.getString("estado"));
				lista.add(obj);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no ClienteDAO listarClientes(){} :" + e);
		}
		return lista;
	}

	public ArrayList<Clientes> buscaClientePorNome(String nome) {
		try {
			String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";
//con.preparedStatement é usado para criar um objeto que representa a instrução SQL que será executada, sendo que é invocado por meio do objeto Connetion.
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Clientes obj = new Clientes();
				obj.setId(rs.getInt("id"));
				obj.setNomeCliente(rs.getString("nome"));
				obj.setRgCliente(rs.getString("rg"));
				obj.setCpfCliente(rs.getString("cpf"));
				obj.setEmailCliente(rs.getString("email"));
				obj.setTelFixo(rs.getString("telefone"));
				obj.setCelCliente(rs.getString("celular"));
				obj.setCepCliente(rs.getString("cep"));
				obj.setEnderecoCliente(rs.getString("endereco"));
				obj.setNumeroCasaCliente(rs.getString("numero"));
				obj.setComplementoCliente(rs.getString("complemento"));
				obj.setBairroCliente(rs.getString("bairro"));
				obj.setCidadeCliente(rs.getString("cidade"));
				obj.setUfCliente(rs.getString("estado"));
				lista.add(obj);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no ClienteDAO buscaClientes(){} :" + e);
		}
		return lista;
	}

}
