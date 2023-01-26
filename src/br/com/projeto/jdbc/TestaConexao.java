/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.jdbc;


import javax.swing.JOptionPane;


public class TestaConexao {

    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            //JOptionPane.showMessageDialog cria uma janela de aviso para o usuário.
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!", "Aviso!", 1);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,  "Ops, erro do tipo: " + error);

        }
    }
}
