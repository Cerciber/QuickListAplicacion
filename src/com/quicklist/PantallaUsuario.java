/*
 * PantallaUsuario.java
 *
 * version 1.0
 *
 * 05-Octubre-2015
 *
 * Copyright (c) 2014-2016 Cesar Torres, Andres Santana, Alejandra Sierra.
 * #750566 Analisis Y Desarrollo De Sistemas De Informacion (ADSI)
 * Servicio Nacional De Aprendizaje (SENA) Bogotá, Colombia
 * All rights reserved.
 *
 */

package com.quicklist;

import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Funcionario;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.quicklist.funciones.MoverObjeto;
import com.quicklist.funciones.Arreglo;
import com.quicklist.funciones.ConvertirConsulta;
import com.quicklist.funciones.EventosMenu;
import com.quicklist.funciones.AnimacionObjetos;
import com.quicklist.funciones.UbicarLista;
import com.quicklist.funciones.DarIcono;
import com.quicklist.funciones.DatosUsuario;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;


public final class PantallaUsuario extends javax.swing.JPanel {

    public String usuario;
    Statement declaracion;
    public Component[] objeto;
    public int velocidad=100;
    String retorno;
    String tipo;
    String nombrePantalla;
    public JTextArea[][] TextArea;
    public JLabel[][] label;
    public Component[][] componente;
    String[] ID;
    boolean mostrarBotones=true;
    JScrollPane[][] scrollPane;
    
    //menu de botones
    public PantallaUsuario(String tipo,String[] menu,String[] vinculo,String retorno,String nombrePantalla,String usuario,Statement declaracion,String[] ID) {
        
        this.tipo=tipo;
        this.retorno=retorno;
        this.nombrePantalla=nombrePantalla;
        this.usuario=usuario;
        this.declaracion=declaracion;
        this.ID=ID;
        
        initComponents();
        new DatosUsuario(usuario,tipo,declaracion,jLabel1,jLabel2,jLabel3);
        PantallaUsuario.this.crearMenu(menu,vinculo);
        new MoverObjeto(jPanel8);
        
        
    }
    
    //menu de texto con botones
    public PantallaUsuario(String tipo,String[][] menu,String[] nombreBoton, String[] nombreIcono,String[] nombreColumna,String[] vinculo,String retorno,String nombrePantalla,String usuario,Statement declaracion,String[] ID) {
        
        this.tipo=tipo;
        this.retorno=retorno;
        this.nombrePantalla=nombrePantalla;
        this.usuario=usuario;
        this.declaracion=declaracion;
        this.ID=ID;
        
        initComponents();
        new DatosUsuario(usuario,tipo,declaracion,jLabel1,jLabel2,jLabel3);
        crearMenu(menu,vinculo,nombreBoton,nombreIcono,nombreColumna);
        new MoverObjeto(jPanel8);
        
    }    
    
    public void movimientoInicial(){
        
        Component[] componentes=new Component[objeto.length+2];
        componentes[0]=jPanel2;
        componentes[1]=jPanel3;
        
        for(int i=2;i<=componentes.length-1;i++){
        
            componentes[i]=objeto[i-2];
            
        }
        
        new AnimacionObjetos().Izquierda(componentes, velocidad);
    
    }
    
    public void movimientoSecuencial(){
        
        new AnimacionObjetos().Izquierda(objeto, velocidad);
    
    }
    
    public void crearMenu(String[] menu,String[] vinculo){
        
            JButton[] boton = new JButton[menu.length];
            objeto = new Component[menu.length];

            for(int i=0;i<=boton.length-1;i++){

                boton[i] = new JButton();
                boton[i].setFont(jButton5.getFont());
                boton[i].setBackground(jButton5.getBackground());
                boton[i].setForeground(jButton5.getForeground());
                boton[i].setText(menu[i]);

                objeto[i]=boton[i];

            }

            new UbicarLista(jPanel8,boton);
            new EventosMenu(objeto,velocidad,boton,this,vinculo,nombrePantalla,tipo,usuario,declaracion,ID);

    }
    
