import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Select_Round extends JFrame
{
    private JLabel round;
    private JButton user_input;
    private TriangleArrowPanel leftArrow;
    private TriangleArrowPanel rightArrow;
    private JButton select_weapon;
    private JButton back_button;
    private JFrame select_round;

    //Non-JComponent IVs
    private static int numberOfRounds = 3;



    public Select_Round()
    {
        numberOfRounds = 3; //Resetting rounds to 1 every time a new game is started, instead of starting at the original value inputted

        select_round = new JFrame("Select Rounds Page");
        select_round.setSize(1000, 800);
        select_round.setLayout(null);
        select_round.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        select_round.setResizable(false);

        // Background Panel with Image
        BackgroundPanel backgroundPanel = new BackgroundPanel("Home page image\\castle-background.jpg"); // Replace with your image
        backgroundPanel.setLayout(null); // Use null layout for absolute positioning
        select_round.setContentPane(backgroundPanel);

        // Title Label
        round = new JLabel("🔢 Select Number of Rounds 🔢");
        round.setBounds(75, 125, 850, 60);
        round.setFont(new Font("Trajan Pro", Font.BOLD, 50)); // Use a more regal font
        round.setForeground(new Color(255, 223, 0)); // Gold color
        round.setHorizontalAlignment(SwingConstants.CENTER);
        round.setOpaque(true);
        round.setBackground(new Color(0, 0, 0, 150)); // Darker semi-transparent background
        round.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 180, 140), 3), // Light brown border
                new EmptyBorder(10, 10, 10, 10)
        ));

        // Count display button (non-clickable)
        user_input = new JButton("Rounds: " + numberOfRounds);
        user_input.setBounds(400, 300, 200, 60);
        user_input.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font
        user_input.setBackground(new Color(100, 180, 255));
        user_input.setForeground(Color.WHITE);
        user_input.setFocusPainted(false);
        user_input.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        // Left arrow panel
        int[] xLeft = {30, 30, 10};
        int[] yLeft = {10, 40, 25};
        leftArrow = new TriangleArrowPanel(xLeft, yLeft, 3, new Color(255, 223, 0)); // Gold arrows
        leftArrow.setBounds(345, 300, 60, 60);
        leftArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftArrow.setOpaque(false);
        leftArrow.addMouseListener(new ArrowMouse("left"));

        // Right arrow panel
        int[] xRight = {10, 10, 30};
        int[] yRight = {10, 40, 25};
        rightArrow = new TriangleArrowPanel(xRight, yRight, 3, new Color(255, 223, 0));  // Gold arrows
        rightArrow.setBounds(615, 300, 60, 60);
        rightArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightArrow.setOpaque(false);
        rightArrow.addMouseListener(new ArrowMouse("right"));

        // Select Weapon Button
        select_weapon = new JButton("Continue");
        select_weapon.setBounds(525, 500, 250, 70); // Increased size
        select_weapon.setFont(new Font("Trajan Pro", Font.BOLD, 30)); // Use a regal font
        select_weapon.setBackground(new Color(0, 150, 70)); // Darker green
        select_weapon.setForeground(Color.WHITE);
        select_weapon.setFocusPainted(false);
        select_weapon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        select_weapon.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0, 100, 50), 2), // Darker border
                new EmptyBorder(8, 16, 8, 16)
        ));
        select_weapon.addActionListener(new Action());

        // Back Button
        back_button = new JButton("Back");
        back_button.setFont(new Font("Trajan Pro", Font.BOLD, 30));
        back_button.setBounds(225, 500, 250, 70);  // Increased size
        back_button.setBackground(new Color(200, 0, 0)); // Darker red
        back_button.setForeground(Color.WHITE);
        back_button.setFocusPainted(false);
        back_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(150, 0, 0), 2), // Darker border
                new EmptyBorder(8, 16, 8, 16)
        ));
        back_button.addActionListener(new Action());

        // Add subtle glow effect to buttons
        select_weapon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_weapon.setBackground(new Color(0, 180, 90)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_weapon.setBackground(new Color(0, 150, 70));
            }
        });

        back_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back_button.setBackground(new Color(230, 0, 0)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                back_button.setBackground(new Color(200, 0, 0));
            }
        });

        // Add to frame
        backgroundPanel.add(back_button);
        backgroundPanel.add(round);
        backgroundPanel.add(user_input);
        backgroundPanel.add(leftArrow);
        backgroundPanel.add(rightArrow);
        backgroundPanel.add(select_weapon);
        select_round.setLocationRelativeTo(null);
        select_round.setVisible(true);
    }

    // ... ( rest of your Action, TriangleArrowPanel, ArrowMouse classes remain the same )
    public class Action implements ActionListener {
        public Action() {
            // default constructor
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == select_weapon)
            {
                select_round.setVisible(false);
                new WeaponSelection();
            } 

            else if (e.getSource() == back_button)
            {
                select_round.setVisible(false);
                new Home_Screen();
            }
        }
    }

    public static int getTotalRounds()
    {
        return numberOfRounds;
    }

    public class TriangleArrowPanel extends JPanel {
        private int[] x1, y1;
        private int numpoints;
        private Color color;

        public TriangleArrowPanel(int[] x1, int[] y1, int numpoints, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.numpoints = numpoints;
            this.color = color;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(color);
            g2d.fillPolygon(x1, y1, numpoints);
            g2d.dispose();
        }
    }

    public class ArrowMouse implements MouseListener
    {
        private String direction;

        public ArrowMouse(String direction)
        {
            this.direction = direction;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (direction.equals("left") && numberOfRounds > 3)
            {
                numberOfRounds--;
            }
            
            else if (direction.equals("right") && numberOfRounds < 10)
            {
                numberOfRounds++;
            }

            user_input.setText("Rounds: " + numberOfRounds);
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    // Background Panel with Image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Failed to load background image: " + imagePath);
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw the image first if it's loaded
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            // Create and draw the gradient
            Color color1 = new Color(0, 0, 50, 150); // Darker, more transparent blue
            Color color2 = new Color(0, 0, 100, 200); // Slightly lighter, still transparent blue
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        public BackgroundPanel() {
            // Constructor for fallback gradient if no image path is provided
        }
    }
}
