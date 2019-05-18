/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary_items;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView flagPic = new ImageView(new Image("flag.jpg"));
    
    public CellButton(int x, int y, Controller controller){
        this.x = x;
        this.y = y;
        this.controller = controller;
        this.setPrefSize(33, 33);
        flagPic.setFitHeight(20);
        flagPic.setFitWidth(15);
        flag = true;
        
        this.setOnMouseClicked(a -> {
             if (a.getButton() == MouseButton.PRIMARY && flag)
                 controller.setContent(new int[] {x, y});
             
             
             if (a.getButton() == MouseButton.SECONDARY && this.getText().equals("") && (this.getGraphic() == null || this.getGraphic() == flagPic)){
                 if (flag){
                     this.setGraphic(flagPic);
                     controller.flagControll(flag);
                     flag = false;
                 }
                 else {
                     this.setGraphic(null);
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
