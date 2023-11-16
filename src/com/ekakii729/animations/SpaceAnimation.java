/*
 * Author: Abhay Manoj
 * Purpose: Space Animation
 * Date of Creation: November 15, 2023
 */

package com.ekakii729.animations;
import com.ekakii729.graphicsLibrary.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.Random;

public class SpaceAnimation {

    static class Star {

        private static final Random RANDOM = new Random(); // to generate random numbers
        private static int starSize = 20; // diameter of star
        private int xPosition; // x position of star
        private int yPosition; // y position of star

        public Star() {
            xPosition = 0;
            yPosition = 0;
        }

        /** Method Name: generateXY
         * @Author Abhay Manoj
         * @Date November 15, 2023
         * @Modified November 15, 2023
         * @Description generates x and y position for star
         * @Parameters N/A
         * @Returns N/A, Data Type: Void
         * Dependencies: N/A
         * Throws/Exceptions: N/A
         */

        public void generateXY() {
            final int MIN_POSITION_VALUE = 10; // the minimum position of the star
            final int MAX_POSITION_VALUE = 790; // the max position of the star
            xPosition = RANDOM.nextInt(MIN_POSITION_VALUE, MAX_POSITION_VALUE);
            yPosition = RANDOM.nextInt(MIN_POSITION_VALUE, MAX_POSITION_VALUE);
        }

        /** Method Name: draw
         * @Author Abhay Manoj
         * @Date November 15, 2023
         * @Modified November 15, 2023
         * @Description draws a star
         * @Parameters console - console used to show graphics
         * @Returns N/A, Data Type: Void
         * Dependencies: GraphicsConsole
         * Throws/Exceptions: N/A
         */

        public void draw(GraphicsConsole console) {
            console.fillStar(xPosition, yPosition, starSize, starSize);
        }

        public static int getStarSize() { return starSize; }
        public static void setStarSize(int starSize) { Star.starSize = starSize; }
    }

    private static final Star[] STARS_LIST = new Star[100]; // list of stars
    private static BufferedImage rocketShip; // image of the rocket
    private static GraphicsConsole console; // graphics console used to show graphics
    private static boolean starsHaveReachedMaxSize = false; // seeing if the stars have reached their max size
    private static int rocketX = -20; // x position of the rocket
    private static int rocketY = 850; // y position of the rocket

    public SpaceAnimation(GraphicsConsole console, int numberOfFrames) throws IOException, InterruptedException {
        SpaceAnimation.console = console;
        init();
        console.setBackgroundColor(Color.BLACK);
        drawGraphics(numberOfFrames);
        fadeOut();
    }

    /** Method Name: init
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description initializes some aspects of program
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole, ImageIO
     * Throws/Exceptions: IOException
     */

    private void init() throws IOException {
        rocketShip = ImageIO.read(new File("rocket.png"));
        console.setFont(new Font("Arial", Font.BOLD, 20));
        createStars();
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
        final int SLEEP_TIME_IN_MILLISECONDS = 100; // the sleep time of the graphic
        final int EQUALS_BUTTON = 61; // key code for equals button
        int frameCounter = 0; // count of elapsed frames
        while (frameCounter < numberOfFrames) {
            if (console.getKeyCode() == EQUALS_BUTTON) createStars();
            console.setBackgroundColor(Color.BLACK);
            drawStars();
            updateStarSize();
            writeHelpText();
            drawRocket();
            moveRocket();
            Thread.sleep(SLEEP_TIME_IN_MILLISECONDS);
            console.clear();
            frameCounter++;
        }
    }

    /** Method Name: createStars
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description fills star list
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private void createStars() {
        for (int i = 0; i < STARS_LIST.length; i++) {
            STARS_LIST[i] = new Star();
            STARS_LIST[i].generateXY();
        }
    }

    /** Method Name: drawStars
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws stars on screen
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawStars() {
        console.setColor(Color.WHITE);
        for (Star currentStar : STARS_LIST) currentStar.draw(console);
    }

    /** Method Name: updateStarSize
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description updates size of stars
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private void updateStarSize() {
        final int MAX_STAR_SIZE = 20; // the max size of the star
        final int MIN_STAR_SIZE = 10; // the minimum size of the star
        if (Star.getStarSize() < MIN_STAR_SIZE) starsHaveReachedMaxSize = false;
        if (Star.getStarSize() > MAX_STAR_SIZE) starsHaveReachedMaxSize = true;
        if (starsHaveReachedMaxSize) Star.setStarSize(Star.getStarSize() - 1);
        else Star.setStarSize(Star.getStarSize() + 1);
    }

    /** Method Name: writeHelpText
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description writes help text on screen
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void writeHelpText() {
        final int HELP_TEXT_X = 225; // x position of the help text
        final int HELP_TEXT_Y = 50; // y position of the help text
        console.setColor(Color.YELLOW);
        console.drawString("Press '=' to change layout of stars", HELP_TEXT_X, HELP_TEXT_Y);
    }

    /** Method Name: drawRocket
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description draws rocket on screen
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: N/A
     */

    private void drawRocket() {
        final int ROCKET_SIZE = 250; // size of rocket
        console.drawImage(rocketShip, rocketX, rocketY, ROCKET_SIZE, ROCKET_SIZE);
    }

    /** Method Name: moveRocket
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description moves rocket on screen
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private void moveRocket() {
        final int WINDOW_WIDTH = 800; // width of the window
        final int ROCKET_STARTING_X = -20; // x position of starting rocket
        final int ROCKET_STARTING_Y = 850; // y position of starting rocket
        final int ROCKET_SPEED = 10; // speed of rocket
        if (rocketX > WINDOW_WIDTH) {
            rocketX = ROCKET_STARTING_X;
            rocketY = ROCKET_STARTING_Y;
        } rocketX += ROCKET_SPEED;
        rocketY -= ROCKET_SPEED;
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
        drawStars();
        writeHelpText();
        drawRocket();
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

    public static void main(String[] args) throws IOException, InterruptedException {
        new SpaceAnimation(new GraphicsConsole(800,800), 10);
    }
}
