package UI;


import controller.AdminController;
import entities.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminPanel extends JFrame {
    private AdminController adminController;
    private JTable bookTable;
    private BookTableModel bookTableModel;

    public AdminPanel() {
        setTitle("Admin Panel - Book Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        adminController = new AdminController();

        // Book Table Model and JTable
        bookTableModel = new BookTableModel();
        bookTable = new JTable(bookTableModel);
        refreshBookTable();
        add(new JScrollPane(bookTable), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton updateButton = new JButton("Update Book");
        JButton deleteButton = new JButton("Delete Book");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(this, "Enter Book Title:");
        if (title != null && !title.isEmpty()) {
            Book book = new Book();
            book.setTitle(title);
            adminController.addBook(book);
            refreshBookTable();
        }
    }

    private void updateBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            Book book = bookTableModel.getBookAt(selectedRow);
            String newTitle = JOptionPane.showInputDialog(this, "Update Book Title:", book.getTitle());
            if (newTitle != null && !newTitle.isEmpty()) {
                book.setTitle(newTitle);
                adminController.updateBook(book);
                refreshBookTable();
            }
        }
    }

    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            Book book = bookTableModel.getBookAt(selectedRow);
            adminController.deleteBook(book.getId());
            refreshBookTable();
        }
    }

    private void refreshBookTable() {
        List<Book> books = adminController.getBooks();
        bookTableModel.setBooks(books);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.setVisible(true);
        });
    }
}
