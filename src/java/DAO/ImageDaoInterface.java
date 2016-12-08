/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Image;

/**
 *
 * @author Ren
 */
public interface ImageDaoInterface {
    public boolean addImage(Image img);
    public Image getImage(String img_name);
}
