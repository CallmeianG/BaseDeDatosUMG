package com.edu.umg.bdd;

import com.edu.umg.DTO.Persona;
import com.edu.umg.config.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DMLBdd {

     private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Constructor
    public DMLBdd() {
    }

 public List<Persona> getAllPersonas() {
        Session session = null;
        List<Persona> students = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            students = session.createQuery("from Persona", Persona.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return students;
    }

     // Obtener una persona por ID
    public Persona getPersonatById(Long id) {
        Session session = null;
        Persona student = null;
        try {
            session = sessionFactory.openSession();
            student = session.get(Persona.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return student;
    }
    
    // Agregar un nueva persona
    public void addPersona(Persona persona) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
    
       // Actualizar una persona existente
    public void updatePersona(Persona persona) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
    
      // Eliminar una persona por ID
    public void deletePersona(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Persona persona = session.get(Persona.class, id);
            if (persona != null) {
                session.delete(persona);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
 
}
