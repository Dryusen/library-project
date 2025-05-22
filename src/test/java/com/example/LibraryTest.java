package com.example;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("Java Programming", "John Doe");
        book2 = new Book("Clean Code", "Robert C. Martin");
    }

    @Test
    void testAddBook() {
        library.addBook(book1);
        assertEquals(1, library.getBookCount());
        assertTrue(library.getBooks().contains(book1));
    }

    @Test
    void testRemoveBook_success() {
        library.addBook(book1);
        library.addBook(book2);

        assertTrue(library.removeBook(book1));
        assertEquals(1, library.getBookCount());
        assertFalse(library.getBooks().contains(book1));
    }

    @Test
    void testRemoveBook_notExists() {
        library.addBook(book1);
        assertFalse(library.removeBook(book2));
        assertEquals(1, library.getBookCount());
    }

    @Test
    void testGetBooks_returnsCopy() {
        library.addBook(book1);
        List<Book> books = library.getBooks();
        books.clear();

        assertEquals(1, library.getBookCount());
    }

    @Test
    void testAddNullBook_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> library.addBook(null));
    }

    @Test
    void testRemoveNullBook_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> library.removeBook(null));
    }

    @Test
    void testCreateBookWithNullTitle_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "Author"));
    }

    @Test
    void testCreateBookWithNullAuthor_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Book("Title", null));
    }

    @Test
    void testSetNullTitle_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> book1.setTitle(null));
    }

    @Test
    void testSetNullAuthor_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> book1.setAuthor(null));
    }

    @Test
    void testBookEqualsAndHashCode() {
        Book book1Copy = new Book("Java Programming", "John Doe");
        assertEquals(book1, book1Copy);
        assertEquals(book1.hashCode(), book1Copy.hashCode());
    }

    @Test
    void testBookToString() {
        String expected = "Book{title='Java Programming', author='John Doe'}";
        assertEquals(expected, book1.toString());
    }
}
