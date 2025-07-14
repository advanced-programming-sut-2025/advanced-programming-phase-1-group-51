package io.github.StardewValley.Models;

import io.github.StardewValley.Models.Enums.Types.TrashcanType;

public class TrashCan {
    private TrashcanType TrashCanType;

    public TrashCan(TrashcanType trashCanType) {
        TrashCanType = trashCanType;
    }

    public TrashcanType getTrashCanType() {
        return TrashCanType;
    }

    public void setTrashCanType(TrashcanType trashCanType) {
        TrashCanType = trashCanType;
    }


}
