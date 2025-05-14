package Models;

import java.time.LocalDateTime;

public class ActiveBuff {

    private Buff foodBuff;
    private LocalDateTime expirationTime;


    public ActiveBuff(Buff foodBuff) {
        LocalDateTime currentTime = App.getCurrentUser().getCurrentGame().getDate();
        this.foodBuff = foodBuff;
        this.expirationTime = handleFormattingOfTime(currentTime, foodBuff.getDuration());
    }

    private LocalDateTime handleFormattingOfTime(LocalDateTime dateTime, int durationInHours) {
        LocalDateTime output = dateTime.plusHours(durationInHours);
        if (output.getDayOfMonth() == 29) {
            output = output.plusMonths(1);
            output = output.minusDays(28);
        }
        return output;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public Buff getFoodBuff() {
        return foodBuff;
    }
}
