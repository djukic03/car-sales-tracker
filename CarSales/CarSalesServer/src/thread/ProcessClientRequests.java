/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.ServerController;
import domain.DefaultDomainObject;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author user
 */
public class ProcessClientRequests extends Thread{
    Socket socket;
    Sender sender;
    Receiver receiver;

    public ProcessClientRequests(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while(true){
                try {
                    Request request = (Request) receiver.receive();
                    Response response = new Response();
                    try {
                        ServerController controller = ServerController.getInstance();
                        switch(request.getOperation()){
                            case Operation.GET_ALL:
                                response.setResult(controller.getAll((DefaultDomainObject) request.getArgument()));
                                break;
                            case Operation.GET_ALL_ORDERED:
                                response.setResult(controller.getAllOrdered((DefaultDomainObject) request.getArgument()));
                                break;
                            case Operation.GET_BY_CONDITION:
                                response.setResult(controller.getByCondition((DefaultDomainObject) request.getArgument()));
                                break;
                            case Operation.INSERT_ROW:
                                controller.insertRow((DefaultDomainObject) request.getArgument());
                                break;
                            case Operation.INSERT_ROW_AND_GET_ID:
                                response.setResult(controller.insertRowAndGetId((DefaultDomainObject) request.getArgument()));
                                break;
                            case Operation.DELETE_ROW:
                                controller.deleteRow((DefaultDomainObject) request.getArgument());
                                break;
                            case Operation.UPDATE_ROW:
                                controller.updateRow((DefaultDomainObject) request.getArgument());
                                break;
                            case Operation.GET_ALL_CAR_BRANDS:
                                response.setResult(controller.getAllCarBrands());
                                break;
                            case Operation.CLOSE_CON:
                                controller.closeCon();
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.setException(e);
                    }
                    sender.send(response);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
}
