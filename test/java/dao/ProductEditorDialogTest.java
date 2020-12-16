/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import gui.ProductEditor;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author 432br
 */
public class ProductEditorDialogTest {

    private DAOInterface dao;
    private DialogFixture fixture;
    private Robot robot;

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        // Slow down the robot a little bit - default is 30 (milliseconds).
        // Do NOT make it less than 10 or you will have thread-race problems.
        robot.settings().delayBetweenEvents(30);

        // add some categories for testing with
        Collection<String> categories = new ArrayList<>();
        categories.add("cat1");
        categories.add("cat2");

        // create a mock for the DAO
        dao = mock(DAOInterface.class);

        // stub the getCategories method to return the test categories
        when(dao.getCategories()).thenReturn(categories);
    }

    @After
    public void tearDown() {
        // clean up fixture so that it is ready for the next test
        fixture.cleanUp();
    }
    
    /*
    @Test
    public void testEdit() {
        // a product to edit
        Product prodOne = new Product("1", "name1", "cat1", "desc1",
            new BigDecimal("11.00"), new BigDecimal("22.00"));

        // create dialog passing the mocked DAO
        ProductEditor dialog = new ProductEditor(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // verify that the UI componenents contains the products's details
        fixture.textBox("txtId").requireText("1");
        fixture.textBox("txtName").requireText("name1");
        fixture.comboBox("cmbCategory").requireSelection("cat1");
        fixture.textBox("txtDescription").requireText("desc1");
        fixture.textBox("txtPrice").requireText("11.00");
        fixture.textBox("txtQuantity").requireText("22.00");

        // edit the name and category
        fixture.textBox("txtName").selectAll().deleteText().enterText("name2");
        fixture.comboBox("cmbCategory").selectItem("cat2");

        // click the save button
        fixture.button("buttonSave").click();

        // create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.saveProduct method was called, and capture the passed student
        verify(dao).saveProduct(argument.capture());

        // retrieve the passed product from the captor
        Product editedProduct = argument.getValue();

        // check that the changes were saved
        assertEquals("Ensure the name was changed", "name2", editedProduct.getName());
        assertEquals("Ensure the category was changed", "cat2", editedProduct.getCategory());
    }
    */

    @Test
    public void testSave() {
        // create the dialog passing in the mocked DAO
        ProductEditor dialog = new ProductEditor(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();

        // enter some details into the UI components
        fixture.textBox("txtId").enterText("1");
        fixture.textBox("txtName").enterText("name1");
        fixture.textBox("txtDescription").enterText("description1");
        fixture.comboBox("cmbCategory").selectItem("cat1");
        fixture.textBox("txtPrice").enterText("10.00");
        fixture.textBox("txtQuantity").enterText("3");

        // click the save button
        fixture.button("buttonSave").click();

        // create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called, and capture the passed product
        verify(dao).saveProduct(argument.capture());

        // retrieve the passed student from the captor
        Product savedProduct = argument.getValue();

        // test that the student's details were properly saved
        assertEquals("Ensure the product ID was saved", "1", savedProduct.getProductId());
        assertEquals("Ensure the name was saved", "name1", savedProduct.getName());
        assertEquals("Ensure the category was saved", "cat1", savedProduct.getCategory());
    }

}



