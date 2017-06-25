package com.github.alien11689.jcabixmltesting;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import com.jcabi.xml.XSL;
import com.jcabi.xml.XSLDocument;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XslTest {

    XSL xsl = new XSLDocument(this.getClass().getResourceAsStream("/exampleTransformation.xslt"));

    @Test
    public void shouldTransformXml() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        XML result = xsl.transform(xml);
        assertEquals("test", result.xpath("/a/@b").get(0));
        assertEquals("1", result.xpath("/a/@c").get(0));
        System.out.println(result);
    }

    @Test
    public void shouldCreateStringFromTransformation() throws IOException {
        XML xml = new XMLDocument(this.getClass().getResourceAsStream("/correct.xml"));
        String result = xsl.applyTo(xml);
        assertNotNull(result);
        assertEquals("<a b=\"test\" c=\"1\"/>", result.trim());
        System.out.println(result);
    }
}
