/*
 * Arquivo para conexão da aplicação com o banco de dados.
 * Com função de iniciar e finalizar conexão dom o banco de dados.
 */
package Util;

//Bibliotecas necenssárias para interação com o banco de dados.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Lucas Lima
 */
public class ConnectionFactory {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/AppAlphaStudio";
    public static final String USER = "root";
    public static final String PASS = "";
    
    //Connection method.
    //Retorna uma conexão para o usuário.
    public static Connection getConnection(){
        //Tenta executar o método.
        try{
        //Carrega e iniciaiza o arquivo passado como parâmetro para a aplicação.
            Class.forName(DRIVER);
        //Usa as dependências utilizando o driveManager com os parâmetros.
            return DriverManager.getConnection(URL,USER,PASS);
        }
        //Executa caso dê erro.
        //O erro é caso de excessão.
        catch (Exception ex){
            //Executa caso dê erro.
            //  Usa quando a excessão pode ser prevenida
            throw new RuntimeException("Erro de conexão com o banco de dados");
        }
    }
    
    //Fecha a comunicação.
    public static void closeConnection(Connection connection){
        try{
            //Verifica se a conexão existe.
            if(connection != null){
                connection.close();
            }
        }catch(Exception ex){
            throw new RuntimeException("Erro de conexão com o banco de dados");
        }
    }
    //Metodo chamado para fechar o statement.
    public static void closeConnection(Connection connection, PreparedStatement statement){
        try{
            //Verifica se a conexão existe.
            if(connection != null){
                connection.close();
            }
            //Verifica se o statement está aberto e fecha caso estiver.
            if(statement != null){
                statement.close();
            }
        }catch(Exception ex){
            throw new RuntimeException("Erro de conexão com o banco de dados");
        }
    }
    
    public static void closeConnection(Connection connection,
            PreparedStatement statement, ResultSet resultSet){
        try{
            //Verifica se a conexão existe.
            if(connection != null){
                connection.close();
            }
            //Verifica se o statement está aberto e fecha caso estiver.
            if(statement != null){
                statement.close();
            }
            //Verifica se o resultSet está aberto e fecha caso estiver.
            if(resultSet != null){
                resultSet.close();
            }
        }catch(Exception ex){
            throw new RuntimeException("Erro de conexão com o banco de dados");
        }
    }
}
