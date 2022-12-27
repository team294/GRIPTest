package app;

import java.util.Date;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.opencv.highgui.HighGui;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("GRIPTest starting.");

        App app = new App();
        app.runPipelineMulti();
     }

    private GripPipeline pipeline;
    private VideoCapture camera;
    private Mat imageIn; // = new Mat();
    private Mat imageOut;  // = new Mat();

    public App() {
        pipeline = new GripPipeline();
		imageIn = new Mat();
		imageOut = new Mat();
        // camera = makeCamera(1, 1920, 1080, -1);			// On this laptop, Camera 1 = USB plugged-in camera
        // camera = makeCamera(1, 640, 480, -1);			// On this laptop, Camera 1 = USB plugged-in camera
        camera = makeCamera(0, 640, 480, -1);				// On this laptop, Camera 0 = built-in web camera
    }

	/**
	 * Make a connection to a camera.
	 * 
	 * @param device  Camera number.
	 * @param width  Window width in pixels.
	 * @param height Window height in pixels.
	 * @param exposure Relative exposure.
	 * @return
	 */
	public VideoCapture makeCamera(int device, int width, int height, double exposure) {
		VideoCapture camera = new VideoCapture(device);
		camera.set(Videoio.CAP_PROP_FRAME_WIDTH, width);
		camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, height);
		camera.set(Videoio.CAP_PROP_BRIGHTNESS, 50);
		if (exposure > -1.0) {
			System.out.println("\t" + exposure);
			camera.set(Videoio.CAP_PROP_AUTO_EXPOSURE, 0);
			camera.set(Videoio.CAP_PROP_EXPOSURE, exposure);
		}
		if (!camera.isOpened()) {
			throw new RuntimeException("Camera will not open");
		}
		camera.set(Videoio.CAP_PROP_CONTRAST, 200);

		return camera;
	}
    
    public void runPipelineOnce() {
        camera.read(imageIn);
        imageOut = pipeline.process(imageIn);

		// Show image on screen and wait for keypress (forces window to open).
		// An alternate approach would be to create a video server for NetworkTables
		// and to export NetworkTable keys for features identified in the video.
		HighGui.imshow("ImageIn", imageIn);
		HighGui.imshow("ImageOut", imageOut);
		HighGui.waitKey();
    }

	public void runPipelineMulti() {

		runPipelineOnce();
		
		//This method returns the time in millis
		Date date = new Date();
		long timeMilliStart = date.getTime();
  
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		runPipelineOnce();
		Date dateEnd = new Date();
		long timeMilliEnd = dateEnd.getTime();
		System.out.println("Time start: " + timeMilliStart);
		System.out.println("Time end: " + timeMilliEnd);
		System.out.println("FPS: " + (10.0/((timeMilliEnd - timeMilliStart)/1000.0)));
  
	}
}