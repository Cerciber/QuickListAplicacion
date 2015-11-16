/*
 * TomarAsistencia.java
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

import static com.quicklist.clases.Configuracion.cargarConfiguracion;
import java.awt.Component;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.quicklist.clases.Funcionario;
import com.quicklist.clases.Inasistencia;
import com.quicklist.funciones.MoverObjeto;
import com.quicklist.funciones.Arreglo;
import com.quicklist.funciones.AnimacionObjetos;
import com.quicklist.funciones.UbicarLista;


public final class TomarAsistencia extends javax.swing.JPanel {

    public String usuario;
    Statement declaracion;
    public Component[] objeto;
    public int velocidad=100;
    String vinculo;
    String retorno;
    String tipo;
    String[] ID;
    String[] ID_ResultadoDeAprendizaje;
    JComboBox[][] jComboBox;
    String[][] lista;
    String nombrePantalla;
    
    /**
     * Arreglo que contiene la configuración actual de la aplicación
     */
    int[] conf=cargarConfiguracion();
    
    //menu de botones
    public TomarAsistencia(String tipo,String vinculo,String retorno,String nombrePantalla,String usuario,String[] ID,Statement declaracion) {
        
        this.tipo=tipo;
        this.retorno=retorno;
        this.vinculo=vinculo;
        this.usuario=usuario;
        this.declaracion=declaracion;
        this.ID=ID;
        this.nombrePantalla=nombrePantalla;
        
        initComponents();
        datosUsuario();
        datosActividad(ID);
        /*Quitar el boton de edición de datos*/
        jButton8.setVisible(false);
        
        new MoverObjeto(jPanel8);
        
        
    }
    
    
    public void datosUsuario() {
        
        String[][] menu=Funcionario.SeleccionarDatosUsuario(declaracion, usuario);
        jLabel1.setText(menu[0][0]+" "+menu[0][1]);
        jLabel2.setText(menu[0][2]);
        
    }
    
    public void datosActividad(String[] ID) {
        
        lista = Inasistencia.SeleccionarPorFormacion(declaracion, ID[ID.length-1]);
        
        JButton[] columna = new JButton [6];
        JLabel[][] label = new JLabel[lista.length][3];
        jComboBox = new JComboBox[lista.length][2];
        Component[][] componente = new Component[lista.length][2+1];
        JButton[] excusa = new JButton [lista.length];
        
        for(int i=0;i<=columna.length-1;i++){
            
            columna[i] = new JButton();
            columna[i].setBackground(new java.awt.Color(0, 102, 102));
            columna[i].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[2]));
            columna[i].setForeground(new java.awt.Color(204, 255, 255));
            columna[i].setAutoscrolls(true);
            columna[i].setOpaque(false);  

        } 
        
        columna[0].setText("Documento");
        columna[1].setText("Nombre");
        columna[2].setText("Apellido");
        columna[3].setText("Estado");
        columna[4].setText("Justificacion");
        columna[5].setText("excusa");
        
        for(int i=0;i<=label.length-1;i++){
            for(int j=0;j<=label[i].length-1;j++){

                label[i][j] = new JLabel();
                label[i][j].setBackground(new java.awt.Color(204, 255, 255));
                label[i][j].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3])); // NOI18N
                label[i][j].setForeground(new java.awt.Color(0, 102, 102));
                label[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label[i][j].setText(lista[i][j+1]);
                label[i][j].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                label[i][j].setOpaque(true);

            }
        }
        
        for(int i=0;i<=jComboBox.length-1;i++){
            
            jComboBox[i][0] = new JComboBox();
            jComboBox[i][0].setBackground(new java.awt.Color(0, 153, 153));
            jComboBox[i][0].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
            jComboBox[i][0].setForeground(new java.awt.Color(255, 255, 255));
            jComboBox[i][0].setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO","CE", "SE" }));
            jComboBox[i][0].setSelectedItem(lista[i][5]);
            componente[i][0]=jComboBox[i][0];
            
            jComboBox[i][1] = new JComboBox();
            jComboBox[i][1].setBackground(new java.awt.Color(0, 153, 153));
            jComboBox[i][1].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[2]));
            jComboBox[i][1].setForeground(new java.awt.Color(255, 255, 255));
            jComboBox[i][1].setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
            jComboBox[i][1].setSelectedItem(lista[i][6]);
            componente[i][1]=jComboBox[i][1];
            
            excusa[i] = new JButton();
            excusa[i].setBackground(new java.awt.Color(0, 102, 102));
            excusa[i].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24));
            excusa[i].setForeground(new java.awt.Color(204, 255, 255));
            excusa[i].setAutoscrolls(true);
            excusa[i].setOpaque(false);  
            excusa[i].setText("Ver excusa");
            
            final String inasistencia=lista[i][0];
            final JComboBox jComboBox2 = jComboBox[i][1];
            
            excusa[i].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                     VerExcusa excusa = new VerExcusa(declaracion,inasistencia,jComboBox2);
                     excusa.setLocationRelativeTo(null);
                     excusa.setVisible(true);

                }  
            });
            
            componente[i][2]=excusa[i];
            
        }
        
        new UbicarLista(jPanel8,componente,label,columna);
        
    }
    
    public void movimiento(){
        
        Component[] objeto2={jPanel8};
        objeto=objeto2;
        new AnimacionObjetos().Izquierda(objeto, velocidad);
    
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
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

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

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
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
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(311, Short.MAX_VALUE))
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

        jPanel1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.setOpaque(false);

        jPanel8.setOpaque(false);

        jLabel15.setBackground(new java.awt.Color(153, 255, 255));
        jLabel15.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Documento");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);

        jButton5.setBackground(new java.awt.Color(0, 153, 153));
        jButton5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 255, 255));
        jButton5.setText("Guardar");
        jButton5.setAutoscrolls(true);
        jButton5.setOpaque(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox4.setBackground(new java.awt.Color(0, 153, 153));
        jComboBox4.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(455, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
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

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(557, 70));

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jButton7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 102, 102));
        jButton7.setText("Volver");
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton7);

        jButton10.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 102, 102));
        jButton10.setText("Salir");
        jButton10.setBorder(null);
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton10);

        jButton11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 102, 102));
        jButton11.setText("☼");
        jButton11.setBorder(null);
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton11);

        jButton12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 102, 102));
        jButton12.setText("?");
        jButton12.setBorder(null);
        jButton12.setContentAreaFilled(false);
        jPanel9.add(jButton12);

        jButton6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 102, 102));
        jButton6.setText("Guardar");
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(98, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        

        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        String[][] datos = new String[jComboBox.length][jComboBox[0].length+1];
        
        for(int i=0;i<=jComboBox.length-1;i++){
            
            for(int j=0;j<=jComboBox[0].length-1;j++){

                datos[i][j]=jComboBox[i][j].getSelectedItem().toString();

            }
            
            datos[i][2]=lista[i][0];
            
        }
        
        Inasistencia.ActualizarInasistencias(declaracion, datos);
        
        if("☺".equals(ID[ID.length-2])){
            
            new AnimacionObjetos().RIzquierda(objeto, velocidad,this,vinculo,nombrePantalla,tipo,usuario,Arreglo.agregar(Arreglo.quitar(Arreglo.quitar(ID))),declaracion);
        
        }else{
            
            new AnimacionObjetos().RIzquierda(objeto, velocidad,this,vinculo,nombrePantalla,tipo,usuario,Arreglo.quitar(Arreglo.quitar(ID)),declaracion);
        
        }
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        if("PantallaInicio".equals(retorno)){

            jButton10ActionPerformed(evt);

        }else{

            new AnimacionObjetos().RIzquierda(objeto, velocidad,this,retorno,nombrePantalla,tipo,usuario,Arreglo.quitar(ID),declaracion);

        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        Component[] componentes=new Component[objeto.length+2];
        componentes[0]=jPanel2;
        componentes[1]=jPanel3;

        for(int i=2;i<=componentes.length-1;i++){

            componentes[i]=objeto[i-2];
        }

        new AnimacionObjetos().RIzquierda(componentes, velocidad,this,"PantallaInicio",nombrePantalla,tipo,usuario,null,declaracion);

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        
        /* Se abre la ventana de configuración de la aplicación */
        Configuracion c = new Configuracion();  //Instanciación
        c.setSize(800, 600);    //Tamaño de ventana
        c.setLocationRelativeTo(null);      //Ubicar al centro
        c.setVisible(true);     //Dar visivilidad
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        
        Foto foto = new Foto(jLabel3,declaracion,usuario,tipo);
        foto.setLocationRelativeTo(null);
        foto.setVisible(true);
        
    }//GEN-LAST:event_jPanel6MousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed

        Foto foto = new Foto(jLabel3,declaracion,usuario,tipo);
        foto.setLocationRelativeTo(null);
        foto.setVisible(true);

    }//GEN-LAST:event_jLabel3MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
