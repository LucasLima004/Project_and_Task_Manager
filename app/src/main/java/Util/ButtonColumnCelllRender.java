/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Model.Task;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lucas Lima
 */
public class ButtonColumnCelllRender extends DefaultTableCellRenderer {
    private String buttonType;

    public ButtonColumnCelllRender(String buttonType){
        this.buttonType = buttonType;
    }
    
    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,
            boolean hasFocus, int row, int column)
    {
        JLabel label ;
        label = (JLabel) super.getTableCellRendererComponent( table,  value, isSelected, hasFocus,  row,  column);
        //Centraliza a label.s
        label.setHorizontalAlignment(JLabel.CENTER);
        //Seta um ícone para a label.
        //Pegando o caminho da pasta que está localizada o ícone.
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" + buttonType + ".png")));
        
        return label;
    }
}
