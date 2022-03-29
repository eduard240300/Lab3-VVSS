import domain.Nota;
import domain.Student;
import domain.Tema;
import org.testng.annotations.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TestAddAssignment {

    @Test
    public void testAddAssignmentSuccessfully(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        String idTema = "4";
        String descriere = "Tema 1";
        int deadline = 5;
        int startline = 2;
        int success = service.saveTema(idTema, descriere, deadline, startline);

        assertTrue(success != 0);
        Iterable<Tema> teme = service.findAllTeme();

        boolean temaGasita = false;
        Iterator<Tema> iteratorTeme = teme.iterator();

        do{
            Tema tema = iteratorTeme.next();
            if ((tema.getID().equals(idTema)) && (tema.getDescriere().equals(descriere))
                    && (tema.getDeadline() == deadline) && (tema.getStartline() == startline)) {
                temaGasita = true;
            }
        } while(iteratorTeme.hasNext());

        assertTrue(temaGasita);
    }

    @Test
    public void testAddInvalidAssignment(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        String idTema = "1";
        String descriere = "Tema 1";
        int deadline = 30;
        int startline = 2;
        int success = service.saveTema(idTema, descriere, deadline, startline);

        assertEquals(1, success);
    }

}
