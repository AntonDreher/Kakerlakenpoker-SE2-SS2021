package com.example.kakerlakenpoker.playerstatus;

import com.example.kakerlakenpoker.player.Player;

public class Status {
    Enums enums;
    Player p1;
    Player p2;
    Player p3;
    Player p4;

    public Status(Enums enums) {
        this.enums = enums;
    }

    public Enums getWartet() {
        return Enums.WARTET;
    }

    public Enums getAngefragt() {
        return Enums.ANGEFRAGT;
    }

    public Enums getSpielt() {
        return Enums.SPIELT;
    }

    public void setWartet() {
        enums = Enums.WARTET;
    }

    public void setSpielt() {
        enums = Enums.SPIELT;
    }

    public void setAngefragt() {
        enums = Enums.ANGEFRAGT;
    }

    public void setStart() {
        setWartet();
    }

    public void diceRoll() {
        int startet = (int) (Math.random() * 100) % 3;

        switch (startet) {
            case 0:
                p1.setStatus(new Status(getSpielt()));
                break;
            case 1:
                p2.setStatus(new Status(getSpielt()));
                break;
            case 2:
                p3.setStatus(new Status(getSpielt()));
                break;
            case 4:
                p4.setStatus(new Status(getSpielt()));
                break;
            default:
                throw new IllegalArgumentException("Fehler");
        }
    }

    public void statusChanges() {
        if (p1.toString().equals(Enums.ANGEFRAGT.toString())) {
            //to implement who u want to choose
        }
        if (p1.toString().equals(Enums.ANGEFRAGT.toString())) {
            //to implement who u want to choose

        }
        if (p1.toString().equals(Enums.ANGEFRAGT.toString())) {
            //to implement who u want to choose

        }
        if (p1.toString().equals(Enums.ANGEFRAGT.toString())) {
            //to implement who u want to choose

        }
    }

    

}
