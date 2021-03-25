package com.intuit.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Player")
public class Player
{
    @Id
    @Column
    private String playerID;

    @Column
    private String birthYear;
    @Column
    private String birthMonth;
    @Column
    private String birthDay;
    @Column
    private String birthCountry;
    @Column
    private String birthState;
    @Column
    private String birthCity;
    @Column
    private String deathYear;

    private String deathMonth;
    private String deathDay;

    private String deathCountry;

    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private String weight;
    private String height;
    private String bats;
    private String throwsDone;

    private String debut;
    private String finalGame;
    private String retroID;
    private String bbrefID;
}
