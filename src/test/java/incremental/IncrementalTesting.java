package incremental;

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

import static org.testng.AssertJUnit.assertEquals;

public class IncrementalTesting {
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

        assertEquals(1, result);
    }

    @Test
    public void testAddStudentAndAddAssignment(){
        int result1 = service.saveStudent("13", "Popescu", 100);
        int result2 = service.saveTema("14", "Desc", 6, 4);

        assertEquals(2, result1 + result2);
    }

    @Test
    public void testAddStudentAndAddAssignmentAndAddGrade(){
        int result1 = service.saveStudent("15", "Ionescu", 563);
        int result2 = service.saveTema("16", "Ceva", 8, 5);
        int result3 = service.saveNota("15", "16", 9, 8, "Feedback");

        assertEquals(3, result1 + result2 + result3);
    }
}
