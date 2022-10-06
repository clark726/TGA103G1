<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="image/jpeg"%>
<%@ page
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
<%!Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
int width = 80;
int height = 25;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
Graphics g = image.getGraphics();
Random random = new Random();
g.setColor(getRandColor(200, 250));
g.fillRect(0, 0, width, height);
g.setFont(new Font("rect", Font.PLAIN, 18));
g.setColor(getRandColor(160, 200));
for (int i = 0; i < 155; i++) {
	int x = random.nextInt(width);
	int y = random.nextInt(height);
	int xl = random.nextInt(12);
	int yl = random.nextInt(12);
	g.drawLine(x, y, x + xl, y + yl);
}
StringBuffer sRand = new StringBuffer();
String[] str = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w",
		"x", "y", "z", "2", "3", "4", "5", "6", "7", "8", "9" };
for (int i = 0; i < 4; i++) {
	String rand = str[random.nextInt(str.length)];
	sRand.append(rand);
	g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
	g.drawString(rand, 16 * i + 6, 19);
}
session.setAttribute("rand", sRand.toString());
g.dispose();
ImageIO.write(image, "JPEG", response.getOutputStream());
out.clear();
out = pageContext.pushBody();
%>