package code;

import image.APImage;
import image.Pixel;

import static java.lang.Math.abs;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage apImage = new APImage("cyberpunk2077.jpg");
        apImage.draw();
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 20);


    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage apImage = new APImage(pathOfFile);
        for(int i = 0; i<apImage.getWidth();i++){
            for(int j = 0;j < apImage.getHeight();j++){
                int red = apImage.getPixel(i,j).getRed();
                int green = apImage.getPixel(i,j).getGreen();
                int blue = apImage.getPixel(i,j).getBlue();
                int avg = (red + green + blue)/3;
                Pixel newPixel = new Pixel(avg, avg, avg);
                apImage.setPixel(i,j,newPixel);
            }
        }
        apImage.draw();
    }


    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int avg = (r+b+g)/3;
        return avg;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage apImage = new APImage(pathOfFile);
        for(int i = 0; i<apImage.getWidth();i++){
            for(int j = 0;j < apImage.getHeight();j++){
                int avg = getAverageColour(apImage.getPixel(i,j));
                Pixel blackPixel = new Pixel(255, 255, 255);
                Pixel whitePixel = new Pixel(0,0,0);
                if (avg < 128){
                    apImage.setPixel(i,j,whitePixel);
                }
                else {
                    apImage.setPixel(i, j, blackPixel);
                }

            }
        }
        apImage.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage apImage = new APImage(pathToFile);
        Pixel whitePixel = new Pixel(255, 255, 255);
        Pixel blackPixel = new Pixel(0,0,0);
        for(int i = 0; i<apImage.getWidth();i++){
            for(int j = 0;j < apImage.getHeight();j++){
                if (i+1 < apImage.getWidth() && j+1 < apImage.getHeight()) {
                    int leftRed = apImage.getPixel(i,j).getRed();
                    int leftGreen = apImage.getPixel(i,j).getGreen();
                    int leftBlue = apImage.getPixel(i,j).getBlue();
                    Pixel leftClone = new Pixel(leftRed,leftGreen,leftBlue);


                    int currentAvg = getAverageColour(apImage.getPixel(i+1,j));
                    int leftAvg = getAverageColour(leftClone);
                    int downAvg = getAverageColour(apImage.getPixel(i+1,j+1));

                    if ((abs(currentAvg - leftAvg) > threshold) || (abs(currentAvg - downAvg) > threshold)) {
                        apImage.setPixel(i, j, blackPixel);
                    } else
                        apImage.setPixel(i, j, whitePixel);
                }
                else
                    apImage.setPixel(i, j, whitePixel);
            }
        }
        apImage.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {

    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {

    }

}
