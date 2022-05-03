package integration;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.testng.AssertJUnit.assertTrue;

public class IntegrationTesting {
    Service service;

    @BeforeMethod
    public void setUp(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddStudent(){

        int result = service.saveStudent("10", "Eduard Lungu", 934);

        assertTrue(result == 1);
    }

    @Test
    public void testAddAssignment(){
        int result = service.saveTema("4", "Tema 1", 5, 2);

        assertTrue(result == 1);
    }

    @Test
    public void testAddGrade(){
        int result = service.saveNota("2", "2", 10.00, 7, "feedback");

        assertTrue(result == 1);
    }

    @Test
    public void testAll(){
        int resultAddStudent = service.saveStudent("10", "Eduard Lungu", 934);
        int resultAddAssignment = service.saveTema("4", "Tema 1", 5, 2);
        int resultAddGrade = service.saveNota("10", "4", 10.00, 7, "feedback");

        assertTrue((resultAddStudent == 1) && (resultAddAssignment == 1) && (resultAddGrade == 1));
    }
}
