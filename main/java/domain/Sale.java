/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author brija795
 */
public class Sale {
    
    private Integer saleId;
    private LocalDate date;
    private String status;
    private ArrayList<SaleItem> items = new ArrayList<>();
    private Customer customer;
    
    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (SaleItem item : items) {
            total = total.add(item.getItemTotal());
        }
        return total;
    }
    
    public void addItem(SaleItem item){
        items.add(item);
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<SaleItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SaleItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @Override
    public String toString() {
        return "Sale{" + "saleId=" + saleId + ", date=" + date + ", status=" + status + ", items=" + items + ", customer=" + customer + '}';
    }
}
