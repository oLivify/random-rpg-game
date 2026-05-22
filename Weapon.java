public class Weapon extends Item {

    private int damage;

    public Weapon() {
        super();
    }

    public Weapon(Weapon other) {
        super(other);
        damage = other.damage;
    }

    public Weapon(String _name, String _description, int _damage) {
        super(_name, _description);
        damage = _damage;
    }

    public int getDamage() {
        return damage;
    }


    


}
