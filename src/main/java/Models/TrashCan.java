package Models;

public class TrashCan {
    private String TrashCanType;
    private int PercentOfCashBack;

    public TrashCan(String trashCanType, int percentOfCashBack) {
        TrashCanType = trashCanType;
        PercentOfCashBack = percentOfCashBack;
    }

    public String getTrashCanType() {
        return TrashCanType;
    }

    public void setTrashCanType(String trashCanType) {
        TrashCanType = trashCanType;
    }

    public int getPercentOfCashBack() {
        return PercentOfCashBack;
    }

    public void setPercentOfCashBack(int percentOfCashBack) {
        PercentOfCashBack = percentOfCashBack;
    }
}
