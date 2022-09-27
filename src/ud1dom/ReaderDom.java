/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud1dom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author IFC
 */
public class ReaderDom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
         * Las clases DocumentBuilderFactory y DocumentBuilder permiten la 
         * generación de un documento XML vacío almacenándolo en memoria
         */
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            /**
             * Creamos un documento a partir del fichero de nombre empleados.xml
             */
            Document document = builder.parse(new File("src\\ud1dom\\empleados.xml"));
            /**
             * El método accede al nodo raíz del documento 
             * y el método normalice elimina los nodos vacíos y combina adyacentes
             * en caso de que los hubiera
             */
            document.getDocumentElement().normalize();

            /**
             * Crea una lista con todos los nodos empleado
             */
            NodeList empleados = document.getElementsByTagName("empleado");
            System.out.println("Nodos empleado a recorrer: " + empleados.getLength());

            /**
             * Recorrer la lista
             */
            for (int i = 0; i < empleados.getLength(); i++) {
                /**
                 * Obtener un nodo empleado
                 */
                Node nodoEmpleado = empleados.item(i);

                /**
                 * Siempre y cuando el nodo empleado sea del tipo Elemento, 
                 * podemos acceder al contenido del xml teniendo en cuenta
                 * que este código depende de que conozcamos la estructura y 
                 * etiquetas utilizadas.
                 */
                if (nodoEmpleado.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoEmpleado = (Element) nodoEmpleado;

                    System.out.println("Id: " + elementoEmpleado.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("Apellido: "
                            + elementoEmpleado.getElementsByTagName("apellido").item(0).getTextContent());
                    System.out.println("Departamento: "
                            + elementoEmpleado.getElementsByTagName("dep").item(0).getTextContent());
                    System.out.println("Salario: "
                            + elementoEmpleado.getElementsByTagName("salario").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
