/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appGraficaCliente;

import capaLogica.Cine;
import capaLogica.Cliente;
import capaLogica.Pelicula;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author steve
 */
public class JDlgReservar extends javax.swing.JDialog {

    /**
     * Creates new form JDlgReservar
     */
    private Pelicula peliculaSeleccionada;
    private Cine cine;
    private static String asientosReservados = "";
    private static int totalTiquetes;
    private static int cantidadAsientosSeleccionados = 0;
    private static List<String> listaReservados = new ArrayList<String>();

    public JDlgReservar() {
        this.setLocationRelativeTo(null);
        cine = new Cine();
        cantidadAsientosSeleccionados = 0; //Resetea cada vez que yo instancio el jDialog
    }

    public JDlgReservar(java.awt.Frame parent, boolean modal, Pelicula pelicula, int totalTiquetes) {
        super(parent, modal);
        this.peliculaSeleccionada = pelicula;
        this.totalTiquetes = totalTiquetes;
        cantidadAsientosSeleccionados = 0;
        cine = new Cine();
        initComponents();
    }

    public static int getCantidadAsientosSeleccionados() {
        return cantidadAsientosSeleccionados;
    }

    public static String getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(String asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    public static int getTotalTiquetes() {
        return totalTiquetes;
    }

    //Características de los botones
    int filas = 10;
    int columnas = 8;
    int largoBoton = 100;
    int anchoBoton = 50;
    int ejeX = 110; // Espacio a la derecha
    int ejeY = 40; // Espacio abajo

    // Se crea la matrix de botones
    public JToggleButton[][] jTbotones = new JToggleButton[filas][columnas]; //Matriz de botones

    // Las filas son las que van hacia la derecha - horizontal 
    // Columnas son las que van a ir hacia abajo - vertical
    public void botones() { // Darle estilo a los botones y agregarlos al frame

        Font fuenteLetra = new Font("Arial", Font.BOLD, 14); // Edita la fuente, remarca con negrita y cambia el tamaño

        jTbotones = new JToggleButton[filas][columnas];

        for (int i = 0; i < filas; i++) { // Recorre filas
            for (int j = 0; j < columnas; j++) { // Recorre columnas

                jTbotones[i][j] = new JToggleButton(); // Se llena la matriz botones

                jTbotones[i][j].setBounds(ejeX, ejeY, largoBoton, anchoBoton);//Le doy las especificaciones a los botones

                jPnlBotones.add(jTbotones[i][j]); // Agregamos los botones al panel
                jTbotones[i][j].setFont(fuenteLetra); // Se le agrega las especificaciones de letra a los botones

                if (cine.estaDisponible(peliculaSeleccionada.getNumeroSala() - 1, i, j)) {
                    jTbotones[i][j].setBackground(Color.BLUE); // Color de asientos disponibles
                    jTbotones[i][j].setForeground(Color.WHITE); // Cambia el color a la fuente
                    jTbotones[i][j].setSelected(false); // Deselecciona

                } else {
                    jTbotones[i][j].setBackground(Color.RED); // Color de asientos disponibles
                    jTbotones[i][j].setForeground(Color.WHITE); // Cambia el color a la fuente
                    jTbotones[i][j].setEnabled(false); // Deshabilita los botones
                }
                jTbotones[i][j].setText(obtenerAsiento(i, j).replace("-", ""));
                AccionBotones accion = new AccionBotones(); // Se crea la instancia de AccionBotones
                jTbotones[i][j].addActionListener(accion); // Se agrega a los botones las acciones de la clase AccionBotones

                ejeX += 160; // Separación entre columnas

            }

            ejeX = 110; // Posición inicial de los botones
            ejeY += 70; // Separación de los botones en cuanto a filas

        }

    }

    public class AccionBotones implements ActionListener { // Se ejecuta cuando se le da click a uno de los botones

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {

                    if (jTbotones[i][j].isSelected()) {

                        // Cada vez que doy click, cuenta cada asiento seleccionado
                        // Obtiene atributos y hace la validación para que seleccione las misma de asientos que los tiquetes que compró
                        if (JDlgReservar.cantidadAsientosSeleccionados <= JDlgReservar.totalTiquetes) {

                            Cliente cliente = new Cliente();
                            cine.reservacion(peliculaSeleccionada.getNumeroSala() - 1, i, j, cliente);
                            jTbotones[i][j].setBackground(Color.RED);
                            jTbotones[i][j].setForeground(Color.BLACK);
                            asientosReservados += obtenerAsiento(i, j);
                        }
                        agregarSeleccion(jTbotones[i][j].getText());

                    } else if (cine.estaDisponible(peliculaSeleccionada.getNumeroSala() - 1, i, j)) {
                        removerSeleccion(jTbotones[i][j].getText(), i, j);
                        if (jTbotones[i][j].isEnabled()) {
                            jTbotones[i][j].setBackground(Color.BLUE);
                            jTbotones[i][j].setForeground(Color.WHITE);
                        }

                    } else {
                        removerSeleccion(jTbotones[i][j].getText(), i, j);
                        if (jTbotones[i][j].isEnabled()) {
                            jTbotones[i][j].setBackground(Color.BLUE);
                        }
                        eliminarAsiento(i, j);

                    }
                }
            }
        }

        public void agregarSeleccion(String seleccionado) {

            if (listaReservados.isEmpty()) {
                JDlgReservar.cantidadAsientosSeleccionados++;
                listaReservados.add(seleccionado);
                return;

            }

            for (int i = 0; i < listaReservados.size(); i++) {
                if (!listaReservados.contains(seleccionado)) {
                    JDlgReservar.cantidadAsientosSeleccionados++;
                    listaReservados.add(seleccionado);
                }
            }
        }

