package nl.hanze.week1;

import com.thoughtworks.xstream.XStream;

public class Main {
  public static void main(String[] args) {
    Student student1 = new Student();
        student1.setNaam("Test");
        student1.setGeslacht(false);
        student1.setLeeftijd(39);

        System.out.println(convertToXML(student1));

        Room room1 = new Room();
        room1.naam = "A245";
        String xmlOut = room1.getStudent("Michael");

        // start webservice
        WSPublisher.main(args);;
    }

    public static String convertToXML(Object input) {
        XStream xstream = new XStream();
        String output = xstream.toXML(input);
        return (output);
    }
}