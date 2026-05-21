/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
   public void cadastrarProduto(ProdutosDTO produto){

    String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";

    conn = new conectaDAO().connectDB();

    try {

        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, produto.getNome());
        ps.setInt(2, produto.getValor());
        ps.setString(3, produto.getStatus());

        ps.execute();
        ps.close();

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    
   public ArrayList<ProdutosDTO> listarProdutos(){

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    try {

        conn = new conectaDAO().connectDB();

        String sql = "SELECT * FROM produtos";

        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

        java.sql.ResultSet rs = ps.executeQuery();

        while(rs.next()){

            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            listagem.add(produto);
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return listagem;
}
   
   public void venderProduto(int id){

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    conn = new conectaDAO().connectDB();

    try {

        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ps.execute();
        ps.close();

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    try {

        conn = new conectaDAO().connectDB();

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

        java.sql.ResultSet rs = ps.executeQuery();

        while(rs.next()){

            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            listagem.add(produto);
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return listagem;
}

}

