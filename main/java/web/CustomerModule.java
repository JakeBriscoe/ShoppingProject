/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerDAO;
import domain.Customer;
import net.sf.oval.Validator;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Status;

/**
 *
 * @author brija795
 */
public class CustomerModule extends Jooby {

    public CustomerModule(CustomerDAO dao) {
        get("/api/customers/:username", (req) -> {
            String username = req.param("username").value();
            if(dao.getCustomer(username) == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {
                return dao.getCustomer(username);
            }
        });
        
        post("/api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);
            new Validator().assertValid(customer);
            dao.save(customer);
            rsp.status(Status.CREATED);
        });
    }
}