    public void crearMenu2(String[][] menu, String[] vinculo, String[] nombreBoton){
        
            String[] ID = new String[menu.length]; 
            String[][] menu2 = new String[menu.length][menu[0].length-1];
            
            for(int i=0;i<=menu.length-1;i++){
            
                ID[i] = menu[i][0];
                
                for(int j=1;j<=menu[0].length-1;j++){

                    menu2[i][j-1]=menu[i][j];

                }
            }
            menu=menu2;  
        
            scrollPane = new JScrollPane[menu.length][menu[0].length];
            TextArea = new JTextArea[menu.length][menu[0].length];
            objeto = new Component[(menu.length*menu[0].length)+(menu.length*nombreBoton.length)];
            
            for(int i=0;i<=scrollPane.length-1;i++){

                for(int j=0;j<=scrollPane[i].length-1;j++){

                    scrollPane[i][j] = new JScrollPane();
                    scrollPane[i][j].setBorder(jScrollPane1.getBorder());
                    
                    TextArea[i][j] = new JTextArea();
                    
                    TextArea[i][j].setBackground(jTextArea1.getBackground());
                    TextArea[i][j].setColumns(jTextArea1.getColumns());
                    TextArea[i][j].setFont(jTextArea1.getFont());
                    TextArea[i][j].setForeground(jTextArea1.getForeground());
                    TextArea[i][j].setLineWrap(jTextArea1.getLineWrap());
                    TextArea[i][j].setRows(jTextArea1.getRows());
                    TextArea[i][j].setOpaque(true);
                    TextArea[i][j].setText(menu[i][j]);
                    TextArea[i][j].setWrapStyleWord(jTextArea1.getWrapStyleWord());
                    TextArea[i][j].setFocusable(false);
                    TextArea[i][j].setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);
                    scrollPane[i][j].setViewportView(TextArea[i][j]);
                    
                }

            }
            
            JButton[][] boton = new JButton[menu.length][nombreBoton.length];
            
            for(int i=0;i<=menu.length-1;i++){

                for(int j=0;j<=nombreBoton.length-1;j++){

                    boton[i][j] = new JButton();
                    boton[i][j].setFont(jButton6.getFont());
                    boton[i][j].setBackground(jButton6.getBackground());
                    boton[i][j].setForeground(jButton6.getForeground());
                    boton[i][j].setText(nombreBoton[j]);
                    
                }

            }
            
            
            new UbicarLista(jPanel8,scrollPane,boton);
            
            int cont=-1;
            
            for(int i=0;i<=scrollPane.length-1;i++){
                
                for(int j=scrollPane[i].length-1;j>=0;j--){

                    cont++;
                    objeto[cont]=scrollPane[i][j];
                    
                }
                
                for(int j=0;j<=nombreBoton.length-1;j++){

                    cont++;
                    objeto[cont]=boton[i][j];
                    
                }
            }
            
