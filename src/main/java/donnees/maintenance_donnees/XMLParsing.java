package donnees.maintenance_donnees;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class XMLParsing {

    public static String xml = "";

    public static Document getXML() {
        URL url = null;
        try {
            url = new URL("https://mijatovic.xyz/pays.php");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        InputStream flux = null;
        try {
            flux = url.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner lecteur = new Scanner(flux);
        lecteur.useDelimiter("\\A");
        xml = lecteur.next();
        lecteur.close();

        DocumentBuilder parseur = null;
        try {
            parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = null;
        try {
            document = parseur.parse(new ByteArrayInputStream(xml.getBytes()));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return document;
    }

    public static ArrayList<String> selectCountries() {
        ArrayList<String> retour = new ArrayList<String>();
        Document doc = XMLParsing.getXML();
        NodeList listeNoeudsTitres = doc.getElementsByTagName("nom");
        Element element;
        for (int i = 0; i< listeNoeudsTitres.getLength();i++) {
            element = (Element)listeNoeudsTitres.item(i);
            retour.add(element.getTextContent());
        }
        return retour;
    }

    public static String[] selectFromCountry(String pays, boolean habitants, boolean PIB, boolean moyenneAge, boolean moyenneAgeVie, boolean superficie) {
        String[] retour = new String[10];
        boolean allTrue = !habitants && !PIB && !moyenneAge && !moyenneAgeVie && !superficie;
        boolean trouve = false;

        Document doc = XMLParsing.getXML();
        NodeList listeNoeudsTitres = doc.getElementsByTagName("pays");
        Element element;
        int i = 0;
        while (!trouve && i<10000000) {
            element = (Element)listeNoeudsTitres.item(i);
            if (element.getElementsByTagName("nom").item(0).getTextContent().equals(pays)) {
                trouve = true;
                if (habitants || allTrue) retour[0] = element.getElementsByTagName("habitants").item(0).getTextContent();
                if (PIB || allTrue) retour[1] = element.getElementsByTagName("PIB").item(0).getTextContent();
                if (moyenneAge || allTrue) retour[2] = element.getElementsByTagName("moyenneAge").item(0).getTextContent();
                if (moyenneAgeVie || allTrue) retour[3] = element.getElementsByTagName("moyenneAgeVie").item(0).getTextContent();
                if (superficie || allTrue) retour[4] = element.getElementsByTagName("superficie").item(0).getTextContent();
            }
            i++;
        }
        return retour;
    }

    public static void main(String[] args) {
        Iterator<String> it = XMLParsing.selectCountries().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
