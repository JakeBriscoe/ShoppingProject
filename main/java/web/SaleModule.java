/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SaleDAO;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author brija795
 */
public class SaleModule extends Jooby {
    
    public SaleModule(SaleDAO dao) {
        post("/api/sale", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            dao.save(sale);
            System.out.println(sale);
            rsp.status(Status.CREATED);
        });
    }
}



