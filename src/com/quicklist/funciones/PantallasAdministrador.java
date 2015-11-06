/*
 * PantallasAdministrador.java
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

package com.quicklist.funciones;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.AprobarActividades;
import com.quicklist.clases.Actividad;
import com.quicklist.clases.ActividadDeAprendizaje;
import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Competencia;
import com.quicklist.clases.Ficha;
import com.quicklist.clases.Formacion;
import com.quicklist.clases.Funcionario;
import com.quicklist.clases.Horario;
import com.quicklist.clases.PlanDeEstudios;
import com.quicklist.clases.ResultadoDeAprendizaje;
import com.quicklist.Confirmacion;
import com.quicklist.FormActividad;
import com.quicklist.FormActividadDeAprendizaje;
import com.quicklist.FormAprendiz;
import com.quicklist.FormCompetencia;
import com.quicklist.FormFicha;
import com.quicklist.FormFormacion;
import com.quicklist.FormFuncionarios;
import com.quicklist.FormHorario;
import com.quicklist.FormPlanDeEstudios;
import com.quicklist.FormResultadoDeAprendizaje;
import com.quicklist.PantallaInicio;
import com.quicklist.PantallaUsuario;
import com.quicklist.TomarAsistencia;

/**
 *
 * @author pabloycesar
 */
public class PantallasAdministrador {
    
    int usuario;
    ResultSet resultado;
    
