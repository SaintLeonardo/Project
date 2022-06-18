/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.ItemVenda;
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
public class ItemVendaDAO {
    private Connection con; 

    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection(); //Atributo com irá receber nova conexão com o banco de dados
        //Portanto, toda vez que precisar manipular a conexão apenas chamar objeto con
}
    //Metodo de cadastrar itens
    public void cadastraItem(ItemVenda obj){
        try {
        String sql = "insert into tb_itensvendas(venda_id, produto_id, qtd, subtotal) values(?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql); //Organiza o comando insert e diz exatamente o que cada ? irá receber
            
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());
            
            
            
            stmt.execute();
            stmt.close();

            //JOptionPane.showMessageDialog(null, "Item registrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
    }
    
    //Metodos que lista itens vendidos
    public List<ItemVenda> listarItensPorVenda(int venda_id) {
        try {
            //Criando a lista
            List<ItemVenda> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i "
                    + "inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, venda_id);
            
            
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****

            while (rs.next()) {
                ItemVenda item = new ItemVenda(); //Item armazena o que tem no ItemVenda
                Produtos prod = new Produtos();
                
                
                
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                
                item.setProduto(prod);
                

                lista.add(item);

            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }
    
}//FInal    
