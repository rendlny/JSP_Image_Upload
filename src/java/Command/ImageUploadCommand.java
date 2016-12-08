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

        InputStream inputStream = null;
        Part filePart = null;
        try {
            filePart = request.getPart("uploadImage");
        } catch (IOException ex) {
            session.setAttribute("error", "IOException");
            forwardToJsp = "index.jsp";
        } catch (ServletException e) {
            session.setAttribute("error", "ServletException");
            forwardToJsp = "index.jsp";
        }
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

            ImageDao imageDao = new ImageDao("jsp_image_upload");
            Image img = new Image(img_name, inputStream);
            boolean added = imageDao.addImage(img);
            if (added == true) {

            } else {
                session.setAttribute("error", "Failed to upload image to DB");
                forwardToJsp = "index.jsp";
            }
        } else {
            session.setAttribute("error", "You must upload an image");
            forwardToJsp = "index.jsp";
        }

        return forwardToJsp;
    }

}
