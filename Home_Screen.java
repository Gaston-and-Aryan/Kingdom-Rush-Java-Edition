import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home_Screen
{
    private JFrame home_screen;
    private JButton start_game, instruction;
    private JLabel creditsLabel;

    public Home_Screen() {
        home_screen = new JFrame("Home Page");
        home_screen.setPreferredSize(new Dimension(1000, 800));
        home_screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home_screen.pack();
        home_screen.setLocationRelativeTo(null);
        home_screen.setResizable(false);

        BackgroundPanel background = new BackgroundPanel("Home page image/kingdom-rush-archers-and-assassins.jpg");
        background.setLayout(null);

        // Title with attractive styling
        JLabel title = new JLabel("🏰 Kingdom Rush: Java Edition 👑", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 50)); // More regal font
        title.setForeground(new Color(255, 215, 0)); // Gold
        title.setBounds(100, 50, 800, 80);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 150)); // Slightly more transparent black
        title.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 180, 140), 5), // Light brown border
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        background.add(title);

        // Start Button with improved style
        start_game = createStyledButton("Start Game");
        start_game.setBounds(400, 275, 200, 60);
        background.add(start_game);

        // Instructions Button with improved style
        instruction = createStyledButton("Instructions");
        instruction.setBounds(400, 375, 200, 60);
        background.add(instruction);

        // Attractive Credits Label
        creditsLabel = new JLabel("Created By: Gaston C. & Aryan D. | Last Updated: May 2025", SwingConstants.CENTER);
        creditsLabel.setFont(new Font("Goudy Old Style", Font.ITALIC, 24)); // Elegant, old-style font
        creditsLabel.setForeground(new Color(255, 223, 0)); // Light gold
        creditsLabel.setBounds(135, 680, 750, 60);
        creditsLabel.setOpaque(true);
        creditsLabel.setBackground(new Color(0, 0, 0, 180)); // Semi-transparent black
        creditsLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 82, 45), 3), // Sienna border
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        background.add(creditsLabel);

        // Add background
        home_screen.setContentPane(background);
        home_screen.setVisible(true);

        // Button actions
        start_game.addActionListener(e -> {
            home_screen.setVisible(false);
            new Select_Round();
        });

        instruction.addActionListener(e -> {
            home_screen.setVisible(false);
            new Instruction();
        });
    }

    private JButton createStyledButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Georgia", Font.BOLD, 20)); // Slightly larger font
        button.setForeground(Color.WHITE); // Off-white
        button.setBackground(new Color(50,30,10)); // Saddle brown
        button.setBorder(BorderFactory.createLineBorder(new Color(205, 133, 63), 4)); // Peru border
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e)
            {
                button.setBackground(new Color(160, 82, 45)); // Sienna on hover
            }

            public void mouseExited(MouseEvent e)
            {
                button.setBackground(new Color(50,30,10)); // Saddle brown on exit
            }
        }
        
        );

        return button;
    }

    // Custom panel that loads and draws background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Failed to load background image.");
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}