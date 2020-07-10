package AppDesi;

import java.util.Random;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FallImage {
	// ******************************
	// フィールド
	// ******************************
	private Mat m_imposeImg; // 重畳する(落とす)画像 
	private Rect mrect;//落とす画像の領域
	private Mat resizedImg;//リサイズ済みの落とす画像
	private Rect []rect;
	private int count = 0;//落ちてくる物を何個取れたか



	//コンストラクタ
public FallImage() {
	mrect = new Rect();
	resizedImg = new Mat();
	
}

public void resizeFallObjectImage() {
	

	// ---------------------------
	// 1. 重畳する画像のリサイズ
	// ---------------------------
	
	double ratio = 0.1; // 拡大/縮小倍率
	Imgproc.resize(m_imposeImg, resizedImg, new Size(0, 0), ratio, ratio);
	
	// ---------------------------
	// 2. 重畳領域の決定
	// ---------------------------
	Random ram = new Random();
	mrect.y = 0;
	mrect.x = ram.nextInt(1024);

	// 画像サイズは重畳する画像（リサイズ済み）のサイズに指定
	mrect.width = resizedImg.cols();
	mrect.height = resizedImg.rows();
	}


	// ------------------------------
	// 重畳すべき画像を読み込むメソッド
	// @param filePath 画像のファイルパス
	// ------------------------------
	public void readImage(String filePath) {
		m_imposeImg = Imgcodecs.imread(filePath);

	}
	//+------------------------------
	//物を降らせるメソッド
	//画像を画面上の座標を変えながら
	//連続的にコピーする
	//上のメソッドとほぼ同じ
	//@img 落とす画像
	//@mor 顔領域の座標行列
	//+-------------------------------
	public void fallImage(Mat img,MatOfRect mor) {


		// ---------------------------
		// 3. 画像の重畳
		// ---------------------------

		// 重畳する画像がカメラ画像の範囲外にならないようにする
		try {
			int i = 0;
			rect = mor.toArray();

		if(mrect.x + mrect.width < img.cols() && mrect.y + mrect.height < img.rows()) {
			Mat roiImg = img.submat(mrect); // カメラ画像中の重畳領域の指定
			resizedImg.copyTo(roiImg); // その領域に画像を重畳（コピー）
			//もし取れたら
			if(isGet(rect[i],mrect)) {
				System.out.println("GET!");
				//画面から消えたように見せるため
				//XY座標を画面の範囲外に設定
				mrect.x = img.cols()+1000;
				mrect.y = img.rows()+1000;
				
				
			}

			//xyの座標を変えていく
			mrect.y += mrect.height/2;
			
			}
		
		
		}
		catch(Exception e) {
			}
		}
	
	
	
	//-------------------------
	//物が取れたかどうかの判定
	//@rect 検出された顔の範囲
	//@mrect 降ってくる画像の範囲
	//-------------------------
	public boolean isGet(Rect rect,Rect mrect) {
		boolean isget = false;
		if(Math.abs((rect.x+rect.width/2) - (mrect.x+mrect.width/2)) < 80 
				&& Math.abs(rect.y - mrect.y) < 100) {
			isget = true;
		}
		return isget;
	}
	
	//-------------------------
	//Mainで呼び出す用の
	//isGetメソッド
	//-------------------------
	public boolean isGet(Mat img,MatOfRect mor) {
	int i = 0;
	boolean isGet = false;
	rect = mor.toArray();
	if(i < rect.length) {
		isGet = isGet(rect[i],mrect);
	}
	i++;
	return isGet;
	}
	
	
	
	public Rect getMRect() {
		return this.mrect;
	}
	
	public Rect[] getRect() {
		return this.rect;
	}
}
