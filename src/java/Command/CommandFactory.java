/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

/**
 *
 * @author Ren
 */
public class CommandFactory {

    public Command createCommand(String action) {
        Command command = null;
        if(action.equals("upload_image")) {
            command = new ImageUploadCommand();
        }
        return command;
    }
}
