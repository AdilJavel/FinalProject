package repositories;

import data.interfaces.IDB;
import entities.Author;
import entities.Book;
import entities.Publisher;
import repositories.interfaces.IAuthorRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AuthorRepository implements IAuthorRepository {
    @Inject
    private IDB db;

    @Override
    public List<Author> getAllAuthors() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM authors";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Author> authors = new LinkedList<>();
            while (rs.next()) {
                Author author =
                        new Author(
                                rs.getInt("author_id"),
                                rs.getString("author_name"),
                                rs.getString("author_gender")


                );
                authors.add(author);
            }

            return authors;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author getAuthorById(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM authors WHERE author_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Author author = new Author(
                        rs.getInt("author_id"),
                        rs.getString("author_name"),
                        rs.getString("author_gender")

                );

                return author;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean create(Author author) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO authors(author_name,author_gender) VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, author.getName());
            st.setString(2, author.getGender());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Book> getAuthorBook(String name) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM books b, authors a, publishers p WHERE b.publisherid=p.publisher_id AND b.authorid=a.author_id AND a.author_name=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            List<Book> books = new LinkedList<>();
            while (rs.next()) {
                Book book =

                        new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("genre")
                        );
                books.add(book);
            }

            return books;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}