/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package faturamento;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ASUS
 */
public class FaturamentoDistribuidora {
    //
    public static void main(String[] args) throws Exception {
        try{
            NodeList lista = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("faturamento.xml")).getElementsByTagName("row");
            List<Double> valores = new ArrayList<>();
            for (int x = 0; x < lista.getLength(); x++) {
                double valor = Double.parseDouble(((Element) lista.item(x)).getElementsByTagName("valor").item(0).getTextContent());
                if (valor > 0){ 
                    valores.add(valor);
                }
            }

            double menor = Collections.min(valores), maior = Collections.max(valores);
            double media = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            long acimaMedia = valores.stream().filter(v -> v > media).count();

            System.out.printf("Menor: %.2f\nMaior: %.2f\nDias acima da media: %d\n", menor, maior, acimaMedia);
        }catch(IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e){
             System.out.println("Erro: "+e);
             System.out.println("noma mensagem");   
        }
    }
}

