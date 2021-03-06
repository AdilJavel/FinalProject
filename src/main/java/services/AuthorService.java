package services;

import entities.Author;
import entities.Book;
import repositories.interfaces.IAuthorRepository;
import services.interfaces.IAuthorService;

import javax.inject.Inject;
import java.util.List;

public class AuthorService implements IAuthorService {
    @Inject
    private IAuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.getAllAuthors();
        return authors;
    }

    @Override
    public boolean create(Author author) {
        return authorRepository.create(author);
    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.getAuthorById(id);
    }

    @Override
    public List<Book> getAuthorBook(String name) {
        List<Book> books = authorRepository.getAuthorBook(name);
        return books;
    }
}