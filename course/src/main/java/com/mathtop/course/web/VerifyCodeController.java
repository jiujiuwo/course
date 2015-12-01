package com.mathtop.course.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/verifycode")
public class VerifyCodeController {
	private int width = 100;// 定义图片的width
	private int height = 30;// 定义图片的height
	private int codeCount = 4;// 定义图片上显示验证码的个数
	private int xx = 20;
	private int fontHeight = 22;
	// private int codeY = 25;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private Color getRandomColor() {
		Random random = new Random();
		int red = 0, green = 0, blue = 0;
		// 产生随机的颜色分量来构造颜色值
		red = random.nextInt(255);
		green = random.nextInt(255);
		blue = random.nextInt(255);

		return new Color(red, green, blue);
	}

	public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, w, h);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}

	BufferedImage createRandomCharImage(String code) {
		BufferedImage buffImg = new BufferedImage(fontHeight, fontHeight, BufferedImage.TYPE_INT_RGB);

		Graphics gd = buffImg.getGraphics();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, fontHeight, fontHeight);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);

		// 用随机产生的颜色将验证码绘制到图像中。
		gd.setColor(getRandomColor());

		gd.drawString(code, 0, fontHeight);

		return buffImg;
	}

	@RequestMapping("/code")
	public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		// Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);

		// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {

			// 随机产生codeCount数字的验证码。

			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(36)]);

			BufferedImage charimage = createRandomCharImage(code);

			BufferedImage r = rotateImage(charimage, random.nextInt(60) - 30);

			gd.drawImage(r, (i) * xx + 10, 3, null);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);

		}

		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。

		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);

			// 线条的颜色为随机产生的颜色。
			gd.setColor(getRandomColor());

			gd.drawLine(x, y, x + xl, y + yl);
		}

		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		// System.out.print(randomCode);
		session.setAttribute("code", randomCode.toString());

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
}
