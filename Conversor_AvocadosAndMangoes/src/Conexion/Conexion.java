/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Clases.Client;
import Clases.HProduct;
import Clases.Order;
import Clases.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class Conexion {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String ipServer = "jdbc:mysql://localhost:3306/";
    private String baseDatos = "avocadosandmangoes";
    private String user = "root";
    private String contrasena = "root";
    public Connection con;

    public Conexion() {

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(ipServer + baseDatos, user, contrasena);
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
    }

    
    public static void main(String[] args) {
        //cargarArchivo("c:\\Users\\diego\\Desktop\\archivo.csv");
        
        ArrayList<String> direcciones = new ArrayList<>();
        ArrayList<String[][]> datos = new ArrayList<>();
        ArrayList<HProduct> productos = new ArrayList<>();
        //cargarArchivo("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\ordenes.csv");
        //formatearTelefono("+1234567");
        //cargarArchivoClientesManuales("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\clientes3.csv");
       // datos = cargarArchivoClientes("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\clientes.csv");
       // escribirArchivo("c:\\Users\\diego\\Desktop\\ordenes.csv");
       //cargarArchivoRutasOR("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\routesOP.csv");
       // escribirArchivoProductos("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\products.csv");
         //escribirArchivoRutas("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\rutas.csv");
         //insertarAHistorial();
        //escribirArchivoHOrdersXFecha("14/05/2021", "14/08/2021", "C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\horders.csv");
       // escribirArchivoClientes("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\Clientes.csv");
      //  escribirArchivoClientesCambio("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\clientesCambio.csv");
       // productos = llenarTablaProductos();
        //cargarArchivoProductos("C:\\Users\\diego\\Desktop\\Archivos varios\\datosAvocados\\products1.csv");
       /*
        for (HProduct producto : productos) {
            System.out.println(producto.getNombre()+";"+producto.getCantidad());
        }*/
       obtenerSemanaFecha("30/09/2021");
       obtenerSemanaFecha("27/02/2021");
       obtenerSemanaFecha("31/12/2021");
       obtenerSemanaFecha("15/06/2021");
       obtenerSemanaFecha("27/10/2021");
    }
    

    public static void listarDatos() {
        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        try {
            st = (Statement) cn.con.createStatement();
            //rs = st.executeQuery("insert into products (id, nombre, precio, cantidad) values (3,'cereza', 900,5 )");
            rs = st.executeQuery("select * from products");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("nombre") + " ");
            }
            cn.con.close();
        } catch (Exception e) {
        }
    }

    public static void insertarDatos(Object object, Conexion cn) {
        Conexion cn1 = new Conexion();
       // cn = new Conexion();

        if (object instanceof Client) {
            
            Statement st;
            ResultSet rs;
            boolean existe = false;
            String phone = ((Client) object).getShippingPhone();
            try {
                st = (Statement) cn1.con.createStatement();
                 rs = st.executeQuery("select * from clients WHERE shippingPhone = '"+phone+"'");
                 
                while (rs.next()) {
                    existe = true;
                 }
                cn1.con.close();
            } catch (Exception e) {
                System.out.println("Error");
            }
            
            if(!existe)
            {
                try {
                    PreparedStatement PS = cn.con.prepareStatement("insert into clients (shippingPhone, shopifyCode, name, address, address2, city, postalCode, email, totalSpent, class) values (?,?,?,?,?,?,?,?,?,? )");
                    PS.setString(1, ((Client) object).getShippingPhone());
                    PS.setString(2, ((Client) object).getShopifyCode());
                    PS.setString(3, ((Client) object).getName());
                    PS.setString(4, ((Client) object).getAddress());
                    PS.setString(5, ((Client) object).getAddress2());
                    PS.setString(6, ((Client) object).getCity());
                    PS.setString(7, ((Client) object).getPostalCode());
                    PS.setString(8, ((Client) object).getEmail());
                    PS.setDouble(9, ((Client) object).getTotalSpent());
                    PS.setString(10, ((Client) object).getClasse());
                    PS.executeUpdate();

                   // cn.con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
            }
        } else if (object instanceof Order) {
            try {
                PreparedStatement PS = cn.con.prepareStatement("insert into orders ( id, codeSP, stop, shippingPhone, shippingName, address, address2, city, postalCode, itemName, cant, value, total, payment, comments, date, skuCode) values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
                PS.setString(1, ((Order) object).getCodeSP());
                PS.setInt(2, ((Order) object).getStop());
                PS.setString(3, ((Order) object).getShippingPhone());
                PS.setString(4, ((Order) object).getShippingName());
                PS.setString(5, ((Order) object).getAddress());
                PS.setString(6, ((Order) object).getAddress2());
                PS.setString(7, ((Order) object).getCity());
                PS.setString(8, ((Order) object).getPostalCode());
                PS.setString(9, ((Order) object).getItemName());
                PS.setInt(10, ((Order) object).getCant());
                PS.setDouble(11, ((Order) object).getValue());
                PS.setDouble(12, ((Order) object).getTotal());
                PS.setString(13, ((Order) object).getPayment());
                PS.setString(14, ((Order) object).getComments());
                PS.setString(15, ((Order) object).getDate());
                PS.setString(16, ((Order) object).getSkuCode());
                PS.executeUpdate();

             //   cn.con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ( object instanceof Product){
            try {
                PreparedStatement PS = cn.con.prepareStatement("insert into products ( id, name, skuCode, purchaseValue) values (null,?,?,? )");
                PS.setString(1, ((Product) object).getNombre());
                PS.setString(2, ((Product) object).getSkuCode());
                PS.setDouble(3, ((Product) object).getPurchaseValue());
                PS.executeUpdate();

               // cn.con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if ( object instanceof HProduct){
            try {
                
                Statement st;
                ResultSet rs;
                boolean existe = false;
                int id = 0;
                try {
                    st = (Statement) cn1.con.createStatement();
                    rs = st.executeQuery("select * from hproducts WHERE name = '"+((HProduct) object).getNombre()+"' and uploadDate = '"+((HProduct) object).getDate()+"'");
                 
                    while (rs.next()) {
                        existe = true;
                        id = rs.getInt("id");
                    }
                    cn1.con.close();
                } catch (Exception e) {
                    System.out.println("Error");
                }
                
                if(!existe)
                {
                    PreparedStatement PS = cn.con.prepareStatement("insert into hproducts ( id, name, purchaseValue, saleValue, quantity, uploadDate, skuCode, week) values (null,?,?,?,?,?,?,? )");
                    PS.setString(1, ((HProduct) object).getNombre());
                    PS.setDouble(2, ((HProduct) object).getPurchaseValue());
                    PS.setDouble(3, ((HProduct) object).getSaleValue());
                    PS.setInt(4, ((HProduct) object).getCantidad());
                    PS.setString(5, ((HProduct) object).getDate());
                    PS.setString(6, ((HProduct) object).getSkuCode());
                    PS.setString(7, ((HProduct) object).getWeek());
                    PS.executeUpdate();
                }
                else
                {
                    
                String sql = "";
                st = (Statement) cn.con.createStatement(); 
            
                sql = "UPDATE hproducts SET "
                    + "purchaseValue = "+((HProduct) object).getPurchaseValue()+", "
                    + "saleValue = "+((HProduct) object).getSaleValue()+", "
                    + "quantity = "+((HProduct) object).getCantidad()+" "
                    + "WHERE id = "+id+"";
            
            //System.out.println(sql);
            
                st.executeUpdate(sql);
                }

               // cn.con.close();
            } catch (Exception ex) {
                System.out.println("An error has occurred connecting with database"+ex);
            }
        }
    }

    public static ArrayList<String[][]> cargarArchivo(String ruta) throws SQLException {
        
        vaciarTabla();
        Path filePath = Paths.get(ruta);
        String vectorDatos[][];
        String vectorDatosTmp[][];
        String vectorDatosCopia[][];
        ArrayList<String[][]> datos = new ArrayList<>();
        //ArrayList<String[]> datosRutas = new ArrayList<>();
        //datosRutas = cargarArchivoRutas(rutaRoutes);
        //System.out.println(datosRutas.get(0)[0]);
        Conexion cn = new Conexion();
        try {
            BufferedReader bf = Files.newBufferedReader(filePath);
            String linea;
            String encabezados = "";
            String[] encabezadosVector;
            String[] encabezadoCampos;
            StringBuilder dato;
            String nameAnterior = "";
            int posicionAddress = 0;
            boolean primeraLinea = true;
            int numeroDatos = 0;
            if (primeraLinea) {
                encabezados = bf.readLine();
                for (int i = 0; i < encabezados.length(); i++) {
                    if (encabezados.charAt(i) == ',') {
                        numeroDatos++;
                    }
                }
            }
            encabezadosVector = encabezados.split(",");
            int campos[] = validarCampos(encabezadosVector, "order");
            encabezadoCampos = new String[campos.length];
            for (int i = 0; i < campos.length; i++) {
                encabezadoCampos[i] = encabezadosVector[campos[i]];
            }

            vectorDatos = new String[1][numeroDatos];
            vectorDatosTmp = new String[1][numeroDatos];
            vectorDatosCopia = new String[1][numeroDatos];
            int contadorDatos = 0;
            //Recorremos las lineas del archivo
            while ((linea = bf.readLine()) != null) {
                dato = new StringBuilder("");
                //Recorremos los caracteres de la linea leida
                for (int i = 0; i < linea.length(); i++) {
                    //Si la linea leida es igual a una coma es por que viene un nuevo dato
                    if (linea.charAt(i) == ',') {
                        //Agregamos el dato al array y formateamos la cadena
                        vectorDatos[0][contadorDatos] = dato.toString();
                        contadorDatos++;
                        dato = new StringBuilder("");
                        //Se pregunta que el siguiente caracter este dentro de la variable linea
                        if (i + 1 < linea.length()) {
                            //Se pregunta si el caracter que sigue a la coma es un '"' esto nos indica que leeremos una cadena
                            //que puede contener comas en su interior
                            if (linea.charAt(i + 1) == '"') {
                                i = i + 2;
                                while (linea.charAt(i) != '"') {
                                    dato.append(linea.charAt(i));
                                    i++;
                                }

                            } else {
                                if (linea.charAt(i) != ',') {
                                    dato.append(linea.charAt(i));
                                }
                            }
                        }
                    } else {
                        if (linea.charAt(i) != ',') {
                            dato.append(linea.charAt(i));
                        }
                    }
                }
                //order.setShippingName(vector[15]);
                //agreagarDatos(order);
                // String[] datosLinea = linea.split(",");
                // System.out.println(datos.get(0));
                int contadorTmp = 0;
                if (nameAnterior.equalsIgnoreCase(vectorDatos[0][0])){/*(vectorDatosTmp[0][0] != null && vectorDatosTmp[0][0].equalsIgnoreCase(vectorDatos[0][campos[0]])) || (vectorDatosCopia[0][0] != null && vectorDatosCopia[0][0].equalsIgnoreCase(vectorDatos[0][campos[0]]))*/ 
                    vectorDatosCopia = vectorDatosTmp;
                    

                    for (int x = 0; x < campos.length; x++) {
                        String campoTabla = buscarEnFieldConfigure(encabezadosVector[campos[x]], cn, "order");
                        if(campoTabla.equalsIgnoreCase("ErrorBase")){
                            break;
                        }
                        if (campoTabla.equalsIgnoreCase("itemName")
                                || campoTabla.equalsIgnoreCase("cant")
                                || campoTabla.equalsIgnoreCase("value")) {
                            vectorDatosCopia[0][contadorTmp] = vectorDatos[0][campos[x]];
                           // System.out.println( vectorDatosCopia[0][contadorTmp]);
                            contadorTmp++;
                        } else if(campoTabla.equalsIgnoreCase("total")){
                            vectorDatosCopia[0][contadorTmp] = "0.0";
                            contadorTmp++;
                        }else{
                            
                            contadorTmp++;
                        }

                    }

                    datos.add(vectorDatosCopia);
                    nameAnterior = vectorDatos[0][0];
                    vectorDatos = new String[1][numeroDatos];
                    contadorDatos = 0;
                    // System.out.println("Copia"+vectorDatosCopia[0][0]+vectorDatosCopia[0][1]+vectorDatosCopia[0][2]+vectorDatosCopia[0][3]+vectorDatosCopia[0][4]+vectorDatosCopia[0][5]+vectorDatosCopia[0][6]+vectorDatosCopia[0][7]+vectorDatosCopia[0][8]+vectorDatosCopia[0][9]+vectorDatosCopia[0][10]+vectorDatosCopia[0][11]);
                    crearOrden(encabezadoCampos, vectorDatosCopia, cn);
                    //cn.con.close();
                } else {
                    // vectorDatosTmp = new String[1][numeroDatos];
                    //Se recorre la lista de campos para agregar solo los campos necesarios
                    for (int j = 0; j < campos.length; j++) {
                        
                        if(buscarEnFieldConfigure(encabezadosVector[campos[j]], cn, "order").equalsIgnoreCase("shippingPhone")){
                            vectorDatosTmp[0][contadorTmp] = formatearTelefono(vectorDatos[0][campos[j]]);
                        }
                        else if(buscarEnFieldConfigure(encabezadosVector[campos[j]], cn, "order").equalsIgnoreCase("date")){
                            vectorDatosTmp[0][contadorTmp] = formatearFecha(vectorDatos[0][campos[j]]);
                        }
                        else
                        {
                             vectorDatosTmp[0][contadorTmp] = vectorDatos[0][campos[j]];
                        }
                        // System.out.println(vectorDatos[0][campos[j]]);
                        contadorTmp++;
                    }
                    datos.add(vectorDatosTmp);
                    crearOrden(encabezadoCampos, vectorDatosTmp, cn);
                    //cn.con.close();
                    // System.out.println("vector"+vectorDatosTmp[0][2]);
                    contadorDatos = 0;
                    nameAnterior = vectorDatos[0][0];
                    vectorDatos = new String[1][numeroDatos];
                }
            }
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "An error has occurred reading the file, please check the file path");
             return null;
        }
        JOptionPane.showMessageDialog(null, "The orders file has been uploaded succesfully");
        //insertarAHistorial();
      //  cn.con.close();
        return datos;
    }

    public static int[] validarCampos(String campos[], String clase) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        String shoppingName;
        String dbName;
        int posicionCampos[] = null;
        int contadorCampos = 0;
        int j = 0;
        String sql;
        
         if(clase.equalsIgnoreCase("order"))
               sql = "select * from fieldconfigure";
         else if(clase.equalsIgnoreCase("product"))
                 sql = "select * from fieldconfigureproduct";
         else
               sql = "select * from fieldconfigureclient";
        

        try {
            st = (Statement) cn.con.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                contadorCampos++;
                // System.out.println(rs.getInt("id")+" " +rs.getString("nombre")+" ");
            }
            posicionCampos = new int[contadorCampos];
            st = (Statement) cn.con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                shoppingName = rs.getString("shippingName");
                // dbName = rs.getString("dbName");
                for (int i = 0; i < campos.length; i++) {
                    if (campos[i].equalsIgnoreCase(shoppingName)) {
                        posicionCampos[j] = i;
                        j++;
                    }
                }

            }
            cn.con.close();
            return posicionCampos;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "An error has occurred connecting with database to check the fields");
        }
        return posicionCampos;
    }

    public static void crearOrden(String encabezados[], /*ArrayList<String[][]>*/ String[][] datos,  Conexion cn) {
       // cn = new Conexion();
        Order order;
        String campoAddress = "";
        String campoTabla = "";
        String cadDia = "";
        String cadMes = "";
        /*
        Calendar fecha = Calendar.getInstance();
        Integer dia = fecha.get(Calendar.DATE);
        if(dia.toString().length() == 1)
            cadDia = "0"+dia;
        else
            cadDia = dia.toString();
            
        Integer mes = fecha.get(Calendar.MONTH)+1;
        if(mes.toString().length() == 1)
            cadMes = "0"+mes;
        else
            cadMes = mes.toString();
            
        int annio = fecha.get(Calendar.YEAR);
        String date = cadDia+"/"+cadMes+"/"+annio;
        */
        /* for (int i = 0; i < encabezados.length; i++) {
            System.out.print(encabezados[i]+" ");
        }
        
        System.out.println("-------------------------");*/

        order = new Order();
        // System.out.println("Copia"+datos[0][0]+datos[0][1]+datos[0][2]+datos[0][3]+datos[0][4]+datos[0][5]+datos[0][6]+datos[0][7]+datos[0][8]+datos[0][9]+datos[0][10]+datos[0][11]);
        for (int i = 0; i < datos.length; i++) {

            for (int j = 0; j < encabezados.length; j++) {
                campoTabla = buscarEnFieldConfigure(encabezados[j], cn, "order");
                //System.out.println("FieldConfigure: " + buscarEnFieldConfigure(encabezados[j], cn));
                if(campoTabla.equalsIgnoreCase("ErrorBase")){
                    return;
                }else if (campoTabla.equalsIgnoreCase("shippingPhone")) {
                    order.setShippingPhone(datos[0][j]);
                    //      System.out.println("datos: "+datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("shippingName")) {
                    order.setShippingName(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("address")) {
                    order.setAddress(datos[0][j]);
                    campoAddress = datos[0][j];
                } else if (campoTabla.equalsIgnoreCase("address2")) {
                    order.setAddress2(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("city")) {
                    order.setCity(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("postalCode")) {
                    order.setPostalCode(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("itemName")) {
                    order.setItemName(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("cant")) {
                    order.setCant(Integer.parseInt(datos[0][j]));
                } else if (campoTabla.equalsIgnoreCase("value")) {
                    order.setValue(Double.parseDouble(datos[0][j]));
                } else if (campoTabla.equalsIgnoreCase("total")) {
                    order.setTotal(Double.parseDouble(datos[0][j]));
                } else if (campoTabla.equalsIgnoreCase("payment")) {
                    order.setPayment(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("comments")) {
                    order.setComments(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("codeSP")) {
                    order.setCodeSP(datos[0][j]);
                }else if (campoTabla.equalsIgnoreCase("date")) {
                    order.setDate(datos[0][j]);
                }else if (campoTabla.equalsIgnoreCase("skuCode")) {
                    order.setSkuCode(datos[0][j]);
                }
            }

           /* for (int j = 0; j < direcciones.size(); j++) {
                // System.out.println("Campo: " +campoAddress+ " direccion: "+ direcciones.get(j)[0]);
                if (direcciones.get(j)[1].equalsIgnoreCase(campoAddress)) {
                    order.setStop(Integer.parseInt(direcciones.get(j)[0]));
                }
            }
*/
      //     order.setDate(date);
            insertarDatos(order, cn);
            actualizarClientes(order, cn);
            // cn.con.close();
        }
        // return order;
    }

    public static ArrayList<String[]> cargarArchivoRutasOR(String ruta) {
        Path filePath = Paths.get(ruta);
        ArrayList<String[]> direcciones = new ArrayList<>();
        try {
            BufferedReader bf = Files.newBufferedReader(filePath);
            String linea;
            String[] encabezadosVector;
            String[] datosLinea;
            String[] datoGuardar = new String[2];
            boolean primeraLinea = true;
            int posicionAddress = 0;
            int posicionStop = 0;

            //Recorremos las lineas del archivo
            while ((linea = bf.readLine()) != null) {
                datoGuardar = new String[2];
                if (primeraLinea) {
                    encabezadosVector = linea.split(";");
                    for (int i = 0; i < encabezadosVector.length; i++) {
                        if (encabezadosVector[i].equalsIgnoreCase("Address")) {
                            posicionAddress = i;
                        } else if (encabezadosVector[i].equalsIgnoreCase("Stop Number")) {
                            posicionStop = i;
                        }
                    }
                    primeraLinea = false;
                } else {
                    datosLinea = linea.split(";");
                    datoGuardar[0] = datosLinea[posicionStop];
                    datoGuardar[1] = datosLinea[posicionAddress];
                    //System.out.println("Stop:"+datoGuardar[0]+"Addres:"+datoGuardar[1]);
                    direcciones.add(datoGuardar);
                    actualizarRutas( Integer.parseInt(datoGuardar[0]), datoGuardar[1]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred reading the routes file, please check the file path");
             return null;
        }
        
        JOptionPane.showMessageDialog(null, "The routes file has been uploaded succesfully");
        return direcciones;
    }

    
     public static ArrayList<String[]> cargarArchivoRutas(String ruta) {
        Path filePath = Paths.get(ruta);
        ArrayList<String[]> direcciones = new ArrayList<>();
        try {
            BufferedReader bf = Files.newBufferedReader(filePath);
            String linea;
            String[] encabezadosVector;
            String[] datosLinea;
            String[] datoGuardar = new String[2];
            boolean primeraLinea = true;
            int posicionAddress = 0;
            int posicionStop = 0;

            //Recorremos las lineas del archivo
            while ((linea = bf.readLine()) != null) {
                datoGuardar = new String[2];
                if (primeraLinea) {
                    encabezadosVector = linea.split(";");
                    
                    primeraLinea = false;
                } else {
                    datosLinea = linea.split(";");
                    datoGuardar[0] = datosLinea[1];
                    datoGuardar[1] = datosLinea[0];
                    //System.out.println("Stop:"+datoGuardar[0]+"Addres:"+datoGuardar[1]);
                    direcciones.add(datoGuardar);
                    actualizarRutas( Integer.parseInt(datoGuardar[0]), datoGuardar[1]);
                }
            }
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "An error has occurred reading the routes file, please check the file path");
             return null;
        }
        
        JOptionPane.showMessageDialog(null, "The routes file has been uploaded succesfully");
        return direcciones;
    }
     
     
    public static String buscarEnFieldConfigure(String campo, Conexion cn, String tipo) {
        //cn=new Conexion();
        Statement st;
        ResultSet rs;
        if(tipo.equalsIgnoreCase("order"))
        {
            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from fieldconfigure where shippingName = '" + campo + "'");

                while (rs.next()) {
                    return rs.getString("dbName");
                }
              //  cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Search error trying to get the fields from database");
                 return "ErrorBase";
            }

            return "error";
        }
        else if(tipo.equalsIgnoreCase("product"))
        {
            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from fieldconfigureproduct where shippingName = '" + campo + "'");

                while (rs.next()) {
                    return rs.getString("dbName");
                }
              //  cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Search error trying to get the fields from database");
                 return "ErrorBase";
            }

            return "error";
        }
        else
        {
            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from fieldconfigureclient where shippingName = '" + campo + "'");

                while (rs.next()) {
                    return rs.getString("dbName");
                }
              //  cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Search error trying to get the fields from database"+e);
                 return "ErrorBase";
            }

            return "error";
        }
    }

    public static int escribirArchivo(String ruta) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        StringBuilder contenido = new StringBuilder();
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String encabezado = "Stop;Phone;Name;Address;Address2;City;Zip;Item;Quantity;Price;Total;Notes;PM;\n";
           bw.write(encabezado);

            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from orders");
                while (rs.next()) {
                    contenido.append(rs.getString("stop"));
                    contenido.append(";");
                    contenido.append(rs.getString("shippingPhone"));
                    contenido.append(";");
                    contenido.append(rs.getString("shippingName"));
                    contenido.append(";");
                    contenido.append(rs.getString("address"));
                    contenido.append(";");
                    contenido.append(rs.getString("address2"));
                    contenido.append(";");
                    contenido.append(rs.getString("city"));
                    contenido.append(";");
                    contenido.append(rs.getString("postalCode"));
                    contenido.append(";");
                    contenido.append(rs.getString("itemName"));
                    contenido.append(";");
                    contenido.append(rs.getInt("cant"));
                    contenido.append(";");
                    contenido.append(rs.getDouble("value"));
                    contenido.append(";");
                    contenido.append(rs.getDouble("total"));
                    contenido.append(";");
                    contenido.append(rs.getString("comments"));
                    contenido.append(";");
                    if(rs.getString("payment").equalsIgnoreCase("Bank Deposit")){
                        contenido.append("BD");
                    }else if(rs.getString("payment").equalsIgnoreCase("Shopify Payments")){
                        contenido.append("SP");
                    }else if(rs.getString("payment").equalsIgnoreCase("Cash on Delivery (COD)")){
                        contenido.append("COD");
                    }
                    //contenido.append(rs.getString("payment"));
                    contenido.append("\n");
                    bw.write(contenido.toString());
                    contenido = new StringBuilder();
                    
                }
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "An error has occurred trying to connect to database");
                 return 0;
            }
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the file, please check the export path");
             return 0;
        }
        
        return 1;
    }
    
     public static int actualizarRutas( int stop, String direccion) {
        Statement st;
        ResultSet rs;
        String address;
        int  id;
        String postalCode;
        PreparedStatement PS;
        try {
            Conexion cn = new Conexion();
            st = (Statement) cn.con.createStatement();
            rs = st.executeQuery("select * from orders");
            while (rs.next()) {
                address = rs.getString("address");
                id = rs.getInt("id");
                postalCode = rs.getString("postalCode");
                if(direccion.equalsIgnoreCase(address+" "+postalCode)){
                    PS = cn.con.prepareStatement("UPDATE orders SET stop = '"+stop+"' WHERE id ="+id);
                    PS.execute(); 
                    PS.close();
                }
                
            }
            
            cn.con.close();
            
            return 1;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to update the routes in database");
             return 0;
        }
    }
     
     public static void vaciarTabla(){
         Conexion cn = new Conexion();
         Statement st;
         ResultSet rs;
         PreparedStatement PS;
         try{
            PS = cn.con.prepareStatement("TRUNCATE TABLE orders ");  
            PS.execute(); 
            PS.close();
            cn.con.close();
         }catch (Exception e){
              JOptionPane.showMessageDialog(null, "An error has occurred while the system was trying to clean the orders table");
         }
     }
     
     public static String formatearTelefono(String telefono){
         StringBuilder  telefonoSB = new StringBuilder();
         System.out.println("Telefono: "+telefono);
         
         
         for (int i = 0; i < telefono.length(); i++) {
             
             if(telefono.charAt(i) == '+')
                i = i+2;
             
             if((telefono.charAt(i) == '0') || (telefono.charAt(i) == '1')
                     || (telefono.charAt(i) == '2') || (telefono.charAt(i) == '3')
                     || (telefono.charAt(i) == '4') || (telefono.charAt(i) == '5')
                     || (telefono.charAt(i) == '6') || (telefono.charAt(i) == '7')
                     || (telefono.charAt(i) == '8') || (telefono.charAt(i) == '9')){
                 telefonoSB.append(telefono.charAt(i));
             }
         }
         
         System.out.println("TelefoboSB :"+ telefonoSB.toString());
         return telefonoSB.toString();
     }
     
     
     
     public static String formatearFecha(String fecha){
         String[] fechaSplit;
         String[] fechaSplitRever;
         
         fechaSplit = fecha.split(" ");
         
         fechaSplitRever = fechaSplit[0].split("-");
         
         String fechaValida = fechaSplitRever[2]+"/"+fechaSplitRever[1]+"/"+fechaSplitRever[0];
         
         
         System.out.println("fechaValida"+fechaValida);
         return fechaValida;
     }
     
      public static int escribirArchivoProductos(String ruta) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        StringBuilder contenido = new StringBuilder();
        Product producto;
        ArrayList<Product> listaProductos = new ArrayList<>();
        String nombre;
        int    cantidad;
        
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from orders");
                while (rs.next()) {
                    nombre = rs.getString("itemName");
                    cantidad = rs.getInt("cant");
                    boolean primerDato = true;
                    
                    for (int i = 0; i < listaProductos.size(); i++) {
                        if(listaProductos.get(i).getNombre().equalsIgnoreCase(nombre)){
                            cantidad = cantidad + listaProductos.get(i).getCantidad();
                            listaProductos.get(i).setCantidad(cantidad);
                            primerDato = false;
                        }
                    }
                    
                    if(primerDato){
                        producto = new Product(nombre, cantidad, 0, "");
                        listaProductos.add(producto);
                    }
 
                }
                bw.write("Item;Quantity\n");
                for (Product listaProducto : listaProductos) {
                    bw.write(listaProducto.getNombre()+";"+listaProducto.getCantidad()+"\n");
                    
                }
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Error conecting to the data base");
                 return 0;
            }
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the products file, please check the path");
             return 0;
        }
        
        return 1;
    }
      
      public static int escribirArchivoRutas(String ruta) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        StringBuilder contenido = new StringBuilder();
        ArrayList<String> listaProductos = new ArrayList<>();
        String address;
        String postalCode;
        
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from orders");
                while (rs.next()) {
                    address = rs.getString("address");
                    postalCode = rs.getString("postalCode");
                    boolean primerDato = true;
                    
                    for (int i = 0; i < listaProductos.size(); i++) {
                        if(listaProductos.get(i).equalsIgnoreCase(address+" "+postalCode)){
                            primerDato = false;
                        }
                    }
                    
                    if(primerDato){
                        listaProductos.add(address+" "+postalCode);
                    }
 
                }
                bw.write("Address\n");
                for (int i = 0; i < listaProductos.size(); i++) {
                    bw.write(listaProductos.get(i)+"\n");
                    
                }
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Error conecting to the data base");
                 return 0;
            }
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the products file, please check the path");
             return 0;
        }
        
        return 1;
    }
    
      
      public static void actualizarClientes( Order order, Conexion cn) {
       // Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        Client client = new Client();
        boolean existe = false;
        try {
            st = (Statement) cn.con.createStatement();
            //rs = st.executeQuery("insert into products (id, nombre, precio, cantidad) values (3,'cereza', 900,5 )");
            rs = st.executeQuery("select * from clients WHERE shippingPhone = '"+order.getShippingPhone()+"'");
            while (rs.next()) {
                existe = true;
            }
            //cn.con.close();
        } catch (Exception e) {
        }
        
        if(!existe){
            client.setShippingPhone(order.getShippingPhone());
            client.setName(order.getShippingName());
            client.setAddress(order.getAddress());
            client.setAddress2(order.getAddress2());
            client.setCity(order.getCity());
            client.setPostalCode(order.getPostalCode());
            client.setShopifyCode(order.getCodeSP());
            client.setSubscribe(0);
            client.setClasse("");
            insertarDatos(client, cn);
        }
    }
      
      public static int insertarAHistorial() {
        Statement st;
        ResultSet rs;
        String address;
        int  id;
        String postalCode;
        PreparedStatement PS;
        try {
            Conexion cn = new Conexion();
            //PS = cn.con.prepareStatement("INSERT INTO horders SELECT * FROM orders");
            PS = cn.con.prepareStatement("insert into horders ( codeSP, stop, shippingPhone, shippingName, address, address2, city, postalCode,"
                    + " itemName, cant, value, total, payment, comments, date)"
                    + "SELECT O.codeSP, O.stop, O.shippingPhone, O.shippingName, O.address, O.address2, O.city, O.postalCode, O.itemName, O.cant, O.value, O.total,"
                    + "O.payment, O.comments, O.date FROM orders O");

            PS.execute(); 
            PS.close();            
            cn.con.close();
            
            return 1;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to create history orders in database"+e);
             return 0;
        }
        
    }
      
      public static int escribirArchivoHOrdersXFecha(String fechaDesde, String fechaHasta, String ruta) {

       Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        StringBuilder contenido = new StringBuilder();
        String [] vecFechaDesde = fechaDesde.split("/");
        String [] vecFechaHasta = fechaHasta.split("/");
        String cadLongFechaDesde = vecFechaDesde[2]+vecFechaDesde[1]+vecFechaDesde[0];
        String cadLongFechaHasta = vecFechaHasta[2]+vecFechaHasta[1]+vecFechaHasta[0];
        
        long   longFechaDesde = Long.parseLong(cadLongFechaDesde);
        long   longFechaHasta = Long.parseLong(cadLongFechaHasta);
        
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String encabezado = "Stop;Phone;Name;Address;Address2;City;Zip;Item;Quantity;Price;Total;Notes;PM;Date\n";
           bw.write(encabezado);

            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from horders");
                while (rs.next()) {
                    String fechaOrder = rs.getString("date");
                    String [] vecFechaOrder = fechaOrder.split("/");
                    String cadLongFechaOrder = vecFechaOrder[2]+vecFechaOrder[1]+vecFechaOrder[0];
                    long   longFechaOrder = Long.parseLong(cadLongFechaOrder);
                    
                    if(longFechaOrder >= longFechaDesde && longFechaOrder <= longFechaHasta)
                    {
                        contenido.append(rs.getString("stop"));
                        contenido.append(";");
                        contenido.append(rs.getString("shippingPhone"));
                        contenido.append(";");
                        contenido.append(rs.getString("shippingName"));
                        contenido.append(";");
                        contenido.append(rs.getString("address"));
                        contenido.append(";");
                        contenido.append(rs.getString("address2"));
                        contenido.append(";");
                        contenido.append(rs.getString("city"));
                        contenido.append(";");
                        contenido.append(rs.getString("postalCode"));
                        contenido.append(";");
                        contenido.append(rs.getString("itemName"));
                        contenido.append(";");
                        contenido.append(rs.getInt("cant"));
                        contenido.append(";");
                        contenido.append(rs.getDouble("value"));
                        contenido.append(";");
                        contenido.append(rs.getDouble("total"));
                        contenido.append(";");
                        contenido.append(rs.getString("comments"));
                        contenido.append(";");
                        if(rs.getString("payment").equalsIgnoreCase("Bank Deposit")){
                            contenido.append("BD");
                        }else if(rs.getString("payment").equalsIgnoreCase("Shopify Payments")){
                            contenido.append("SP");
                        }else if(rs.getString("payment").equalsIgnoreCase("Cash on Delivery (COD)")){
                            contenido.append("COD");
                        }
                        contenido.append(";");
                        contenido.append(fechaOrder);
                        //contenido.append(rs.getString("payment"));
                        contenido.append("\n");
                        bw.write(contenido.toString());
                        contenido = new StringBuilder();
                    }
                    
                }
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "An error has occurred trying to connect to database");
                 return 0;
            }
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the file, please check the export path");
             return 0;
        }
        
        return 1;
      }
      
      public static int escribirArchivoClientes(String ruta) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        StringBuilder contenido = new StringBuilder();
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String encabezado = "Phone;SPCode;Name;Address;Address2;City;Zip;Class;\n";
            bw.write(encabezado);

            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from clients");
                while (rs.next()) {
                    contenido.append(rs.getString("shippingPhone"));
                    contenido.append(";");
                    contenido.append(rs.getString("shopifyCode"));
                    contenido.append(";");
                    contenido.append(rs.getString("name"));
                    contenido.append(";");
                    contenido.append(rs.getString("address"));
                    contenido.append(";");
                    contenido.append(rs.getString("address2"));
                    contenido.append(";");
                    contenido.append(rs.getString("city"));
                    contenido.append(";");
                    contenido.append(rs.getString("postalCode"));
                    contenido.append(";");
                    contenido.append(rs.getString("class"));
                    contenido.append(";");
                    //contenido.append(rs.getString("payment"));
                    contenido.append("\n");
                    bw.write(contenido.toString());
                    contenido = new StringBuilder();
                    
                }
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "An error has occurred trying to connect to database"+e);
                 return 0;
            }
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the file, please check the export path");
             return 0;
        }
        
        return 1;
    }
 
      public static ArrayList<HProduct> llenarTablaProductos(String fecha) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        StringBuilder contenido = new StringBuilder();
        HProduct producto;
        ArrayList<HProduct> listaProductos = new ArrayList<>();
        String nombre;
        int    cantidad;
        Double valor;
        String skuCode;
        String cadDia;
        String cadMes;
        Double purchaseValue;
        String fechaPartes[];
        String week = "";
        String fechaOrder = "";
        /*
        fechaPartes = fecha.split("/");
        
        if(Integer.parseInt(fechaPartes[0]) <= 7)
            week = "1";
        else if(Integer.parseInt(fechaPartes[0]) > 7 && Integer.parseInt(fechaPartes[0]) <= 14)
            week = "2";
        else if(Integer.parseInt(fechaPartes[0]) > 14 && Integer.parseInt(fechaPartes[0]) <= 21)
            week = "3";
        else if(Integer.parseInt(fechaPartes[0]) > 21 && Integer.parseInt(fechaPartes[0]) <= 28)
            week = "4";
        else if(Integer.parseInt(fechaPartes[0]) > 28 )
            week = "5";
        */
            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from orders");
                while (rs.next()) {
                    nombre = rs.getString("itemName");
                    cantidad = rs.getInt("cant");
                    valor = rs.getDouble("value");
                    skuCode = rs.getString("skuCode");
                    fechaOrder = rs.getString("date");
                    purchaseValue = obtenerValorCompraProducto(nombre);
                    //System.out.println("Value: "+valor);
                    
                    //valor = valor * cantidad;
                    boolean primerDato = true;
                    
                    String semana = obtenerSemanaFecha(fechaOrder);
                    
                    for (int i = 0; i < listaProductos.size(); i++) {
                        
                        if(listaProductos.get(i).getNombre().equalsIgnoreCase(nombre) && listaProductos.get(i).getDate().equalsIgnoreCase(semana)){
                            
                            cantidad = cantidad + listaProductos.get(i).getCantidad();
                            listaProductos.get(i).setCantidad(cantidad);
                            //valor = valor + listaProductos.get(i).getSaleValue();
                            //listaProductos.get(i).setSaleValue(valor);
                            primerDato = false;
                        
                        }
                    }
                    
                    if(primerDato){
                     //   producto = new HProduct(nombre, cantidad, purchaseValue, valor, fecha, skuCode, week);
                        producto = new HProduct(nombre, cantidad, purchaseValue, valor, semana, skuCode, week);
                     //   insertarDatos(producto, cn);
                        listaProductos.add(producto);
                    }
 
                }
                
                for (HProduct listaProducto : listaProductos) {
                    insertarDatos(listaProducto, cn);
                  //  actualizarProductos(listaProducto);
                    System.out.println(listaProducto.getCantidad());
                }
                
                
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Error conecting to the data base");
                 return null;
            }
            
          
        
        return listaProductos;
    }
      
     public static Double obtenerValorCompraProducto(String name) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        String    address;
        Double    purchaseValue = 0.0;
        
        try {
            st = (Statement) cn.con.createStatement();
            rs = st.executeQuery("select * from products where name = '"+name+"'");
            while (rs.next()) {
                
                purchaseValue = rs.getDouble("purchaseValue");
                
            }
                
            cn.con.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to read the clients table"+e);
             return 0.0;
        }
        
        return purchaseValue;
    }
      
      public static ArrayList<Product> actualizarProductos(Product producto) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        StringBuilder contenido = new StringBuilder();
        ArrayList<Product> listaProductos = new ArrayList<>();
        String nombre;
        int    cantidad;
        Double valor;
        String sql;
        
        int    busqueda = buscarProducto(producto);
        if( 0 == busqueda )
        {
            try {
               
                    PreparedStatement PS = cn.con.prepareStatement("insert into products ( id, name, purchaseValue) values (null,?,? )");
                    PS.setString(1, producto.getNombre());
                    PS.setDouble(2, producto.getPurchaseValue());
                    PS.executeUpdate();
                    //actualizarProductos(listaProductos);
                cn.con.close();
               
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Error conecting to the data base"+e);
                 return null;
            }
        }
        else
        {
            try {
              Statement prepStat = (Statement) cn.con.createStatement(); 
              
              int   quantity = busqueda+producto.getCantidad();
              
                sql = "UPDATE products SET "
                    + "name = '"+producto.getNombre()+"', "
                    + "purchaseValue = '"+producto.getPurchaseValue()+"' "
                    + "WHERE name = '"+producto.getNombre()+"'";
            
            //System.out.println(sql);
            
                prepStat.executeUpdate(sql);
                cn.con.close();
               
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Error conecting to the data base"+e);
                 return null;
            }
        }
        
        
        return listaProductos;
    }
      
    public static int buscarProducto(Product producto) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        int       retorno = 0;
        
            try {
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from products WHERE name = '"+producto.getNombre()+"'");
                while (rs.next()) {
                    //retorno = rs.getInt("quantity");
                    retorno = 1;
                    break;
                }                
                
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Error conecting to the data base"+e);
                 return 2;
            }
            
        return retorno;
    }
      
      public static ArrayList<String[][]> cargarArchivoClientes(String ruta) {
        
        //vaciarTabla();
        Path filePath = Paths.get(ruta);
        String vectorDatos[][];
        String vectorDatosTmp[][];
        String vectorDatosCopia[][];
        ArrayList<String[][]> datos = new ArrayList<>();
        //ArrayList<String[]> datosRutas = new ArrayList<>();
        //datosRutas = cargarArchivoRutas(rutaRoutes);
        //System.out.println(datosRutas.get(0)[0]);
        Conexion cn = new Conexion();
        try {
            BufferedReader bf = Files.newBufferedReader(filePath);
            String linea;
            String encabezados = "";
            String[] encabezadosVector;
            String[] encabezadoCampos;
            StringBuilder dato;
            String nameAnterior = "";
            int posicionAddress = 0;
            boolean primeraLinea = true;
            int numeroDatos = 0;
            if (primeraLinea) {
                encabezados = bf.readLine();
                for (int i = 0; i < encabezados.length(); i++) {
                    if (encabezados.charAt(i) == ',') {
                        numeroDatos++;
                    }
                }
            }
            encabezadosVector = encabezados.split(",");
            int campos[] = validarCampos(encabezadosVector,"");
            encabezadoCampos = new String[campos.length];
            for (int i = 0; i < campos.length; i++) {
                encabezadoCampos[i] = encabezadosVector[campos[i]];
            }

            vectorDatos = new String[1][numeroDatos];
            vectorDatosTmp = new String[1][numeroDatos];
            vectorDatosCopia = new String[1][numeroDatos];
            int contadorDatos = 0;
            //Recorremos las lineas del archivo
            while ((linea = bf.readLine()) != null) {
                dato = new StringBuilder("");
                //Recorremos los caracteres de la linea leida
                for (int i = 0; i < linea.length(); i++) {
                    //Si la linea leida es igual a una coma es por que viene un nuevo dato
                    if (linea.charAt(i) == ',') {
                        //Agregamos el dato al array y formateamos la cadena
                        vectorDatos[0][contadorDatos] = dato.toString();
                        contadorDatos++;
                        dato = new StringBuilder("");
                        //Se pregunta que el siguiente caracter este dentro de la variable linea
                        if (i + 1 < linea.length()) {
                            //Se pregunta si el caracter que sigue a la coma es un '"' esto nos indica que leeremos una cadena
                            //que puede contener comas en su interior
                            if (linea.charAt(i + 1) == '"') {
                                i = i + 2;
                                while (linea.charAt(i) != '"') {
                                    dato.append(linea.charAt(i));
                                    i++;
                                }

                            } else {
                                if (linea.charAt(i) != ',') {
                                    dato.append(linea.charAt(i));
                                }
                            }
                        }
                    } else {
                        if (linea.charAt(i) != ',') {
                            dato.append(linea.charAt(i));
                        }
                    }
                }
                //order.setShippingName(vector[15]);
                //agreagarDatos(order);
                // String[] datosLinea = linea.split(",");
                // System.out.println(datos.get(0));
                int contadorTmp = 0;
                
                    // vectorDatosTmp = new String[1][numeroDatos];
                    //Se recorre la lista de campos para agregar solo los campos necesarios
                    for (int j = 0; j < campos.length; j++) {
                        
                        if(buscarEnFieldConfigure(encabezadosVector[campos[j]], cn, "client").equalsIgnoreCase("shippingPhone")){
                            vectorDatosTmp[0][contadorTmp] = formatearTelefono(vectorDatos[0][campos[j]]);
                        }
                        else
                        {
                             vectorDatosTmp[0][contadorTmp] = vectorDatos[0][campos[j]];
                        }
                        // System.out.println(vectorDatos[0][campos[j]]);
                        contadorTmp++;
                    }
                    datos.add(vectorDatosTmp);
                    crearCliente(encabezadoCampos, vectorDatosTmp, cn);
                    // System.out.println("vector"+vectorDatosTmp[0][2]);
                    contadorDatos = 0;
                    nameAnterior = vectorDatos[0][0];
                    vectorDatos = new String[1][numeroDatos];
                
            }
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "An error has occurred reading the file, please check the file path");
             return null;
        }
        JOptionPane.showMessageDialog(null, "The clients file has been uploaded succesfully");
        return datos;
    }
      
      
      
       public static void crearCliente(String encabezados[], /*ArrayList<String[][]>*/ String[][] datos,  Conexion cn) {
        cn = new Conexion();
        Client client;
        String campoAddress = "";
        String campoTabla = "";
        String cadDia = "";
        String cadMes = "";
        StringBuilder nombre = new StringBuilder();
        Calendar fecha = Calendar.getInstance();
        Integer dia = fecha.get(Calendar.DATE);
        if(dia.toString().length() == 1)
            cadDia = "0"+dia;
        else
            cadDia = dia.toString();
            
        Integer mes = fecha.get(Calendar.MONTH)+1;
        if(mes.toString().length() == 1)
            cadMes = "0"+mes;
        else
            cadMes = mes.toString();
            
        int annio = fecha.get(Calendar.YEAR);
        String date = cadDia+"/"+cadMes+"/"+annio;
        /* for (int i = 0; i < encabezados.length; i++) {
            System.out.print(encabezados[i]+" ");
        }
        System.out.println("-------------------------");*/

        client = new Client();
        //   System.out.println("Encabezados"+encabezados[0]+encabezados[1]+encabezados[2]+encabezados[3]+encabezados[4]+encabezados[5]+encabezados[6]+encabezados[7]+encabezados[8]);
        // System.out.println("Copia"+datos[0][0]+";"+datos[0][1]+";"+datos[0][2]+";"+datos[0][3]+";"+datos[0][4]+";"+datos[0][5]+";"+datos[0][6]+";"+datos[0][7]+";"+datos[0][8]+datos[0][9]+datos[0][10]+datos[0][11]);
        for (int i = 0; i < datos.length; i++) {

            for (int j = 0; j < encabezados.length-1; j++) {
                campoTabla = buscarEnFieldConfigure(encabezados[j], cn, "client");
                //System.out.println("FieldConfigure: " + buscarEnFieldConfigure(encabezados[j], cn, "cliente"));
                if(campoTabla.equalsIgnoreCase("ErrorBase")){
                    return;
                }else if (campoTabla.equalsIgnoreCase("shippingPhone")) {
                    client.setShippingPhone(datos[0][j]);
                         // System.out.println("datos: "+datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("name")) {
                    nombre.append(" "+datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("address")) {
                    client.setAddress(datos[0][j]);
                    campoAddress = datos[0][j];
                } else if (campoTabla.equalsIgnoreCase("address2")) {
                    client.setAddress2(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("city")) {
                    client.setCity(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("postalCode")) {
                    client.setPostalCode(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("email")) {
                    client.setEmail(datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("totalSpent")) {
                    client.setTotalSpent(Double.parseDouble(datos[0][j]));
            }
            }
           /* for (int j = 0; j < direcciones.size(); j++) {
                // System.out.println("Campo: " +campoAddress+ " direccion: "+ direcciones.get(j)[0]);
                if (direcciones.get(j)[1].equalsIgnoreCase(campoAddress)) {
                    order.setStop(Integer.parseInt(direcciones.get(j)[0]));
                }
            }
*/
           client.setName(nombre.toString());
               // System.out.println(client.getShippingPhone());
            if(client.getShippingPhone().equalsIgnoreCase("") || client.getShippingPhone().isEmpty())
            {
                continue;
            }
            
            Statement st;
            ResultSet rs;
          //  Client client = new Client();
            boolean existe = false;
            try {
                st = (Statement) cn.con.createStatement();
                //rs = st.executeQuery("insert into products (id, nombre, precio, cantidad) values (3,'cereza', 900,5 )");
                rs = st.executeQuery("select * from clients WHERE shippingPhone = '"+client.getShippingPhone()+"'");
                while (rs.next()) {
                    existe = true;
                }
                cn.con.close();
            } catch (Exception e) {
            }

            System.out.println(encabezados.length);
            if(!existe)
            {
                insertarDatos(client, cn);
                System.out.println("inserto");
            }
            // cn.con.close();
        
        // return order;
    }
 }
      
   public static ArrayList<String[]> cargarArchivoClientesManuales(String ruta) {
        Path filePath = Paths.get(ruta);
        ArrayList<String[]> direcciones = new ArrayList<>();
        Client client;
        int lineas = 0;
        Conexion cn = new Conexion();
        try {
            BufferedReader bf = Files.newBufferedReader(filePath);
            String linea;
            String[] encabezadosVector;
            String[] datosLinea;
            String nombre;
            boolean primeraLinea = true;
            int posicionAddress = 0;
            int posicionStop = 0;

            //Recorremos las lineas del archivo
            while ((linea = bf.readLine()) != null) {
                lineas++;
                System.out.println(lineas);
                if (primeraLinea) {
                    encabezadosVector = linea.split(";");
                    
                    primeraLinea = false;
                } else {
                    client = new Client();
                    nombre = "";
                    datosLinea = linea.split(";");
                    client.setShippingPhone(datosLinea[1]);
                    nombre = datosLinea[3]+datosLinea[4];
                    client.setName(nombre);
                    client.setAddress(datosLinea[5]);
                    client.setPostalCode(datosLinea[7]);
                    client.setCity(datosLinea[8]);
                    
                    //System.out.println("Stop:"+datoGuardar[0]+"Addres:"+datoGuardar[1]);
                    insertarDatos(client, cn);
                }
            }
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "An error has occurred reading the clients file, please check the file path"+e);
             return null;
        }
        
        JOptionPane.showMessageDialog(null, "The clients file has been uploaded succesfully");
        return null;
    }     
      
      
      public static int escribirArchivoClientesCambio(String ruta) {

        Conexion cn = new Conexion();
        Conexion cn1 = new Conexion();
        Statement st;
        ResultSet rs;
        Statement stCli;
        ResultSet rsCli;
        StringBuilder contenido = new StringBuilder();
        ArrayList<String> listaPhones = new ArrayList<>();
        String prevClass = "";
        String lastClass = "";
        String phone;
        String name;
        
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

                bw.write("shippingPhone;name;prevClass;lastClass\n");
            
                st = (Statement) cn.con.createStatement();
                rs = st.executeQuery("select * from class");
                while (rs.next()) {
                   
                
                    prevClass = rs.getString("prevClass");
                    lastClass = rs.getString("lastClass");
                    phone = rs.getString("shippingPhone");
                    name = rs.getString("name");
                    
                    if(!prevClass.equalsIgnoreCase(lastClass))
                    {
                           
                     bw.write(phone+";"+name+";"+prevClass+";"+lastClass+"\n");
                    }
                }
                
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the clients file, please check the path"+e);
             return 0;
        }
        
        return 1;
    }
      
      
     public static int validarClientes(String ruta) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        String    name;
        String    address;
        String    phone;
        
        try {
            st = (Statement) cn.con.createStatement();
            rs = st.executeQuery("select * from clients");
            while (rs.next()) {
                
                name = rs.getString("name");
                address = rs.getString("address");
                phone = rs.getString("shippingPhone");
                
                generarArchivoClientesRepetidos(name, address, phone, ruta);
            }
                
            cn.con.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to read the clients table"+e);
             return 0;
        }
        
        return 1;
    }
      
      public static int generarArchivoClientesRepetidos(String name, String address, String phone, String ruta) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        String phoneRepeated;
        String contenido;
        
         try {
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
                String encabezado = "Name;Address;OldPhone;NewPhone;\n";
                bw.write(encabezado);
            }
          
           
            try {
               st = (Statement) cn.con.createStatement();
               rs = st.executeQuery("select * from clients where name = \""+name+"\" and address = \""+address+"\" and shippingPhone <> \""+phone+"\"");
                while (rs.next()) {
                    phoneRepeated = rs.getString("shippingPhone");
                    
                    contenido = name+";"+address+";"+phone+";"+phoneRepeated+";\n";
                    bw.write(contenido);
                    
                    contenido = "";
                }
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "An error has occurred trying to connect to database"+e);
                 return 0;
            }
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the file, please check the export path");
             return 0;
        }
        
        return 1;
    }
      
     
    public static int actualizarOrdenes( String phoneIni, String phoneRepet) {
        PreparedStatement PS;
        Conexion cn = new Conexion();
        try {
           
            PS = cn.con.prepareStatement("UPDATE horders SET shippingPhone = '"+phoneIni+"' WHERE shippingPhone = '"+phoneRepet+"'");
            PS.execute(); 
            PS.close();
                
            
            cn.con.close();
            
            return 1;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to update the horders in database"+e);
             return 0;
        }
    }
    
    public static int eliminarCliente( String phone) {
        PreparedStatement PS;
        Conexion cn = new Conexion();
        try {
           
            PS = cn.con.prepareStatement("DELETE from clients WHERE shippingPhone = '"+phone+"'");
            PS.execute(); 
            PS.close();
               
            cn.con.close();
            
            return 1;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to delete the client in database"+e);
             return 0;
        }
    }
    
    public static int actualizarClienteRepetido(String phone) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        String    name;
        String    address;
        
        try {
            st = (Statement) cn.con.createStatement();
            rs = st.executeQuery("select * from clients where shippingPhone = '"+phone+"'");
            while (rs.next()) {
                
                name = rs.getString("name");
                address = rs.getString("address");
                
                validarClientesRepetidos(name, address, phone);
            }
                
            cn.con.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to read the clients table"+e);
             return 0;
        }
        
        return 1;
    }
     
    public static int validarClientesRepetidos(String name, String address, String phone) {

        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        String phoneRepeated;
        
            try {
               st = (Statement) cn.con.createStatement();
               rs = st.executeQuery("select * from clients where name = \""+name+"\" and address = \""+address+"\" and shippingPhone <> \""+phone+"\"");
                while (rs.next()) {
                    phoneRepeated = rs.getString("shippingPhone");
                    actualizarOrdenes( phone, phoneRepeated);
                    eliminarCliente(phoneRepeated);
                }
                cn.con.close();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "An error has occurred trying to connect to database"+e);
                 return 0;
            }
            
        
        return 1;
    }
    
    
    
    public static ArrayList<String[][]> cargarArchivoProductos(String ruta) {
        
        //vaciarTabla();
        Path filePath = Paths.get(ruta);
        String vectorDatos[][];
        String vectorDatosTmp[][];
        String vectorDatosCopia[][];
        ArrayList<String[][]> datos = new ArrayList<>();
        //ArrayList<String[]> datosRutas = new ArrayList<>();
        //datosRutas = cargarArchivoRutas(rutaRoutes);
        //System.out.println(datosRutas.get(0)[0]);
        Conexion cn = new Conexion();
        try {
            BufferedReader bf = Files.newBufferedReader(filePath);
            String linea;
            String encabezados = "";
            String[] encabezadosVector;
            String[] encabezadoCampos;
            StringBuilder dato;
            String nameAnterior = "";
            int posicionAddress = 0;
            boolean primeraLinea = true;
            int numeroDatos = 0;
            if (primeraLinea) {
                encabezados = bf.readLine();
                for (int i = 0; i < encabezados.length(); i++) {
                    if (encabezados.charAt(i) == ',') {
                        numeroDatos++;
                    }
                }
            }
            encabezadosVector = encabezados.split(",");
            int campos[] = validarCampos(encabezadosVector,"");
            encabezadoCampos = new String[campos.length];
            for (int i = 0; i < campos.length; i++) {
                encabezadoCampos[i] = encabezadosVector[campos[i]];
            }

            vectorDatos = new String[1][numeroDatos];
            vectorDatosTmp = new String[1][numeroDatos];
            vectorDatosCopia = new String[1][numeroDatos];
            int contadorDatos = 0;
            //Recorremos las lineas del archivo
            while ((linea = bf.readLine()) != null) {
                dato = new StringBuilder("");
                //Recorremos los caracteres de la linea leida
                for (int i = 0; i < linea.length(); i++) {
                    //Si la linea leida es igual a una coma es por que viene un nuevo dato
                    if (linea.charAt(i) == ',') {
                        //Agregamos el dato al array y formateamos la cadena
                        vectorDatos[0][contadorDatos] = dato.toString();
                        contadorDatos++;
                        dato = new StringBuilder("");
                        //Se pregunta que el siguiente caracter este dentro de la variable linea
                        if (i + 1 < linea.length()) {
                            //Se pregunta si el caracter que sigue a la coma es un '"' esto nos indica que leeremos una cadena
                            //que puede contener comas en su interior
                            if (linea.charAt(i + 1) == '"') {
                                i = i + 2;
                                while (linea.charAt(i) != '"') {
                                    dato.append(linea.charAt(i));
                                    i++;
                                }

                            } else {
                                if (linea.charAt(i) != ',') {
                                    dato.append(linea.charAt(i));
                                }
                            }
                        }
                    } else {
                        if (linea.charAt(i) != ',') {
                            dato.append(linea.charAt(i));
                        }
                    }
                }
                //order.setShippingName(vector[15]);
                //agreagarDatos(order);
                // String[] datosLinea = linea.split(",");
                // System.out.println(datos.get(0));
                int contadorTmp = 0;
                
                    // vectorDatosTmp = new String[1][numeroDatos];
                    //Se recorre la lista de campos para agregar solo los campos necesarios
                    for (int j = 0; j < campos.length; j++) {
                        
                        vectorDatosTmp[0][contadorTmp] = vectorDatos[0][campos[j]];
                        // System.out.println(vectorDatos[0][campos[j]]);
                        contadorTmp++;
                    }
                    datos.add(vectorDatosTmp);
                    // System.out.println("vector"+vectorDatosTmp[0][2]);
                    contadorDatos = 0;
                    nameAnterior = vectorDatos[0][0];
                    vectorDatos = new String[1][numeroDatos];
                
            }
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "An error has occurred reading the file, please check the file path");
             return null;
        }
        JOptionPane.showMessageDialog(null, "The clients file has been uploaded succesfully");
        return datos;
    }
    
    public static void crearOrdenProducto(String encabezados[], /*ArrayList<String[][]>*/ String[][] datos,  Conexion cn) {
        cn = new Conexion();
        Product product;
        String campoAddress = "";
        String campoTabla = "";
        String cadDia = "";
        String cadMes = "";

        product = new Product();
        // System.out.println("Copia"+datos[0][0]+datos[0][1]+datos[0][2]+datos[0][3]+datos[0][4]+datos[0][5]+datos[0][6]+datos[0][7]+datos[0][8]+datos[0][9]+datos[0][10]+datos[0][11]);
        for (int i = 0; i < datos.length; i++) {

            for (int j = 0; j < encabezados.length; j++) {
                campoTabla = buscarEnFieldConfigure(encabezados[j], cn, "product");
                //System.out.println("FieldConfigure: " + buscarEnFieldConfigure(encabezados[j], cn));
                if(campoTabla.equalsIgnoreCase("ErrorBase")){
                    return;
                }else if (campoTabla.equalsIgnoreCase("name")) {
                    product.setNombre(datos[0][j]);
                    //      System.out.println("datos: "+datos[0][j]);
                } else if (campoTabla.equalsIgnoreCase("purchaseValue")) {
                    product.setPurchaseValue(Double.parseDouble(datos[0][j]));
                }else if (campoTabla.equalsIgnoreCase("skuCode")) {
                    product.setSkuCode(datos[0][j]);
                }
            }

          
            insertarDatos(product, cn);
            // cn.con.close();
        }
        // return order;
    }
    
    public static int escribirArchivoEstadisticaXFecha(String fechaDesde, String fechaHasta, String ruta, JTable tablaModelo) {

        StringBuilder contenido = new StringBuilder();
        String [] vecFechaDesde = fechaDesde.split("/");
        String [] vecFechaHasta = fechaHasta.split("/");
        System.out.println("tamanio:" +vecFechaDesde.length);
        String cadLongFechaDesde = vecFechaDesde[2]+vecFechaDesde[1]+vecFechaDesde[0];
        String cadLongFechaHasta = vecFechaHasta[2]+vecFechaHasta[1]+vecFechaHasta[0];
        
        long   longFechaDesde = Long.parseLong(cadLongFechaDesde);
        long   longFechaHasta = Long.parseLong(cadLongFechaHasta);
        
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String encabezado = "Product;Quantity;SaleValue;PurchaseValue;SaleTotal;PurchaseTotal;Date\n";
            bw.write(encabezado);

                    
            for (int i = 0; i < tablaModelo.getRowCount(); i++) {
                String fecha = ""+tablaModelo.getValueAt(i, 6);
                String [] vecFecha = fecha.split("/");
                String cadLongFecha = vecFecha[2]+vecFecha[1]+vecFecha[0];
                long   longFecha = Long.parseLong(cadLongFecha);

                if(longFecha >= longFechaDesde && longFecha <= longFechaHasta)
                {
                    contenido.append(tablaModelo.getValueAt(i, 0));
                    contenido.append(";");
                    contenido.append(tablaModelo.getValueAt(i, 1));
                    contenido.append(";");
                    contenido.append(tablaModelo.getValueAt(i, 2));
                    contenido.append(";");
                    contenido.append(tablaModelo.getValueAt(i, 3));
                    contenido.append(";");
                    contenido.append(tablaModelo.getValueAt(i, 4));
                    contenido.append(";");
                    contenido.append(tablaModelo.getValueAt(i, 5));
                    contenido.append(";");
                    contenido.append(tablaModelo.getValueAt(i, 6));

                    contenido.append("\n");
                    bw.write(contenido.toString());
                    contenido = new StringBuilder();
                }
            }
            
            bw.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error trying to write the file, please check the export path");
             return 0;
        }
        
        return 1;
      }
    
    public static ArrayList<String[][]> cargarArchivoCategorias(String ruta) {
        
        vaciarTabla();
        Path filePath = Paths.get(ruta);
        String vectorDatos[][];
        String vectorDatosTmp[][];
        String vectorDatosCopia[][];
        ArrayList<String[][]> datos = new ArrayList<>();
        //ArrayList<String[]> datosRutas = new ArrayList<>();
        //datosRutas = cargarArchivoRutas(rutaRoutes);
        //System.out.println(datosRutas.get(0)[0]);
        Conexion cn = new Conexion();
        try {
            BufferedReader bf = Files.newBufferedReader(filePath);
            String linea;
            String encabezados = "";
            String[] encabezadosVector;
            String[] encabezadoCampos;
            StringBuilder dato;
            String nameAnterior = "";
            int posicionAddress = 0;
            boolean primeraLinea = true;
            int numeroDatos = 0;
            if (primeraLinea) {
                encabezados = bf.readLine();
                for (int i = 0; i < encabezados.length(); i++) {
                    if (encabezados.charAt(i) == ',') {
                        numeroDatos++;
                    }
                }
            }
            encabezadosVector = encabezados.split(",");
            //int campos[] = validarCampos(encabezadosVector, "order");
            int campos[] = {0,1,2,3,4,5,6,7,8};
            encabezadoCampos = new String[campos.length];
            for (int i = 0; i < campos.length; i++) {
                encabezadoCampos[i] = encabezadosVector[campos[i]];
            }

            vectorDatos = new String[1][numeroDatos+1];
            int contadorDatos = 0;
            //Recorremos las lineas del archivo
            while ((linea = bf.readLine()) != null) {
                dato = new StringBuilder("");
                //Recorremos los caracteres de la linea leida
                for (int i = 0; i < linea.length(); i++) {
                    //Si la linea leida es igual a una coma es por que viene un nuevo dato
                    if (linea.charAt(i) == ',') {
                        //Agregamos el dato al array y formateamos la cadena
                        vectorDatos[0][contadorDatos] = dato.toString();
                        contadorDatos++;
                        dato = new StringBuilder("");
                        //Se pregunta que el siguiente caracter este dentro de la variable linea
                        if (i + 1 < linea.length()) {
                            //Se pregunta si el caracter que sigue a la coma es un '"' esto nos indica que leeremos una cadena
                            //que puede contener comas en su interior
                            if (linea.charAt(i + 1) == '"') {
                                i = i + 2;
                                while (linea.charAt(i) != '"') {
                                    dato.append(linea.charAt(i));
                                    i++;
                                }

                            } else {
                                if (linea.charAt(i) != ',') {
                                    dato.append(linea.charAt(i));
                                }
                            }
                        }
                    } else {
                     //   if (linea.charAt(i) != ',') {
                       //     dato.append(linea.charAt(i));
                        //}
                        if (i == 0 && linea.charAt(i) == '"') {
                                i = i + 1;
                                while (linea.charAt(i) != '"') {
                                    dato.append(linea.charAt(i));
                                    i++;
                                }

                            }
                        else if (linea.charAt(i) != ',') {
                            dato.append(linea.charAt(i));
                        }
                        
                    }
                }
                vectorDatos[0][contadorDatos] = dato.toString();
                //order.setShippingName(vector[15]);
                //agreagarDatos(order);
                // String[] datosLinea = linea.split(",");
                // System.out.println(datos.get(0));
                crearCategorias(vectorDatos);
                contadorDatos = 0;
            }
            
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "An error has occurred reading the file, please check the file path");
             return null;
        }
        JOptionPane.showMessageDialog(null, "The categories file has been uploaded succesfully");
        //insertarAHistorial();
        return datos;
    }
    
    public static void crearCategorias(String vectorDatos[][]) {
        Conexion cn = new Conexion();
        Product producto;
        
        if(existeCategoria(vectorDatos[0][0].trim()) != 1)
        {
            try {
                PreparedStatement PS = cn.con.prepareStatement("insert into categories ( id, product, tag1, tag2, tag3, tag4, tag5, tag6, tag7) values (null,?,?,?,?,?,?,?,?)");
                PS.setString(1, vectorDatos[0][0].trim());
                PS.setString(2, vectorDatos[0][1].trim());
                PS.setString(3, vectorDatos[0][2].trim() );
                PS.setString(4, vectorDatos[0][3].trim());
                PS.setString(5, vectorDatos[0][4].trim());
                PS.setString(6, vectorDatos[0][5].trim());
                PS.setString(7, vectorDatos[0][6].trim());
                PS.setString(8, vectorDatos[0][7].trim());
                PS.executeUpdate();

                cn.con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            Statement prepStat = null;
            Conexion cnx = new Conexion();
            String sql = "";
        
            try {
            
                prepStat = (Statement) cnx.con.createStatement(); 
            
                sql = "UPDATE categories SET "
                        + "tag1 = '"+vectorDatos[0][1]+"', "
                        + "tag2 = '"+vectorDatos[0][2]+"', "
                        + "tag3 = '"+vectorDatos[0][3]+"', "
                        + "tag4 = '"+vectorDatos[0][4]+"', "
                        + "tag5 = '"+vectorDatos[0][5]+"', "
                        + "tag6 = '"+vectorDatos[0][6]+"', "
                        + "tag7 = '"+vectorDatos[0][7]+"' "
                        + "WHERE product = '"+vectorDatos[0][0]+"'";
            
                System.out.println(sql);
            
                prepStat.executeUpdate(sql);
            
                cnx.con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        
        System.out.println("Producto: "+vectorDatos[0][8]);
        
        if(!vectorDatos[0][8].equalsIgnoreCase(""))
            producto = new Product(vectorDatos[0][0], 0, Double.parseDouble(vectorDatos[0][8]), "");
        else
             producto = new Product(vectorDatos[0][0], 0, 0, "");
             
        actualizarProductos(producto);
    }
    
    
    public static int existeCategoria(String producto) {
        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        try {
            st = (Statement) cn.con.createStatement();
            //rs = st.executeQuery("insert into products (id, nombre, precio, cantidad) values (3,'cereza', 900,5 )");
            rs = st.executeQuery("select * from categories where product = '"+producto+"'");
            while (rs.next()) {
                //System.out.println(rs.getInt("id") + " " + rs.getString("nombre") + " ");
                return 1;
            }
            cn.con.close();
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
    
    
    public static int existeProducto(String producto) {
        Conexion cn = new Conexion();
        Statement st;
        ResultSet rs;
        try {
            st = (Statement) cn.con.createStatement();
            //rs = st.executeQuery("insert into products (id, nombre, precio, cantidad) values (3,'cereza', 900,5 )");
            rs = st.executeQuery("select * from products where name = '"+producto+"'");
            while (rs.next()) {
                //System.out.println(rs.getInt("id") + " " + rs.getString("nombre") + " ");
                return 1;
            }
            cn.con.close();
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
    
    public static String obtenerSemanaFecha(String fecha)
    {
        String fechaRetorno;
        String[] fechaSplit = fecha.split("/");
        
        int dia = Integer.parseInt(fechaSplit[0]); 
        int mes = Integer.parseInt(fechaSplit[1])-1; 
        int anio = Integer.parseInt(fechaSplit[2]); 
        
        int primerDia = 0;
        int diasMes = 0;
        
        int semana2 = 0;
        int semana3 = 0;
        int semana4 = 0;
        int semana5 = 0;
        
        Formatter obj = new Formatter();
       
       // GregorianCalendar fechaD = new GregorianCalendar(anio, mes, dia);
        
        for (int i = 0; i < 7; i++) {
             Calendar fechaPrimerMier = new GregorianCalendar(anio, mes, i);
        
            fechaPrimerMier.add(Calendar.DATE, 1);
            int diaAct = fechaPrimerMier.get(Calendar.DAY_OF_WEEK);
            diasMes = fechaPrimerMier.getActualMaximum(Calendar.DAY_OF_MONTH);
            
            //System.out.println("Dias meses: "+diasMes);
           // System.out.println("DiaAct: " + diaAct);
            if(diaAct == 4)
            {
                primerDia = i+1;
                break;
            }
            
        }
        
        
        semana2 = primerDia + 7;
        semana3 = semana2 + 7;
        semana4 = semana3 + 7;
        semana5 = semana4 + 7;
        
        
        if(dia <= primerDia)
        {
            fechaRetorno = ""+String.valueOf(obj.format("%02d/%02d", primerDia, (mes+1)))+"/"+anio;
        }
        else if(dia > primerDia && dia <= semana2)
        {
            fechaRetorno = ""+String.valueOf(obj.format("%02d/%02d", semana2, (mes+1)))+"/"+anio;
        }
        else if(dia > semana2 && dia <= semana3)
        {
            fechaRetorno = ""+String.valueOf(obj.format("%02d/%02d", semana3, (mes+1)))+"/"+anio;
            
        }
        else if(dia > semana3 && dia <= semana4)
        {
            fechaRetorno = ""+String.valueOf(obj.format("%02d/%02d", semana4, (mes+1)))+"/"+anio;
        }
        else if(semana5 <= diasMes && (dia > semana4 && dia <= semana5))
        {
            fechaRetorno = ""+String.valueOf(obj.format("%02d/%02d", semana5, (mes+1)))+"/"+anio;
        }
        else
        {
            int primerDiaSigMes = 0;
            
            if(mes == 11)
            {
                mes = 0;
                anio = anio + 1;
            }
            else
            {
                mes = mes + 1;
            }
            
            for (int i = 0; i < 7; i++) {
                Calendar fechaPrimerMier = new GregorianCalendar(anio, (mes), i);
        
                fechaPrimerMier.add(Calendar.DATE, 1);
                int diaAct = fechaPrimerMier.get(Calendar.DAY_OF_WEEK);
                if(diaAct == 4)
                {
                    primerDiaSigMes = i+1;
                    break;
                }
            
        
            }
            
            fechaRetorno = ""+String.valueOf(obj.format("%02d/%02d", primerDiaSigMes, (mes+1)))+"/"+anio;
        }
        
        //System.out.println("fechaRetorno: "+fechaRetorno);
    
        return fechaRetorno;
    }
      
}


