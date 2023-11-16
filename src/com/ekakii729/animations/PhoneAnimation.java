/*
 * Author: Abhay Manoj
 * Purpose: Phone Animation
 * Date of Creation: November 15, 2023
 */

package com.ekakii729.animations;
import com.ekakii729.graphicsLibrary.*;
import java.awt.*;
import java.util.Random;

public class PhoneAnimation {

    private static final Random RANDOM = new Random(); // used to generate random numbers
    private static GraphicsConsole console; // console used to show graphics

    public PhoneAnimation(GraphicsConsole console, int numberOfFrames) throws InterruptedException {
        PhoneAnimation.console = console;
        console.setColor(Color.PINK);
        console.fillRect(0,0,800,800);
        drawGraphics(numberOfFrames);
        fadeOut();
    }

    /** Method Name: drawGraphics
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws the graphics on screen
     * @Parameters numberOfFrames - number of frames to run for
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: InterruptedException
     */

    private void drawGraphics(int numberOfFrames) throws InterruptedException {
        final int SLEEP_TIME_IN_MILLISECONDS = 2000; // the sleep time of the graphics
        int frameCounter = 0; // counter for amount of elapsed frames
        while (frameCounter < numberOfFrames) {
            console.setBackgroundColor(getRandomColor());
            drawPhoneBorder();
            Thread.sleep(SLEEP_TIME_IN_MILLISECONDS);
            drawPhoneScreen();
            drawText();
            Thread.sleep(SLEEP_TIME_IN_MILLISECONDS);
            console.clear();
            frameCounter += 10;
        }
    }

    /** Method Name: getRandomColor
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description gets a random color
     * @Parameters N/A
     * @Returns a random color, Data Type: Color
     * Dependencies: Random
     * Throws/Exceptions: N/A
     */

    private Color getRandomColor() {
        return new Color(RANDOM.nextInt(254) + 1, RANDOM.nextInt(254) + 1, RANDOM.nextInt(254) + 1); // max rgb value is 255
    }

    /** Method Name: drawPhoneBorder
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws the border of the phone
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawPhoneBorder() {
        console.setColor(Color.BLACK);
        console.fillRoundRect(300,200,200,400,20,20); //  black rectangle in the middle
    }

    /** Method Name: drawPhoneScreen
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws the screen of the phone
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawPhoneScreen() {
        console.setColor(new Color(196, 15, 166)); // a strong pink
        console.fillRoundRect(310,210,180,380,20,20); // slightly smaller than the border
    }

    /** Method Name: drawText
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws the text on the screen
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawText() {
        console.setColor(Color.WHITE);
        console.setFont(new Font("Arial", Font.BOLD, 50));
        console.drawString("9:41", 350, 300);
        console.setFont(new Font("Arial", Font.PLAIN, 20));
        console.drawString("You got mail!", 320, 350);
    }

    /** Method Name: fadeOut
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description fades the screen out
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: InterruptedException
     */

    private void fadeOut() throws InterruptedException {
        drawPhoneBorder();
        drawPhoneScreen();
        drawText();
        int fadeOutFrames = 50; // amount of frames to fade out
        while (fadeOutFrames > 0) {
            console.setColor(new Color(0,0,0, 0.1f)); // black, gets darker as the loop continues
            console.fillRect(0,0,800,800);
            Thread.sleep(100);
            fadeOutFrames--;
        }
    }

    /** Method Name: main
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description main method of the program
     * @Parameters args - arguments to be passed in
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    public static void main(String[] args) throws InterruptedException {
        new PhoneAnimation(new GraphicsConsole(800,800), 10);
    }
}
