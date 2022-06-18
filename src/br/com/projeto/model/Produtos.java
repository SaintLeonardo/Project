/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.model;

/**
 *
 * @author Leonardo Santos
 */
public class Produtos {
    
    //Atributos
    private int id;
    private String descricao;
    private double preco;
    private int qtd_estoque;
    private Fornecedores fornecedor;//Representa chave estrangeira no java através de obj
                                    //Toda vez que precisar pegar dados de Fornecedor p/ salvar no BD através de Produtos
                                    //Pega todos os dados necessários, monta o obj e através dos comandos no DAO utilizaremos apenas o ID 
    
    
    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    //Aqui manipularemos fornecedores
    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
}
