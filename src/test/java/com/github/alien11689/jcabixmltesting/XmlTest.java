package com.github.alien11689.jcabixmltesting;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlTest {
    @Test
    public void shouldCreateXmlFromString() {
        XML xml = new XMLDocument("<test/>");
        assertNotNull(xml);
    }

    @Test
    public void shouldCreateXmlFromInputStream() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        assertNotNull(xml);
    }

    @Test
    public void shouldGetRootName() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        assertEquals("a", xml.node().getFirstChild().getLocalName());
    }

    @Test
    public void shouldGetValueByXpath() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        assertEquals(Arrays.asList("test"), xml.xpath("/a/b/text()"));
    }

    @Test
    public void shouldGetNodesByXpath() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        List<XML> b = xml.nodes("//b");
        assertEquals(1, b.size());
        assertEquals("<b>test</b>", b.get(0).toString().trim());
    }
}
