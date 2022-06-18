/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
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
public class ProdutosDAO {

    private Connection con; //Conectando com o banco de dados

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection(); //Atributo com irá receber nova conexão com o banco de dados
        //Portanto, toda vez que precisar manipular a conexão apenas chamar objeto con
    }

    //Metodo cadastrar produtos
    public void cadastrar(Produtos obj) {
        try {
            String sql = "insert into tb_produtos(descricao, preco, qtd_estoque, for_id) values(?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql); //Organiza o comando insert e diz exatamente o que cada ? irá receber
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
    }

    //Metodo listar produtos
    public List<Produtos> listarProdutos() {
        try {
            //Criando a lista
            List<Produtos> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****

            while (rs.next()) {
                Produtos obj = new Produtos(); //Pega todos os dados de produto
                Fornecedores f = new Fornecedores();// Pega todos os dados de fornecedor

                obj.setId(rs.getInt("p.id"));//Manda obj setar o ID pegando o ID da coluna p.id
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);

            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }

    //Listando alterar produto
    public void alterarProduto(Produtos obj) {

        try {
            //Alterando registros
            String sql = "update tb_produtos set descricao = ? , preco = ?, qtd_estoque = ?, for_id = ? where id = ? ";

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            //Organizando onde cada coisa irá ser inserida
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }

    }

    //Excluindo
    public void excluirProduto(Produtos obj) {

        try {
            //Apagando registros
            String sql = "delete from tb_produtos where id = ?";

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

    //Listando produtos por nome 
    public List<Produtos> listarProdutosPorNome(String nome) { //Variável que receberá nome e que fará pesquisa
        try {
            //Criando a lista
            List<Produtos> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?"; //Usando o campo descrição da tabela de produto como parâmetro

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Informando que a interrogação irá receber parâmetro nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****

            while (rs.next()) {
                Produtos obj = new Produtos(); //Pega todos os dados de produto
                Fornecedores f = new Fornecedores();// Pega todos os dados de fornecedor

                obj.setId(rs.getInt("p.id"));//Manda obj setar o ID pegando o ID da coluna p.id
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);

            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }

    //Método consulta por nome 1ª tela
    public Produtos consultaPorNome(String nome) { //Variável que receberá nome e que fará pesquisa
        try {

            //Criando, organizando e executando o sql
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?"; //Usando o campo descrição da tabela de produto como parâmetro
            //Uso do igual para busca específica
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Informando que a interrogação irá receber parâmetro nome

            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));//Manda obj setar o ID pegando o ID da coluna p.id
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

            }

            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }

    //Busca produto por código
    public Produtos buscaPorCodigo(int id) { //Variável que receberá nome e que fará pesquisa
        try {

            //Criando, organizando e executando o sql
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.id = ?"; //Usando o campo id da tabela de produto como parâmetro
            //Uso do igual para busca específica
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id); //Informando que a interrogação irá receber parâmetro nome

            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));//Manda obj setar o ID pegando o ID da coluna p.id
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

            }

            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }

    //Metodo da baixa no estoque
    public void baixaEstoque(int id, int qtd_nova) {
        try {
            String sql = "update tb_produtos set qtd_estoque = ? where id = ?"; //Alterando apenas o estoque

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);//Passando qtd para primeiro ?
            stmt.setInt(2, id); //Passando ID para segundo ?
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    //Retorna estoque atual
    public int retornaEstoqueAtual(int id) {
        try {
            int qtd_estoque = 0;
            String sql = "select qtd_estoque from tb_produtos where id = ?"; //Mostrando a quantidade disponível de produtos

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);//Passando qtd para primeiro ?

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                qtd_estoque = (rs.getInt("qtd_estoque"));//
            }

            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}//F
