package com.github.alien11689.jcabixmltesting;

import com.jcabi.xml.*;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.*;

public class XsdTest {

    XSD xsd = new XSDDocument(this.getClass().getResourceAsStream("/exampleSchema.xsd"));

    @Test
    public void shouldCreateStrictXml() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        XML strictXML = new StrictXML(xml, xsd);
        assertNotNull(strictXML);
    }

    @Test
    public void shouldThrowExceptionOnInvalidXml() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/invalid.xml"));
        try {
            new StrictXML(xml, xsd);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldValidateCorrectXmlFromStream() throws IOException {
        Collection<SAXParseException> result = xsd.validate(new StreamSource(this.getClass().getResourceAsStream("/correct.xml")));
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldValidateCorrectXmlFromXML() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        Collection<SAXParseException> result = xsd.validate(new DOMSource(xml.node()));
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldGetValidationErrors() throws IOException {
        Collection<SAXParseException> result = xsd.validate(new StreamSource(this.getClass().getResourceAsStream("/invalid.xml")));
        assertEquals(3, result.size());
        result.forEach(System.out::println);
    }
}
