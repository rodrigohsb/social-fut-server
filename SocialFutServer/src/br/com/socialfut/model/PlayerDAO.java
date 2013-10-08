package br.com.socialfut.model;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.socialfut.persistence.Position;
import lombok.Data;

@Data
@XmlRootElement
public class PlayerDAO
{
    private long id;

    private String deviceRegistrationId;

    Position position;
}
