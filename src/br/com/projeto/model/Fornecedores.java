/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.model;

/**
 *
 * @author Leonardo Santos
 */
public class Fornecedores extends Clientes{
    //Atributos
    private String CNPJ;
    
    //Getters and setters

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
    
    @Override //Reescrevendo para que retorne o nome ao invés do endereço do objeto
    public String toString(){
        return this.getNome();
    }
    
}
