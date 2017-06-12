package dataprovider.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testCase")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCase {

    @XmlElement(name = "userCredentials")
    private UserCredentials userCredentials;

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(String username) {
        this.userCredentials = userCredentials;
    }
}