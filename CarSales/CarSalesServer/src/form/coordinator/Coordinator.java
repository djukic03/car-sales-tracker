/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.coordinator;

import form.DatabaseConfigForm;
import form.ServerForm;
import form.controllers.DBConfigController;
import form.controllers.ServerFormController;

/**
 *
 * @author user
 */
public class Coordinator {
    private static Coordinator instance;
    private final ServerFormController serverFormController;

    public Coordinator() {
        this.serverFormController = new ServerFormController(new ServerForm());
    }

    public static Coordinator getInstance() {
        if(instance == null){
            instance = new Coordinator();
        }
        return instance;
    }

    public ServerFormController getServerFormController() {
        return serverFormController;
    }
    
    public void openServerForm(){
        serverFormController.openForm();
    }

    public void openDBConfigForm() {
        DBConfigController controller = new DBConfigController(new DatabaseConfigForm(serverFormController.getServerForm(), true));
        controller.openForm();
    }
    
    
}
