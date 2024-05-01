/*package org.example.test;

import org.example.models.Author;
import org.example.models.Book;
import org.example.models.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class JPATest {

    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void setUp() {
        sessionFactory = EntityManagerFactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @After
    public void tearDown() {
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testJPA() {
        Author author = new Author("Mark Twain");
        session.save(author);

        Genre genre = new Genre("Fiction");
        session.save(genre);

        Book book = new Book(author, "The Adventures of Tom Sawyer", 1876, genre);
        session.save(book);

        Book foundBook = session.get(Book.class, 1);
        assertEquals(book, foundBook);

        List<Book> booksByAuthor = session.createQuery("FROM Book WHERE author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
        assertEquals(1, booksByAuthor.size());

        List<Book> booksByName = session.createQuery("FROM Book WHERE title = :title", Book.class)
                .setParameter("title", "The Adventures of Tom Sawyer")
                .getResultList();
        assertEquals(1, booksByName.size());
    }
}
*/