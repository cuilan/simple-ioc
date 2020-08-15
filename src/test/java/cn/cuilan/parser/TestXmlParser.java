package cn.cuilan.parser;

import java.util.Map;

import cn.cuilan.entity.Bean;
import org.junit.Test;

public class TestXmlParser {

    @Test
    public void testParserXml() {
        Map<String, Bean> beans = XmlParser.parser("/applicationContext.xml");
        for (Map.Entry<String, Bean> entry : beans.entrySet()) {
            System.out.println("key: " + entry.getKey());
            System.out.println("value: " + entry.getValue());
            System.out.println("\t" + entry.getValue().getProperties());
        }
    }
}
