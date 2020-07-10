package AppDesi;

	import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

	//******************************
	//画像処理クラス
	//
	//処理：
	//
	//- 検出結果を描画する
	//
	//******************************

	public class MyImageProcessing {

		// ******************************
		// フィールド
		// ******************************
		private Mat m_imposeImg; // 重畳する画像 ←かごの画像
		
		public MyImageProcessing() {
		}

		// ------------------------------
		// 重畳すべき画像を読み込むメソッド
		// @param filePath 画像のファイルパス
		// ------------------------------
		public void readImage(String filePath) {
			m_imposeImg = Imgcodecs.imread(filePath);
		}

		// ------------------------------
		// 検出結果の座標位置に四角形を描画するメソッド
		// @param img 描画する画像
		// @param mor 顔領域を示すデータ（左上の(x,y)座標と幅と高さが格納されている）
		// ------------------------------
		public void drawDetectionResults(Mat img, MatOfRect mor) {
			for (Rect rect : mor.toArray()) {
				
				// ---------------------------
				// 1. 重畳する画像のリサイズ
				// ---------------------------
				
				double ratio = 0.5; // 拡大/縮小倍率
				Mat resizedImg = new Mat();
				Imgproc.resize(m_imposeImg, resizedImg, new Size(0, 0), ratio, ratio);

				// ---------------------------
				// 2. 重畳領域の決定
				// ---------------------------
				Rect mrect = new Rect();
				// 検出された顔領域の左上の座標を指定
				mrect.x = rect.x+rect.width/5;
				mrect.y = rect.y-resizedImg.rows();
				// 画像サイズは重畳する画像（リサイズ済み）のサイズに指定
				mrect.width = resizedImg.cols();
				mrect.height = resizedImg.rows();

				// ---------------------------
				// 3. 画像の重畳
				// ---------------------------

				// 重畳する画像がカメラ画像の範囲外にならないようにする
				try {
				if(mrect.x + mrect.width < img.cols() && mrect.y + mrect.height < img.rows()) {
					Mat roiImg = img.submat(mrect); // カメラ画像中の重畳領域の指定
					resizedImg.copyTo(roiImg); // その領域に画像を重畳（コピー）
			}
		}catch(Exception e) {
				}
			}
		}
		

		}
	

