package br.com.muniz.integrationtests.dto.wrapper.xml;

import br.com.muniz.integrationtests.dto.BookDTOV1;
import br.com.muniz.integrationtests.dto.PersonDTOV1;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class PageModelBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "content")
    public List<BookDTOV1> content;

    public PageModelBook() {}

    public List<BookDTOV1> getContent() {
        return content;
    }

    public PageModelBook setContent(List<BookDTOV1> content) {
        this.content = content;
        return this;
    }
}
