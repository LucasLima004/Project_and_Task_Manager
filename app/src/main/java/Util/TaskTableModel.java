/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * Este documento equivale à costumização da tabela e suas ações.
 */
package Util;

import Model.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lucas Lima
 */
//Adição da herança.
public class TaskTableModel extends AbstractTableModel {
    //Propriedade das colunas que irão aparecer para o usuário.    
    String[] columns = {"Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir"};
    //Coloca as informações que irão ter a table.
    List<Task> tasks = new ArrayList();
    //Métodos obrigatórios da extensão Java.
    
    //Sobrescreve um método.
    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
    //Fala quantas linhas a tabela terá.
    @Override
    public int getRowCount() {
        //Retorna apenas as linhas de acordo com a quantidade de tarefas.
        return tasks.size();
    }
    //Fala quantas colunas terá a tabela.
    @Override
    public int getColumnCount() {
        //Retorna as colunas presentes no array columns.
        return columns.length;
    }
    
    
    //Retorna a classe de um eemento em determinada coluna.
    @Override
    public Class<?> getColumnClass(int columnIndex){
        //Verifica se tem dados para serem mostrados.
        if(tasks.isEmpty()){
            return Object.class;
        }
        //Retorna o tipo de informação em determinada linha.
        //Retorna o valor da primeira linha, ou seja, o formato da coluna determinada no index.
        return this.getValueAt(0, columnIndex).getClass();
    }
    //Verifica se a célula de localização nos parâmetros é editável.
    public boolean isCellEditable(int rowIndex, int columnIndex){
        //Só permite edição de uma determinada coluna.
         //return columnIndex == 3;
         if(columnIndex == 3){
             return true;
         }else{
             return false;
         }
    }
    //Pega a informação referente a determinado local na tabela.
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Retorna um valor de acordo comaposição das colunas.
        switch(columnIndex){
            case 0:
                //Não precisa de break por estar sendo utilizado o return.
                //Pega a linha determinada no index.
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
                return dateFormat.format(tasks.get(rowIndex).getDeadline());
            case 3:
                return tasks.get(rowIndex).isIsCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados não encontrados.";
        }
    }
    //Recebe verdadeiro quando clicado no Object.
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        //Transforma o dado em boleano.
        tasks.get(rowIndex).setIsCompleted((boolean) aValue);
    }
    
    
    //Get e setters.    
    public String[] getColumns() {
        return columns;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
    
    
    
}
