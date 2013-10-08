package br.com.socialfut.model;

import lombok.Data;

@Data
public class PositionDAO
{
    private int id;

    private String positionName;

    public PositionDAO(int id, String positionName)
    {
        this.id = id;
        this.positionName = positionName;
    }
}
