/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;

import br.com.projeto.model.Funcionarios;
import br.com.projeto.view.FRMLogin;
import br.com.projeto.view.FRMMenu;
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
public class FuncionariosDAO {
    
    
    //Conectando ao BD
    private Connection con; 

    public FuncionariosDAO() {
        this.con = new ConnectionFactory().getConnection(); //Atributo com irá receber nova conexão com o banco de dados
        //Portanto, toda vez que precisar manipular a conexão apenas chamar objeto con
    }
 
    //Cadastrando os funcionários
    public void cadastrarFuncionarios(Funcionarios obj) { //Objeto que vai pegar dados da tela, que vão estar empacotados e que serão enviados ao BD
        try {
            //Criando comando sql
            String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            //Conecantando bd e organizando o camando sql
            //Organiza o stmt para receber cada variavel, indica o que cada ? significa respectivamente
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5,obj.getSenha());
            stmt.setString(6,obj.getCargo());
            stmt.setString(7,obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
    
    //Listando alterar funcionario
    public void alterarFuncionario(Funcionarios obj) {

        
        try {
            //Alterando registros
            String sql = "update tb_funcionarios set nome = ? , rg = ? , cpf = ? , email = ? , senha = ?, cargo = ?, nivel_acesso = ?, telefone = ? , celular = ? , cep = ? , endereco = ? , numero = ?,"
                    + "complemento = ? , bairro = ? , cidade = ? , estado = ? where id = ?";
                    

            //Conecantando bd e organizando o camando sql
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());

            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());

            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            
            stmt.setInt(17, obj.getId());
            

            //Executando o comando SQL
            stmt.execute(); //Executando o comando SQL
            stmt.close(); //Fechando a conexão

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
        
    }
    
    
    
    
    
    
    
    
    //Excluindo funcionario
    public void excluirFuncionario(Funcionarios obj) {

        try {
            //Apagando registros
            String sql = "delete from tb_funcionarios where id = ?";

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
    
    
    
    
    
    
    
    
    
    //
    //Listando os funcionários na tabela da tela 2
    public List<Funcionarios> listarFuncionarios() {
        try {
            //Criando a lista
            List<Funcionarios> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
           
            //ResultSet é o objeto que recebe a execução de um select
            //Toda vez que execustar select no banco jogamos o resultado no ResultSet
            
            while(rs.next()) { //Enquanto ResultSet conseguir percorrer os registros, monta o obj tipo funcionario e pega os dados encontrados no BD
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj); //Adiciona todos os objetos montado numa lista  

            }

            return lista; //Retorna a lista
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }
    
    
    //Metodo consultaFuncionario por nome
    public Funcionarios consultaPorNome(String nome){ //RETORNA UM OBJ
        try {
            //Criando, organizando e executando o sql
            String sql = "select * from tb_funcionarios where nome =?";//Busca específica, portanto usa =, PESQUISA ESPECÍFICA
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Onde índice for 1, setar o nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
            Funcionarios obj = new Funcionarios();
            
            if(rs.next()) { //Pesquisa e verifica se encontra o nome, se encontrar monta obj e retorna esse obj;
                
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                

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
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado!");
            return null;
        }
        
        
    }
    
    
    
    
    
    //Método lista funcionario por nome
    public List<Funcionarios> listaFuncionarioPorNome(String nome) { //Recebe variável que funcionará como filtro RETORNA UMA LISTA
        try {
            //Criando a lista
            List<Funcionarios> lista = new ArrayList<>();

            //Criando, organizando e executando o sql
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome); //Onde índice for 1, setar o nome
            ResultSet rs = stmt.executeQuery(); //Prestar atenção pra ver se funciona ****
           
            
            while(rs.next()) {
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                
                
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                
                
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
    
    //Efetuando Login
    public void efetuaLogin(String email, String senha){
        try {
         //Fazendo login com SQL
         
         String sql = "select * from tb_funcionarios where email = ? and senha =?"; //Tras todas as colunas de func onde passa email e senha, se bater as info entra
         PreparedStatement stmt = con.prepareStatement(sql);
         stmt.setString(1, email); //Onde índice for 1, setar o email
         stmt.setString(2, senha); //Onde índice for 1, setar a senha
         
         ResultSet rs = stmt.executeQuery();//Recebe retorno do select
         if(rs.next()){
             //Usuário logou
             //Caso usuário seja admin
             if(rs.getString("nivel_acesso").equals("Admin")){ //Verifica se o que tem em nivelacesso é igual ao usuario
                //Se conseguir percorrer todo o registro que encontrou = Logou
            JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema!");
             FRMMenu tela = new FRMMenu();
             tela.usuariologado = rs.getString("nome");
             tela.setVisible(true); //Setando para aparecer a tela de menu logo que o usuário logar 
             }
             
             //Caso usuario limitado
             else if(rs.getString("nivel_acesso").equals("Usuário")){
             //Se conseguir percorrer todo o registro que encontrou = Logou
             JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema!");
             FRMMenu tela = new FRMMenu();
             tela.usuariologado = rs.getString("nome");
             
             //Desabilitando os menus
             tela.menu_posicao.setEnabled(false);
             tela.menu_controle.setVisible(false);
             
             tela.setVisible(true); //Setando para aparecer a tela de menu logo que o usuário logar    
             }



            
            
            
            
         }else{
             
             //Dados incorretos
             
             JOptionPane.showMessageDialog(null, "Dados incorretos!");
             new FRMLogin().setVisible(true); //Se o usuário não entrar, a tela se mantém aberta e não fecha
             
             
         }
         
         
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
        }
    }
    
    
    
}//Final
