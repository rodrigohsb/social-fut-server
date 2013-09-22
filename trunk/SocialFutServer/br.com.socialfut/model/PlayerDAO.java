package model;

import lombok.Data;

@Data
public class PlayerDAO
{
    private long id;

    private PositionDAO position;

    private float rating;

    private String deviceRegistrationId;
}
