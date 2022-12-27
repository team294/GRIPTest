package app;			// TODO:  Add package

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

/**
* GripPipeline class.
*
* <p>An OpenCV pipeline generated by GRIP.
*
* @author GRIP
*/
public class GripPipeline {

	//Outputs
	private Mat resizeImageOutput = new Mat();
	private Mat desaturateOutput = new Mat();

	static {
		// System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// TODO:  Point the loader to the OpenCV DLL in the installed OpenCV path
		System.load("C:\\Users\\Team294\\Documents\\Projects\\opencv\\build\\java\\x64\\opencv_java460.dll");
	}

	/**
	 * This is the primary method that runs the entire pipeline and updates the outputs.
	 */
	public Mat process(Mat source0) {		// TODO:  Return type = Mat for the final processed Mat (auto-generated coded had "void" return type)
		// Step Resize_Image0:
		Mat resizeImageInput = source0;
		double resizeImageWidth =  320.0;  // 426.0;  Use 426x240 for scaling 1920x1080
		double resizeImageHeight = 240.0;
		int resizeImageInterpolation = Imgproc.INTER_CUBIC;
		resizeImage(resizeImageInput, resizeImageWidth, resizeImageHeight, resizeImageInterpolation, resizeImageOutput);

		// Step Desaturate0:
		Mat desaturateInput = resizeImageOutput;
		desaturate(desaturateInput, desaturateOutput);
		return desaturateOutput;			// TODO:  Return the final processed Mat
	}

	/**
	 * This method is a generated getter for the output of a Resize_Image.
	 * @return Mat output from Resize_Image.
	 */
	public Mat resizeImageOutput() {
		return resizeImageOutput;
	}

	/**
	 * This method is a generated getter for the output of a Desaturate.
	 * @return Mat output from Desaturate.
	 */
	public Mat desaturateOutput() {
		return desaturateOutput;
	}


	/**
	 * Scales and image to an exact size.
	 * @param input The image on which to perform the Resize.
	 * @param width The width of the output in pixels.
	 * @param height The height of the output in pixels.
	 * @param interpolation The type of interpolation.
	 * @param output The image in which to store the output.
	 */
	private void resizeImage(Mat input, double width, double height,
		int interpolation, Mat output) {
		Imgproc.resize(input, output, new Size(width, height), 0.0, 0.0, interpolation);
	}

	/**
	 * Converts a color image into shades of grey.
	 * @param input The image on which to perform the desaturate.
	 * @param output The image in which to store the output.
	 */
	private void desaturate(Mat input, Mat output) {
		switch (input.channels()) {
			case 1:
				// If the input is already one channel, it's already desaturated
				input.copyTo(output);
				break;
			case 3:
				Imgproc.cvtColor(input, output, Imgproc.COLOR_BGR2GRAY);
				break;
			case 4:
				Imgproc.cvtColor(input, output, Imgproc.COLOR_BGRA2GRAY);
				break;
			default:
				throw new IllegalArgumentException("Input to desaturate must have 1, 3, or 4 channels");
		}
	}




}
