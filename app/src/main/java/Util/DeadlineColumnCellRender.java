/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * Cria um renderizador para ser utilizado nas células.
 */
package Util;

import Model.Task;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lucas Lima
 */
public class DeadlineColumnCellRender extends DefaultTableCellRenderer{
    //Customização da coluna de renderização.
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,
            boolean hasFocus, int row, int column)
    {
        //Cria um elemenyo JLabel.
        //Label que será devolvida no grid.
        JLabel label ;
        //Pea a classe de renderização padrão.
        label = (JLabel) super.getTableCellRendererComponent( table,  value, isSelected, hasFocus,  row,  column);
        //Define o alinhamento no centro.
        label.setHorizontalAlignment(CENTER);
        
        TaskTableModel taskModel = (TaskTableModel)table.getModel();
        Task task = taskModel.getTasks().get(row);
        
        //Verifica se o deadline está depois do momento atual.
        //After diz se está depois do atual(data atual).
        if(task.getDeadline().after(new Date())){
            //Altera a cor de fundo para verde.
            label.setBackground(Color.GREEN);
        }else{
            label.setBackground(Color.RED);
        }
        //Retorna o costumizador editado e não o padrão para determinados objetos.
        return label;
    }
}
