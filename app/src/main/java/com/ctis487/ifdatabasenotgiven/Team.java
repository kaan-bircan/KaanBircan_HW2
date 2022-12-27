package com.ctis487.ifdatabasenotgiven;

public class Team {
    private int Team_id;
    private String Team_name;
    private String Supporter;

    public Team(int Team_id, String Team_name, String Supporter) {
        this.Team_id = Team_id;
        this.Team_name = Team_name;
        this.Supporter = Supporter;
    }
    public Team(String Team_name, String Supporter) {
        this.Team_name = Team_name ;
        this.Supporter = Supporter;
    }

    public int getId() {
        return Team_id;
    }

    public void setId(int Team_id) {
        this.Team_id = Team_id;
    }

    public String getName() {
        return Team_name;
    }

    public void setName(String Team_name) {
        this.Team_name = Team_name;
    }

    public String getSupporter() {
        return Supporter;
    }

    public void setSupporter(String supporter) {
        Supporter = supporter;
    }

    @Override
    public String toString() {
        return Team_id+" "+Team_name+" "+Supporter;
    }
}
