public class Weapon
{
    //Instance Variables
    private String name;
    private int energyCost;
    private int lowerBound;
    private int upperBound;
    private String imagePath;
    private String abilityName;
    private String abilityDescription;

    private int mysticBlossomStartDamage = 10;
    public static boolean bleedingEffect = false;
    public static boolean poisonEffect = false;
    public static boolean vineStun = false;
    

    //Constructor
    public Weapon(String someName, int someEnergyCost, int someLowerBound, int someUpperBound, String someAbilityName, String someAbilityDescription, String someImagePath)
    {
        this.name = someName;
        this.energyCost = someEnergyCost;
        this.lowerBound = someLowerBound;
        this.upperBound = someUpperBound;
        this.abilityName = someAbilityName;
        this.abilityDescription = someAbilityDescription;
        this.imagePath = someImagePath;
    }

    //Getters
    public String getName() {return name;}
    public int getEnergyCost() {return energyCost;}
    public int getLowerBound() {return lowerBound;}
    public int getUpperBound() {return upperBound;}
    public String getAbilityName() {return abilityName;}
    public String getAbilityDescription() {return abilityDescription;}
    public String getImagePath() {return imagePath;}

    public static String getStats(Weapon someWeapon)
    {
        String stats = "Damage: " + someWeapon.getLowerBound() + "-" + someWeapon.getUpperBound() + "<br> Energy: " + someWeapon.getEnergyCost();
        return stats;
    }

    public static Weapon rangersBow = new Weapon("Ranger's Bow", 1, 10, 15, "Poisonous Arrows: ", "Enemy loses 5 HP every turn until death.", "Weapon Images/ranger.jpg");
    public static Weapon musketeersRifle = new Weapon("Musketeer's Rifle", 3, 20, 35, "Headshot: ", "50% chance to do double damage.", "Weapon Images/Musketeer.jpg");
    public static Weapon goldenLongbow = new Weapon("Golden Longbow", 5, 1, 100, "Crimson Sentence: ", "Damage is always 50% of enemy's current HP.", "Weapon Images/Golden.jpg");
    public static Weapon paladinsSword = new Weapon("Paladin's Sword", 1, 8, 16, "Healing Light: ", "Heal back 10 HP every use.", "Weapon Images/Knights.jpg");
    public static Weapon barbariansAxe = new Weapon("Barbarian's Axe", 5, 35, 55, "Bleeding Edge: ", "Enemy bleeds 20 HP every turn until death.", "Weapon Images/Barbarians.jpg");
    public static Weapon forestKeepersSpear = new Weapon("Forest Keeper's Spear", 3, 18 , 32, "Vicious Vines: ", "50% chance to stun enemy for 1 turn.", "Weapon Images/Forest.jpg");
    public static Weapon mysticBlossom = new Weapon("Mystic Blossom", 1, 10, 10, "Power Overgrowth: ", "Damage increases by 1 every use.", "Weapon Images/Mystic.jpg");
    public static Weapon sorcerersCrystal = new Weapon("Sorcerer's Crystal", 3, 20, 33, "Energy Preservation: ", "50% chance this attack costs 0 energy.", "Weapon Images/Sourcer.jpg");
    public static Weapon arcanePrism = new Weapon("Arcane Prism", 5, 45, 65, "Disintegration Beam: ", "50% chance to instakill the enemy.", "Weapon Images/Arcane.jpg");

    public int calculateDamage()
    {
        return (int) ((Math.random() * (upperBound - lowerBound)) + lowerBound);
    }



