package org.abondar.experimental;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookRequestHandlerTest {

    private static BookRequestHandler bookRequestHandler;

    @BeforeAll
    public static void setupServer() {
        bookRequestHandler = new BookRequestHandler();
    }

    @AfterAll
    public static void stopServer() {
        if (bookRequestHandler != null) {
            bookRequestHandler.getApplicationContext().close();
        }
    }

    @Test
    public void testHandler() {
        var book = new Book();
        book.setName("Microlambda");
        var res = bookRequestHandler.execute(book);

        assertNotNull(res);
        assertFalse(res.isEmpty());
    }
}
