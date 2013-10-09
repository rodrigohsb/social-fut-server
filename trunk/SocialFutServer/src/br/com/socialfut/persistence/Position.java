package br.com.socialfut.persistence;

import lombok.Data;

@Data
public class Position
{
    private int id;

    private String name;

    public Position(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
