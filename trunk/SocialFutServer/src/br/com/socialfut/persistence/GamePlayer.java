package br.com.socialfut.persistence;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class GamePlayer
{
    private long id;

    private long gameId;

    private Player player;

    private float value;

    private int qntRates;
}
