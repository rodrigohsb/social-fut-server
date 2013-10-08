package br.com.socialfut.persistence;

import lombok.Data;

@Data
public class Position
{
    private int id;

    private String positionName;

    public Position(int id, String positionName)
    {
        this.id = id;
        this.positionName = positionName;
    }
}
