package guesscolor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

import static guesscolor.GuessColor.State.*;
import static java.lang.ClassLoader.getSystemResource;
import static javax.swing.SwingConstants.CENTER;

/**
 * Author: katooshka
 * Date: 9/27/15.
 */

public class GuessColor {
    State state = SHOWING_COLOR;
    Color rememberColor;

    public static void main(String[] args){
        new GuessColor();
    }

    public GuessColor() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(650, 650));
        frame.setLocationRelativeTo(null);

        JButton chooseColor = new JButton("Выбрать цвет");
        chooseColor.setEnabled(false);

        JLabel redLabel = new JLabel("Красный");
        JLabel greenLabel = new JLabel("Зеленый");
        JLabel blueLabel = new JLabel("Синий");
        redLabel.setEnabled(false);
        greenLabel.setEnabled(false);
        blueLabel.setEnabled(false);

        JSlider adjustRed = new JSlider(JSlider.VERTICAL, 0, 255, 127);
        JSlider adjustGreen = new JSlider(JSlider.VERTICAL, 0, 255, 127);
        JSlider adjustBlue = new JSlider(JSlider.VERTICAL, 0, 255, 127);
        for (JSlider i : new JSlider[]{adjustRed, adjustBlue, adjustGreen}) {
            i.setMajorTickSpacing(255);
            i.setMinorTickSpacing(32);
            i.setPaintTicks(true);
            i.setPaintLabels(true);
        }

        Random random = new Random();
        JLabel initialColorName = new JLabel("Первоначальный цвет", CENTER);
        JLabel initialColor = new JLabel();
        rememberColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        initialColor.setBackground(rememberColor);
        JLabel userColorName = new JLabel("Ваш цвет", CENTER);
        JLabel userColor = new JLabel();

        userColor.setBackground(new Color(
                adjustRed.getValue(),
                adjustGreen.getValue(),
                adjustBlue.getValue()));

        ChangeListener sliderMoved = e -> userColor.setBackground(new Color(
                adjustRed.getValue(),
                adjustGreen.getValue(),
                adjustBlue.getValue()
        ));

        adjustRed.addChangeListener(sliderMoved);
        adjustRed.setEnabled(false);
        adjustGreen.addChangeListener(sliderMoved);
        adjustGreen.setEnabled(false);
        adjustBlue.addChangeListener(sliderMoved);
        adjustBlue.setEnabled(false);


        for (JLabel i : new JLabel[]{initialColor, userColor}){
            i.setPreferredSize(new Dimension(frame.getWidth() / 3, frame.getWidth() / 6));
            i.setBorder(new LineBorder(new Color(25, 8, 8)));
            i.setOpaque(true);
        }

        JButton exit = new JButton("Выход");
        JButton replay = new JButton("Угадать цвет");

        ImageIcon image = new ImageIcon(getSystemResource("guesscolor/question_mark.png"));
        exit.addActionListener(e -> System.exit(0));

        ActionListener gameStarts = e -> {
            if (state == SHOWING_COLOR) {
                initialColor.setBackground(new Color(255, 255, 255));
                initialColor.setIcon(image);
                initialColor.setHorizontalAlignment(CENTER);
                for (JComponent i : new JComponent[]{adjustRed, adjustGreen, adjustBlue, chooseColor, redLabel, greenLabel, blueLabel}) {
                    i.setEnabled(true);
                }
                state = COLOR_CHOICE;
            } else if (state == RESULT_SHOWING){
                rememberColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
                initialColor.setBackground(rememberColor);
                replay.setText("Угадать цвет");
                state = SHOWING_COLOR;
            }
        };

        ActionListener userTurn = e -> {
            initialColor.setBackground(rememberColor);
            initialColor.setIcon(null);
            for (JComponent i : new JComponent[]{adjustRed, adjustGreen, adjustBlue, chooseColor, redLabel, greenLabel, blueLabel}){
                i.setEnabled(false);
            }
            replay.setText("Заново");
            state = RESULT_SHOWING;
        };

        replay.addActionListener(gameStarts);
        chooseColor.addActionListener(userTurn);

        JPanel colorChoice = new JPanel();
        colorChoice.setLayout(new FlowLayout());
        colorChoice.add(redLabel);
        colorChoice.add(adjustRed);
        colorChoice.add(greenLabel);
        colorChoice.add(adjustGreen);
        colorChoice.add(blueLabel);
        colorChoice.add(adjustBlue);
        colorChoice.add(chooseColor);

        JPanel initCol = new JPanel();
        initCol.setBorder(BorderFactory.createEmptyBorder(0, 30, 40, 30));
        initCol.setLayout(new GridLayout(2, 1, 5, 5));
        initCol.add(initialColorName);
        initCol.add(initialColor);

        JPanel userCol = new JPanel();
        userCol.setBorder(BorderFactory.createEmptyBorder(0, 30, 40, 30));
        userCol.setLayout(new GridLayout(2, 1, 5, 5));
        userCol.add(userColorName);
        userCol.add(userColor);

        Box colors = Box.createHorizontalBox();
        colors.add(Box.createVerticalStrut(30));
        colors.add(initCol);
        colors.add(Box.createVerticalStrut(30));
        colors.add(userCol);
        colors.add(Box.createVerticalStrut(30));

        Box commands = Box.createHorizontalBox();
        commands.add(Box.createVerticalStrut(30));
        commands.add(replay);
        commands.add(exit);
        commands.add(Box.createVerticalStrut(30));


        JPanel allElements = new JPanel();
        allElements.setLayout(new BorderLayout());
        allElements.add(colorChoice, BorderLayout.NORTH);
        allElements.add(colors, BorderLayout.CENTER);
        allElements.add(commands, BorderLayout.SOUTH);

        frame.setContentPane(allElements);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    enum State {
        SHOWING_COLOR,
        COLOR_CHOICE,
        RESULT_SHOWING
    }
}
