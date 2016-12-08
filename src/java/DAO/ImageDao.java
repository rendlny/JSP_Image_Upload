/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.InputStream;
import java.sql.ResultSet;

/**
 *
 * @author Ren
 */
public class ImageDao extends Dao implements ImageDaoInterface {

    public ImageDao(String database) {
        super(database);
    }

    @Override
    public boolean addImage(Image img) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "INSERT INTO image VALUES(?,?)";

            ps = con.prepareStatement(query);
            ps.setString(1, img.getImage_name());

            InputStream image = img.getImage();
            if (image != null) {
                // fetches input stream of the upload file for the blob column
                ps.setBlob(2, image);
            }

            int row = ps.executeUpdate();
            if (row > 0) {
                added = true;
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the addImage()"
                    + " method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the addImage() method: " + e.getMessage());
            }
        }
        return added;
    }

    @Override
    public boolean addImage(String img_name, InputStream stream) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "INSERT INTO image VALUES(?,?)";

            ps = con.prepareStatement(query);
            ps.setString(1, img_name);

            if (stream != null) {
                // fetches input stream of the upload file for the blob column
                ps.setBlob(2, stream);
            }

            int row = ps.executeUpdate();
            if (row > 0) {
                added = true;
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the addImage()"
                    + " method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the addImage() method: " + e.getMessage());
            }
        }
        return added;
    }

    @Override
    public Image getImage(String img_name) {
        Image img = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT FROM image WHERE image_name = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, img_name);
            rs = ps.executeQuery();

            if (rs.next()) {
                img = new Image(
                        rs.getString("image_name"),
                        (InputStream) rs.getBlob("image"));
            }

        } catch (SQLException ex) {
            System.out.println("Exception occured in the getImage()"
                    + " method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the getImage() method: " + e.getMessage());
            }
        }
        return img;
    }

}
