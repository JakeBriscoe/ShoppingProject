/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CollectionsDAO;
import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.DAOInterface;
import dao.DatabaseDAO;
import dao.CustomerDatabaseDAO;
import dao.SaleDAO;
import dao.SaleDatabaseDAO;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;
import web.auth.BasicHttpAuthenticator;

/**
 *
 * @author brija795
 */
public class Server extends Jooby {

    DAOInterface prodDAO = new DatabaseDAO();
    CustomerDAO custDAO = new CustomerDatabaseDAO();
    SaleDAO saleDAO = new SaleDatabaseDAO();
    
    public Server() {
        port(8080);
        use(new Gzon());
        use(new AssetModule());
        List<String> noAuth = Arrays.asList("/api/register");
        use(new BasicHttpAuthenticator(custDAO, noAuth));
        use(new ProductModule(prodDAO));
        use(new CustomerModule(custDAO));
        use(new SaleModule(saleDAO));
    }

    public static void main(String[] args) throws Exception {
            
        System.out.println("\nStarting Server.");
        Server server = new Server();
        
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
        
        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }

}
