package dataprovider;

import dataprovider.xml.TestCase;
import dataprovider.xml.UserBase;
import dataprovider.xml.UserCredentials;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataProviderXML {

    private static final Path XML_DATA_FILE = Paths.get("./src/main/resources/LoginExamples.xml");

    @DataProvider(name = "xmlDataProvider")
    public Object[][] getData() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserBase.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        UserBase userBase = (UserBase) jaxbUnmarshaller.unmarshal(XML_DATA_FILE.toFile());

        int arraySize = userBase.getCases().size();

        Object[][] dataRows = new Object[arraySize][];
        for (int i = 0; i < arraySize; i++) {
            TestCase testCase = userBase.getCases().get(i);
            UserCredentials user = testCase.getUserCredentials();

            dataRows[i] = new Object[]{
                    user.getUsername(),
                    user.getPassword(),
                    user.getValidationType()
            };
        }
        return dataRows;
    }
}