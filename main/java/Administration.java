
import dao.DAOInterface;
import dao.DatabaseDAO;
import gui.ProductAdministration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brija795
 */
public class Administration {
    
    public static void main(String[] args) {
        DAOInterface dao = new DatabaseDAO();
        ProductAdministration frame = new ProductAdministration(dao);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
