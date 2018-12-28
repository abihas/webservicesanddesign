// opgave b
package nl.hanze.week1;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Room {
    public String naam;

    @WebMethod
    public String getStudent(String name) {
        Student st = new Student();
        st.setNaam(name);
        st.setLeeftijd(39);
        st.setGeslacht(true);

        return Main.convertToXML(st);
    }
}



