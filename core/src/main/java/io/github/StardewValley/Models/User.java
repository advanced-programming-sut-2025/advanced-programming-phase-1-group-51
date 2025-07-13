package io.github.StardewValley.Models;


import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String gender;
    private String securityQuestion;
    private String securityAnswer;
    private int numberOfGamesPlayed;
    private int maxMoneyEarnedInGame;
    private Game currentGame;
    private final ArrayList<Game> games = new ArrayList<>();
    private boolean stayLoggedIn;

    public User(String username, String password, String email, String nickName, String gender, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.numberOfGamesPlayed = 0;
        this.maxMoneyEarnedInGame = 0;
        this.stayLoggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGender() {
        return gender;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }


    public int getMaxMoneyEarnedInGame() {
        return maxMoneyEarnedInGame;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
