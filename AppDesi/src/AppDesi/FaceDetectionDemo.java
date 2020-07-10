package AppDesi;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FaceDetectionDemo {

	public static void main(String[] args) {
		//スタートボタン
			    String panelName = "openning";
			    OpeningPanel openingPanel = new OpeningPanel(panelName);
			    JPanel cardPanel = new JPanel();
			    JFrame frame = new JFrame();
			    CardLayout layout = new CardLayout();
			    boolean i =false;

			    cardPanel.setLayout(layout);
			    cardPanel.add(openingPanel,panelName);
			    openingPanel.showPanel(panelName);

			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			    //パネルの表示
			    frame.setTitle("ゲーム");
			    frame.getContentPane().add(cardPanel,BorderLayout.CENTER);
			    frame.setBounds(0, 0, 1500, 1000);
			    frame.setVisible(true);


			    openingPanel.changePanel(panelName,frame);
			    while(!i) {
				    i=openingPanel.opening(frame);
			    	try {
			    	Thread.sleep(100);
			    }catch(Exception e){
			    }
			}



	    //完成図画像のファイルパス
		String filepath = null;
		int count=0; //正解の物に対するゲットカウント
		int a = 0; //全ての物に対するゲットカウント


		String buf = "test";

		// *****************************
		// 処理開始
		// *****************************

		// OpenCVを使うために必ず入れる一行
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// 映像取得モジュールのインスタンス化
		VideoCaptureModule vcm = new VideoCaptureModule(buf);

		// 顔検出モジュールのインスタンス化
		FaceDetection fd = new FaceDetection();

		// 画像処理モジュールのインスタンス化
		MyImageProcessing mip = new MyImageProcessing();
		// mip.readImage("/Users/yoshiokarina/Documents/sugimura.png");

		if(i) {
		// --------------------------------------------------------------------
		// ビデオ処理の例
		// --------------------------------------------------------------------
		// 映像取得開始
		// カメラが開いている間処理を続ける

	mip.readImage("./kago.png");

	//落とすもののインスタンス生成
		FallImage glass = new FallImage(); //メガネ
		FallImage border = new FallImage(); //ボーダー
		FallImage pc =  new FallImage(); //PC
		FallImage comment =  new FallImage(); //吹き出し
		FallImage incorrect = new FallImage();//不正解
		FallImage incorrect2 = new FallImage();//不正解
		FallImage incorrect3 = new FallImage();//不正解
		FallImage incorrect4 = new FallImage();//不正解



		glass.readImage("./glass2.jpeg");
		glass.resizeFallObjectImage();
		border.readImage("./border2.jpeg");
		border.resizeFallObjectImage();
		pc.readImage("./pc2.jpeg");
		pc.resizeFallObjectImage();
		comment.readImage("./comment2.jpeg");
		comment.resizeFallObjectImage();
		incorrect.readImage("./apple2.png");
		incorrect.resizeFallObjectImage();
		incorrect2.readImage("./apple2.png");
		incorrect2.resizeFallObjectImage();
		incorrect3.readImage("./apple2.png");
		incorrect3.resizeFallObjectImage();
		incorrect4.readImage("./apple2.png");
		incorrect4.resizeFallObjectImage();

		int mrectY = 0;

		//取れたものの判定用フィールド
		boolean glassGet = false;
		boolean borderGet = false;
		boolean pcGet = false;
		boolean commentGet = false;


		//現在図表示用のインスタンス生成
		Image I1= new Image();
        I1.readImage("./teacher12.jpg");

        Image I2= new Image();
        I2.readImage("./teacher13.jpg");

		long start = System.currentTimeMillis();//開始時間


		while (vcm.isCameraOpened()) {
			Mat image = vcm.getFrameFromCamera(); // カメラ映像から画像を一枚取り出す

			// ---------------------------
			// カメラ画像の縮小（処理高速化のため）
			// ---------------------------
		double ratio = 1.2; // 入力画像を縮小する割合
		Imgproc.resize(image, image, new Size(0, 0), ratio, ratio);

			// ---------------------------
			// 顔検出の実行
			// ---------------------------
			MatOfRect mor = fd.execFaceDetection(image);

			// ---------------------------
			// 顔領域に対する画像処理
			// ---------------------------
			mip.drawDetectionResults(image, mor); // 顔位置に矩形を描画

	        I1.drawDetectionResults(image, mor, 80);
	        I2.drawDetectionResults(image, mor, 520);
	        
	        if(incorrect.isGet(image, mor)) {
	        	System.out.println("Get!");
	        	a++;
	        }
	        if(incorrect2.isGet(image, mor)) {
	        	System.out.println("GET!");
	        	a++;
	        }
	        if(incorrect3.isGet(image, mor)) {
	        	System.out.println("GET!");
	        	a++;
	        }
	        if(incorrect4.isGet(image, mor)) {
	        	System.out.println("GET!");
	        	a++;
	        }

		    if(glass.isGet(image,mor) ){
				System.out.println("せいかい！");
				count++;
				a++;
			    glassGet = true;

			    if(glassGet) {
			    	filepath = "./teacher1.jpg";
			     I1.readImage(filepath);
			              I1.drawDetectionResults(image, mor, 80);
				}
		    }
		    if(border.isGet(image, mor)) {
		    	System.out.println("正解！");
		    	count++;
		    	a++;
		        borderGet = true;

		        if(glassGet && borderGet){
		        	filepath = "./teacher3.jpg";
		         I1.readImage(filepath);
		               I1.drawDetectionResults(image, mor, 80);
		           }else if(borderGet) {
		        	   filepath = "./teacher11.jpg";
		               I1.readImage(filepath);
		               I1.drawDetectionResults(image, mor, 80);
		           }
		    }
		    if(pc.isGet(image, mor)) {
		    	System.out.println("正解！");
		    	count++;
		    	a++;
		        pcGet=true;

		        if(glassGet && borderGet && pcGet) {
		        	filepath = "./teacher6.jpg";
		         I1.readImage(filepath);
		               I1.drawDetectionResults(image, mor, 80);
		           }else if(borderGet && pcGet) {
		        	   filepath = "./teacher10.jpg";
		               I1.readImage(filepath);
		               I1.drawDetectionResults(image, mor, 80);
		           }else if(glassGet && pcGet) {
		        	   filepath = "./teacher2.jpg";
		               I1.readImage(filepath);
		               I1.drawDetectionResults(image, mor, 80);
		           }else if(pcGet) {
		        	   filepath = "./teacher17.jpg";
		               I1.readImage(filepath);
		               I1.drawDetectionResults(image, mor, 80);
		           }
		    }
		    if(comment.isGet(image,mor)) {
		    	System.out.println("正解！");
		    	count++;
		    	a++;
		        commentGet=true;

		          if(glassGet && borderGet && pcGet && commentGet) {
		        	  filepath = "./teacher13.jpg";
		           I1.readImage(filepath);
		              I1.drawDetectionResults(image, mor, 80);
		          }else if(borderGet && pcGet && commentGet) {
		        	  filepath = "./teacher5.jpg";
		              I1.readImage(filepath);
		              I1.drawDetectionResults(image, mor, 80);
		          }else if(glassGet && pcGet && commentGet) {
		        	  filepath = "./teacher14.jpg";
		              I1.readImage(filepath);
		              I1.drawDetectionResults(image, mor, 80);
		          }else if(glassGet && borderGet && commentGet) {
		        	  filepath = "./teacher7.jpg";
		              I1.readImage(filepath);
		              I1.drawDetectionResults(image, mor, 80);
		          }else if(glassGet && commentGet) {
			        	I1.readImage("./teacher0.jpg");
			            I1.drawDetectionResults(image, mor, 80);
			        }else if(borderGet && commentGet) {
			        	I1.readImage("./teacher9.jpg");
		                I1.drawDetectionResults(image, mor, 80);
			        }else if(pcGet && commentGet) {
			        	I1.readImage("./teacher15.jpg");
			            I1.drawDetectionResults(image, mor, 80);
		          }else if(commentGet) {
			        	I1.readImage("./teacher4.jpg");
			            I1.drawDetectionResults(image, mor, 80);
		          }
		    }


			vcm.showImage(image); // 取り込んだ画像を表示


			//+--------------------------
			//物をふらす
			//+--------------------------

			glass.fallImage(image,mor);
			incorrect.fallImage(image, mor);
			mrectY = glass.getMRect().y;
			if(mrectY > image.rows()/1.5) {
			border.fallImage(image, mor);
			incorrect2.fallImage(image, mor);
			mrectY  = border.getMRect().y;
			}
			if(mrectY > image.rows()/1.5) {
			pc.fallImage(image, mor);
			incorrect3.fallImage(image, mor);
			mrectY = pc.getMRect().y;
			}

			if(mrectY > image.rows()/1.5) {
			comment.fallImage(image, mor);
			incorrect4.fallImage(image, mor);
			}



						// 表示している画面をアクティブにするとキー入力を受け付ける
						vcm.getInputKey();
						long currenttime = System.currentTimeMillis();//経過時間
								System.out.println(currenttime-start);
								if(currenttime-start>30*1000)//単位がmsのため"*1000"をおこなっている
									break;
								if(a==4) break;
		}
		}

		// 映像取得を終了する（プログラムを終了する）メソッド
		if(vcm.getInputKey() == 81) {
		vcm.stopVideoCapture();
		}



		//結果表示
	    //インスタンス生成


	    Result result = new Result();
	    int key = 0;
	    boolean isClose = false;

	    result.readImage(filepath);
	    while(key != 81) {
	    	result.showImage();
	    	key = result.getInputKey();
	    }

	    if(key == 81) {
	    	isClose = true;
	    	key = 0;
	    }

	    if(isClose) {

	    if(count==0) result.readImage("./result0.png");
	    if(count==1) result.readImage("./result25.png");
	    if(count==2) result.readImage("./result50.png");
	    if(count==3) result.readImage("./result75.png");
	    if(count==4) result.readImage("./result100.png");

	    

	    while(key != 81) {
	    result.showImage();
	    key = result.getInputKey();
	    }
	    }
	}
	}




