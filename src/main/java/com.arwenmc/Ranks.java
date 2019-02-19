package com.arwenmc;

public enum Ranks {

    OWNER(100),
    ADMIN(90),
    MODERATOR(80),
    HELPER(70),
    DONATOR(50),
    MEMBER(40),
    DEFAULT(0);

    private int rankValue;
    Ranks(int value) {
        this.rankValue = value;
    }

    public int getValue() {
        return this.rankValue;
    }

    public Ranks getRank(int value) {
        for (Ranks rank : Ranks.values()) {
            if (rank.getValue() == value) {
                return rank;
            }
        }
        return null;
    }
}
