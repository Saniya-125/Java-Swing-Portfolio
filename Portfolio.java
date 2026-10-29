import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class Portfolio extends JFrame {

    private static final Color BG = new Color(12, 10, 20);
    private static final Color CARD = new Color(25, 19, 36);
    private static final Color PURPLE = new Color(183, 92, 255);
    private static final Color GREEN = new Color(76, 255, 166);
    private static final Color WHITE = new Color(245, 240, 255);
    private static final Color MUTED = new Color(174, 163, 190);

    // Replace these with your real profile URLs.
    private static final String GITHUB_URL =
            "https://github.com/Saniya-125";
    private static final String LINKEDIN_URL =
            "https://www.linkedin.com/in/saniya-kamble/";
            

    public Portfolio() {
        setTitle("Saniya Kamble | Developer Portfolio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 650));
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(BG);

        add(createHeader(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        // Use the available screen space instead of a fixed 1100 x 800 window.
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG);
        header.setBorder(new EmptyBorder(8, 25, 8, 25));

        JLabel logo = new JLabel("SANIYA.DEV");
        logo.setFont(new Font("SansSerif", Font.BOLD, 21));
        logo.setForeground(PURPLE);

        JPanel links = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        links.setOpaque(false);
        links.add(makeButton("GitHub", PURPLE,
                () -> openProfile(GITHUB_URL, "GitHub")));
        links.add(makeButton("LinkedIn", GREEN,
                () -> openProfile(LINKEDIN_URL, "LinkedIn")));

        header.add(logo, BorderLayout.WEST);
        header.add(links, BorderLayout.EAST);
        return header;
    }

    private JPanel createMainContent() {
        JPanel main = new JPanel();
        main.setBackground(BG);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(4, 25, 4, 25));

        main.add(createHero());
        main.add(Box.createVerticalStrut(10));
        main.add(createProjects());
        main.add(Box.createVerticalStrut(10));
        main.add(createBottomSection());
        return main;
    }

    private JPanel createHero() {
        JPanel hero = new GlowPanel(PURPLE);
        hero.setLayout(new BorderLayout(18, 0));
        hero.setBorder(new EmptyBorder(12, 18, 12, 18));
        hero.setMaximumSize(new Dimension(Integer.MAX_VALUE, 125));
        hero.setPreferredSize(new Dimension(1000, 125));

        JPanel intro = new JPanel();
        intro.setOpaque(false);
        intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));

        JLabel hello = new JLabel("HELLO, WORLD!");
        hello.setForeground(GREEN);
        hello.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel name = new JLabel("Hi, I'm Saniya Kamble.");
        name.setForeground(WHITE);
        name.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel role = new JLabel("BCA Student | Aspiring Developer");
        role.setForeground(PURPLE);
        role.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel about = new JLabel(
                "I enjoy coding, analyzing data and building practical projects.");
        about.setForeground(MUTED);
        about.setFont(new Font("SansSerif", Font.PLAIN, 12));

        intro.add(hello);
        intro.add(Box.createVerticalStrut(3));
        intro.add(name);
        intro.add(Box.createVerticalStrut(2));
        intro.add(role);
        intro.add(Box.createVerticalStrut(3));
        intro.add(about);

        JPanel avatar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(183, 92, 255, 35));
                g2.fillOval(0, 0, 82, 82);
                g2.setColor(PURPLE);
                g2.setStroke(new BasicStroke(2f));
                g2.drawOval(4, 4, 74, 74);
                g2.setFont(new Font("SansSerif", Font.BOLD, 24));
                FontMetrics fm = g2.getFontMetrics();
                g2.setColor(WHITE);
                g2.drawString("SK",
                        (82 - fm.stringWidth("SK")) / 2,
                        (82 + fm.getAscent() - fm.getDescent()) / 2);
                g2.dispose();
            }
        };
        avatar.setOpaque(false);
        avatar.setPreferredSize(new Dimension(82, 82));
        avatar.setMinimumSize(new Dimension(82, 82));

        hero.add(intro, BorderLayout.CENTER);
        hero.add(avatar, BorderLayout.EAST);
        return hero;
    }

    private JPanel createProjects() {
        JPanel section = new JPanel(new BorderLayout(0, 7));
        section.setOpaque(false);
        section.add(sectionTitle("FEATURED PROJECTS"), BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1, 4, 12, 0));
        cards.setOpaque(false);
        cards.add(projectCard("01", "Expense Tracker", "Python",
                "Track and organize expenses.", PURPLE, "image/expence.png"));
        cards.add(projectCard("02", "HR Analytics", "Power BI",
                "Visualize HR data and insights.", GREEN, "image/hr_analytics.png"));
        cards.add(projectCard("03", "Retail SQL Analysis", "MySQL",
                "Query and analyze retail data.", PURPLE, "image/retail.png"));
        cards.add(projectCard("04", "Student Management", "Java",
                "Manage student records.", GREEN, "image/student_mngmt.png"));

        section.add(cards, BorderLayout.CENTER);
        section.setPreferredSize(new Dimension(1000, 175));
        section.setMaximumSize(new Dimension(Integer.MAX_VALUE, 175));
        return section;
    }

    private JPanel projectCard(String number, String name, String tech,
                               String description, Color accent, String imagePath) {
        JPanel card = new GlowPanel(accent);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(8, 10, 8, 10));

        JLabel numberLabel = new JLabel("// " + number);
        numberLabel.setForeground(accent);
        numberLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        numberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon(imagePath);
        JLabel sticker = new JLabel();
        if (icon.getIconWidth() > 0) {
            Image resized = icon.getImage().getScaledInstance(
                    78, 58, Image.SCALE_SMOOTH);
            sticker.setIcon(new ImageIcon(resized));
        } else {
            sticker.setText("Image not found");
            sticker.setForeground(accent);
        }
        // Keep the sticker slightly left of the card's center.
        sticker.setAlignmentX(Component.CENTER_ALIGNMENT);
        sticker.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel projectName = new JLabel(
                "<html><b>" + name + "</b></html>");
        projectName.setForeground(WHITE);
        projectName.setFont(new Font("SansSerif", Font.BOLD, 13));
        projectName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel technology = new JLabel(tech);
        technology.setForeground(accent);
        technology.setFont(new Font("SansSerif", Font.BOLD, 11));
        technology.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descriptionText = new JTextArea(description);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setLineWrap(true);
        descriptionText.setEditable(false);
        descriptionText.setFocusable(false);
        descriptionText.setOpaque(false);
        descriptionText.setForeground(MUTED);
        descriptionText.setFont(new Font("SansSerif", Font.PLAIN, 11));
        descriptionText.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionText.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 28));

        card.add(numberLabel);
        card.add(Box.createVerticalStrut(2));
        card.add(sticker);
        card.add(Box.createVerticalStrut(2));
        card.add(projectName);
        card.add(Box.createVerticalStrut(3));
        card.add(technology);
        card.add(Box.createVerticalStrut(4));
        card.add(descriptionText);
        return card;
    }

    private JPanel createBottomSection() {
        JPanel bottom = new JPanel(new GridLayout(1, 2, 16, 0));
        bottom.setOpaque(false);

        JPanel skills = new GlowPanel(PURPLE);
        skills.setLayout(new BoxLayout(skills, BoxLayout.Y_AXIS));
        skills.setBorder(new EmptyBorder(9, 15, 8, 15));
        skills.add(sectionTitle("TECHNICAL SKILLS"));
        skills.add(Box.createVerticalStrut(4));
        skills.add(skillLine("LANGUAGES", "C  |  C++  |  Java  |  Python  |  SQL"));
        skills.add(Box.createVerticalStrut(3));
        skills.add(skillLine("WEB DEVELOPMENT",
                "HTML  |  CSS  |  JavaScript  |  Bootstrap  |  React"));
        skills.add(Box.createVerticalStrut(3));
        skills.add(skillLine("DATABASE", "MySQL  |  SQL Queries"));
        skills.add(Box.createVerticalStrut(3));
        skills.add(skillLine("DATA TOOLS", "Power BI  |  Pandas"));
        skills.add(Box.createVerticalStrut(3));
        skills.add(skillLine("CONCEPTS", "OOP  |  Data Analysis"));

        bottom.add(skills);
        bottom.add(createContactForm());
        bottom.setPreferredSize(new Dimension(800, 220));
        bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        return bottom;
    }

    private JPanel skillLine(String heading, String skillNames) {
        JPanel line = new JPanel();
        line.setOpaque(false);
        line.setLayout(new BoxLayout(line, BoxLayout.Y_AXIS));

        JLabel h = new JLabel(heading);
        h.setForeground(GREEN);
        h.setFont(new Font("SansSerif", Font.BOLD, 10));

        JLabel s = new JLabel(skillNames);
        s.setForeground(WHITE);
        s.setFont(new Font("SansSerif", Font.PLAIN, 12));

        line.add(h);
        line.add(Box.createVerticalStrut(2));
        line.add(s);
        return line;
    }

    private JPanel createContactForm() {
        JPanel contact = new GlowPanel(GREEN);
        contact.setLayout(new BorderLayout(0, 6));
        contact.setBorder(new EmptyBorder(9, 13, 9, 13));
        contact.add(sectionTitle("LET'S CONNECT"), BorderLayout.NORTH);

        JPanel fields = new JPanel(new GridLayout(3, 1, 0, 5));
        fields.setOpaque(false);
        JTextField nameField = makeTextField("Your name");
        JTextField emailField = makeTextField("Your email");
        JTextField messageField = makeTextField("Your message");
        fields.add(nameField);
        fields.add(emailField);
        fields.add(messageField);

        JButton send = makeButton("Send Message", GREEN, () -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String message = messageField.getText().trim();

            if (name.isEmpty() || name.equals("Your name")
                    || email.isEmpty() || email.equals("Your email")
                    || message.isEmpty() || message.equals("Your message")) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all three fields.",
                        "Missing information", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid email address.",
                        "Invalid email", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this,
                    "Thank you, " + name + "!\n"
                            + "Email delivery is not connected yet.",
                    "Contact form demo", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel formBody = new JPanel(new BorderLayout(0, 5));
        formBody.setOpaque(false);
        formBody.add(fields, BorderLayout.CENTER);
        formBody.add(send, BorderLayout.SOUTH);
        contact.add(formBody, BorderLayout.CENTER);
        return contact;
    }

    private JTextField makeTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setText(placeholder);
        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setForeground(MUTED);
        field.setCaretColor(WHITE);
        field.setBackground(new Color(35, 27, 47));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(75, 58, 91)),
                new EmptyBorder(4, 8, 4, 8)));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(MUTED);
                }
            }
        });
        return field;
    }

    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        return label;
    }

    private JButton makeButton(String text, Color accent, Runnable action) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(accent.equals(GREEN)
                ? new Color(8, 20, 15) : WHITE);
        button.setBackground(accent);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accent),
                new EmptyBorder(6, 12, 6, 12)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> action.run());
        return button;
    }

    private void openProfile(String url, String platform) {
        if (url.contains("your-username") || url.contains("your-profile")) {
            JOptionPane.showMessageDialog(this,
                    "Replace the placeholder " + platform
                            + " URL in Portfolio.java first.");
            return;
        }
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Your system cannot open browser links.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not open the profile link.");
        }
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 3));
        footer.setBackground(BG);
        JLabel text = new JLabel(
                "© 2026 Saniya Kamble | Built with Java Swing");
        text.setForeground(MUTED);
        text.setFont(new Font("SansSerif", Font.PLAIN, 10));
        footer.add(text);
        return footer;
    }

    private class GlowPanel extends JPanel {
        private final Color accent;

        GlowPanel(Color accent) {
            this.accent = accent;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            g2.setColor(CARD);
            g2.fillRoundRect(4, 4, w - 8, h - 8, 20, 20);
            g2.setColor(new Color(accent.getRed(), accent.getGreen(),
                    accent.getBlue(), 45));
            g2.setStroke(new BasicStroke(5f));
            g2.drawRoundRect(4, 4, w - 8, h - 8, 20, 20);
            g2.setColor(new Color(accent.getRed(), accent.getGreen(),
                    accent.getBlue(), 190));
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(4, 4, w - 8, h - 8, 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {
                // Use the default look and feel.
            }
            Portfolio portfolio = new Portfolio();
            portfolio.setVisible(true);
        });
    }
}
