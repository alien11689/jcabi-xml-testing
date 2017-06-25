<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes" indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="a">
        <xsl:element name="a">
            <xsl:attribute name="b">
                <xsl:value-of select="b/text()"/>
            </xsl:attribute>
            <xsl:attribute name="c">
                <xsl:value-of select="c/text()"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>