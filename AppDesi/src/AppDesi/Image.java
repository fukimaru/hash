package AppDesi;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Image {
 private Mat m_imposeImg1;

 //コンストラクタ
 public Image() {

 }

 public void readImage(String filePath) {
  m_imposeImg1 = Imgcodecs.imread(filePath);
 }

 public void drawDetectionResults(Mat img, MatOfRect mor,int y) {
  double ratio = 0.25; // 拡大/縮小倍率
  Mat resizedImg = new Mat();
  Imgproc.resize(m_imposeImg1, resizedImg, new Size(0, 0), ratio, ratio);

  Rect mrect = new Rect();
  // 検出された顔領域の左上の座標を指定
  mrect.x = img.cols()*3/4;
  mrect.y = y;
  // 画像サイズは重畳する画像（リサイズ済み）のサイズに指定
  mrect.width = resizedImg.cols();
  mrect.height = resizedImg.rows();

  // 重畳する画像がカメラ画像の範囲外にならないようにする
  try {
   if (mrect.x + mrect.width < img.cols() && mrect.y + mrect.height < img.rows()) {
    Mat roiImg = img.submat(mrect); // カメラ画像中の重畳領域の指定
    resizedImg.copyTo(roiImg); // その領域に画像を重畳（コピー）
   }
  }catch(Exception e) {
  }
 }
}