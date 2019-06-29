package com.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.store.domain.Category;
import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.service.impl.ProductServiceImpl;
import com.store.utils.UUIDUtils;
import com.store.utils.UploadUtils;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminProductServlet
 */
@WebServlet("/AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
	ProductService service = new ProductServiceImpl();
	Product product = null;
	PageModel pm = null;
	Integer curNum = null;

	public String findAllProductsWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取当前页
		curNum = Integer.parseInt(request.getParameter("num"));
		// 调用service的findAllProductsWithPage返回PageModel
		pm = service.findAllProductsWithPage(curNum);
		// 将page放入request
		request.setAttribute("page", pm);
		// 转发到/admin/product/list.jsp
		return "/admin/product/list.jsp";
	}

	public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取全部分类信息
		List<Category> list = new CategoryServiceImpl().getAllCats();
		// 将分类信息放入request
		request.setAttribute("allCats", list);
		// 转发到/admin/product/add.jsp
		return "/admin/product/add.jsp";
	}

	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 存储表单数据
		Map<String, String> map = new HashMap<String, String>();
		// 携带表单数据
		product = new Product();

		// 利用request.getInputStream()获取请求中全部数据，然后进行拆分和封装
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> list = upload.parseRequest(request);

		// 遍历集合
		for (FileItem fileItem : list) {
			if (fileItem.isFormField()) {
				// 普通项，key：name属性的值 value：获取的内容
				map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
			} else {
				// 上传项，key： value：
				// 获取要上传的文件的名称
				String oldFileName = fileItem.getFieldName();
				// 变成新的名称,避免重名
				String newFileName = UploadUtils.getUUIDName(oldFileName);
				// 通过FileItem获取输入流对象，通过输入流可以获取到图片的二进制数据
				InputStream is = fileItem.getInputStream();
				// 获取当前项目下product/3的真实路径
				String realPath = getServletContext().getRealPath("/product/3/");
				String dir = UploadUtils.getDir(newFileName);
				String path = realPath + dir;
				// 在内存中创建一个目录
				File newDir = new File(path);
				if (!newDir.exists()) {
					newDir.mkdirs();
				}
				// 在服务端创建一个空文件，后缀与上传项后缀相同
				File finalFile = new File(newDir, newFileName);
				if (!finalFile.exists()) {
					finalFile.createNewFile();
				}
				// 建立对应输出流
				OutputStream os = new FileOutputStream(finalFile);
				// 将输入流中的数据刷到输出流中
				IOUtils.copy(is, os);
				// 释放资源
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
				// 向map中放入数据
				map.put("pimage", "/product/3" + dir + "/" + newFileName);
			}
		}
		// 利用BeanUtils将MAP中的数据填充到Product对象上
		BeanUtils.populate(product, map);
		product.setPid(UUIDUtils.getId());
		product.setPdate(new Date());
		product.setPflag(0);
		// 调用service的saveProduct()
		service.saveProduct(product);

		// 重定向到findAllProductsWithPage
		response.sendRedirect("/store01/AdminProductServlet?method=findAllProductsWithPage&num=1");
		return null;
	}

	public String editUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		product = service.findProductByPid(pid);
		request.setAttribute("product", product);
		// 获取全部分类信息
		List<Category> list = new CategoryServiceImpl().getAllCats();
		// 将分类信息放入request
		request.setAttribute("allCats", list);

		return "/admin/product/edit.jsp";
	}

	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 存储表单数据
		Map<String, String> map = new HashMap<String, String>();
		// 携带表单数据
		product = new Product();

		// 利用request.getInputStream()获取请求中全部数据，然后进行拆分和封装
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> list = upload.parseRequest(request);

		// 遍历集合
		for (FileItem fileItem : list) {
			if (fileItem.isFormField()) {
				// 普通项，key：name属性的值 value：获取的内容
				map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
			} else {
				// 上传项，key： value：
				// 获取要上传的文件的名称
				String oldFileName = fileItem.getFieldName();
				// 变成新的名称,避免重名
				String newFileName = UploadUtils.getUUIDName(oldFileName);
				// 通过FileItem获取输入流对象，通过输入流可以获取到图片的二进制数据
				InputStream is = fileItem.getInputStream();
				// 获取当前项目下product/3的真实路径
				String realPath = getServletContext().getRealPath("/product/3/");
				String dir = UploadUtils.getDir(newFileName);
				String path = realPath + dir;
				// 在内存中创建一个目录
				File newDir = new File(path);
				if (!newDir.exists()) {
					newDir.mkdirs();
				}
				// 在服务端创建一个空文件，后缀与上传项后缀相同
				File finalFile = new File(newDir, newFileName);
				if (!finalFile.exists()) {
					finalFile.createNewFile();
				}
				// 建立对应输出流
				OutputStream os = new FileOutputStream(finalFile);
				// 将输入流中的数据刷到输出流中
				IOUtils.copy(is, os);
				// 释放资源
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
				// 向map中放入数据
				map.put("pimage", "/product/3" + dir + "/" + newFileName);
			}
		}
		// 利用BeanUtils将MAP中的数据填充到Product对象上
		BeanUtils.populate(product, map);
		product.setPid(request.getParameter("pid"));
		product.setPdate(new Date(Date.parse(request.getParameter("pdate").replace("-", "/"))));
		product.setPflag(Integer.parseInt(request.getParameter("pflag")));
		// 调用service的edit()
		service.edit(product);

		// 重定向到findAllProductsWithPage
		response.sendRedirect("/store01/AdminProductServlet?method=findAllProductsWithPage&num=1");
		return null;
	}

	public String pushDownUI(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Product> list=service.findPushDownProduct();
		request.setAttribute("productList", list);
		return "/admin/product/pushDown_list.jsp";
	}
	
	
	public String pushDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		product = service.findProductByPid(pid);
		service.pushDown(product);
		List<Product> list = new ArrayList<Product>();
		list.add(product);
		// 重定向到findAllProductsWithPage
		response.sendRedirect("/store01/AdminProductServlet?method=pushDownUI");
		return null;
	}

	public String upper(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		product = service.findProductByPid(pid);
		service.upper(product);
		List<Product> list = new ArrayList<Product>();
		list.remove(product);
		// 重定向到findAllProductsWithPage
		response.sendRedirect("/store01/AdminProductServlet?method=findAllProductsWithPage&num=1");
		return null;
	}

}
