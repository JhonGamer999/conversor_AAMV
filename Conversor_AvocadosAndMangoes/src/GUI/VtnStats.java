/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.FieldConfigure;
import Conexion.Conexion;
import static Conexion.Conexion.cargarArchivoProductos;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class VtnStats extends javax.swing.JFrame {

    /**
     * Creates new form VtnOrders
     */
    private VtnMain ppal;
    private VtnVisualizarStats visProducts;
    
    public VtnStats() {
        initComponents();
        this.setLocation(250, 80);
        this.setResizable(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnRegresar = new javax.swing.JToggleButton();
        lblAccion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnUploadClients = new javax.swing.JToggleButton();
        dirProducts = new javax.swing.JTextField();
        uploadClientsDB = new javax.swing.JToggleButton();
        listClients = new javax.swing.JToggleButton();
        exportClients = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        classClients = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/flecha.png"))); // NOI18N
        btnRegresar.setBorder(null);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblAccion.setText("Nada");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnUploadClients.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btnUploadClients.setText("Upload Products");
        btnUploadClients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnUploadClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadClientsActionPerformed(evt);
            }
        });

        dirProducts.setEditable(false);

        uploadClientsDB.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        uploadClientsDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/btnCargarBaseDatos.png"))); // NOI18N
        uploadClientsDB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        uploadClientsDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadClientsDBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(dirProducts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUploadClients, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(uploadClientsDB, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 224, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dirProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadClients, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(uploadClientsDB, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        listClients.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        listClients.setText("List Products");
        listClients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        listClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listClientsActionPerformed(evt);
            }
        });

        exportClients.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        exportClients.setText("Export Stats");
        exportClients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        exportClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportClientsActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("INFORMATION SYSTEM");

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("The Home Market Inc.");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/market_icon.png"))); // NOI18N

        classClients.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        classClients.setText("Upload products from orders");
        classClients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        classClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classClientsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(exportClients, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listClients, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(classClients)
                        .addGap(171, 171, 171))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 105, Short.MAX_VALUE)
                        .addComponent(lblAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(listClients, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exportClients, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classClients, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadClientsActionPerformed
        // TODO add your handling code here:
        String rutaOrdenes = "";
        JFileChooser jfile = new JFileChooser();
        jfile.showOpenDialog(this);
        File archivo = jfile.getSelectedFile();
        if ( archivo != null ){
            dirProducts.setText(archivo.getAbsolutePath());
            //rutaOrdenes = dirOrdenes.getText();
            
        }
    }//GEN-LAST:event_btnUploadClientsActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        ppal = new VtnMain();
        irA(ppal);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void listClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listClientsActionPerformed
        // TODO add your handling code here:
        visProducts = new VtnVisualizarStats();
        irA(visProducts);
    }//GEN-LAST:event_listClientsActionPerformed

    private void exportClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportClientsActionPerformed
        
        String rutaArchivo = "";
        String dia;
        String mes;
        String annio;
        
        Calendar c1 = Calendar.getInstance();
        dia = Integer.toString(c1.get(Calendar.DATE));
        mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
        annio = Integer.toString(c1.get(Calendar.YEAR));
        
        rutaArchivo = JOptionPane.showInputDialog(this, "Export path: ");
        
        rutaArchivo = rutaArchivo + "\\clientsExp"+dia+mes+annio+".csv";
        
        if(Conexion.escribirArchivoClientes(rutaArchivo) == 1)
            JOptionPane.showMessageDialog(this, "The file has been exported succesfully \n"+rutaArchivo, null, 1);
    }//GEN-LAST:event_exportClientsActionPerformed

    private void uploadClientsDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadClientsDBActionPerformed
        // TODO add your handling code here:
        
        String rutaOrdenes = dirProducts.getText();
        
        if(!rutaOrdenes.equalsIgnoreCase("")){
            Conexion.cargarArchivoProductos(rutaOrdenes);
        }
        
        dirProducts.setText("");
    }//GEN-LAST:event_uploadClientsDBActionPerformed

    private void classClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classClientsActionPerformed
        //Funcion para clasificar los clientes y registrar los resultados en la tabla de clasificacion
        String fecha = "";
        
        fecha = JOptionPane.showInputDialog(this, "Date: (dd/mm/yyyy)");
        
        Conexion.llenarTablaProductos(fecha);
        JOptionPane.showMessageDialog(this, "The products has been upload in a database\n", null, 1);
        
    }//GEN-LAST:event_classClientsActionPerformed

    public void irA(JFrame ventana){
        this.dispose();
        ventana.setVisible(true);
    }

    public void clasificarClientes(){
        
        Statement prepStatHO = null;
        ResultSet resSetHOrder = null;
        ResultSetMetaData rsMd = null;
        String sql = "";
        
        //Variables para adicionar en base de datos
        String   fechaEjecucion = "";
        String[] partesFecha;
        String   sqlHOrder = "";
        int      mesEjec;
        int      anio;
        int      mesMinimo;
        int      anioMinimo;
        int      mesHOrder;
        int      anioHOrder;
        String   telefono = "";
        String   SPcode = "";
        String   cliente = "";
        String   claseAnt = "";
        ArrayList<String[][]> vectorClientes;
        //Crea vectores con los meses de un año atras a partir del mes indicado en fechaEjecucion
        int      cantRegs;
        int      cantMesesMarcados;
        
        //Obtiene la fecha en formato dd/mm/aaaa
        fechaEjecucion = JOptionPane.showInputDialog(this, "Fecha de partida (dd/mm/aaaa)");
        
        //Separa los componentes de la fecha (partesFecha[0]/partesFecha[1]/partesFecha[2])
        partesFecha = fechaEjecucion.split("\\/");
        mesEjec = Integer.parseInt(partesFecha[1]);
        anio = Integer.parseInt(partesFecha[2]);
        
        anioMinimo = anio - 1; //El año anterior
        mesMinimo = mesEjec + 1; //12 meses atras
        
        //Si la fecha de partida es diciembre, el mes minimo se inicia en enero del mismo año
        if ( mesEjec == 12 )
            mesMinimo = 1;
        
        //Obtiene vector con los numeros de los clientes (PK)
        vectorClientes = obtenerVectorClientes();
        
        //Limpia la tabla para cargar las nuevas clasificaciones de clientes
        limpiarTablaClasesCliente();
        
        //Ingresa los datos de la clasificacion del cliente en la tabla de clases
        try {
            
            //Recorre vector de clientes
            for ( int j = 0; j < vectorClientes.size(); j++ )
            {
                String   claseNueva = "";
                String   shippingPhone = "";
                Conexion cnxClientes = new Conexion();
                int      vecMeses [] = new int[13];     //Pos 0 no se utiliza

                                    
                shippingPhone = vectorClientes.get(j)[0][0];
                
                prepStatHO = (Statement) cnxClientes.con.createStatement(); 
                
                sqlHOrder = "SELECT * FROM horders WHERE shippingPhone = '"+ shippingPhone +"'";
                System.out.println("shippingPhone "+shippingPhone);
                resSetHOrder = prepStatHO.executeQuery(sqlHOrder);
                while ( resSetHOrder.next() )
                {
                    String[] partesFechaHOrder = new String[3];
                    String   fechaHOrder = "";
                    
                    fechaHOrder = (resSetHOrder.getString("date"));
                    
                     //Separa los componentes de la fecha historial (partesFecha[0]/partesFecha[1]/partesFecha[2])
                    partesFechaHOrder = fechaHOrder.split("\\/");
                    mesHOrder = Integer.parseInt(partesFechaHOrder[1]);
                    anioHOrder = Integer.parseInt(partesFechaHOrder[2]);
                    //Si el año de la orden es igual al año de la ejecucion del proceso
                    if ( anioHOrder == anio )
                    {
                        if ( mesHOrder <= mesEjec  )
                        {
                            //System.out.println("mesHOrde "+mesHOrder+" anioHOrder "+ anioHOrder);
                            vecMeses[mesHOrder]= 1;
                        }
                    }
                    else if ( anioHOrder == anioMinimo )
                    {
                        if ( mesHOrder >= mesEjec  )
                        {
                            //System.out.println("mesHOrde "+mesHOrder+" anioHOrder "+ anioHOrder);
                            vecMeses[mesHOrder]= 1;
                        }
                    }
                }  
                
                cantRegs = 1;
                cantMesesMarcados = 0;
                claseNueva = "";
                
                //Analiza vector de meses para determinar su clase
                for ( int i = mesEjec; i > 0; i-- )
                {
                    
                    cantMesesMarcados += vecMeses[i];
                        
                    if ( (cantMesesMarcados == 3) && cantRegs == 3 )
                    {
                        System.out.println("Frequent");
                        claseNueva = "Frequent";
                        break;
                    }
                    
                    if ( (cantMesesMarcados >= 1) && cantRegs == 3 )
                    {
                        System.out.println("Recurrente");
                        claseNueva = "Recurrent";
                        break;
                    }
                    
                    if ( (cantMesesMarcados >= 1) && cantRegs > 3 && cantRegs <= 6 )
                    {
                        claseNueva = "Occasional (Semester)";
                        break;
                    }
                    
                    if ( (cantMesesMarcados >= 1) && cantRegs > 6 )
                    {
                        claseNueva = "Occasional (Yearly)";
                        break;
                    }
                    
                    //Si llega al inicio de los meses, pasa al fin del año anterior
                    if ( i == 1 )
                        i = 12;
                    
                    if ( mesMinimo == i )
                        break;
                    
                    cantRegs++;
                }
                
                //Si no encontro ventas en el último año, es cliente inactivo
                if ( cantMesesMarcados == 0 )
                    claseNueva = "Inactive";
                try{
                    
                    Statement prepStatHOPut = null;
                    ResultSet resSetHOrderPut = null;
                    claseAnt = vectorClientes.get(j)[0][1];
                    SPcode = vectorClientes.get(j)[0][2];
                    cliente = vectorClientes.get(j)[0][3];
                    
                    Conexion cnx = new Conexion();
                    
                    prepStatHOPut = (Statement) cnx.con.createStatement(); 

                    sql = "INSERT INTO class (shippingPhone, shopifyCode, name, prevClass, lastClass)"
                        + " VALUES ("
                        + "'"+shippingPhone+"',"
                        + "'"+SPcode+"',"
                        + "'"+cliente+"',"
                        + "'"+claseAnt+"',"
                        + "'"+claseNueva+"');";

                    System.out.println(sql);
                    
                    prepStatHOPut.executeUpdate(sql);

                    //Actualiza la clase del cliente 
                    actualizarClaseCliente(shippingPhone, claseNueva);
                    cnx.con.close();
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error trying to change the client classes: "+e, "Error", 0);
                    System.out.println(e.toString());
                }
                
                cnxClientes.con.close();
            }//For vector clientes
            
            //System.out.println(sql);
         
            JOptionPane.showMessageDialog(this, "The client classes has been updated in database", "Database", 1);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error trying to change the client classes: "+e, "Error", 0);
            System.out.println(e.toString());
        }
    }
    
    public ArrayList<String[][]> obtenerVectorClientes(){
        
        ArrayList<String[][]> vectorClientes = new ArrayList<>();
        
        Conexion cnx = new Conexion();
        Statement prepStat = null;
        ResultSet resSetClients = null;
        
        String   sqlClients = "";
        String   mtClient[][];  
        
        try
        {
            prepStat = (Statement) cnx.con.createStatement();
        
            sqlClients = "SELECT * FROM clients";
            resSetClients = prepStat.executeQuery(sqlClients);
        
            //Recorre cada uno de los clientes registrado en la tabla 'clients'
            while ( resSetClients.next() )
            {
                mtClient = new String[1][4];
                
                mtClient[0][0] = (resSetClients.getString("shippingPhone"));
                mtClient[0][1] = (resSetClients.getString("class"));
                mtClient[0][2] = (resSetClients.getString("shopifyCode"));
                mtClient[0][3] = (resSetClients.getString("name"));
                
                //Añade la matriz con telefono-clase al vector a retornar
                vectorClientes.add(mtClient);
            }
            
            cnx.con.close();
            
        }catch ( Exception e )
        {
            JOptionPane.showMessageDialog(this, "Error trying to change the client classes: "+e, "Error", 0);
            System.out.println(e.toString());
        }
        
        return vectorClientes;
    }
    
    public static void limpiarTablaClasesCliente(){
         Conexion cn = new Conexion();
         Statement st;
         ResultSet rs;
         PreparedStatement PS;
         try{
            PS = cn.con.prepareStatement("TRUNCATE TABLE class ");  
            PS.execute(); 
            PS.close();
            cn.con.close();
         }catch (Exception e){
              JOptionPane.showMessageDialog(null, "An error has occurred while the system was trying to clean the orders table");
         }
     }
    
    //Funcion para modificar en base de datos el registro seleccionado
    public void actualizarClaseCliente(String shippingPhone, String clase){
        
        Statement prepStat = null;
        Conexion cnx = new Conexion();
        String sql = "";
        
        try {
            
            prepStat = (Statement) cnx.con.createStatement(); 
            
            sql = "UPDATE clients SET "
                    + "class = '"+clase+"' "
                    + "WHERE shippingPhone = '"+shippingPhone+"'";
            
            //System.out.println(sql);
            
            prepStat.executeUpdate(sql);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error trying to update the client to database: "+e, "Error", 0);
            System.out.println(e.toString());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnRegresar;
    private javax.swing.JToggleButton btnUploadClients;
    private javax.swing.JToggleButton classClients;
    private javax.swing.JTextField dirProducts;
    private javax.swing.JToggleButton exportClients;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JLabel lblAccion;
    private javax.swing.JToggleButton listClients;
    private javax.swing.JToggleButton uploadClientsDB;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the lblAccion
     */
    public javax.swing.JLabel getLblAccion() {
        return lblAccion;
    }

    /**
     * @param lblAccion the lblAccion to set
     */
    public void setLblAccion(javax.swing.JLabel lblAccion) {
        this.lblAccion = lblAccion;
    }
}
