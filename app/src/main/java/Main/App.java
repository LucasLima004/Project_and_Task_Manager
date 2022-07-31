/*
 * Projeto principal, equivalente à função MAIN().
 */
package Main;

import Controler.ProjectController;
import Model.Project;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.util.List;

public class App {
 
    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("Está é a descrição do primeiro projeto");
        projectController.save(project);
        
     
    }

    public Object getGreeting() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
