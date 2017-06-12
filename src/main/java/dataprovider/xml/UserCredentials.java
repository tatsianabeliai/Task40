package dataprovider.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCredentials {
    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "counter")
    private String validationType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidationType() {
        return validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = this.validationType;
    }
}
