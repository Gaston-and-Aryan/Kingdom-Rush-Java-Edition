public class Enemy
{
    //Instance Variables
    private String name;
    private int currentHealth;
    private final int totalHealth;
    private int lowerBound;
    private int upperBound;
    private static int points;
    private String imagePath;
    

    //Constructor
    public Enemy(String someName, int someHealth, int someLowerBound, int someUpperBound, String someImagePath)
    {
        this.name = someName;
        this.totalHealth = someHealth;
        this.currentHealth = someHealth;
        this.lowerBound = someLowerBound;
        this.upperBound = someUpperBound;
        this.imagePath = someImagePath;
    }

    //Getters
    public String getName() {return name;}
    public int getCurrentHealth() {return currentHealth;}
    public int getTotalHealth() {return totalHealth;}
    public int getLowerBound() {return lowerBound;}
    public int getUpperBound() {return upperBound;}
    public String getImagePath() {return imagePath;}
    public static int getPoints() {return points;}

    public int calculateDamage()
    {
        return (int) ((Math.random() * (upperBound - lowerBound)) + lowerBound);
    }

    public void setHealth(int someHealth)
    {
        this.currentHealth = someHealth;
    }

    public static void setPoints(int somePoints)
    {
        points = somePoints;
    }



    //Creating All 10 Enemies
    public static Enemy makeGoblin() { return new Enemy("Goblin", 20, 5, 10, "Enemy Images/Goblin.jpg"); }
    public static Enemy makeTroll() { return new Enemy("Troll", 50, 8, 12, "Enemy Images/Troll.jpg"); }
    public static Enemy makeDarkKnight() { return new Enemy("Dark Knight", 80, 20, 30, "Enemy Images/DarkKnight.jpg"); }
    public static Enemy makeTaintedTarget() { return new Enemy("Tainted Target", 135, 20, 38, "Enemy Images/TaintedTarget.jpg"); }
    public static Enemy makeAbomination() { return new Enemy("Abomination", 175, 18, 35, "Enemy Images/Abomination.jpg"); }
    public static Enemy makeOgre() { return new Enemy("Ogre", 150, 35, 50, "Enemy Images/Ogre.jpg"); }
    public static Enemy makeYeti() { return new Enemy("Yeti", 175, 45, 60, "Enemy Images/Yeti.jpg"); }
    public static Enemy makeDemonChampion() { return new Enemy("Demon Champion", 200, 50, 65, "Enemy Images/DemonChampion.jpg"); }
    public static Enemy makeSaurianBrute() { return new Enemy("Saurian Brute", 220, 10, 80, "Enemy Images/SaurianBrute.jpg"); }
    public static Enemy makeBraveryGolem() { return new Enemy("Twilight Golem", 250, 35, 50, "Enemy Images/BraveryGolem.jpg"); }



    public static Enemy generateEnemy()
    {
        Enemy currentEnemy = null;
        int randomNumber = (int)(Math.random()*10 + 1);
        switch (randomNumber)
        {
            case 1: currentEnemy = makeGoblin(); break;
            case 2: currentEnemy = makeSaurianBrute(); break;          
            case 3: currentEnemy = makeTroll(); break;       
            case 4: currentEnemy = makeDarkKnight(); break;          
            case 5: currentEnemy = makeBraveryGolem(); break;            
            case 6: currentEnemy = makeTaintedTarget(); break;            
            case 7: currentEnemy = makeAbomination(); break;      
            case 8: currentEnemy = makeOgre(); break;           
            case 9: currentEnemy = makeYeti(); break;   
            case 10: currentEnemy = makeDemonChampion(); break;
        } //End Of Switch Case Statement

        return currentEnemy;
    }
}