    public void trySpecialAbility(Player somePlayer, Enemy someEnemy)
    {
        if (this.getName() == "Ranger's Bow")
        {
            int energyCost = this.getEnergyCost();
            int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
            somePlayer.setEnergy(newEnergy);

            int damage = this.calculateDamage();
            int health = someEnemy.getCurrentHealth() - damage;
            someEnemy.setHealth(health);

            poisonEffect = true;

            System.out.println("The special ability WAS activated and the enemy is now poisoned! You selected the " + this.getName() + " and dealt " + damage + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
        }


        if (this.getName() == "Musketeer's Rifle")
        {
            if (Math.random() > 0.5) //Do special ability - Double damage
            {
                int energyCost = this.getEnergyCost();
                int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
                somePlayer.setEnergy(newEnergy);

                int damage = this.calculateDamage() * 2; //Dealing double damage
                int health = someEnemy.getCurrentHealth() - damage;
                someEnemy.setHealth(health);
                System.out.println("The special ability WAS activated! You selected the " + this.getName() + " and dealt " + damage + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
            }

            //Otherwise, do NOT do special ability and just use regular attack
            else
            {
                this.doNormalAttack(somePlayer, someEnemy);
            }
        }

        
        else if (this.getName() == "Golden Longbow")
        {
            //Energy Cost
            int energyCost = this.getEnergyCost();
            int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
            somePlayer.setEnergy(newEnergy);

            //Setting Enemy Health to 0
            int health = someEnemy.getCurrentHealth() / 2;
            someEnemy.setHealth(health);
            System.out.println("The special ability WAS activated! You selected the " + this.getName() + " and dealt " + health + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
        }


        else if (this.getName() == "Paladin's Sword")
        {
            int energyCost = this.getEnergyCost();
            int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
            somePlayer.setEnergy(newEnergy);

            int damage = this.calculateDamage(); //Dealing double damage
            int health = someEnemy.getCurrentHealth() - damage;
            someEnemy.setHealth(health);

            int userHealth = somePlayer.getCurrentHealth() + 10;
            somePlayer.setHealth(userHealth);
            System.out.println("The special ability WAS activated and you healed back 10 HP!");
        }


        if (this.getName() == "Barbarian's Axe")
        {
            int energyCost = this.getEnergyCost();
            int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
            somePlayer.setEnergy(newEnergy);

            int damage = this.calculateDamage();
            int health = someEnemy.getCurrentHealth() - damage;
            someEnemy.setHealth(health);

            bleedingEffect = true;

            System.out.println("The special ability WAS activated and the enemy is now bleeding! You selected the " + this.getName() + " and dealt " + damage + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
        }


        if (this.getName() == "Forest Keeper's Spear")
        {
            //Have a 50% chance to stun the enemy
            if (Math.random() > 0.5)
            {
                int energyCost = this.getEnergyCost();
                int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
                somePlayer.setEnergy(newEnergy);

                int damage = this.calculateDamage();
                int health = someEnemy.getCurrentHealth() - damage;
                someEnemy.setHealth(health);

                vineStun = true;

                System.out.println("The special ability WAS activated and you stunned the enemy! You selected the " + this.getName() + " and dealt " + damage + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
            }

            else
            {
                vineStun = false;
                this.doNormalAttack(somePlayer, someEnemy);
            }
        }


        else if (this.getName() == "Mystic Blossom")
        {
            //Energy Cost
            int energyCost = this.getEnergyCost();
            int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
            somePlayer.setEnergy(newEnergy);

            int damage = mysticBlossomStartDamage;
            int health = someEnemy.getCurrentHealth() - mysticBlossomStartDamage;
            someEnemy.setHealth(health);
            System.out.println("The special ability WAS activated! You selected the " + this.getName() + " and dealt " + mysticBlossomStartDamage + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
            mysticBlossomStartDamage += 1; //Every move means +1 damage
        }


        if (this.getName() == "Sorcerer's Crystal")
        {
            //50% chance to not expend any energy at all
            if (Math.random() > 0.5)
            {
                int damage = this.calculateDamage(); //Dealing double damage
                int health = someEnemy.getCurrentHealth() - damage;
                someEnemy.setHealth(health);
                System.out.println("The special ability WAS activated and you didn't expend any energy! You selected the " + this.getName() + " and dealt " + damage + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
            }

            else
            {
                this.doNormalAttack(somePlayer, someEnemy);
            }
        }


        else if (this.getName() == "Arcane Prism")
        {
            if (Math.random() > 0.5) //Do special ability - Instakill
            {
                //Energy Cost
                int energyCost = this.getEnergyCost();
                int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
                somePlayer.setEnergy(newEnergy);

                //Setting Enemy Health to 0
                someEnemy.setHealth(0);
                System.out.println("The special ability WAS activated! You selected the " + this.getName() + " and instakilled the enemy! You have " + somePlayer.getCurrentEnergy() + " energy left.");
            }

            else
            {
                this.doNormalAttack(somePlayer, someEnemy);
            }
        }
    }

    

    public void doNormalAttack(Player somePlayer, Enemy someEnemy)
    {
        //Decrementing Player's Energy
        int energyCost = this.getEnergyCost();
        int newEnergy = somePlayer.getCurrentEnergy() - energyCost;
        somePlayer.setEnergy(newEnergy);

        //Calculating & Inflicting Damage
        int damage = this.calculateDamage();
        int newHealth = someEnemy.getCurrentHealth() - damage;
        someEnemy.setHealth(newHealth);

        System.out.println("The special ability was NOT activated! You selected the " + this.getName() + " and dealt " + damage + " damage! The Enemy has " + someEnemy.getCurrentHealth() + " health left and you have " + somePlayer.getCurrentEnergy() + " energy left.");
    }
}