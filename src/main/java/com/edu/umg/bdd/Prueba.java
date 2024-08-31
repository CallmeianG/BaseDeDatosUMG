/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.bdd;

import com.edu.umg.DTO.PersonaDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mayela
 */
public class Prueba {
     public static void main(String[] args) throws SQLException {
        // Crear una instancia de la clase DMLBdd
        DMLBdd dml = new DMLBdd();
        
        // Obtener la lista de personas desde la base de datos
        List<PersonaDTO> personas = dml.listaPersona();
        
        // Imprimir los datos de cada persona
        for (PersonaDTO persona : personas) {
            System.out.println("ID: " + persona.getIdPersona());
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Apellido: " + persona.getApellido());
            System.out.println("Correo: " + persona.getCorreo());
    
    
    
            }
        
        }
}
