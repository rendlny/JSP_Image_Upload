/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Image;
import java.io.InputStream;

/**
 *
 * @author Ren
 */
public interface ImageDaoInterface {
    public boolean addImage(Image img);
    public boolean addImage(String img_name, InputStream stream);
    public Image getImage(String img_name);
}
