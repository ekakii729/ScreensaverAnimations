/*
 * Author: Abhay Manoj
 * Purpose: Runs all animations within the package
 * Date of Creation: November 15, 2023
 */

package com.ekakii729.animations;
import com.ekakii729.graphicsLibrary.GraphicsConsole;
import java.io.IOException;

public class AnimationRunner {

    private static final int numberOfFrames = 100; // number of frames each animation runs for

    /** Method Name: runAnimations
     * @Author Abhay Manoj
     * @Date November 15, 2023
     * @Modified November 15, 2023
     * @Description runs all the animations in package
     * @Parameters N/A
     * @Returns N/A, Data Type: Void
     * Dependencies: GraphicsConsole
     * Throws/Exceptions: IOException, InterruptedException
     */

    private static void runAnimations() throws IOException, InterruptedException {
        GraphicsConsole console = new GraphicsConsole(800,800, "Abhay's Animations"); // console to show graphics
        while (true) {
            new SunsetAnimation(console, numberOfFrames);
            new SpaceAnimation(console, numberOfFrames);
            new PhoneAnimation(console, numberOfFrames);
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
     * Throws/Exceptions: IOException, InterruptedException
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        runAnimations();
    }
}
