package com.sahjan.app.moovy.service;

import com.sahjan.app.moovy.payload.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


@Service
public class XMLParserService {

    @Autowired
    public XMLParserService() {
    }

    /***
     * This method parses an XML file into a list of Movie objects
     * @param inputStream the input stream of the file
     * @return a list of Movie objects
     */
    public List<Movie> parse(InputStream inputStream) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List<Movie> movieList = new ArrayList<>();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("movie");
            //Use Document object to get movie data and put into arraylist
            for (int i = 0; i < nodeList.getLength(); i++) {
                movieList.add(createMovieObj(nodeList.item(i)));
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        return movieList;
    }

    /***
     * Creates a Movie object from the node
     * @param node the node to traverse
     * @return the Movie object
     */
    private static Movie createMovieObj(Node node) {
        Movie movie = new Movie();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            movie.setName(getTagValue("name", element));
            movie.setPath(getTagValue("path", element));
            movie.setRating(getTagValue("rating", element));
            movie.setAcValue(Integer.parseInt(getTagValue("acValue", element)));
            movie.setHsValue(Integer.parseInt(getTagValue("hsValue", element)));
            movie.setTwaValue(Integer.parseInt(getTagValue("twaValue", element)));
            movie.setSfValue(Integer.parseInt(getTagValue("sfValue", element)));
        }
        return movie;
    }

    /***
     * Get the tag value of the specified element
     * @param tag the specific tag value
     * @param element the specific element
     * @return the value of the tag, as a String
     */
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