            new EventosMenu(objeto,velocidad,boton,this.ID,ID,this,vinculo,nombrePantalla,tipo,usuario,declaracion);

    }
    
    public void crearMenu(String[][] menu, String[] vinculo, String[] nombreBoton,String[] nombreIcono, String[] nombreColumna){
        
            String[] ID = new String[menu.length]; 
            String[][] menu2;
            
            try{
            
                menu2 = new String[menu.length][menu[0].length-1];
            
            }catch(ArrayIndexOutOfBoundsException ex){
            
                menu2 = new String[1][1];
                menu2[0][0]="No existen registros para mostrar";
                mostrarBotones=false;
                
            }
            
            
            for(int i=0;i<=menu.length-1;i++){
            
                ID[i] = menu[i][0];
                
                for(int j=1;j<=menu[0].length-1;j++){

                    menu2[i][j-1]=menu[i][j];

                }
            }
            menu=menu2;  
        
            label = new JLabel[menu.length][menu[0].length];
            scrollPane = new JScrollPane[menu.length][menu[0].length];
            TextArea = new JTextArea[menu.length][menu[0].length];
            componente = new Component[menu.length][menu[0].length];
            objeto = new Component[(menu.length*menu[0].length)+(menu.length*nombreBoton.length)];
            
            for(int i=0;i<=label.length-1;i++){

                for(int j=0;j<=label[i].length-1;j++){
                    
                    if(menu[i][j].length()<30){
                    
                        label[i][j] = new JLabel();

                        label[i][j].setBackground(new java.awt.Color(204, 255, 255));
                        label[i][j].setFont(jTextArea1.getFont());
                        label[i][j].setForeground(new java.awt.Color(0, 102, 102));
                        label[i][j].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        label[i][j].setText(menu[i][j]);
                        label[i][j].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                        label[i][j].setOpaque(true);
                        componente[i][j]=label[i][j];
                        
                    }else{
                        
                        scrollPane[i][j] = new JScrollPane();
                        scrollPane[i][j].setBorder(jScrollPane1.getBorder());

                        TextArea[i][j] = new JTextArea();

                        TextArea[i][j].setBackground(jTextArea1.getBackground());
                        TextArea[i][j].setColumns(jTextArea1.getColumns());
                        TextArea[i][j].setFont(jTextArea1.getFont());
                        TextArea[i][j].setForeground(jTextArea1.getForeground());
                        TextArea[i][j].setLineWrap(jTextArea1.getLineWrap());
                        TextArea[i][j].setRows(jTextArea1.getRows());
                        TextArea[i][j].setOpaque(true);
                        TextArea[i][j].setText(menu[i][j]);
                        TextArea[i][j].setWrapStyleWord(jTextArea1.getWrapStyleWord());
                        TextArea[i][j].setFocusable(false);
                        TextArea[i][j].setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);
                        
                        scrollPane[i][j].setMinimumSize(new Dimension(100,100));
                        scrollPane[i][j].setViewportView(TextArea[i][j]);
                        componente[i][j]=scrollPane[i][j];
                        
                    }
                    
                    

                }

            }
            
            JButton[][] boton = new JButton[menu.length][nombreBoton.length];
            JButton[] columna = new JButton [menu[0].length+nombreBoton.length];
            
            for(int i=0;i<=menu.length-1;i++){

                for(int j=0;j<=nombreBoton.length-1;j++){

                    boton[i][j] = new JButton();
                    boton[i][j].setFont(jButton6.getFont());
                    boton[i][j].setBackground(jButton6.getBackground());
                    boton[i][j].setForeground(jButton6.getForeground());
                    DarIcono.darIcono(boton[i][j],nombreIcono[j]);
                    boton[i][j].setVisible(mostrarBotones);
                    boton[i][j].setToolTipText(nombreBoton[j]);
                    
                }

            }
            
            for(int i=0;i<=columna.length-1;i++){
            
                columna[i] = new JButton();
                columna[i].setBackground(new java.awt.Color(0, 102, 102));
                columna[i].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24));
                columna[i].setForeground(new java.awt.Color(204, 255, 255));
                columna[i].setAutoscrolls(true);
                columna[i].setOpaque(false); 
                columna[i].setText(nombreColumna[i]);
                columna[i].setVisible(mostrarBotones);
                
                if("".equals(nombreColumna[i])){
                    columna[i].setBackground(new java.awt.Color(190, 255, 255));
                    columna[i].setFocusable(false);
                }

            }
            
            new UbicarLista(jPanel8,componente,boton,columna);
            
            int cont=-1;
            
            for(int i=0;i<=componente.length-1;i++){
                
                for(int j=componente[i].length-1;j>=0;j--){

                    cont++;
                    objeto[cont]=componente[i][j];
                    
                }
                
                for(int j=0;j<=nombreBoton.length-1;j++){

                    cont++;
                    objeto[cont]=boton[i][j];
                    
                }
            }
            
            objeto = new Component[1];
            objeto[0]=jPanel8;

            new EventosMenu(objeto,velocidad,boton,this.ID,ID,this,vinculo,nombrePantalla,tipo,usuario,declaracion);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(240, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setOpaque(false);
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Nombre Apellido");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setText("N° cedula");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        jButton8.setBackground(new java.awt.Color(0, 102, 102));
        jButton8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(204, 255, 255));
        jButton8.setText("Editar mis datos");
        jButton8.setAutoscrolls(true);
        jButton8.setOpaque(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 130, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(557, 70));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jButton3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 102));
        jButton3.setText("Volver");
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jButton4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 102, 102));
        jButton4.setText("Salir");
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("☼");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("?");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jPanel4.add(jButton2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(302, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.setOpaque(false);

        jPanel8.setOpaque(false);

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 255, 255));
        jButton5.setText("Texto");
        jButton5.setAutoscrolls(true);
        jButton5.setOpaque(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(204, 255, 255));
        jLabel15.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Realizar el informe de requerimientos");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(204, 255, 255));
        jButton6.setText("Texto");
        jButton6.setAutoscrolls(true);
        jButton6.setOpaque(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextArea1.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 102, 102));
        jTextArea1.setLineWrap(true);
        jTextArea1.setTabSize(0);
        jTextArea1.setText("Realizar hjhkjhkjhkjhkjhkjhkjhkjhkjhkjhkj el informe de requerimientos teniendo en cuenta cada uno de  los asepectos descritos en las normas IEEE");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));
        jPanel9.setLayout(new java.awt.CardLayout());

        jRadioButton1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 204, 204));
        jRadioButton1.setText("Opcion");
        jRadioButton1.setToolTipText("");
        jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jRadioButton1, "card2");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6)
                            .addComponent(jButton5))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        new AnimacionObjetos().RIzquierda(objeto, velocidad,this,"EditarMisDatos",nombrePantalla,tipo,usuario,ID,declaracion);
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        if("PantallaInicio".equals(retorno)){
        
            jButton4ActionPerformed(evt);
            
        }else{
        
            new AnimacionObjetos().RIzquierda(objeto, velocidad,this,retorno,nombrePantalla,tipo,usuario,Arreglo.quitar(ID),declaracion);
        
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  
        Component[] componentes=new Component[objeto.length+2];
        componentes[0]=jPanel2;
        componentes[1]=jPanel3;
        
        for(int i=2;i<=componentes.length-1;i++){
        
            componentes[i]=objeto[i-2];
        }
        
        new AnimacionObjetos().RIzquierda(componentes, velocidad,this,"PantallaInicio",nombrePantalla,tipo,usuario,null,declaracion);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        
        Foto foto = new Foto(jLabel3,declaracion,usuario,tipo);
        foto.setLocationRelativeTo(null);
        foto.setVisible(true);
        
    }//GEN-LAST:event_jPanel6MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}