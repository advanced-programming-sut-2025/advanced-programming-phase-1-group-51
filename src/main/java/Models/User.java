package Models;

import Models.Items.Item;
import Models.Items.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {

    public static List<User> users = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String gender;
    private int securityQuestionNumber;
    private String securityAnswer;
    private Game currentGame;
    private int gamesPlayed = 0;



    public User(String username, String password, String nickName,  String email, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
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


}
