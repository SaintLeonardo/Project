/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.WebServiceCep;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Leonardo Santos
 */
public class ClientesDAO {

    private Connection con; //Conectando com o banco de dados

    public ClientesDAO() {
        this.con = new ConnectionFactory().getConnection(); //Atributo com irá receber nova conexão com o banco de dados
        //Portanto, toda vez que precisar manipular a conexão apenas chamar objeto con
    }

    //Metodo cadastrar cliente
    public void cadastrarCliente(Clientes obj) {
        try {
            //Criando comando sql
            String sql = "insert into tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?);";

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //Metodo alterar cliente
    public void alterarCliente(Clientes obj) {

        
        try {
            //Alterando registros
            String sql = "update tb_clientes set nome = ? , rg = ? , cpf = ? , email = ? , telefone = ? , celular = ? , cep = ? , endereco = ? , numero = ?,"
                    + "complemento = ? , bairro = ? , cidade = ? , estado = ? where id = ?";
                    

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            stmt.setInt(14, obj.getId());
            

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
        
    }

//Metodo excluir cliente
    public void excluirCliente(Clientes obj) {

        try {
            //Apagando registros
            String sql = "delete from tb_clientes where id = ?";

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setInt(1, obj.getId());
            

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Excluído com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
        
        
    }

    
    //Lista os clientes na aba 2, mostrando a tabela
    public List<Clientes> listarClientes() {
        try {
            //Criando a lista
            List<Clientes> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
           
            
            while(rs.next()) {
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    //Metodo consultaCliente por nome
    public Clientes consultaPorNome(String nome){ //RETORNA UM OBJ
        try {
            //Criando, organizando e executando o sql
            String sql = "select * from tb_clientes where nome =?";//Busca específica, portanto usa =, PESQUISA ESPECÍFICA
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Onde índice for 1, setar o nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
            Clientes obj = new Clientes();
            
            if(rs.next()) { //Pesquisa e verifica se encontra o nome, se encontrar monta obj e retorna esse obj;
                
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
        
        
    }
    
    //Busca cliente por CPF
    public Clientes buscaPorCpf(String cpf){ //RETORNA UM OBJ
        try {
            //Criando, organizando e executando o sql
            String sql = "select * from tb_clientes where cpf =?";//Busca específica, portanto usa =, PESQUISA ESPECÍFICA
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf); //Onde índice for 1, setar o nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
            Clientes obj = new Clientes();
            
            if(rs.next()) { //Pesquisa e verifica se encontra o nome, se encontrar monta obj e retorna esse obj;
                
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
        
        
    }
    
    
    
    
    
    
    
    //Método buscar cliente por nome
    public List<Clientes> buscaClientePorNome(String nome) { //Recebe variável que funcionará como filtro RETORNA UMA LISTA
        try {
            //Criando a lista
            List<Clientes> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Onde índice for 1, setar o nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
           
            
            while(rs.next()) {
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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

    //Busca Cep
    
    public Clientes buscaCep(String cep) {
       
        //Instancia um objeto classe webService 
        //Passa CEP como parâmetro no método search
        //Ocorre a pesquisa
        
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Clientes obj = new Clientes();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            
            //Se obtiver sucesso, preenche objeto com os dados que retornar
            
            return obj; //Retorno obj com os dados
            //Mostra na tela
        } else { //Caso contrário, mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
            
        }

    }
    
    
    
    
    
    
}//Último


