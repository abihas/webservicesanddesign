package nl.hanze.week1;

        import javax.xml.bind.annotation.XmlAccessType;
        import javax.xml.bind.annotation.XmlAccessorType;
        import javax.xml.bind.annotation.XmlElement;
        import javax.xml.bind.annotation.XmlRootElement;
        import javax.xml.bind.annotation.XmlType;
        // import com.thoughtworks.xstream.XStream;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={
        "naam",
        "leeftijd",
        "geslacht"
})
@XmlRootElement(name="student")

public class Student {
    // private XStream xstream = new XStream();

    @XmlElement(required=true)
    private String naam;
    @XmlElement(required=true)
    private int leeftijd;
    @XmlElement(required=true)
    private boolean geslacht;

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public int getleeftijd() {
        return leeftijd;
    }
    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }
    public boolean isGeslacht() {
        return geslacht;
    }
    public void setGeslacht(boolean geslacht) {
        this.geslacht = geslacht;
    }

    // protected String toXML() {
    protected String toXML() {
        //opgave 1a: Implementeer de methode toXML() in Student.
        // return xstream.toXML(this);
        // omdat bovenstaande niet werkt heb ik dit geimplementeerd als
        // een aparte methode binnen Main()
        return null;
    }
}

