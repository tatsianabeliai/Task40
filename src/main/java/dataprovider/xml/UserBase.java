package dataprovider.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "userbase")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserBase {

    @XmlElement(name = "testCase")
    private List<TestCase> cases;

    public List<TestCase> getCases() {
        return cases;
    }

    public void setCases(List<TestCase> cases) {
        this.cases = cases;
    }
}
