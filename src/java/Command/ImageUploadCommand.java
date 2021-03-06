/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import DAO.ImageDao;
import DTO.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Ren
 */
public class ImageUploadCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        String img_name = request.getParameter("imageName");
         if(img_name!=null){
        try {
            InputStream inputStream = null;

            // obtains the upload file part in this multipart request
            Part filePart = request.getPart("uploadImage");
           
            if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());

                try {
                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(ImageUploadCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                session.setAttribute("error", "You must upload an image");
                forwardToJsp = "index.jsp";
            }

            ImageDao imageDao = new ImageDao("jsp_image_upload");
            Image img = new Image(img_name, inputStream);
            boolean added = imageDao.addImage(img_name, inputStream);
            
            if (added == true) {
                session.setAttribute("error", "Image successfully uploaded to DB");
                forwardToJsp = "uploaded.jsp";
            } else {
                session.setAttribute("error", "Failed to upload image to DB, "
                        + "image name: " + img_name + ", inputStream: " + inputStream);
                forwardToJsp = "index.jsp";
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageUploadCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ImageUploadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
         }else{
             session.setAttribute("error", "Enter Image name");
                forwardToJsp = "index.jsp";
         }
        return forwardToJsp;
    }

}
