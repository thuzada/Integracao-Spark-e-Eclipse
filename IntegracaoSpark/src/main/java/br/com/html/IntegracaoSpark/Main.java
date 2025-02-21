package br.com.html.IntegracaoSpark;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        port(8081);
        // Configuração do Spark para servir arquivos estáticos
        staticFiles.location("/public");
     // Rota para buscar um produto por ID
        get("/produtos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM produtos WHERE id = ?")) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                    );
                    return produto;
                } else {
                    res.status(404); // Not Found
                    return "Produto não encontrado!";
                }
            }
        }, gson::toJson);
        // Rota para listar todos os produtos
        get("/produtos", (req, res) -> {
            List<Produto> produtos = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM produtos")) {
                while (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                    );
                    produtos.add(produto);
                }
            }
            res.type("application/json");
            return produtos;
        }, gson::toJson);

        // Rota para adicionar um novo produto
        post("/produtos", (req, res) -> {
            Produto produto = gson.fromJson(req.body(), Produto.class);
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO produtos (nome, descricao, preco) VALUES (?, ?, ?)")) {
                pstmt.setString(1, produto.getNome());
                pstmt.setString(2, produto.getDescricao());
                pstmt.setDouble(3, produto.getPreco());
                pstmt.executeUpdate();
            }
            res.status(201); // Created
            return "Produto adicionado!";
        });

        // Rota para atualizar um produto (implementação similar)
     // Rota para atualizar um produto
        put("/produtos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Produto produto = gson.fromJson(req.body(), Produto.class);
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE produtos SET nome = ?, descricao = ?, preco = ? WHERE id = ?")) {
                pstmt.setString(1, produto.getNome());
                pstmt.setString(2, produto.getDescricao());
                pstmt.setDouble(3, produto.getPreco());
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
            }
            return "Produto atualizado!";
        });
        // Rota para deletar um produto (implementação similar)
     // Rota para deletar um produto
        delete("/produtos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM produtos WHERE id = ?")) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
            return "Produto deletado!";
        });
    }
}