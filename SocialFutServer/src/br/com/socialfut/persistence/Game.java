package br.com.socialfut.persistence;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class Game
{
    private long id;

    private float value;

    private int qntRates;
}
