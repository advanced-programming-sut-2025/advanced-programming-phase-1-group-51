package Models;

import Models.Items.Item;
import Models.Items.Tool;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class User {


    @JsonIgnore
    private Game currentGame; // Avoid circular reference

    // Add this to handle serialization of games list
    @JsonProperty("games")
    public List<GameInfo> getGameInfos() {
        return games.stream()
                .map(game -> new GameInfo(game.getDate(), game.getPlayers().size()))
                .collect(Collectors.toList());
    }

    // Inner class for simplified game info
    private static class GameInfo {
        public LocalDateTime date;
        public int playerCount;

        public GameInfo(LocalDateTime date, int playerCount) {
            this.date = date;
            this.playerCount = playerCount;
        }
    }
    public static List<User> users = new ArrayList<>();
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("gender")
    private String gender;
    private int securityQuestionNumber;
    private String securityAnswer;
    private int gamesPlayed = 0;
    private int id;
    private int maxMoneyEarnedInGame;
    private final ArrayList<Game> games = new ArrayList<>();
    private boolean stayLoggedIn = false;


    @JsonCreator
    public User(@JsonProperty("username") String username,
                @JsonProperty("password") String password,
                @JsonProperty("nickName") String nickName,
                @JsonProperty("email") String email,
                @JsonProperty("gender") String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
    }

    public boolean shouldStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSecurityQuestionNumber() {
        return securityQuestionNumber;
    }

    public void setSecurityQuestionNumber(int securityQuestionNumber) {
        this.securityQuestionNumber = securityQuestionNumber;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void GamesPlayed() {
        this.gamesPlayed++;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }


    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        User.users = users;
    }

    public int getMaxMoneyEarnedInGame() {
        return maxMoneyEarnedInGame;
    }

    public void setMaxMoneyEarnedInGame(int maxMoneyEarnedInGame) {
        this.maxMoneyEarnedInGame = maxMoneyEarnedInGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

}
