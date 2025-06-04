import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;


public class BattlePage extends JFrame
{
    //JComponents
    private JLabel battlePageTitle;

    private JPanel enemyPanel = new JPanel();

    private JLabel enemyHealthBarLabel;
    private JLabel chooseYourWeaponLabel;
    private JLabel playerHealthBarLabel;
    private JLabel playerEnergyBarLabel;
    private JButton useArcherButton;
    private JButton useMeleeButton;
    private JButton useMagicButton;
    private JButton skipTurnButton;
    private JLabel skipTurnLabel;

    //Bars, Bar Labels, & Bar Fraction Labels
    private Bar playerHealthBar;
    private Bar playerEnergyBar;
    private Bar enemyHealthBar;
    private JLabel playerHealthFractionLabel;
    private JLabel playerEnergyFractionLabel;
    private JLabel enemyHealthFractionLabel;
    private String playerHealthFraction;
    private String playerEnergyFraction;
    private String enemyHealthFraction;

    private Bar turnIndicator;
    private JLabel turnIndicatorLabel;

    //JPanels
    private JPanel titlePanel;
    private JPanel divider;


    private JLabel roundsLabel;
    private JLabel scoreLabel;
    private JPanel scorePanel;
    private JPanel roundsPanel;


    private JPanel archerPanel;
    private JPanel meleePanel;
    private JPanel magicPanel;

    //Non-JComponent Instance Variables
    private Player currentPlayer;
    private Enemy currentEnemy;
    private boolean keepFighting = false;
    private boolean part2Flag = false;
    private int currentRound = 1;
    private boolean usedWeapon = false; //This should be renamed to roundlyEnergy or something or postAttackEnergy
    


