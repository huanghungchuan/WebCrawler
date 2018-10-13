package word;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


public class TwodimeCodeUtil {
	
	private static final int DEFAULT_SIZE = 7;
	//public static final int MIN_SIZE = 12;
	//public static final int MAX_SIZE = 15;
	public static final String JPEG = "jpeg";
	public static final String PNG = "png";
	public static final String GIF = "gif"; 
	
	
	/**
	 * 
	 * @param content�洢����
	 * @param imgTypeͼƬ����
	 * @return
	 */
	public static byte[] encoderQRCodeAsByteArray(String content, String imgType) {
		return encoderQRCodeAsByteArray(content,imgType,DEFAULT_SIZE);
	}
	
	/**
	 * 
	 * @param content�洢����
	 * @param output �����
	 * @param imgTypeͼƬ����
	 */
	public static void encoderQRCodeAsOutputStream(String content,OutputStream output,String imgType) {
		encoderQRCodeAsOutputStream(content,output,imgType,DEFAULT_SIZE);
	}
	/**
	 * 
	 * @param content �洢����
	 * @param imgType ͼƬ����
	 * @param size	  ��ά��ߴ�
	 * @return
	 */
	public static byte[] encoderQRCodeAsByteArray(String content, String imgType, int size) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			BufferedImage bufImg = qRCodeCommon(content, imgType, size);
			// ���ɶ�ά��QRCodeͼƬ
			ImageIO.write(bufImg, imgType, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	/**
	 * 
	 * @param content �洢����
	 * @param output �����
	 * @param imgTypeͼƬ����
	 * @param size��ά��ߴ�
	 */
	public static void encoderQRCodeAsOutputStream(String content,OutputStream output,String imgType,int size) {
		try {
			BufferedImage bufImg = qRCodeCommon(content, imgType, size);
			// ���ɶ�ά��QRCodeͼƬ
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���ɶ�ά��(QRCode)ͼƬ�Ĺ�������
	 * @param content �洢����
	 * @param imgType ͼƬ����
	 * @param size ��ά��ߴ�
	 * @return
	 */
	private static BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// �������ö�ά��ߴ磬ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��
			qrcodeHandler.setQrcodeVersion(size);
			// ������ݵ��ֽ����飬���ñ����ʽ
			byte[] contentBytes = content.getBytes("utf-8");
			// ͼƬ�ߴ�
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// ���ñ�����ɫ
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// �趨ͼ����ɫ> BLACK
			gs.setColor(Color.BLACK);
			// ����ƫ�����������ÿ��ܵ��½�������
			int pixoff = 2;
			// �������> ��ά��
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

}
