/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo Santos
 */
public class FornecedoresDAO {
    
    private Connection con; //Conectando com o banco de dados

    public FornecedoresDAO() {
        this.con = new ConnectionFactory().getConnection(); //Atributo com irá receber nova conexão com o banco de dados
        //Portanto, toda vez que precisar manipular a conexão apenas chamar objeto con
    }
    
    //Cadastrando fornecedores
    public void cadastrarFornecedor(Fornecedores obj) {
        try {
            //Criando comando sql
            String sql = "insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?);";

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCNPJ());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
    
    //Método excluir Fornecedor
    
    public void excluirFornecedor(Fornecedores obj) {

        try {
            //Apagando registros
            String sql = "delete from tb_fornecedores where id = ?";

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setInt(1, obj.getId());
            

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
        
        
    }
    
    
    //Método alterar Fornecedor
    public void alterarFornecedor(Fornecedores obj) {

        
        try {
            //Alterando registros
            String sql = "update tb_fornecedores set nome = ? , cnpj = ? ,email = ? , telefone = ? , celular = ? , cep = ? , endereco = ? , numero = ?,"
                    + "complemento = ? , bairro = ? , cidade = ? , estado = ? where id = ?";
                    

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCNPJ());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            stmt.setInt(13, obj.getId());
            

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
        
    }
    
    //Lista os clientes na aba 2, mostrando a tabela
    public List<Fornecedores> listarFornecedores() {
        try {
            //Criando a lista
            List<Fornecedores> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
           
            
            while(rs.next()) {
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCNPJ(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);

            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }
    
    
    
    //Método buscar fornecedor por nome
    public List<Fornecedores> buscaFornecedorPorNome(String nome) { //Recebe variável que funcionará como filtro RETORNA UMA LISTA
        try {
            //Criando a lista
            List<Fornecedores> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Onde índice for 1, setar o nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
           
            
            while(rs.next()) {
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCNPJ(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);

            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }
    
    //Consulta fornecedor pelo nome

    public Fornecedores consultaPorNome(String nome){ //RETORNA UM OBJ
        try {
            //Criando, organizando e executando o sql
            String sql = "select * from tb_fornecedores where nome =?";//Busca específica, portanto usa =, PESQUISA ESPECÍFICA
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Onde índice for 1, setar o nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
            Fornecedores obj = new Fornecedores();
            
            if(rs.next()) { //Pesquisa e verifica se encontra o nome, se encontrar monta obj e retorna esse obj;
                
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCNPJ(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
}
            return obj;
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }
        
        
    }
    
    
}//F
