package deteccao;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class DeteccaoFaces {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagemColorida = imread("pessoas\\pessoas3.jpg", CV_LOAD_IMAGE_COLOR);
		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
		
		CascadeClassifier classificador = new CascadeClassifier("cascades\\haarcascade_frontalface_default.xml");
		
		MatOfRect facesDetectadas = new MatOfRect();
		classificador.detectMultiScale(imagemCinza, facesDetectadas,
				1.19, 	// scale factor
				3, 		// minNeighbors
				0, 		//flags
				new Size(30, 30), 		// minSize
				new Size(100, 100));	// maxSize
		
		System.out.println(facesDetectadas.toArray().length);
		
		for (Rect rect: facesDetectadas.toArray() ) {
			System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
			Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y), 
					new Point(rect.x + rect.width, rect.y + rect.height), 
					new Scalar(0, 0, 255), 2);
			
		}
		
		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imagemColorida));
		
	}

}

	