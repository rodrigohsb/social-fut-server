package br.com.socialfut.teste;

import java.sql.Connection;

import br.com.socialfut.jdbc.ConnectionFactory;

/**
 * 
 * <b>Descricao da Classe:</b><br>
 * TODO Explicar detalhadamente propósito da classe.
 * 
 * @author rodrigo.bacellar
 * @since 11/10/2013
 * 
 */
public class TesteBanco
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Connection conn = ConnectionFactory.getConnection();

    }
}
