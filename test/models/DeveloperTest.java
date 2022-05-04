package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {
Developer d1, d2;
    @BeforeEach
    void setUp() {
        d1 = new Developer("john", "www.sdgfjhds.com");
        d2 = new Developer("mary", "jgjhg");
    }

    @Test
    void checkConstructorConstructs() {
        assertEquals("john", d1.getDeveloperName());
        assertEquals("mary", d2.getDeveloperName());

    }

    @Test
    void setDeveloperName() {
    }
}