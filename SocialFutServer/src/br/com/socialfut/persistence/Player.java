package br.com.socialfut.persistence;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class Player
{
    private long id;

    private String deviceRegistrationId;

    private int position;

    private float rating;
}
