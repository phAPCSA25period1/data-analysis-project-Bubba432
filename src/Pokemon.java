/**
 * Represents a single Pokémon entry from the dataset.
 * <p>
 * Each Pokémon has a name, a type, hit points (HP), and an attack value.
 * Objects of this class correspond to one row of the CSV file.
 * </p>
 */
public class Pokemon {

    /** the Pokémon's name */
    private String name;

    /** the Pokémon's elemental type */
    private String type;

    /** hit points, an integer value */
    private int hp;

    /** attack statistic, an integer value */
    private int attack;

    /**
     * Constructs a new Pokémon with all attributes supplied.
     *
     * @param name   the name of the Pokémon
     * @param type   the elemental type (e.g. Fire, Water)
     * @param hp     the hit points (HP) value
     * @param attack the attack value
     */
    public Pokemon(String name, String type, int hp, int attack) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
    }

    /**
     * @return the name of this Pokémon
     */
    public String getName() {
        return name;
    }

    /**
     * @return the elemental type of this Pokémon
     */
    public String getType() {
        return type;
    }

    /**
     * @return the hit points value
     */
    public int getHp() {
        return hp;
    }

    /**
     * @return the attack statistic
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns a string representation of the Pokémon suitable for debugging
     * or simple output. Includes all attributes.
     *
     * @return formatted string describing the object
     */
    @Override
    public String toString() {
        return name + " (" + type + ") HP=" + hp + " ATK=" + attack;
    }
}
