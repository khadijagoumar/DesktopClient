package Controller;

import ejb.stateful.AdminService;
import entities.Book;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

public class AdminController {
    private AdminService adminService;

    public AdminController() {
        try {
            // Configure JNDI properties to locate EJBs
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            props.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            Context context = new InitialContext(props);
            adminService = (AdminService) context.lookup("java:global/backend/AdminService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        adminService.addBook(book);
    }

    public void updateBook(Book book) {
        adminService.updateBook(book);
    }

    public void deleteBook(Long bookId) {
        adminService.deleteBook(bookId);
    }

    public List<Book> getBooks() {
        return adminService.getBooks();
    }
}
