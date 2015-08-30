package cn.com.poka.gzhquery.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.poka.gzhquery.util.PictureUtil;

public class PictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mon = request.getParameter("mon");
		PictureUtil pictureUtil = new PictureUtil();
	
		response.setContentType("image/jpeg");

		byte[] data = null;

		if ( mon!=null &&mon.length()>0 ) {
			//获取图片的byte数据
			data = pictureUtil.GetImgByteByMon(mon);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(data, 0, data.length);
			outputStream.close();
			outputStream = null;
			response.flushBuffer();
			//清除输出流，防止释放时被捕获异常
			/*outputStream.clear();
			out = pageContext.pushBody();*/
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}


}
