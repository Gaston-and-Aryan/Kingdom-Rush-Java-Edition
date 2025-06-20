public class Player
{
    //Instance Variables

    //Player Stats
    private int currentHealth;
    private final int totalHealth;

    private int currentEnergy;
    private final int totalEnergy;

    private static int points;

    //Weapons
    private Weapon archerWeapon;
    private Weapon meleeWeapon;
    private Weapon magicWeapon;



    //Constructor
    public Player(Weapon someArcherWeapon, Weapon someMeleeWeapon, Weapon someMagicWeapon, int totalHealth, int totalEnergy)
    {
        this.archerWeapon = someArcherWeapon;
        this.meleeWeapon = someMeleeWeapon;
        this.magicWeapon = someMagicWeapon;
        this.totalHealth = 150;
        this.currentHealth = 150;
        this.totalEnergy = 10;
        this.currentEnergy = 10;
    }

    //Getters
    public Weapon getArcherWeapon() {return archerWeapon;}
    public Weapon getMeleeWeapon() {return meleeWeapon;}
    public Weapon getMagicWeapon() {return magicWeapon;}

    public int getCurrentHealth() { return currentHealth; }
    public int getTotalHealth() { return totalHealth; }
    public int getCurrentEnergy() { return currentEnergy; }
    public int getTotalEnergy() { return totalEnergy; }
    public static int getPoints() {return points;}


    public void setEnergy(int someEnergy)
    {
        this.currentEnergy = someEnergy;
    }

    public void setHealth(int someHealth)
    {
        this.currentHealth = someHealth;
    }

    public static void setPoints(int somePoint)
    {
        points = somePoint;
    }
}