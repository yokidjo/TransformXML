<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" version="1.0" encoding="UTF-8"/>
    <xsl:template match="/">
        <Objects version="1">
            <xsl:for-each select="Books/Book">
                <Object>
                    <Author>
                        <xsl:value-of select="concat(Author/Surname, ' ',Author/Name)"/>
                    </Author>
                    <Title>
                        <xsl:value-of select="Title"/>
                    </Title>
                </Object>
            </xsl:for-each>
        </Objects>
    </xsl:template>
</xsl:stylesheet>
