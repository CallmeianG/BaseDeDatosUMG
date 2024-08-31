package com.edu.umg.bdd;

import com.edu.umg.DTO.PersonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DMLBdd {

    private static final Connection connection = ConnectionBdd.getConnection();

    // Constructor
    public DMLBdd() {
    }

    public static List<PersonaDTO> listaPersona() throws SQLException {
        List<PersonaDTO> list = new ArrayList<>();
        if (connection != null) {
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = connection.createStatement();
                String query = "SELECT * FROM persona";
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PersonaDTO per = new PersonaDTO();
                    per.setIdPersona(rs.getInt("id_persona"));
                    per.setNombre(rs.getString("nombre"));
                    per.setApellido(rs.getString("apellido"));
                    per.setTelefono(rs.getInt("telefono"));
                    per.setCorreo(rs.getString("correo"));
                    per.setEstado(rs.getInt("estado"));
                    list.add(per);
                }
            } catch (SQLException ex) {
                System.out.println("Error al realizar la consulta: " + ex);
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                // No cerramos la conexión aquí
            }
        }
        return list;
    }

    public void agrgarPersona(PersonaDTO pPersona) {
        if (connection != null) {
            PreparedStatement pstm = null;
            try {
                String insert = "INSERT INTO persona (nombre, apellido, telefono, correo) VALUES (?, ?, ?, ?)";
                pstm = connection.prepareStatement(insert);
                pstm.setString(1, pPersona.getNombre());
                pstm.setString(2, pPersona.getApellido());
                pstm.setInt(3, pPersona.getTelefono());
                pstm.setString(4, pPersona.getCorreo());

                int rowsInserted = pstm.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Se insertó el dato correctamente...");
                }
            } catch (SQLException ex) {
                System.out.println("Error al ejecutar el insert: " + ex);
            } finally {
                try {
                    if (pstm != null) pstm.close();
                    // No cerramos la conexión aquí
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar el PreparedStatement: " + ex);
                }
            }
        }
    }
    
public void eliminarPersona(int idPersona) {
    if (connection != null) {
        try {
            String delete = "DELETE FROM persona WHERE id_persona = ?";
            PreparedStatement pstm = connection.prepareCall(delete);
            pstm.setInt(1, idPersona);
            int rowsDeleted = pstm.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Persona eliminada correctamente...");
            }
            pstm.close();
        } catch (Exception ex) {
            System.out.println("Error al ejecutar la eliminación. " + ex);
        }
    }
}


    
    
public void modificarPersona(PersonaDTO pPersona) {
    if (connection != null) {
        try {
            String update = "UPDATE persona SET correo = ?, telefono = ?, estado = ? WHERE id_persona = ?";
            PreparedStatement pstm = connection.prepareStatement(update);
            pstm.setString(1, pPersona.getCorreo());
            pstm.setInt(2, pPersona.getTelefono());  // Solo usa .intValue() si telefono es un Integer, de lo contrario elimina .intValue()
            pstm.setInt(3, pPersona.getEstado());    // Solo usa .intValue() si estado es un Integer, de lo contrario elimina .intValue()
            pstm.setInt(4, pPersona.getIdPersona());            // Asumiendo que id_persona es int o Integer
            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Persona modificada correctamente...");
            }
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la modificación. " + ex);
        }
        
        
        
    }
    
    
}




    
    
    
}