    public PantallasAdministrador(String tipo,JPanel panelContenedor, String nombreClase,String pantallaActual, String usuario ,String[] ID,Statement declaracion){
    
        try {
            
            if("EditarMisDatos".equals(nombreClase)){
                
                FormFuncionarios p = new FormFuncionarios(tipo,pantallaActual,nombreClase,usuario,Arreglo.agregar(ID, usuario),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("T_Administrador".equals(nombreClase)){

                
                String[] menu={"Funcionarios","Fichas","Plan de estudios"};
                String[] vinculo={"Administrador.Funcionarios","Administrador.Fichas","Administrador.PlanDeEstudios"};
                String retorno="PantallaInicio";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,"Administrador",usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoInicial();

            }
            
            if("Administrador".equals(nombreClase)){

                
                String[] menu={"Funcionarios","Fichas","Plan de estudios"};
                String[] vinculo={"Administrador.Funcionarios","Administrador.Fichas","Administrador.PlanDeEstudios"};
                String retorno="PantallaInicio";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Funcionarios".equals(nombreClase)){

                
                String[] menu={"Ver Funcionarios","Ingresar Funcionario"};
                String[] vinculo={"Administrador.Funcionarios.Ver","Administrador.Funcionarios.Ingresar"};
                String retorno="Administrador";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Funcionarios.Ver".equals(nombreClase)){

                String[][] menu=Funcionario.SeleccionarTodos(declaracion);
                String[] nombreBotones={"Editar","Borrar"};
                String[] nombreIcono={"Editar Usuario","Borrar Usuario"};
                String[] columna={"","","Documento","Contrasena","Nombre","Primer Apellido","Segundo Apellido","cargo","Correo Electronico","Telefono","Celular"};
                String[] vinculo={"Administrador.Funcionarios.Ver.Editar","Administrador.Funcionarios.Ver.Borrar"};
                String retorno="Administrador.Funcionarios";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }
            
            if("Administrador.Funcionarios.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.Funcionarios.Ver";
                
                FormFuncionarios p = new FormFuncionarios(tipo,retorno,pantallaActual,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Funcionarios.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.Funcionarios.Ver.Borrar.Confirmado","Administrador.Funcionarios.Ver"};
                String retorno="Administrador.Funcionarios.Ver";
                String pregunta="¿Desea eliminar el registro "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Funcionarios.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.Funcionarios.Ver.Borrar.Confirmado.Confirmado","Administrador.Funcionarios.Ver"};
                String retorno="Administrador.Funcionarios.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a este usuario";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Funcionarios.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                if(ID[ID.length-1].equals(usuario)){
                
                    String[] vinculo={"Administrador.Funcionarios.Ver.Borrar.Confirmado.Confirmado.BorrarMiCuanta","Administrador.Funcionarios.Ver"};
                    String retorno="Administrador.Funcionarios.Ver";
                    String pregunta="Esta apunto de borrar su cuenta";

                    Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                    panelContenedor.removeAll();
                    panelContenedor.add(p);
                    panelContenedor.validate();
                    p.movimiento();
                
                }else{
                
                    Funcionario.BorrarEnDocumento(declaracion,ID[ID.length-1]);
                    
                    String[] nombreBotones={"Editar","Borrar"};
                    String[] nombreIcono={"Editar Usuario","Borrar Usuario"};
                    String[] columna={"","","Documento_De_Identidad","Documento_De_Identidad","Contrasena","Nombre","Primer_Apellido","Segundo_Apellido","cargo","Correo_Electronico","Telefono_Fijo","Telefono_Celular"};
                    String[][] menu=Funcionario.SeleccionarTodos(declaracion);
                    String[] vinculo={"Administrador.Funcionarios.Ver.Editar","Administrador.Funcionarios.Ver.Borrar"};
                    String retorno="Administrador.Funcionarios";

                    PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,Arreglo.quitar(ID));
                    panelContenedor.removeAll();
                    panelContenedor.add(p);
                    panelContenedor.validate();
                    p.movimientoSecuencial();
                
                }


            }
            
            if("Administrador.Funcionarios.Ver.Borrar.Confirmado.Confirmado.BorrarMiCuanta".equals(nombreClase)){

                Funcionario.BorrarEnDocumento(declaracion, ID[ID.length-1]);
                
                PantallaInicio p = new PantallaInicio();
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.Funcionarios.Ingresar".equals(nombreClase)){

                
                String retorno="Administrador.Funcionarios";
                
                FormFuncionarios p = new FormFuncionarios(tipo,retorno,pantallaActual,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas".equals(nombreClase)){

                
                String[] menu={"Ver Fichas","Ingresar Fichas"};
                String[] vinculo={"Administrador.Fichas.Ver","Administrador.Fichas.Ingresar"};
                String retorno="Administrador";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Fichas.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar","Aprendices","Horarios"};
                String[] nombreIcono={"Editar","Borrar","Aprendices","Horarios"};
                String[] columna={"","","","","Ficha","Plan De Estudios","Fecha Inicio","Fecha Fin"};
                String[][] menu=Ficha.SeleccionarTodos(declaracion);
                String[] vinculo={"Administrador.Fichas.Ver.Editar","Administrador.Fichas.Ver.Borrar","Administrador.Fichas.Ver.Aprendices","Administrador.Fichas.Ver.Horarios"};
                String retorno="Administrador.Fichas";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }
            
            if("Administrador.Fichas.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.Fichas.Ver";
                
                FormFicha p = new FormFicha(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Borrar.Confirmado","Administrador.Fichas.Ver"};
                String retorno="Administrador.Fichas.Ver";
                String pregunta="¿Desea eliminar la ficha "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Borrar.Confirmado.Confirmado","Administrador.Fichas.Ver"};
                String retorno="Administrador.Fichas.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a esta ficha";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                Ficha.BorrarEnID(declaracion,ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.Fichas.Ver",pantallaActual,usuario,ID,declaracion);
                
            }
            
            if("Administrador.Fichas.Ingresar".equals(nombreClase)){

                
                String retorno="Administrador.Fichas";
                
                FormFicha p = new FormFicha(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Aprendices".equals(nombreClase)){

                                                                                
                String[] menu={"Ver Aprendices","Ingresar Aprendices"};
                String[] vinculo={"Administrador.Fichas.Ver.Aprendices.Ver","Administrador.Fichas.Ver.Aprendices.Ingresar"};
                String retorno="Administrador.Fichas.Ver";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Fichas.Ver.Aprendices.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar"};
                String[] nombreIcono={"Editar Usuario","Borrar Usuario"};
                String[] columna={"","","Documento","Contrasena","Nombre","Primer Apellido","Segundo Apellido","Fecha De Nacimiento","Correo Electronico","Genero","Ficha","Telefono","Celular","estado","Proyecto","Estilos Y Ritmos De Aprendizaje","Estrategia Metodológica De Preferencia","Caracteristicas Culturales Y Sociales"};
                String[][] menu=Aprendiz.SeleccionarPorFicha(declaracion,ID[ID.length-1]);
                String[] vinculo={"Administrador.Fichas.Ver.Aprendices.Ver.Editar","Administrador.Fichas.Ver.Aprendices.Ver.Borrar"};
                String retorno="Administrador.Fichas.Ver.Aprendices";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }
            
            if("Administrador.Fichas.Ver.Aprendices.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.Fichas.Ver.Aprendices.Ver";
                
                FormAprendiz p = new FormAprendiz(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Aprendices.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado","Administrador.Fichas.Ver.Aprendices.Ver"};
                String retorno="Administrador.Fichas.Ver.Aprendices.Ver";
                String pregunta="¿Desea eliminar el registro "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado.Confirmado","Administrador.Fichas.Ver.Aprendices.Ver"};
                String retorno="Administrador.Fichas.Ver.Aprendices.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a este usuario";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                Aprendiz.BorrarEnDocumento(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.Fichas.Ver.Aprendices.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);
            
            }
            
            if("Administrador.Fichas.Ver.Aprendices.Ingresar".equals(nombreClase)){

                
                String retorno="Administrador.Fichas.Ver.Aprendices";
                
                FormAprendiz p = new FormAprendiz(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios".equals(nombreClase)){

                
                String[] menu={"Ver Horarios","Ingresar Horarios"};
                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver","Administrador.Fichas.Ver.Horarios.Ingresar"};
                String retorno="Administrador.Fichas.Ver";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar","Actividades","Asistencia"};
                String[] nombreIcono={"Editar","Borrar","Actividades","Asistencia"};
                String[] columna={"","","","","instructor","Dia","Hora Inicio","Hora Fin","Fecha Inicio","Fecha Fin","Lugar","Resultado De Aprendizaje"};
                String[][] menu=Horario.SeleccionarPorFicha(declaracion, ID[ID.length-1]);
                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Editar","Administrador.Fichas.Ver.Horarios.Ver.Borrar","Administrador.Fichas.Ver.Horarios.Ver.Actividades","Administrador.Fichas.Ver.Horarios.Ver.Asistencia"};
                String retorno="Administrador.Fichas.Ver.Horarios";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.Fichas.Ver.Horarios.Ver";
                
                FormHorario p = new FormHorario(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado","Administrador.Fichas.Ver.Horarios.Ver"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver";
                String pregunta="¿Desea eliminar el horario "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado.Confirmado","Administrador.Fichas.Ver.Horarios.Ver"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a este Horario";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                Horario.BorrarPorID(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.Fichas.Ver.Horarios.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ingresar".equals(nombreClase)){
                
                String retorno="Administrador.Fichas.Ver.Horarios";
                
                FormHorario p = new FormHorario(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades".equals(nombreClase)){

                String[] menu={"Ver Actividades","Ingresar Actividad"};
                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver","Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ingresar"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

                

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar","Aprobar"};
                String[] nombreIcono={"Editar","Borrar","Aprobar"};
                String[] columna={"","","","Nombre Actividad","Nombre Evidencia","Medio","Tipo","Fecha RecoleccionEvidencia"};
                String[][] menu=Actividad.SeleccionarPorHorario(declaracion, ID[ID.length-1]);
                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Editar","Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar","Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Aprobar"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Actividades";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";
                
                FormActividad p = new FormActividad(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado","Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";
                String pregunta="¿Desea eliminar la Actividad "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado.Confirmado","Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a este usuario";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                Actividad.BorrarPorID(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);
            
            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ingresar".equals(nombreClase)){
                
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Actividades";
                
                FormActividad p = new FormActividad(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Aprobar".equals(nombreClase)){
                
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";
                
                AprobarActividades p = new AprobarActividades(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia".equals(nombreClase)){

                String[] menu={"Ver Asistencias","Tomar Asistencia"};
                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver","Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar"};
                String[] nombreIcono={"Editar","Borrar"};
                String[] columna={"","","Fecha"};
                String[][] menu=Formacion.SeleccionarPorHorario(declaracion, ID[ID.length-1]);
                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar","Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Asistencia";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";
                String vinculo="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar.Siguiente";
                
                FormFormacion p = new FormFormacion(tipo,vinculo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar.Siguiente".equals(nombreClase)){
                
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar";
                String vinculo="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";
                
                TomarAsistencia p = new TomarAsistencia(tipo,vinculo,retorno,nombreClase,usuario,(ID),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar.Confirmado","Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver"};
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";
                String pregunta="¿Desea eliminar la Formación "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar.Confirmado".equals(nombreClase)){

                Formacion.BorrarPorID(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);
            

            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar".equals(nombreClase)){
                
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Asistencia";
                String vinculo="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar.Siguiente";
                
                FormFormacion p = new FormFormacion(tipo,vinculo,retorno,nombreClase,usuario,Arreglo.agregar(Arreglo.quitar(ID),"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar.Siguiente".equals(nombreClase)){
                
                String retorno="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar";
                String vinculo="Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";
                
                TomarAsistencia p = new TomarAsistencia(tipo,vinculo,retorno,nombreClase,usuario,(ID),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.PlanDeEstudios".equals(nombreClase)){

                
                String[] menu={"Ver Plan De Estudios","Ingresar Plan De Estudios"};
                String[] vinculo={"Administrador.PlanDeEstudios.Ver","Administrador.PlanDeEstudios.Ingresar"};
                String retorno="Administrador";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if("Administrador.PlanDeEstudios.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar","Competencias"};
                String[] nombreIcono={"Editar","Borrar","Competencias"};
                String[] columna={"","","","Nombre","Programa","Version","Meses Etapa Lectiva","Nivel De Formacion"};
                String[][] menu=PlanDeEstudios.SeleccionarTodos(declaracion);
                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Editar","Administrador.PlanDeEstudios.Ver.Borrar","Administrador.PlanDeEstudios.Ver.Competencia"};
                String retorno="Administrador.PlanDeEstudios";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                

            }
            
            if("Administrador.PlanDeEstudios.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.PlanDeEstudios.Ver";
                
                FormPlanDeEstudios p = new FormPlanDeEstudios(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Borrar.Confirmado","Administrador.PlanDeEstudios.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver";
                String pregunta="¿Desea eliminar el Plan De Estudios "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Borrar.Confirmado.Confirmado","Administrador.PlanDeEstudios.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a este Plan De Estudios";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                PlanDeEstudios.BorrarEnID(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.PlanDeEstudios.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Administrador.PlanDeEstudios.Ingresar".equals(nombreClase)){
                
                String retorno="Administrador.PlanDeEstudios";
                
                FormPlanDeEstudios p = new FormPlanDeEstudios(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia".equals(nombreClase)){

                String[] menu={"Ver Competencias","Ingresar Competencia"};
                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver","Administrador.PlanDeEstudios.Ver.Competencia.Ingresar"};
                String retorno="Administrador.PlanDeEstudios.Ver";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar","Actividades De Aprendizaje"};
                String[] nombreIcono={"Editar","Borrar","Actividades De Aprendizaje"};
                String[] columna={"","","","Competencia_A_Desarrollar"};
                String[][] menu=Competencia.SeleccionarPorPlanDeEstudios(declaracion,ID[ID.length-1]);
                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.Editar","Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver";
                
                FormCompetencia p = new FormCompetencia(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado","Administrador.PlanDeEstudios.Ver.Competencia.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver";
                String pregunta="¿Desea eliminar la Competencia "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado.Confirmado","Administrador.PlanDeEstudios.Ver.Competencia.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a esta competencia";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                Competencia.BorrarEnID(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.PlanDeEstudios.Ver.Competencia.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ingresar".equals(nombreClase)){
                
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia";
                
                FormCompetencia p = new FormCompetencia(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }

            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje".equals(nombreClase)){

                String[] menu={"Ver Actividades De Aprendizaje","Ingresar Actividad De Aprendizaje"};
                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ingresar"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar","Resultado De Aprendizaje"};
                String[] nombreIcono={"Editar","Borrar","Resultado De Aprendizaje"};
                String[] columna={"","","","Fase Del Proyecto","Actividad De Aprendizaje"};
                String[][] menu=ActividadDeAprendizaje.SeleccionarPorCompetencia(declaracion,ID[ID.length-1]);
                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Editar","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";
                
                FormActividadDeAprendizaje p = new FormActividadDeAprendizaje(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";
                String pregunta="¿Desea eliminar la Actividad De Aprendizaje "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado.Confirmado","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a esta competencia";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                ActividadDeAprendizaje.BorrarEnID(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ingresar".equals(nombreClase)){
                
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje";
                
                FormActividadDeAprendizaje p = new FormActividadDeAprendizaje(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje".equals(nombreClase)){

                String[] menu={"Ver Resultados De Aprendizaje","Ingresar Resultado De Aprendizaje"};
                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ingresar"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar"};
                String[] nombreIcono={"Editar","Borrar"};
                String[] columna={"","","Resultado_De_Aprendizaje"};
                String[][] menu=ResultadoDeAprendizaje.SeleccionarPorActividadDeAprendizaje(declaracion,ID[ID.length-1]);
                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Editar","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Editar".equals(nombreClase)){

                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver";
                
                FormResultadoDeAprendizaje p = new FormResultadoDeAprendizaje(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver";
                String pregunta="¿Desea eliminar el Resultado De Aprendizaje "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado.Confirmado","Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver"};
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a esta competencia";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                ResultadoDeAprendizaje.BorrarEnID(declaracion, ID[ID.length-1]);
                new PantallasAdministrador(tipo,panelContenedor,"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver",pantallaActual,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ingresar".equals(nombreClase)){
                
                String retorno="Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje";
                
                FormResultadoDeAprendizaje p = new FormResultadoDeAprendizaje(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }

        } catch (NullPointerException ex) {System.out.println(ex);}
    
    }
    
    
}