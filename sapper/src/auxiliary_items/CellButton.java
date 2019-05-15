/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary_items;

import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import sapper.Controller;

/**
 *
 * @author lil
 */
public class CellButton extends Button{
    
    private int x;
    private int y;
    private Controller controller;
    private boolean flag;
    private int flagPic = 0x2047;
    
    public CellButton(int x, int y, Controller controller){
        this.x = x;
        this.y = y;
        this.controller = controller;
        this.setPrefSize(33, 33);
        flag = true;
        
        this.setOnMouseClicked(a -> {
             if (a.getButton() == MouseButton.PRIMARY && flag)
                 controller.setContent(new int[] {x, y});
             
             if (a.getButton() == MouseButton.SECONDARY && (this.getText().equals(Character.toString((char)flagPic)) || this.getText().equals(""))){
                 if (flag){
                     this.setText(Character.toString((char)flagPic));
                     controller.flagControll(flag);
                     flag = false;
                 }
                 else {
                     this.setText("");
                     controller.flagControll(flag);
                     flag = true;
                 }
             }
        } );

                
        
//       EventHandler<MouseEvent> mouseEventHendler = new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent e) {
//                System.out.println("sdads");
//            }
//        };
//               
//       this.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHendler);
        
    }
    

    
}
