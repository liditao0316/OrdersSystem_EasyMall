package easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import easymall.po.Salas;
import easymall.pojo.UpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.ProductsDao;
import easymall.po.Products;
import easymall.pojo.MyProducts;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	private ProductsDao productsDao;

	@Override
	public List<String> allcategories() {
		// TODO Auto-generated method stub
		return productsDao.allcategories();
	}

	@Override
	public List<Products> prodlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productsDao.prodlist(map);
	}

	public Products oneProduct(String pid){
		return productsDao.oneProduct(pid);
	}

	@Override
	public List<Products> prodclass(String prodclass) {
		return productsDao.prodclass(prodclass);
	}
	@Override
	public String save(MyProducts myproducts, HttpServletRequest request) {
		// 1.判断后缀是否合法
		// 获取图名称，后缀名称
		String originName = myproducts.getImgurl().getOriginalFilename();

		// 截取后缀substring split (".png" ".jgp")
		String extName = originName.substring(originName.lastIndexOf("."));

		if (!(extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".png")
				|| extName.equalsIgnoreCase(".gif"))) {// 图片后缀不合法
			return "图片后缀不合法!";
		}
		// 判断木马(java的类判断是否是图片属性，也可以引入第三方jar包判断)
		try {
			BufferedImage bufImage = ImageIO.read(myproducts.getImgurl().getInputStream());
			bufImage.getHeight();
			bufImage.getWidth();
		} catch (Exception e) {
			return "该文件不是图片！";
		}
		// 2.创建upload开始的一个路径
		// 生成多级路径
		String imgurl = "";
		// /a/2/e/a/2/3/j/p
		for (int i = 0; i < 8; i++) {
			imgurl += "/" + Integer.toHexString(new Random().nextInt(16));
		}
		String realpath = request.getServletContext().getRealPath("/WEB-INF");
		realpath += "/upload";
		// D:\java\Workspace\.metadata\.plugins\org.eclipse.wst.server.core
		// \tmp0\wtpwebapps\EasyMall18\WEB-INF/upload/2/6/2/7/2/d/2/1

		File file = new File(realpath + imgurl, originName);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 上传文件
		try {
			myproducts.getImgurl().transferTo(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 拼接图片存入数据库的路径
		System.out.println(imgurl+"-------");
		imgurl = "/upload" + imgurl + "/" + originName;
		System.out.println(imgurl);
		String id = UUID.randomUUID().toString();
		Products products = new Products();
		products.setId(id);
		products.setName(myproducts.getName());
		products.setCategory(myproducts.getCategory());
		products.setPrice(myproducts.getPrice());
		products.setPnum(myproducts.getPnum());
		products.setImgurl(imgurl);
		products.setDescription(myproducts.getDescription());
		if (productsDao.findByImgurl(products.getImgurl()) == null) {
			productsDao.save(products);
		}else{
			String fname = imgurl.substring(0,imgurl.lastIndexOf("."));
			imgurl=fname+System.currentTimeMillis()+extName;
			System.out.println(imgurl);
			products.setImgurl(imgurl);
			System.out.println(products.getImgurl());
			productsDao.save(products);
		}
		return "商品添加成功";
	}

	@Override
	public void delprod(String id) {
		// TODO Auto-generated method stub
		productsDao.delprod(id);
	}

	@Override
	public void updateSalas(Salas salas) {
		productsDao.updateSalas(salas);
	}

	@Override
	public List<Products> findSalasTop() {
		return productsDao.findSalasTop();
	}

	@Override
	public String update(UpdateProduct myproducts, HttpServletRequest request) {
		// 1.判断后缀是否合法
		// 获取图名称，后缀名称
		Products products1 = productsDao.oneProduct(myproducts.getId());

		String originName = myproducts.getImgurl().getOriginalFilename();
		System.out.println(originName=="");
		if(originName != ""){
			// 截取后缀substring split (".png" ".jgp")
			String extName = originName.substring(originName.lastIndexOf("."));

			if (!(extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".png")
					|| extName.equalsIgnoreCase(".gif"))) {// 图片后缀不合法
				return "图片后缀不合法!";
			}
			// 判断木马(java的类判断是否是图片属性，也可以引入第三方jar包判断)
			try {
				BufferedImage bufImage = ImageIO.read(myproducts.getImgurl().getInputStream());
				bufImage.getHeight();
				bufImage.getWidth();
			} catch (Exception e) {
				return "该文件不是图片！";
			}
			// 2.创建upload开始的一个路径
			// 生成多级路径
			String imgurl = "";

			imgurl += "/" + UUID.randomUUID().toString();
			String realpath = request.getServletContext().getRealPath("/WEB-INF");
			realpath += "/upload";
			// D:\java\Workspace\.metadata\.plugins\org.eclipse.wst.server.core
			// \tmp0\wtpwebapps\EasyMall18\WEB-INF/upload/2/6/2/7/2/d/2/1

			File file = new File(realpath + imgurl, originName);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 上传文件
			try {
				myproducts.getImgurl().transferTo(file);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// 拼接图片存入数据库的路径
			System.out.println(imgurl+"-------");
			imgurl = "/upload" + imgurl + "/" + originName;
			System.out.println(imgurl);
			//创建更新对象
			Products products = new Products();
			products.setId(myproducts.getId());
			products.setName(myproducts.getName());
			products.setCategory(myproducts.getCategory());
			products.setPrice(myproducts.getPrice());
			products.setPnum(myproducts.getPnum());
			products.setSoldnum(myproducts.getSoldnum());
			products.setImgurl(imgurl);
			products.setDescription(myproducts.getDescription());
			System.out.println(products);
			if (productsDao.findByImgurl(products.getImgurl()) == null) {
				productsDao.updateProduct(products);
			}else{
				String fname = imgurl.substring(0,imgurl.lastIndexOf("."));
				imgurl=fname+System.currentTimeMillis()+extName;
				System.out.println(imgurl);
				products.setImgurl(imgurl);
				System.out.println(products.getImgurl());
				productsDao.updateProduct(products);
			}
		}else{
			//创建更新对象
			Products products = new Products();
			products.setId(myproducts.getId());
			products.setName(myproducts.getName());
			products.setCategory(myproducts.getCategory());
			products.setPrice(myproducts.getPrice());
			products.setPnum(myproducts.getPnum());
			products.setSoldnum(products1.getSoldnum());
			products.setImgurl(products1.getImgurl());
			products.setDescription(myproducts.getDescription());
			System.out.println(products);
			productsDao.updateProduct(products);
		}

		return "商品更新成功";
	}


}
