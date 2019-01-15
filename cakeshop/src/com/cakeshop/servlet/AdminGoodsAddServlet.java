package com.cakeshop.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cakeshop.model.Goods;
import com.cakeshop.service.GoodsService;

/**
 * Servlet implementation class AdminGoodsAddServlet
 */
@WebServlet("/admin/goods_add")
public class AdminGoodsAddServlet extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);		//文件解析器
		try {
			List<FileItem> list = upload.parseRequest(request);
			Goods goods = new Goods();
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					switch (fileItem.getFieldName()) {
					case "name":
						goods.setName(fileItem.getString("utf-8"));
						break;
					case "price":
						goods.setPrice(Float.parseFloat(fileItem.getString("utf-8")));
						break;
					case "intro":
						goods.setIntro(fileItem.getString("utf-8"));
						break;
					case "stock":
						goods.setStock(Integer.parseInt(fileItem.getString("utf-8")));
						break; 
					case "typeid":
						goods.setTypeId(Integer.parseInt(fileItem.getString("utf-8")));
						break;
					default:
						break;
					}
				} else {
					if (fileItem.getInputStream().available()<=0) {
						continue;
					}else {
						String fileName = fileItem.getName();
						fileName = fileName.substring(fileName.lastIndexOf("."));
						fileName = "/"+new Date().getTime()+fileName;
						String path = this.getServletContext().getRealPath("/picture"+fileName);
						System.out.println(path);
						InputStream in = fileItem.getInputStream();
						FileOutputStream out = new FileOutputStream(path);
						byte[] buffer = new byte[1024];
						int len = 0;
						while((len = in.read(buffer))>0) {
							out.write(buffer);
						}
						in.close();
						out.close();
						fileItem.delete();
						switch(fileItem.getFieldName()) {
						case "cover":
							goods.setCover("/picture"+fileName);
							break;
						case "image1":
							goods.setImage1("/picture"+fileName);
							break;
						case "image2":
							goods.setImage2("/picture"+fileName);
							break;
						}
					}
				}
			}
			
			gService.addGoods(goods);
			request.getRequestDispatcher("/admin/goods_list").forward(request, response);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
