package dao;
 
import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class SaleDatabaseDAO implements SaleDAO {
 
	private static final String url = DbConnection.getDefaultConnectionUri();
 
	@Override
	public void save(Sale sale) {
 
		Connection con = DbConnection.getConnection(url);
		try {
			try (
					PreparedStatement insertOrderStmt = con.prepareStatement(
							"insert into Sale (date, customerId, status) values (?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
 
					PreparedStatement insertOrderItemStmt = con.prepareStatement(
							"insert into SaleItem (quantityPurchased, salePrice, productId, saleId) values (?,?,?,?)");
 
					PreparedStatement updateProductStmt = con.prepareStatement(
							"update Products set quantityInStock = quantityInStock - ? where productId = ?");
                                                            
					) {
 
				// since saving and order involves multiple statements across
				// multiple tables we need to control the transaction ourselves
				// to ensure our DB remains consistent
				// turn off auto-commit which effectively starts a new transaction
				con.setAutoCommit(false);
 
				Customer customer = sale.getCustomer();
 
				// #### save the order ### //
                                
 
				// add a date to the sale if one doesn't already exist
				if(sale.getDate() == null) {
					sale.setDate(LocalDate.now());
				}
 
				// convert sale date into to java.sql.Timestamp
				LocalDate date = sale.getDate();
				Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
 
 
				// ****
				// write code here that saves the timestamp and username in the
				// sale table using the insertOrderStmt statement.
				// ****
                                insertOrderStmt.setTimestamp(1,timestamp);
                                insertOrderStmt.setInt(2,customer.getCustomerId());
                                insertOrderStmt.setString(3,"pending");
                                insertOrderStmt.executeUpdate();
 
 
				// get the auto-generated order ID from the database
				ResultSet rs = insertOrderStmt.getGeneratedKeys();
 
				Integer orderId = null;
 
				if (rs.next()) {
					orderId = rs.getInt(1);
				} else {
					throw new DAOException("Problem getting generated Order ID");
				}
 
				// ## save the order items ## //
 
				Collection<SaleItem> items = sale.getItems();
 
 
				// ****
				// write code here that iterates through the order items and
				// saves them using the insertOrderItemStmt statement.
				// ****
                                for (SaleItem item : items) {
                                    insertOrderItemStmt.setBigDecimal(1,item.getQuantityPurchased());
                                    insertOrderItemStmt.setBigDecimal(2,item.getSalePrice());
                                    insertOrderItemStmt.setString(3,item.getProduct().getProductId());
                                    insertOrderItemStmt.setInt(4,orderId);
                                    insertOrderItemStmt.executeUpdate();
                                }
                                
 
				// ## update the product quantities ## //
				for (SaleItem item : items) {
 
					Product product = item.getProduct();
 
 
					// ****
					// write code here that updates the product quantity using
					// the using the updateProductStmt statement.
					// ****
                                        // updateProductStmt.setBigDecimal(1,product.getQuantityInStock().subtract(item.getQuantityPurchased()));
                                        // and then change the updateProductStmt to update quantityInStock = ?
                                        
                                        updateProductStmt.setBigDecimal(1,item.getQuantityPurchased());
                                        updateProductStmt.setString(2,product.getProductId());
                                        
                                        updateProductStmt.executeUpdate();
 
				}
 
				// commit the transaction
				con.setAutoCommit(true);
			}
		} catch (SQLException ex) {
 
			Logger.getLogger(SaleDatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
 
			try {
				// something went wrong so rollback
				con.rollback();
 
				// turn auto-commit back on
				con.setAutoCommit(true);
 
				// and throw an exception to tell the user something bad happened
				throw new DAOException(ex.getMessage(), ex);
			} catch (SQLException ex1) {
				throw new DAOException(ex1.getMessage(), ex1);
			}
 
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				Logger.getLogger(SaleDatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
 
}