    //Constructor
    public BattlePage(Player somePlayer)
    {
        this.currentPlayer = somePlayer;

        //JFrame Setup
        this.setPreferredSize(new Dimension(1000, 800)); //Make this 1200 later
        this.pack();
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center on screen


        // Title Panel with a Blue background
        titlePanel = new JPanel();
        titlePanel.setBounds(0,0,1000,60);
        titlePanel.setBackground(new Color(0,200,200)); // Light sky blue color
        titlePanel.setLayout(null);

        //Title Label
        battlePageTitle = new JLabel("Time For Battle!");
        battlePageTitle.setBounds(375, 0, 600, 60);
        battlePageTitle.setFont(new Font("Helvetica", Font.BOLD, 35));
        titlePanel.add(battlePageTitle);

        // Divider Panel
        divider = new JPanel();
        divider.setBounds(0,315,1000,5);
        divider.setBackground(new Color(0,0,0));

        // Chose your weapon label
        chooseYourWeaponLabel = new JLabel("Choose Your Weapon!");
        chooseYourWeaponLabel.setBounds(360, 325, 300, 50);
        chooseYourWeaponLabel.setFont(new Font("Helvetica", Font.BOLD, 25));

        // round tracker panel
        roundsPanel = new JPanel();
        roundsPanel.setBounds(775,325,150,35);
        roundsPanel.setBackground(new Color(255, 15, 230)); //Pink
        roundsPanel.setLayout(null);

        // Score tracker panel
        scorePanel = new JPanel();
        scorePanel.setBounds(125, 325, 150, 35);
        scorePanel.setBackground(new Color(255, 15, 230)); //Pink
        scorePanel.setLayout(null);

        //Score Tracker
        scoreLabel = new JLabel();
        scoreLabel.setText("Score: " + Player.getPoints() + "-" + Enemy.getPoints());
        scoreLabel.setBounds(25, 0, 150, 35);
        scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        scorePanel.add(scoreLabel);

        // Rounds tracker
        roundsLabel = new JLabel("Round " + currentRound + "/" + Select_Round.getTotalRounds());
        roundsLabel.setBounds(25, 0, 150, 35);
        roundsLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        roundsPanel.add(roundsLabel);

        this.add(roundsPanel);
        this.add(scorePanel);



        // Skip your turn bar and label
        skipTurnButton = new JButton("Skip Your Turn");
        skipTurnButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        skipTurnButton.setBounds(390, 710, 220, 50);
        skipTurnButton.setForeground(Color.BLACK); // Optional: to make text visible
        skipTurnButton.addActionListener(new ButtonListener());

        skipTurnLabel = new JLabel("No Energy Left?");
        skipTurnLabel.setBounds(410,675,275,35);
        skipTurnLabel.setFont(new Font("Helvetica", Font.BOLD, 25));


        // Whose turn bar and label, Starting round label
        turnIndicator = new Bar(50,10,175,40,Color.BLACK);
        turnIndicator.setBorder(Color.WHITE);
        titlePanel.add(turnIndicator);
        
        turnIndicatorLabel = new JLabel("Your Turn");
        turnIndicatorLabel.setBounds(0,0,100,10);
        turnIndicatorLabel.setForeground(Color.WHITE);
        turnIndicatorLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        turnIndicator.add(turnIndicatorLabel);


        //3 Selection Buttons
        useArcherButton = new JButton("Use Archer Weapon");
        useArcherButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        useArcherButton.setBounds(50, 610, 260, 50);
        useArcherButton.setForeground(Color.BLACK);
        useArcherButton.addActionListener(new ButtonListener());

        useMeleeButton = new JButton("Use Melee Weapon");
        useMeleeButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        useMeleeButton.setBounds(375, 610, 250, 50);
        useMeleeButton.setForeground(Color.BLACK);
        useMeleeButton.addActionListener(new ButtonListener());

        useMagicButton = new JButton("Use Magic Weapon");
        useMagicButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        useMagicButton.setBounds(700, 610, 250, 50);
        useMagicButton.setForeground(Color.BLACK);
        useMagicButton.addActionListener(new ButtonListener());


        // Player Sequence(Displaying the player chosen weapons)

        // Three Blue panel
        // Archer Panel
        archerPanel = new JPanel();
        archerPanel.setLayout(null);
        archerPanel.setBounds(30, 400, 300, 200);
        archerPanel.setBackground(new Color(139, 200, 229)); // Light blue
        this.add(archerPanel);

        // Melee Panel
        meleePanel = new JPanel();
        meleePanel.setLayout(null);
        meleePanel.setBounds(350, 400, 300, 200);
        meleePanel.setBackground(new Color(139, 200, 229)); // Light yellow
        this.add(meleePanel);

        // Magic Panel
        magicPanel = new JPanel();
        magicPanel.setLayout(null);
        magicPanel.setBounds(670, 400, 300, 200);
        magicPanel.setBackground(new Color(139, 200, 229)); // Light purple
        this.add(magicPanel);

        //Begin Fight Sequence
        startFightSequence();


        Color desiredColor1 = new Color(34, 139, 34);
        Color desiredColor2 = new Color(46, 139, 87);
        

        // Adding Weapons to Weapon Panels
        if (currentPlayer.getArcherWeapon().getName().equals("Ranger's Bow")) { addWeaponToPanel(Weapon.rangersBow, archerPanel, desiredColor1, desiredColor2); }
        if (currentPlayer.getArcherWeapon().getName().equals("Musketeer's Rifle")) { addWeaponToPanel(Weapon.musketeersRifle, archerPanel, desiredColor1, desiredColor2);}
        if (currentPlayer.getArcherWeapon().getName().equals("Golden Longbow")) { addWeaponToPanel(Weapon.goldenLongbow, archerPanel, desiredColor1, desiredColor2);}
        if (currentPlayer.getMeleeWeapon().getName().equals("Paladin's Sword")) {addWeaponToPanel(Weapon.paladinsSword, meleePanel, desiredColor1, desiredColor2);}
        if (currentPlayer.getMeleeWeapon().getName().equals("Barbarian's Axe")) {addWeaponToPanel(Weapon.barbariansAxe, meleePanel, desiredColor1, desiredColor2);}
        if (currentPlayer.getMeleeWeapon().getName().equals("Forest Keeper's Spear")) {addWeaponToPanel(Weapon.forestKeepersSpear, meleePanel, desiredColor1, desiredColor2);}
        if (currentPlayer.getMagicWeapon().getName().equals("Mystic Blossom")) {addWeaponToPanel(Weapon.mysticBlossom, magicPanel, desiredColor1, desiredColor2);}
        if (currentPlayer.getMagicWeapon().getName().equals("Sorcerer's Crystal")) {addWeaponToPanel(Weapon.sorcerersCrystal, magicPanel, desiredColor1, desiredColor2);}
        if (currentPlayer.getMagicWeapon().getName().equals("Arcane Prism")) {addWeaponToPanel(Weapon.arcanePrism, magicPanel, desiredColor1, desiredColor2); }

        //Adding JPanels
        this.add(titlePanel);
        this.add(divider);

        //JComponents
        this.add(chooseYourWeaponLabel);
        this.add(useArcherButton);
        this.add(useMeleeButton);
        this.add(useMagicButton);
        this.add(skipTurnButton);
        this.add(skipTurnLabel);

        this.revalidate();  // Ensure the layout is updated
        this.repaint();     // Repaint the frame to show the changes
        this.setVisible(true);
    } //End of Constructor



