/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.InputStream;

/**
 *
 * @author Ren
 */
public class Image {
    private String image_name;
    private InputStream image;
    
    public Image(){
        image_name = null;
        image = null;
    }
    
    public Image(String image_name, InputStream image){
        this.image_name = image_name;
        this.image = image;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" + "image_name=" + image_name + ", image=" + image + '}';
    }
    
    
}
