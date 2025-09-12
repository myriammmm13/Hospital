package Hospital.logic;

import Hospital.data.Data;
import jakarta.xml.bind.*;

import java.io.*;

public class XmlPersister {
    private String path;
    private static XmlPersister theInstance;

    public static XmlPersister instance() {
        if (theInstance == null) {
            theInstance = new XmlPersister("data.xml"); // nombre del archivo
        }
        return theInstance;
    }

    private XmlPersister(String p) {
        path = p;
    }

    public Data load() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Data.class);
        FileInputStream is = new FileInputStream(path);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Data result = (Data) unmarshaller.unmarshal(is);
        is.close();
        return result;
    }

    public void store(Data d) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Data.class);
        FileOutputStream os = new FileOutputStream(path);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(d, os);
        os.flush();
        os.close();
    }
}