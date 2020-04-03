package com.example.predictiveorderer;

public class Account
{
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Account()
    {
        username = "";
        password = "";
        firstName = "";
        lastName = "";
    }

    public Account(String uName, String pass, String fName, String lName)
    {
        username = uName;
        password = pass;
        firstName = fName;
        lastName = lName;
    }

    public void setUserName(String uName)
    {
        username = uName;
    }

    public String getUserName()
    {
        return username;
    }
    public void setPassword(String pass)
    {
        password = pass;
    }

    public String getPassword()
    {
        return password;
    }

    public void setFirstName(String fName)
    {
        firstName = fName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setLastName(String lName)
    {
        lastName = lName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public boolean isMatching(String uName, String pass)
    {
        return (uName.equals(username) && pass.equals(password));
    }

    public String encryptUserName(String uName)
    {
        return uName;
    }
}
