package br.com.socialfut.persistence;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class Player
{
    private Long id;

    private String deviceRegistrationId;

    private Integer position;

    private Float rating;

}