    //FIGHT SEQUENCE
    public void startFightSequence()
    {
        currentEnemy = Enemy.generateEnemy();
        displayEnemy(currentEnemy);

        displayPlayerBars();
        updateLabels();
        updateRoundsAndScore();

        keepFighting = true;
        turnIndicatorLabel.setText("Your Turn");
        System.out.println("You encountered a " + currentEnemy.getName());
    }

    public void endOrNextRound()
    {
        currentRound++;

        //If the player has played all rounds
        if (currentRound > Select_Round.getTotalRounds())
        {
            dispose();
            new GameResults();
        }
        
        else
        {
            startFightSequence();
        }
    }



    //Attack Button - User clicks this button to confirm their attack
    public class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == skipTurnButton)
            {
                if (currentPlayer.getCurrentEnergy() <= 9)
                {
                    usedWeapon = false; //Disabled this so the player does NOT receive extra energy
                    doEnemyAttack();
                    currentPlayer.setEnergy(currentPlayer.getCurrentEnergy() + 1); //Giving 1 energy after user clicks Skip Turn
                    updateLabels();
                    System.out.println("Successfully skipped your turn and gained back 1 energy! You now have " + currentPlayer.getCurrentEnergy() + " energy!");
                }

                else
                {
                    System.out.println("Can't Skip Your Turn when you have max energy! You currently have " + currentPlayer.getCurrentEnergy() + " energy!");
                }
            }

            else
            {
                Weapon chosenWeapon = null;

                if ( (e.getSource() == useArcherButton))
                {
                    chosenWeapon = currentPlayer.getArcherWeapon();
                    part2Flag = true;
                }

                if ( (e.getSource() == useMeleeButton))
                {
                    chosenWeapon = currentPlayer.getMeleeWeapon();
                    part2Flag = true;
                }

                if ( (e.getSource() == useMagicButton))
                {
                    chosenWeapon = currentPlayer.getMagicWeapon();
                    part2Flag = true;
                }

                //Not Enough Energy
                if ((part2Flag == true))
                {
                    if (chosenWeapon.getEnergyCost() > currentPlayer.getCurrentEnergy())
                    {
                        //Update JLabel to say not enough energy
                        System.out.println("You don't have enough energy for that attack!");
                        return;
                    }
                    
                }

                if (chosenWeapon == null) {return;}

                //Locking the buttons so the player can't click on them again until its their turn
                useArcherButton.setEnabled(false);
                useMeleeButton.setEnabled(false);
                useMagicButton.setEnabled(false);
                skipTurnButton.setEnabled(false);

                usedWeapon = true;
                doPlayerAttack(chosenWeapon, currentEnemy);
            }
        }
    }
    

    public void doPlayerAttack(Weapon chosenWeapon, Enemy someEnemy)
    {
        chosenWeapon.trySpecialAbility(currentPlayer, currentEnemy);
        turnIndicatorLabel.setText("Enemy's Turn");
        updateLabels();
        
        //If the enemy DIES from that attack
        if (currentEnemy.getCurrentHealth() <= 0)
        {
            // Pause for a bit just to lock the buttons and prevent the user from breaking the game in-between rounds
            useArcherButton.setEnabled(false);
            useMeleeButton.setEnabled(false);
            useMagicButton.setEnabled(false);
            skipTurnButton.setEnabled(false);

            new javax.swing.Timer(1000, evt ->
            {
                ((Timer) evt.getSource()).stop();
                doPlayerWinsRound();
            }).start();

            //Turn poison and bleed flags off so they can be re-enabled for a new enemy
            Weapon.bleedingEffect = false;
            Weapon.poisonEffect = false;
        }
        
        //If the enemy SURVIVED that attack
        else
        {
            if (Weapon.vineStun == true) //If the enemy is stunned, let the player attack a 2nd time in a row
            {
                new javax.swing.Timer(1000, evt ->
                {
                    ((Timer) evt.getSource()).stop();
                    //Re-Enabling Buttons so user can attack again
                    useArcherButton.setEnabled(true);
                    useMeleeButton.setEnabled(true);
                    useMagicButton.setEnabled(true);
                    skipTurnButton.setEnabled(true); //These 4 lines should stay inside this block to prevent user from clicking button in between method calls, breaking the game
                }).start();

                Weapon.vineStun = false;
            }

            else
            {
                // Pause then let enemy attack
                new javax.swing.Timer(500, evt ->
                {
                    ((Timer) evt.getSource()).stop();
                    doEnemyAttack();
                }).start();
            }
        }
    }


    public void doEnemyAttack()
    {
        if (Weapon.vineStun == true) //If enemy is stunned, then let the player attack again
        {
            new javax.swing.Timer(500, evt ->
            {
                ((Timer) evt.getSource()).stop();
                //Re-Enabling Buttons so user can attack again
                useArcherButton.setEnabled(true);
                useMeleeButton.setEnabled(true);
                useMagicButton.setEnabled(true);
                skipTurnButton.setEnabled(true); //These 4 lines should stay inside this block to prevent user from clicking button in between method calls, breaking the game
            }).start();
        }

        else
        {
            //Enemy Attack
            int enemyDamage = currentEnemy.calculateDamage();
            int newHealth = currentPlayer.getCurrentHealth() - enemyDamage;
            currentPlayer.setHealth(newHealth);
            System.out.println("The enemy did " + enemyDamage + " damage to you! You have " + newHealth + " health left.");

            //Other
            turnIndicatorLabel.setText("Your Turn");
            updateLabels();
            
            //If the Player DIES from that attack
            if (currentPlayer.getCurrentHealth() <= 0)
            {
                // Pause for a bit just to lock the buttons and prevent the user from breaking the game in-between rounds
                useArcherButton.setEnabled(false);
                useMeleeButton.setEnabled(false);
                useMagicButton.setEnabled(false);
                skipTurnButton.setEnabled(false);

                new javax.swing.Timer(1000, evt ->
                {
                    ((Timer) evt.getSource()).stop();
                    doEnemyWinsRound();
                }).start();
            }

            //If player is still alive, then do this:
            else
            {
                //Player Regens 10 HP & 1 Energy
                if (usedWeapon == true)
                {
                    newHealth = currentPlayer.getCurrentHealth() + 10;
                    currentPlayer.setHealth(newHealth);
                    int newEnergy = currentPlayer.getCurrentEnergy() + 1;
                    currentPlayer.setEnergy(newEnergy);
                    System.out.println("You regained 10 HP and 1 Energy, so now you have " + currentPlayer.getCurrentHealth() + " health and " + currentPlayer.getCurrentEnergy() + " energy.");
                }

                //Checking if poison/bleeding were inflicted
                if (Weapon.bleedingEffect == true)
                {
                    int health = currentEnemy.getCurrentHealth() - 20; //Inflicting bleeding damage
                    currentEnemy.setHealth(health);
                    System.out.println("20 Bleeding Damage was inflicted onto the enemy! They now have " + currentEnemy.getCurrentHealth() + " health left.");
                }

                if (Weapon.poisonEffect == true)
                {
                    int health = currentEnemy.getCurrentHealth() - 5; //Inflicting poison damage
                    currentEnemy.setHealth(health);
                    System.out.println("5 Poison Damage was inflicted onto the enemy! They now have " + currentEnemy.getCurrentHealth() + " health left.");
                }

                updateLabels();

                //If the enemy DIES from poison or bleeding
                if (currentEnemy.getCurrentHealth() <= 0)
                {
                    // Pause for a bit just to lock the buttons and prevent the user from breaking the game in-between rounds
                    useArcherButton.setEnabled(false);
                    useMeleeButton.setEnabled(false);
                    useMagicButton.setEnabled(false);
                    skipTurnButton.setEnabled(false);

                    //Turn poison and bleed flags off so they can be re-enabled for a new enemy
                    Weapon.bleedingEffect = false;
                    Weapon.poisonEffect = false;

                    new javax.swing.Timer(1000, evt ->
                    {
                        ((Timer) evt.getSource()).stop();
                        doPlayerWinsRound();
                    }).start();
                }

                new javax.swing.Timer(2000, evt ->
                {
                    ((Timer) evt.getSource()).stop();
                    //Re-Enabling Buttons so user can attack again
                    useArcherButton.setEnabled(true);
                    useMeleeButton.setEnabled(true);
                    useMagicButton.setEnabled(true);
                    skipTurnButton.setEnabled(true); //These 4 lines should stay inside this block to prevent user from clicking button in between method calls, breaking the game
                }).start();
            }
        }
    }



    public void doPlayerWinsRound()
    {
        //Player Rewards
        int newHealth = currentPlayer.getCurrentHealth() + 100;
        currentPlayer.setHealth(newHealth);
        int newEnergy = currentPlayer.getCurrentEnergy() + 5;
        currentPlayer.setEnergy(newEnergy);
        int newPoints = currentPlayer.getPoints() + 1;
        currentPlayer.setPoints(newPoints);

        updateLabels();

        System.out.println("Well done, Warrior! You have slain the enemy. +100 Health, +5 Energy, +1 Point.");

        keepFighting = false; //Generate a new enemy

        //Wait a few seconds and go to next round
        new javax.swing.Timer(1000, evt ->
        {
            ((Timer) evt.getSource()).stop();
            endOrNextRound();
        }).start();
    }


    
    public void doEnemyWinsRound()
    {
        //Enemy Rewards
        int newPoints = Enemy.getPoints() + 1;
        Enemy.setPoints(newPoints);

        //Replenishing Player HP and Energy
        currentPlayer.setHealth(currentPlayer.getTotalHealth());
        currentPlayer.setEnergy(currentPlayer.getTotalEnergy());
        updateLabels();

        System.out.println("You have been slain, Warrior! The Enemy recieves +1 Point.");

        keepFighting = false; //Generate a new enemy

        //Wait a few seconds and go to next round
        new javax.swing.Timer(1000, evt -> {
            ((Timer) evt.getSource()).stop();
            endOrNextRound();
        }).start();
    }



    private void displayEnemy(Enemy someEnemy)
    {
        //Re-Enabling Buttons so user can attack again
        useArcherButton.setEnabled(true);
        useMeleeButton.setEnabled(true);
        useMagicButton.setEnabled(true);
        skipTurnButton.setEnabled(true);

        //Clearing Panel
        enemyPanel.removeAll();
        enemyPanel.revalidate();
        enemyPanel.repaint();

        //Do NOT do enemyPanel setBounds, setLayout and setBG because SOMEHOW that affects player energy bar accuracy???
        // enemyPanel.setBounds(0,60,850,300); enemyPanel.setLayout(null); enemyPanel.setBackground(new Color(0, 0, 0, 0));

        // Title
        JLabel nameLabel = new JLabel("Enemy: " + someEnemy.getName());
        nameLabel.setForeground(new Color(0, 0, 139)); // Dark Blue
        nameLabel.setFont(new Font("Arial", Font.BOLD, 35));
        nameLabel.setBounds(200, 35, 500, 50);
        enemyPanel.add(nameLabel);
    
        // Stats
        JLabel statLabel = new JLabel("<html><font color='#FF1493'>" +
            "<b>DMG: " + someEnemy.getLowerBound() + "-" + someEnemy.getUpperBound() + "</b><br><br>" +
            "<b>Health: " + someEnemy.getCurrentHealth() + "</b></font></html>");
        statLabel.setFont(new Font("Arial", Font.BOLD, 22));
        statLabel.setBounds(250, 80, 250, 100);
        enemyPanel.add(statLabel);
    
        // Image
        ImageIcon icon = new ImageIcon(someEnemy.getImagePath());
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setBounds(680, 10, 150, 150);
        enemyPanel.add(imgLabel);

        //Enemy Healthbar
        enemyHealthBar = new Bar(650,260,200,40,Color.RED);
        enemyHealthBarLabel = new JLabel("Enemy Health");
        enemyHealthBarLabel.setBounds(660, 170, 250, 35);
        enemyHealthBarLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        
        enemyHealthFraction = currentEnemy.getCurrentHealth() + "/" + currentEnemy.getTotalHealth();
        enemyHealthFractionLabel = new JLabel(enemyHealthFraction);
        enemyHealthFractionLabel.setBounds(80,5,100,30);
        enemyHealthFractionLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); // Bold and readable

        enemyHealthBar.add(enemyHealthFractionLabel);
        enemyPanel.add(enemyHealthBar);
        enemyPanel.add(enemyHealthBarLabel);
        this.add(enemyPanel);
    }



    private void displayPlayerBars()
    {
        //Player Health Bar
        playerHealthBar = new Bar(135, 715, 200, 40, Color.RED);
        playerHealthBarLabel = new JLabel("Player Health");
        playerHealthBarLabel.setBounds(165, 675, 275, 35);
        playerHealthBarLabel.setFont(new Font("Helvetica", Font.BOLD, 25));

        playerHealthFractionLabel = new JLabel();
        playerHealthFractionLabel.setText(currentPlayer.getCurrentHealth() + "/" + currentPlayer.getTotalHealth());
        playerHealthFractionLabel.setBounds(80, 650, 100 ,30);
        playerHealthFractionLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); // Bold and readable

        playerHealthBar.add(playerHealthFractionLabel);
        this.add(playerHealthBar);
        this.add(playerHealthBarLabel);

        playerEnergyBar = new Bar(665, 715, 200, 40, Color.GREEN);
        playerEnergyBarLabel = new JLabel("Player Energy");
        playerEnergyBarLabel.setBounds(685, 675, 275, 35);
        playerEnergyBarLabel.setFont(new Font("Helvetica", Font.BOLD, 25));

        playerEnergyFractionLabel = new JLabel();
        playerEnergyFractionLabel.setText(currentPlayer.getCurrentEnergy() + "/" + currentPlayer.getTotalEnergy());
        playerEnergyFractionLabel.setBounds(80, 650, 100, 30); // Adjust as needed for alignment
        playerEnergyFractionLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); // Bold and readable
        playerEnergyFractionLabel.setForeground(Color.BLACK); // Make sure it contrasts with the bar
        
        playerEnergyBar.add(playerEnergyFractionLabel);
        this.add(playerEnergyBar);
        this.add(playerEnergyBarLabel);
    }



    public void updateLabels()
    {
        //Player Healthbars Update
        playerHealthFractionLabel.setText(currentPlayer.getCurrentHealth() + "/" + currentPlayer.getTotalHealth());
        playerEnergyFractionLabel.setText(currentPlayer.getCurrentEnergy() + "/" + currentPlayer.getTotalEnergy()); //This is not being properly updated

        //Enemy Panel Update

        //Clearing Panel
        enemyPanel.remove(enemyHealthBar);
        enemyPanel.revalidate();
        enemyPanel.repaint();

        //Panel
        enemyPanel.setBounds(0,60,1000,250);
        enemyPanel.setLayout(null);
        enemyPanel.setBackground(new Color(255, 255, 255)); //4th Parameter as 0 = Transparent BG so there's no cropping with the enemy health bar

        //Enemy Healthbar
        enemyHealthBar = new Bar(650,200,200,40,Color.RED);
        enemyHealthBarLabel = new JLabel("Enemy Health");
        enemyHealthBarLabel.setBounds(660, 225, 250, 35);
        enemyHealthBarLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        
        enemyHealthFraction = currentEnemy.getCurrentHealth() + "/" + currentEnemy.getTotalHealth();
        enemyHealthFractionLabel = new JLabel(enemyHealthFraction);
        enemyHealthFractionLabel.setBounds(80,5,100,30);
        enemyHealthFractionLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        enemyHealthBar.add(enemyHealthFractionLabel);
        enemyPanel.add(enemyHealthBar);
        this.add(enemyPanel);
    }



    public void updateRoundsAndScore()
    {
        roundsLabel.setText("Round " + currentRound + "/" + Select_Round.getTotalRounds());
        scoreLabel.setText("Score: " + Player.getPoints() + "-" + Enemy.getPoints());
    }



    // Adds a weapon with image, title, and stats to the given panel
    private void addWeaponToPanel(Weapon someWeapon, JPanel panel, Color titleColor, Color statColor)
    {
        // Image
        ImageIcon icon = new ImageIcon(someWeapon.getImagePath());
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(200, 60, 100, 100);
        panel.add(imageLabel);

        // Title
        JLabel title = new JLabel(someWeapon.getName());
        title.setFont(new Font("Georgia", Font.BOLD, 20));
        title.setForeground(titleColor);
        title.setBounds(20, 20, 250, 30);
        panel.add(title);

        // Stats
        JLabel statsLabel = new JLabel("<html> Damage: " + someWeapon.getLowerBound() + "-" + someWeapon.getUpperBound() + "<br>" + "Energy: " + someWeapon.getEnergyCost() + "<html>");
        statsLabel.setBounds(20, 60, 220, 90);
        panel.add(statsLabel);

        JLabel statLabel = new JLabel("<html><div style='color:" + toHex(statColor) + "; font-family:Comic Sans MS; font-size:14px;'>"
            + "DMG: <b>" + someWeapon.getLowerBound() + "-" + someWeapon.getUpperBound() + "</b><br>"
            + "Energy: <b>" + someWeapon.getEnergyCost() + "</i></div></html>");
        statLabel.setBounds(20, 60, 220, 90);
        //panel.add(statLabel);
    }



    public class Bar extends JPanel
    {
        private Color barColor;
    
        public Bar(int x, int y, int width, int height, Color barColor)
        {
            this.barColor = barColor;
            this.setBounds(x, y, width, height);
            this.setOpaque(true); 
        }
    
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(barColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    
        public void setBarColor(Color barColor)
        {
            this.barColor = barColor;
            repaint();
        }

        public void setBorder(Color color)
        {
            this.setBorder(BorderFactory.createLineBorder(color, 5)); // 2 is thickness
        }
    }



    private String toHex(Color color)
    {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}