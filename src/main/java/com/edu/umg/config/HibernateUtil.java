/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Ian
 */
public class HibernateUtil {
      private static SessionFactory sessionFactory;

public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
        try {
            Configuration configuration = new Configuration();
            
            // Configuración de Hibernate
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/clasedesarrollov2");
            configuration.setProperty("hibernate.connection.username","root");
            configuration.setProperty("hibernate.connection.password", "ian123#//YT");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            // Añadir la clase de entidad
            configuration.addAnnotatedClass(com.edu.umg.DTO.Persona.class);
          
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    return sessionFactory;
}


    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
