/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo Santos
 */
public class VendasDAO {
    //Conectando ao BD
    private Connection con; 

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection(); //Atributo com irá receber nova conexão com o banco de dados
        //Portanto, toda vez que precisar manipular a conexão apenas chamar objeto con
    }
    
    
    //Cadastrar venda
    public void cadastrarVendas(Vendas obj){
        try {
        String sql = "insert into tb_vendas(cliente_id, data_venda, total_venda, observacoes) values(?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql); //Organiza o comando insert e diz exatamente o que cada ? irá receber
            
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());
            
            
            stmt.execute();
            stmt.close();

            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
    }
    
    
    
    
    //Retorna ultima venda
    public int retornaUltimaVenda(){
        try {
            int idvenda = 0;
            
 
            String sql = "select max(id)id from tb_vendas"; //Fazendo retornar valor máximo de ID, no caso o último ID
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****

            if (rs.next()) { //Rs verifica se existe o comando
                Vendas p = new Vendas(); //Se existir vai criar obj

                p.setId(rs.getInt("id"));
                
                idvenda = p.getId();//Vai pegar o ID que foi requisitado da linha anterior e manda pra IDVENDA

            }

            return idvenda;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
 
        }
    }
    
    //Mètodo filtra por data
    public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicio, LocalDate data_fim) {
        try {
            //Criando a lista
            List<Vendas> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select v.id, date_format(v.data_venda, '%d/%m/%Y') as data_formatada, c.nome, v.total_venda, v.observacoes from tb_vendas as v "
                    + "inner join tb_clientes as c on(v.cliente_id = c.id)where v.data_venda between ? and ?";//Seleciona valores entre um e outro
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());//Converte em String para conseguir passar como parâmetro
            stmt.setString(2, data_fim.toString());
            
            
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****

            while (rs.next()) {
                Vendas obj = new Vendas(); //Pega todos os dados de produto
                Clientes c = new Clientes();
                Fornecedores f = new Fornecedores();// Pega todos os dados de fornecedor

                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));

               obj.setCliente(c);

                lista.add(obj);

            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }
    
    //metodo que calcula total da venda por data
    public double retornaTotalVendaPorData(LocalDate data_venda){
        try {
            double totalvenda = 0;
            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";//Soma tudo coluna total vendas
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data_venda.toString());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                totalvenda = rs.getDouble("total");
            }
            return totalvenda;
            
        } catch (Exception e) {
        throw new RuntimeException(e);
        }
    }
}//
