/*
 * Author: Abhay Manoj
 * Purpose: Sunset Animation
 * Date of Creation: November 15, 2023
 */

package com.ekakii729.animations;
import com.ekakii729.graphicsLibrary.*;
import java.awt.*;

public class SunsetAnimation {

    private static int sunY = 200; // y position of the sun
    private static int cloudX = 200; // x position of the cloud
    private static final int cloudY = 200; // y position of the cloud
    private static boolean sunHasSet = false; // checking if the sun has set
    private static GraphicsConsole console; // graphics console used to show graphics

    public SunsetAnimation(GraphicsConsole console, int numberOfFrames) throws InterruptedException {
        SunsetAnimation.console = console;
        console.setAntiAlias(true);
        console.setBackgroundColor(Color.BLUE);
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
        final int SLEEP_TIME_IN_MILLISECONDS = 100; // the sleep time of the graphic;
        int frameCounter = 0; // counter for amount of elapsed frames
        while (frameCounter < numberOfFrames) {
            drawSun();
            moveSun();
            drawCloud();
            moveCloud();
            drawGround();
            Thread.sleep(SLEEP_TIME_IN_MILLISECONDS);
            console.clear();
            frameCounter++;
        }
    }

    /** Method Name: drawSun
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws the sun
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawSun() {
        final int SUN_X = 350; // x position of the sun
        final int SUN_SIZE = 100; // size of the sun
        console.setColor(Color.YELLOW);
        console.fillOval(SUN_X, sunY, SUN_SIZE, SUN_SIZE);
    }

    /** Method Name: moveSun
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description moves the sun
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void moveSun() {
        final int SUN_SPEED = 5; // the speed of the sun
        final int LOWEST_POSITION = 600; // lowest position of the sun
        final int HIGHEST_POSITION = 200; // highest position of the sun
        if (sunY > LOWEST_POSITION) sunHasSet = true;
        if (sunY < HIGHEST_POSITION) sunHasSet = false;
        if (sunHasSet) sunY -= SUN_SPEED;
        else sunY += SUN_SPEED;
    }

    /** Method Name: drawCloud
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws the cloud
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawCloud() {
        final int CLOUD_WIDTH = 100; // width of the cloud
        final int CLOUD_HEIGHT = 50; //  height of the cloud
        console.setColor(Color.WHITE);
        console.fillOval(cloudX, cloudY,CLOUD_WIDTH,CLOUD_HEIGHT);
        console.fillOval( cloudX + 20, cloudY + 20, CLOUD_WIDTH, CLOUD_HEIGHT);
        console.fillOval(cloudX - 20, cloudY + 20, CLOUD_WIDTH, CLOUD_HEIGHT);
    }

    /** Method Name: moveCloud
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description moves the cloud
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void moveCloud() {
        final int CLOUD_SPEED = 10;
        final int MAX_CLOUD_X = 810;
        final int RESET_CLOUD_X = -150;
        if (cloudX > MAX_CLOUD_X) cloudX = RESET_CLOUD_X;
        cloudX += CLOUD_SPEED;
    }

    /** Method Name: drawGround
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws the ground
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawGround() {
        console.setColor(new Color(121, 84, 59)); // brown color
        console.fillRect(0, 600, 800,200);
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
        drawSun();
        drawCloud();
        drawGround();
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
        new SunsetAnimation(new GraphicsConsole(800,800), 100);
    }
}
