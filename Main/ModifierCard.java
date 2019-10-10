package Main;

public enum ModifierCard {
    MISS(-3),
    MINUS_TWO(-2),
    MINUS_ONE(-1),
    ZERO(0),
    PLUS_ONE(1),
    PLUS_TWO(2),
    CRIT(3);

    private int value;

    private ModifierCard(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public int apply(int strengthOfAttack, int shield) {
        int damage;
        if (this == MISS) {
            damage = 0;
        } else if (this == CRIT) {
             damage = strengthOfAttack * 2;
        } else {
            damage = strengthOfAttack + value;
        }
        damage -= shield;
        if (damage < 0) {
            damage = 0;
        }
        return damage;
    }
}
