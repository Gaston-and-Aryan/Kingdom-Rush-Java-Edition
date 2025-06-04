import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class GameResults extends JFrame
{
    private JButton playAgainButton;

    public GameResults()
    {
        this.setTitle("Game Results");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center on screen

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 200, 255));
        titlePanel.setBounds(0, 0, 1000, 80);
        titlePanel.setLayout(null);
        this.add(titlePanel);

        JLabel titleLabel = new JLabel("📊 Game Results 📊", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        titleLabel.setBounds(300, 15, 400, 50);
        titlePanel.add(titleLabel);
        this.add(titlePanel);

        playAgainButton = new JButton("🔁 Play Again");
        playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 28));
        playAgainButton.setBounds(375, 600, 250, 70);
        playAgainButton.addActionListener(new ButtonListener());
        this.add(playAgainButton);
        

        if (Player.getPoints() > Enemy.getPoints())
        {
            showWinScreen();
        }
        
        else
        {
            showLossScreen();
        }

        setVisible(true);
    }

    private void showWinScreen()
    {
        getContentPane().setBackground(Color.YELLOW);

        JPanel winPanel = new JPanel();
        winPanel.setLayout(null);
        winPanel.setBounds(0, 80, 1000, 720);
        winPanel.setBackground(Color.YELLOW);

        JLabel winLabel = new JLabel("🎉 You Won! 🥳", SwingConstants.CENTER);
        winLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        winLabel.setOpaque(true);
        winLabel.setBackground(Color.GREEN);
        winLabel.setBounds(250, 60, 500, 80);
        winPanel.add(winLabel);

        JLabel scoreLabel = new JLabel("Final Score: " + Player.getPoints() + " - " + Enemy.getPoints(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.GREEN);
        scoreLabel.setBounds(300, 180, 400, 70);
        winPanel.add(scoreLabel);

        JLabel tag = new JLabel("FLAWLESS VICTORY", SwingConstants.CENTER);
        tag.setFont(new Font("SansSerif", Font.BOLD, 36));
        tag.setOpaque(true);
        tag.setBackground(Color.GREEN);
        tag.setBounds(300, 280, 400, 70);
        winPanel.add(tag);

        this.add(winPanel);
    }

    private void showLossScreen()
    {
        getContentPane().setBackground(Color.BLACK);

        JPanel lossPanel = new JPanel();
        lossPanel.setLayout(null);
        lossPanel.setBounds(0, 80, 1000, 720);
        lossPanel.setBackground(Color.BLACK);

        JLabel lossLabel = new JLabel("💀 You Lost! 😔", SwingConstants.CENTER);
        lossLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        lossLabel.setOpaque(true);
        lossLabel.setBackground(Color.RED);
        lossLabel.setForeground(Color.WHITE);
        lossLabel.setBounds(250, 60, 500, 80);
        lossPanel.add(lossLabel);

        JLabel scoreLabel = new JLabel("Final Score: " + Player.getPoints() + " - " + Enemy.getPoints(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.RED);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(300, 180, 400, 70);
        lossPanel.add(scoreLabel);

        JLabel tag = new JLabel("COMPLETE ANNIHILATION", SwingConstants.CENTER);
        tag.setFont(new Font("SansSerif", Font.BOLD, 32));
        tag.setOpaque(true);
        tag.setBackground(Color.RED);
        tag.setForeground(Color.WHITE);
        tag.setBounds(250, 280, 500, 70);
        lossPanel.add(tag);

        this.add(lossPanel);
    }

    public class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == playAgainButton)
            {
                dispose();
                new Home_Screen();
                Player.setPoints(0);
                Enemy.setPoints(0);
            }
        }
    }

    public static void main(String[] args)
    {
        new GameResults();
    }
}