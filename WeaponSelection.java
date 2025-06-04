import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class WeaponSelection extends JFrame
{
    private JLabel weaponSelectionTitle, archerWeapons, meleeWeapons, magicWeapons;
    private JRadioButton rangerButton, musketeerButton, goldenButton, paladinButton, barbarianButton, forestButton, mysticButton, sorcererButton, arcaneButton;
    private JButton backButton, continueButton;
    private ButtonGroup archerButtonGroup, meleeButtonGroup, magicButtonGroup;
    private JLabel rangerTitle, musketeerTitle, goldenTitle, paladinTitle, barbarianTitle, forestTitle, mysticTitle, sorcererTitle, arcaneTitle;
    private JLabel rangerstat, musketeerstat, goldenstat, paladinstat, barbarianstat, foreststat, mysticstat, sorcererstat, arcanestat;

    //Non-JComponent IVs
    private boolean button1flag, button2flag, button3flag = false;

    //Creating Player Object
    private Player currentPlayer;



    //Creating Weapon Objects
    Weapon archerWeapon = null;
    Weapon meleeWeapon = null;
    Weapon magicWeapon = null;
    


    private Font weaponFont = new Font("Verdana", Font.PLAIN, 18);
    private Font titleFont = new Font("Serif", Font.BOLD, 35);
    private Font categoryFont = new Font("SansSerif", Font.BOLD, 24);

    private Color red = new Color(170, 0, 0);
    private Color green = new Color(0, 170, 0);
    private Color blue = new Color(0, 0, 170);
    private Color purple = new Color(120, 0, 120);
    private Color orange = new Color(204, 102, 0);
    private Color teal = new Color(0, 102, 102);

    // X positions for columns
    private int archerX = 50;
    private int meleeX = 390;
    private int magicX = 720;

    // Y start position and spacing between rows
    private int startY = 110;
    private int gapY = 180;



    public WeaponSelection()
    {
        // JFrame setup
        this.setTitle("Weapon Selection");
        this.setSize(1000, 850);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(245, 245, 245)); // Light background
        this.setLocationRelativeTo(null); // Center on screen
        this.setResizable(false);

        

        // Loading Background
        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(null); // Set layout manager for the panel
        this.setContentPane(contentPane);



        // Main Title
        weaponSelectionTitle = new JLabel("🏹 Weapon Selection 🏹", SwingConstants.CENTER);
        weaponSelectionTitle.setFont(titleFont);
        weaponSelectionTitle.setBounds(200, 5, 600, 60);
        weaponSelectionTitle.setForeground(new Color(50, 50, 50));
        this.add(weaponSelectionTitle);

        // Archer Weapons - Top Right
        archerWeapons = new JLabel("🏹 Archer Weapons");
        archerWeapons.setFont(categoryFont);
        archerWeapons.setForeground(new Color(220, 20, 60)); // Crimson Red
        archerWeapons.setBounds(50, 65, 250, 40);
        this.add(archerWeapons);

        // Melee Weapons - Center
        meleeWeapons = new JLabel("🔪 Melee Weapons");
        meleeWeapons.setFont(categoryFont);
        meleeWeapons.setForeground(new Color(30, 144, 255)); // Dodger Blue
        meleeWeapons.setBounds(380, 65, 250, 40);
        this.add(meleeWeapons);

        // Magic Weapons - Top Left
        magicWeapons = new JLabel("🪄 Magic Weapons");
        magicWeapons.setFont(categoryFont);
        magicWeapons.setForeground(new Color(138, 43, 226)); // Blue Violet
        magicWeapons.setBounds(700, 65, 250, 40);
        this.add(magicWeapons);

        addWeaponBlock(Weapon.rangersBow, archerX, startY, red);
        addWeaponBlock(Weapon.musketeersRifle, archerX, startY + gapY + 20, red);
        addWeaponBlock(Weapon.goldenLongbow, archerX, startY + 2 * gapY + 40, red);
        addWeaponBlock(Weapon.paladinsSword, meleeX, startY, green);
        addWeaponBlock(Weapon.barbariansAxe, meleeX, startY + gapY + 20, green);
        addWeaponBlock(Weapon.forestKeepersSpear, meleeX, startY + 2 * gapY + 40, green);
        addWeaponBlock(Weapon.mysticBlossom, magicX, startY, blue);
        addWeaponBlock(Weapon.sorcerersCrystal, magicX, startY + gapY + 20, blue);
        addWeaponBlock(Weapon.arcanePrism, magicX, startY + 2 * gapY + 40, blue);

        //All JRads
        rangerButton = new JRadioButton();
        rangerButton.setBounds(170, 235, 25, 25);
        rangerButton.setBackground(new Color(170,0,0));
        rangerButton.addActionListener(new ButtonListener());
        this.add(rangerButton);

        musketeerButton = new JRadioButton();
        musketeerButton.setBounds(170, 435, 25, 25);
        musketeerButton.setBackground(new Color(170,0,0));
        musketeerButton.addActionListener(new ButtonListener());
        this.add(musketeerButton);

        goldenButton = new JRadioButton();
        goldenButton.setBounds(170, 635, 25, 25);
        goldenButton.setBackground(new Color(170,0,0));
        goldenButton.addActionListener(new ButtonListener());
        this.add(goldenButton);

        paladinButton = new JRadioButton();
        paladinButton.setBounds(510, 235, 25, 25);
        paladinButton.setBackground(new Color(0, 100, 200));
        paladinButton.addActionListener(new ButtonListener());
        this.add(paladinButton);

        barbarianButton = new JRadioButton();
        barbarianButton.setBounds(510, 435, 25, 25);
        barbarianButton.setBackground(new Color(0, 100, 200));
        barbarianButton.addActionListener(new ButtonListener());
        this.add(barbarianButton);

        forestButton = new JRadioButton();
        forestButton.setBounds(510, 635, 25, 25);
        forestButton.setBackground(new Color(0, 100, 200));
        forestButton.addActionListener(new ButtonListener());
        this.add(forestButton);

        mysticButton = new JRadioButton();
        mysticButton.setBounds(840, 235, 25, 25);
        mysticButton.setBackground(new Color(100, 0, 150));
        mysticButton.addActionListener(new ButtonListener());
        this.add(mysticButton);

        sorcererButton = new JRadioButton();
        sorcererButton.setBounds(840, 435, 25, 25);
        sorcererButton.setBackground(new Color(100, 0, 150));
        sorcererButton.addActionListener(new ButtonListener());
        this.add(sorcererButton);

        arcaneButton = new JRadioButton();
        arcaneButton.setBounds(840, 635, 25, 25);
        arcaneButton.setBackground(new Color(100, 0, 150));
        arcaneButton.addActionListener(new ButtonListener());
        this.add(arcaneButton);



        // Show window
        this.setVisible(true);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        backButton.setBounds(290, 735, 200, 50);  // Bottom-left position
        backButton.setBackground(new Color(220, 50, 50)); // A nicer red
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(new ButtonListener());
        backButton.setOpaque(true);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.repaint();

        backButton.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e)
            {
                backButton.setBackground(new Color(180, 50, 50)); // 
            }

            public void mouseExited(MouseEvent e)
            {
                backButton.setBackground(new Color(255, 5, 5)); //
            }
        }
        
        );


        // Continue Button
        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        continueButton.setBounds(525, 735, 200, 50);
        continueButton.setBackground(new Color(0, 200, 100));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        continueButton.addActionListener(new ButtonListener());
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        continueButton.repaint();

        continueButton.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e)
            {
                continueButton.setBackground(new Color(0, 150, 100)); // 
            }

            public void mouseExited(MouseEvent e)
            {
                continueButton.setBackground(new Color(0, 200, 100)); //
            }
        }
        
        );

        this.add(backButton);
        this.add(continueButton);


        //Making sure that the user can only single 
        archerButtonGroup = new ButtonGroup();
        archerButtonGroup.add(rangerButton);
        archerButtonGroup.add(musketeerButton);
        archerButtonGroup.add(goldenButton);

        meleeButtonGroup = new ButtonGroup();
        meleeButtonGroup.add(paladinButton);
        meleeButtonGroup.add(barbarianButton);
        meleeButtonGroup.add(forestButton);

        magicButtonGroup = new ButtonGroup();
        magicButtonGroup.add(mysticButton);
        magicButtonGroup.add(sorcererButton);
        magicButtonGroup.add(arcaneButton);
    }



    private void addWeaponBlock(Weapon someWeapon, int x, int y, Color bgColor)
    {
        Font weaponFont = new Font("SansSerif", Font.PLAIN, 18);

        JLabel weaponTitle = new JLabel(someWeapon.getName());
        weaponTitle.setFont(weaponFont);
        weaponTitle.setBounds(x, y, 200, 25);
        this.add(weaponTitle);

        JLabel weaponStat = new JLabel("<html><font color='" + getColorHex(bgColor) + "'>" + Weapon.getStats(someWeapon) + "</font></html>");
        weaponStat.setFont(new Font("SansSerif", Font.PLAIN, 12));
        weaponStat.setBounds(x, y + 18, 160, 45);
        this.add(weaponStat);

        ImageIcon weaponIcon = new ImageIcon(someWeapon.getImagePath());
        Image weaponImg = weaponIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        weaponIcon = new ImageIcon(weaponImg);
        JLabel imageLabel = new JLabel(weaponIcon);
        imageLabel.setBounds(x, y + 90, 100, 100);
        this.add(imageLabel);

        JLabel abilityNameLabel = new JLabel(someWeapon.getAbilityName());
        abilityNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        abilityNameLabel.setBounds(x, y + 40, 300, 45);
        this.add(abilityNameLabel);

        JLabel abilityDescriptionLabel = new JLabel(someWeapon.getAbilityDescription());
        abilityDescriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        abilityDescriptionLabel.setBounds(x, y + 55, 300, 45);
        this.add(abilityDescriptionLabel);
    }



    private String getColorHex(Color color)
    {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }



    public class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == backButton)
            {
                new Select_Round();
                dispose();
            }

            else
            {
                //Making Sure that Player has selected all 3 weapons before they enter the Fight Sequence
                if (rangerButton.isSelected() || musketeerButton.isSelected() || goldenButton.isSelected())
                {
                    button1flag = true;
                }

                if (paladinButton.isSelected() || barbarianButton.isSelected() || forestButton.isSelected())
                {
                    button2flag = true;
                }

                if (mysticButton.isSelected() || sorcererButton.isSelected() || arcaneButton.isSelected())
                {
                    button3flag = true;
                }

                //If the user has clicked "Continue" while having chosen 3 weapons
                if (button1flag == true && button2flag == true && button3flag == true)
                {
                    if (rangerButton.isSelected()) {archerWeapon = Weapon.rangersBow;}
                    if (musketeerButton.isSelected()) {archerWeapon = Weapon.musketeersRifle;}
                    if (goldenButton.isSelected()) {archerWeapon = Weapon.goldenLongbow;}
                    if (paladinButton.isSelected()) {meleeWeapon = Weapon.paladinsSword;}
                    if (barbarianButton.isSelected()) {meleeWeapon = Weapon.barbariansAxe;}
                    if (forestButton.isSelected()) {meleeWeapon = Weapon.forestKeepersSpear;}
                    if (mysticButton.isSelected()) {magicWeapon = Weapon.mysticBlossom;}
                    if (sorcererButton.isSelected()) {magicWeapon = Weapon.sorcerersCrystal;}
                    if (arcaneButton.isSelected()) {magicWeapon = Weapon.arcanePrism;}

                    Player creatingPlayer = new Player(archerWeapon, meleeWeapon, magicWeapon, 250, 10);

                    //Player can only move onto the Fight Sequence when they have selected 1 weapon from each category. This button only unlocks once they do so.
                    if ((e.getSource() == continueButton) && ((button1flag == true && button2flag == true && button3flag == true)))
                    {
                        dispose();
                        new BattlePage(creatingPlayer);
                    }

                }
            }
        }
    }


    // Background
    public class GradientPanel extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Color color1 = new Color(200, 220, 250); // Light blue
            Color color2 = new Color(240, 240, 240); // Light gray
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}