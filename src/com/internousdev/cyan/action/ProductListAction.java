package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.ProductInfoDAO;
import com.internousdev.cyan.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware{
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private int price;
	private Map<String, Object> session;

	public String execute(){
		String result = SUCCESS;
		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
			}

			session.remove("keywordsErrorMessageList");

			ProductInfoDAO productInfoDAO = new ProductInfoDAO();
			List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
			productInfoDTOList = productInfoDAO.getProductInfoList();
			session.put("productInfoDTOList", productInfoDTOList);

		return result;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
