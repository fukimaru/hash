package AppDesi;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class Result {
	private HighGui m_highGui;
	private Mat m_imposeImg;
	
	public Result() {
	}
	
	// ------------------------------
	// 重畳すべき画像を読み込むメソッド
	// @param filePath 画像のファイルパス
	// ------------------------------
	public void readImage(String filePath) {
		m_imposeImg = Imgcodecs.imread(filePath);
	}
	
	//------------------------------
	//画像を表示するメソッド
	//@param image 表示する画像
	//------------------------------
	public void showImage()
	{
		//"test"という名前のウインドウで画像表示
		m_highGui.imshow("test", m_imposeImg);
	}
	
	//------------------------------
	//入力キーを返す
	//------------------------------
	public int getInputKey() {

		return m_highGui.waitKey(20); //wait 20 msec to get any key pushed

	}

}