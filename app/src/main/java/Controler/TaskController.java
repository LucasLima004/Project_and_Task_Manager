package Controler; 
/**
 * @author Lucas Lima
 */
import Model.Task;
import Util.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TaskController {
    //Salva a tarefa referente.
    public void save(Task task){
        String sql = "INSERT INTO tasks ("
                + "idProject," 
                + "name,"
                +"description,"
                +"completed,"
                +"notes,"
                +"deadline,"
                +"creatAt,"
              //Toda vez que não souber o valor a se alterar no SQL utiliza o ?.
                +"updateAt) VALUES (?,?,?,?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            //Inicia a comunicação com o banco de dados.
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            //Envia os valores referentes.
            statement.setInt(1,task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3,task.getDescription());
            statement.setBoolean(4,task.isIsCompleted());
            statement.setString(5,task.getNotes());
            //Pega a data do deadline em formato LONG.
            statement.setDate(6,new Date(task.getDeadline().getTime()));
            statement.setDate(7,new Date(task.getCreatAt().getTime()));
            statement.setDate(8,new Date(task.getUpdateAt().getTime()));
            //Execução do código SQL.
            statement.execute();
            
        }catch(Exception ex){
            //Lança a excessão
            throw new RuntimeException("Erro ao salvar a tarefa"
                                        +  ex.getMessage(), ex);
        }finally{
            //Finaliza a comunicação com o banco de dados.
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    //Atualiza a tarefa referente.
    public void update(Task task){
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, notes = ?, deadline = ?, completed = ?, creatAt = ?, updateAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt     (1, task.getIdProject());
            statement.setString  (2, task.getName());
            statement.setString  (3, task.getDescription());
            statement.setString  (4, task.getNotes());
            statement.setDate    (5, new java.sql.Date(task.getDeadline().getTime()));
            statement.setBoolean (6, task.isIsCompleted());
            statement.setDate    (7, new java.sql.Date(task.getCreatAt().getTime()));
            statement.setDate    (8, new java.sql.Date(task.getUpdateAt().getTime()));
            statement.setInt     (9, task.getId());
            statement.execute();
            
        }catch(Exception ex){
            throw new RuntimeException("Erro ao atualizar a tarefa"
                                        +  ex.getMessage(), ex);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    //Remove a tarefa de id determinado.
    public void removeById(int taskId){
        //Comando para interação com o banco de dados.
        //A interrogação significa o primeiro parâmetro.
        String sql = "DELETE FROM TASKS WHERE ID = ?";
        //Variáveis a serem usadas
        Connection connection = null;
        //Ajuda a preparar o sql, para ser usada pela conexão.
        PreparedStatement statement = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            //Envia um valor numérico para ficar no lugar do ? referente ao id.
            statement.setInt(1, taskId);
            //Executa o comando no banco de dados.
            statement.execute();
        }catch(Exception ex){
            throw new RuntimeException("Erro ao salvar a tarefa"
                                        +  ex.getMessage(), ex);
        }
        //Executa independente da situação, dando erro ou dando certo.
        finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    //Busca todas tarefas com base no projeto.
    public List<Task> getAll(int idProject){
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Representa uma lista(Conjunto de valores).
        List<Task> tasks = new ArrayList<Task>();
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            //Executa e devolve a informação do banco de dados.
            resultSet = statement.executeQuery();
            
            //Enquanto tiver dados irá executar.
            while(resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatAt(resultSet.getDate("creatAt"));
                task.setUpdateAt(resultSet.getDate("updateAt"));
                
                //Adiciona as taregas na lista de tarefas.
                tasks.add(task);
                
            }
            
        }catch(Exception ex){
            throw new RuntimeException("Erro ao inserir a tarefa"
                                        +  ex.getMessage(), ex);
        }
        //Executa independente da situação, dando erro ou dando certo.
        finally{
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        //Retorna a lista de tarefa criada com todos dados de resultado.
        return tasks;
    }
}