        public void removerSeleccion(String seleccionado, int fila, int columna) {
            if (listaReservados.contains(seleccionado)) {
                listaReservados.remove(seleccionado);
                cantidadAsientosSeleccionados--;
                cine.eliminarReservacion(peliculaSeleccionada.getNumeroSala() - 1, fila, columna);

            }

        }

    }

    private String obtenerAsiento(int fila, int columna) {
        String posiciones = "ABCDEFGHIJ";
        String posicion = posiciones.charAt(fila) + "" + (columna + 1) + " - ";

        if (asientosReservados.contains(posicion)) {
            return "";
        }
        return posicion;

    }

    private void eliminarAsiento(int fila, int columna) {
        String posiciones = "ABCDEFGHIJ";
        String posicion = posiciones.charAt(fila) + "" + (columna + 1) + " - ";
        if (asientosReservados.contains(posicion)) {
            asientosReservados = asientosReservados.replace(posiciones.charAt(fila) + "" + (columna + 1) + " - ", "");

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblSala = new javax.swing.JLabel();
        jPnlBotones = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLblCantidadTiquetes = new javax.swing.JLabel();
        jBtnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLblSala.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLblSala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblSala.setText("Seleccione la sala: ");

        jPnlBotones.setBackground(new java.awt.Color(0, 0, 0));
        jPnlBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPnlBotones.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("A");
        jLabel5.setAlignmentY(40.0F);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("B");
        jLabel6.setAlignmentY(40.0F);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("C");
        jLabel7.setAlignmentY(40.0F);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("D");
        jLabel8.setAlignmentY(40.0F);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("E");
        jLabel9.setAlignmentY(40.0F);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("F");
        jLabel11.setAlignmentY(40.0F);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("G");
        jLabel12.setAlignmentY(40.0F);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("H");
        jLabel13.setAlignmentY(40.0F);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("I");
        jLabel10.setAlignmentY(40.0F);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("J");
        jLabel14.setAlignmentY(40.0F);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("1");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("2");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("3");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("4");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("5");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("6");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("7");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("8");
        jLabel21.setToolTipText("");

        javax.swing.GroupLayout jPnlBotonesLayout = new javax.swing.GroupLayout(jPnlBotones);
        jPnlBotones.setLayout(jPnlBotonesLayout);
        jPnlBotonesLayout.setHorizontalGroup(
            jPnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBotonesLayout.createSequentialGroup()
                .addGroup(jPnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlBotonesLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel4)
                        .addGap(151, 151, 151)
                        .addComponent(jLabel15)
                        .addGap(150, 150, 150)
                        .addComponent(jLabel16)
                        .addGap(154, 154, 154)
                        .addComponent(jLabel17)
                        .addGap(146, 146, 146)
                        .addComponent(jLabel18)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel19)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel20)
                        .addGap(149, 149, 149)
                        .addComponent(jLabel21))
                    .addGroup(jPnlBotonesLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPnlBotonesLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlBotonesLayout.setVerticalGroup(
            jPnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(50, 50, 50)
                .addComponent(jLabel6)
                .addGap(45, 45, 45)
                .addComponent(jLabel7)
                .addGap(41, 41, 41)
                .addComponent(jLabel8)
                .addGap(44, 44, 44)
                .addComponent(jLabel9)
                .addGap(55, 55, 55)
                .addComponent(jLabel11)
                .addGap(50, 50, 50)
                .addComponent(jLabel12)
                .addGap(48, 48, 48)
                .addComponent(jLabel13)
                .addGap(52, 52, 52)
                .addComponent(jLabel10)
                .addGap(51, 51, 51)
                .addComponent(jLabel14)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Disponible");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Ocupado");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Seleccionado");

        jLblCantidadTiquetes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLblCantidadTiquetes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblCantidadTiquetes.setText("Por favor seleccione tiquetes");

        jBtnConfirmar.setText("Confirmar");
        jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLblSala, javax.swing.GroupLayout.DEFAULT_SIZE, 1491, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(48, 48, 48)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblCantidadTiquetes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1479, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(466, 466, 466))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLblSala)
                .addGap(5, 5, 5)
                .addComponent(jLblCantidadTiquetes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        if (peliculaSeleccionada != null) {
            jLblSala.setText("Seleccione los asientos para la pelicula: " + peliculaSeleccionada + "\n");
            botones();//Llama al método botones para que se visualice
            jLblCantidadTiquetes.setText("Por favor seleccione " + this.totalTiquetes + " asientos");
        }
    }//GEN-LAST:event_formWindowOpened

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
        if (cantidadAsientosSeleccionados > totalTiquetes) {
            JOptionPane.showMessageDialog(null, "Error, solo debe seleccionar " + this.totalTiquetes + " asientos", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            validarAsientos();
        }
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        validarAsientos();

    }//GEN-LAST:event_formWindowClosing
    // Valida que el usuario haya seleccionado asientos para cerrar la ventana

    public void validarAsientos() {
        if (asientosReservados.isEmpty() || cantidadAsientosSeleccionados < totalTiquetes) {
            JOptionPane.showMessageDialog(null, "Error, debe seleccionar " + totalTiquetes + " asientos para hacer la reservación", "Error", JOptionPane.ERROR_MESSAGE);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        } else {
            this.dispose();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDlgReservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgReservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgReservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgReservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgReservar dialog = new JDlgReservar(new javax.swing.JFrame(), true, null, 0);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblCantidadTiquetes;
    private javax.swing.JLabel jLblSala;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnlBotones;
    // End of variables declaration//GEN-END:variables
}
