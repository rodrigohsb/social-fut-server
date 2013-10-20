package br.com.socialfut.persistence;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class Game
{
    private long id;

    private String title;

    private String address;

    private Date createdDate;

    private Date startDate;

    private Date finishDate;

    public Game(long id, String title, String address, Date createdDate, Date startDate, Date finishDate)
    {
        super();
        this.id = id;
        this.title = title;
        this.address = address;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

}
