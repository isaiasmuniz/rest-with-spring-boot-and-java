package br.com.muniz.integrationtests.dto.wrapper.xml;

import br.com.muniz.integrationtests.dto.PersonDTOV1;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class PageModelPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "content")
    public List<PersonDTOV1> content;

    public PageModelPerson() {}

    public List<PersonDTOV1> getContent() {
        return content;
    }

    public PageModelPerson setContent(List<PersonDTOV1> content) {
        this.content = content;
        return this;
    }
}
