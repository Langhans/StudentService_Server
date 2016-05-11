package outputWriter;

import static application.Settings.ERROR_XML_ERRORCONTENT_STUB;

import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import application.MainLogger;
import application.Settings;
import commons.Course;
import commons.Student;
import commons.StudentsRegistrations;

public class XmlWriter implements IContentWriter{


  @Override
  public String writeAllStudents(List<Student> students) throws WriterException {
    try{
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.newDocument();
    
    Element rootElement = doc.createElement(Settings.XML_TAG_STUDENTS);
    doc.appendChild(rootElement);
    
    for (Student s : students){
      Element student = doc.createElement(Settings.XML_TAG_STUDENT);
      student.setAttribute(Settings.XML_TAG_ATTR_ID ,String.valueOf(s.getId()));
      Element name = doc.createElement(Settings.XML_TAG_ATTR_NAME);
      name.appendChild(doc.createTextNode(s.getName()));
      student.appendChild(name);
      rootElement.appendChild(student);
    }
    
    TransformerFactory transFactory = TransformerFactory.newInstance();
    Transformer transformer = transFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    
    DOMSource source = new DOMSource(doc);
    StringWriter sw = new StringWriter();
    StreamResult result = new StreamResult(sw);
    transformer.transform(source, result);
    return sw.toString();
  }catch (ParserConfigurationException pce){
    MainLogger.getLogger().log(Level.SEVERE , "XMLWriter error i ParserConfiguration" , pce);
    throw new WriterException(pce.getMessage() , pce.getCause());  
  }catch (TransformerException  tfe) {
    MainLogger.getLogger().log(Level.SEVERE , "XMLWriter error i Transformer" , tfe);
    throw new WriterException(tfe.getMessage() , tfe.getCause());
  }
  }

  @Override
  public String writeAStudentsRegistrations(
      StudentsRegistrations registration) throws WriterException {
    try{
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      // WRITE STUDENT AS ATTRIBUTES
      Student student = registration.getStudent();
      Element rootElement = doc.createElement(Settings.XML_TAG_COURSES);
      rootElement.setAttribute(Settings.XML_TAG_ATTR_ID, String.valueOf(student.getId()));
      rootElement.setAttribute(Settings.XML_TAG_ATTR_NAME, student.getName());
      doc.appendChild(rootElement);
      // WRITE COURSES STUDENT HAS REGISTERED IN
      List<Course> courses = registration.getCourses();
      
      for (Course c : courses){
        Element cElem = doc.createElement(Settings.XML_TAG_COURSE);
        cElem.setAttribute(Settings.XML_TAG_ATTR_COURSEID, String.valueOf(c.getId()));
        cElem.appendChild(doc.createTextNode(c.getCourse_code()));
        rootElement.appendChild(cElem);
      }
      
      TransformerFactory trFactory = TransformerFactory.newInstance();
      Transformer transformer = trFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      
      DOMSource source = new DOMSource(doc);
      StringWriter sw = new StringWriter();
      StreamResult result = new StreamResult(sw);
      transformer.transform(source, result);
      return sw.toString();
    }  catch (TransformerException tfe){
      MainLogger.getLogger().log(Level.SEVERE , "XMLWriter error i Transformer" , tfe);
    throw new WriterException(tfe.getMessage() , tfe.getCause());
  } catch (ParserConfigurationException e){
    MainLogger.getLogger().log(Level.SEVERE , "XMLWriter error i ParserConfiguration" , e);
    throw new WriterException(e.getMessage() , e.getCause());  
    }
  }

  @Override
  public String writeErrorContent(String errorName, String errorMessage) {
    return String.format(ERROR_XML_ERRORCONTENT_STUB, errorName,
        errorMessage);
  }

}
