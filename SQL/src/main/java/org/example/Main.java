package org.example;

import org.example.config.FactoryConfiguration;
import org.example.entity.Address;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        /*-------------------------INSERT-STUDENT------------------------*/
        NativeQuery insertStudentQuery = session.createNativeQuery("INSERT INTO Student (id, name) VALUES (:id, :name)");
        insertStudentQuery.setParameter("id", 1);
        insertStudentQuery.setParameter("name", "nawo");
        insertStudentQuery.executeUpdate();

        insertStudentQuery.setParameter("id", 2);
        insertStudentQuery.setParameter("name", "ima");
        insertStudentQuery.executeUpdate();

        System.out.println("Students Inserted!");

        /*-------------------------INSERT-Address------------------------*/
        NativeQuery insertAddressQuery = session.createNativeQuery("INSERT INTO Address (Aid, address1, address2, king) VALUES (:Aid, :address1, :address2, :king)");
        insertAddressQuery.setParameter("Aid", 01);
        insertAddressQuery.setParameter("address1", "serupita");
        insertAddressQuery.setParameter("address2", "kalutara");
        insertAddressQuery.setParameter("king", 1);
        insertAddressQuery.executeUpdate();

        insertAddressQuery.setParameter("Aid", 02);
        insertAddressQuery.setParameter("address1", "nigeria");
        insertAddressQuery.setParameter("address2", "colombo");
        insertAddressQuery.setParameter("king", 2);
        insertAddressQuery.executeUpdate();

        System.out.println("Addresses Inserted!");

        /*-------------------------SEARCHBY-ID-----------------------*/
        NativeQuery<Student> searchStudentByIdQuery = session.createNativeQuery("SELECT * FROM Student WHERE id = :id", Student.class);
        searchStudentByIdQuery.setParameter("id", 1);
        List<Student> studentList = searchStudentByIdQuery.list();

        for (Student student : studentList) {
            System.out.println("Found Student: " + student.getId() + " - " + student.getName());
        }


        NativeQuery<Address> searchAddressByIdQuery = session.createNativeQuery("SELECT * FROM Address WHERE Aid = :Aid", Address.class);
        searchAddressByIdQuery.setParameter("Aid", 01);
        List<Address> addressList = searchAddressByIdQuery.list();

        for (Address address : addressList) {
            System.out.println("Found Address: " + address.getAid() + " - " + address.getAddress1());
        }

        /*-------------------------UPDATE-STUDENT-----------------------*/
        NativeQuery updateStudentQuery = session.createNativeQuery("UPDATE Student SET name = :name WHERE id = :id");
        updateStudentQuery.setParameter("name", "chathu");
        updateStudentQuery.setParameter("id", 1);
        int studentUpdateResult = updateStudentQuery.executeUpdate();

        if (studentUpdateResult > 0) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }

        /*-------------------------UPDATE-ADDRESS-----------------------*/
        NativeQuery updateAddressQuery = session.createNativeQuery("UPDATE Address SET address1 = :address1 WHERE Aid = :Aid");
        updateAddressQuery.setParameter("address1", "bolossagama");
        updateAddressQuery.setParameter("Aid", 01);
        int addressUpdateResult = updateAddressQuery.executeUpdate();

        if (addressUpdateResult > 0) {
            System.out.println("Address updated successfully!");
        } else {
            System.out.println("Address not found!");
        }

        /*-------------------------DELETE-ADDRESS-----------------------*/
        NativeQuery deleteAddressQuery = session.createNativeQuery("DELETE FROM Address WHERE Aid = :Aid");
        deleteAddressQuery.setParameter("Aid", 02);
        int addressDeleteResult = deleteAddressQuery.executeUpdate();

        if (addressDeleteResult > 0) {
            System.out.println("Address deleted successfully!");
        } else {
            System.out.println("Address not found!");
        }


        /*-------------------------DELETE-STUDENT-----------------------*/
        NativeQuery deleteStudentQuery = session.createNativeQuery("DELETE FROM Student WHERE id = :id");
        deleteStudentQuery.setParameter("id", 2);
        int studentDeleteResult = deleteStudentQuery.executeUpdate();

        if (studentDeleteResult > 0) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }


        /*-------------------------JOIN-QUERY-----------------------*/
        NativeQuery<Object[]> joinQuery = session.createNativeQuery("SELECT s.id, s.name, a.Aid, a.address1 FROM Student s JOIN Address a ON s.id = a.king");
        List<Object[]> joinResultList = joinQuery.list();

        for (Object[] result : joinResultList) {
            System.out.println("king: " + result[0] + ", Name: " + result[1] + ", Address ID: " + result[2] + ", Address: " + result[3]);
        }

        transaction.commit();
        session.close();
    }
}
