/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, pick express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

@SuppressWarnings("UnusedDeclaration")
public final class XDocument {
    private static DocumentBuilder docBuilder = null;

    //////////////////////////////////////////////////////////////////////

    private XDocument(){
        // nothing
    }

    //////////////////////////////////////////////////////////////////////

    public static DocumentBuilder getXmlDocumentBuilder() throws ParserConfigurationException, FactoryConfigurationError{
        if(docBuilder == null){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setNamespaceAware(true);
            docBuilder = factory.newDocumentBuilder();
        }

        return docBuilder;
    }

    //////////////////////////////////////////////////////////////////////

    public static String safeGetNodeValue(Element element, String tag){
        return safeGetNodeValue(element, tag, null);
    }

    public static String safeGetNodeValue(Element element, String tag, String namespaceUri){
        try{
            NodeList childNodes;
            if(namespaceUri != null)
                childNodes = element.getElementsByTagNameNS(namespaceUri, tag);
            else
                childNodes = element.getElementsByTagName(tag).item(0).getChildNodes();
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < childNodes.getLength(); i++){
                builder.append(childNodes.item(i).getNodeValue());
            }
            return builder.toString();
        }
        catch (Exception e){
            return "";
        }
    }

    public static String safeGetNodeAttributeValue(Element element, String tag, String attribute) {
        try{
            return element.getElementsByTagName(tag).item(0).getAttributes().getNamedItem(attribute).getNodeValue();
        }
        catch (Exception e){
            return "";
        }
    }

    public static String safeGetAttribute(Element element, String attribute){
        try{
            return element.getAttribute(attribute);
        }
        catch (Exception e){
            return "";
        }
    }
}