 package com.example.kakerlakenpoker.playerstatus;


 import com.example.kakerlakenpoker.game.Game;
 import com.example.kakerlakenpoker.player.Player;

 public class Status {
    Enums enums;
    Game game;


    public Status(Enums enums) {
        this.enums = enums;
    }

    public Status() {
        this.enums = enums.WARTET;
    }

    public void setGame(Game game) {
        this.game = game;
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

    final Player p1 = game.getPlayers().get(0);
    final Player p2 = game.getPlayers().get(1);
    final Player p3 = game.getPlayers().get(2);
    final Player p4 = game.getPlayers().get(3);


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
