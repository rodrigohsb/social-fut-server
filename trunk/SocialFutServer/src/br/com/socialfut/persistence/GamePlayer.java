package br.com.socialfut.persistence;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class GamePlayer
{
    private long id;

    private long game_id;

    private int player_id;

    private float value;

    private int qntRates;
}
