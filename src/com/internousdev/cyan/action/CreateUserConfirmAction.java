package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.UserInfoDAO;
import com.internousdev.cyan.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserConfirmAction extends ActionSupport implements SessionAware{

	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String email;
	private String loginId;
	private String password;

	private List<String> sexList = new ArrayList<String>();
	private Map<String,Object> session;

	public String execute() {
		String result = ERROR;
		if(!session.containsKey("mCategoryDTOList")){
			return"timeout";
		}
			session.remove("familyNameErrorMessageList");
			session.remove("firstNameErrorMessageList");
			session.remove("familyNameKanaErrorMessageList");
			session.remove("firstNameKanaErrorMessageList");
			session.remove("emailErrorMessageList");
			session.remove("loginIdErrorMessageList");
			session.remove("passwordErrorMessageList");
			session.remove("loginIdIncorrectErrorMessageList");

			InputChecker inputChecker = new InputChecker();

			session.put("createUserFamilyName",familyName);
			session.put("createUserFirstName",firstName);
			session.put("createUserFamilyNameKana",familyNameKana);
			session.put("createUserFirstNameKana",firstNameKana);

			session.put("createUserSex", sex);
			if(session.get("createUserSex")=="男性"){
				session.put("createUserSexValue","0");
			}else{
				session.put("createUserSexValue","1");
			}

			session.put("createUserEmail",email);
			session.put("createUserLoginId",loginId);
			session.put("createUserPassword",password);

			List<String> familyNameErrorMessageList = new ArrayList<String>();
			List<String> firstNameErrorMessageList = new ArrayList<String>();
			List<String> familyNameKanaErrorMessageList = new ArrayList<String>();
			List<String> firstNameKanaErrorMessageList = new ArrayList<String>();
			List<String> emailErrorMessageList = new ArrayList<String>();
			List<String> loginIdErrorMessageList = new ArrayList<String>();
			List<String> passwordErrorMessageList = new ArrayList<String>();
			List<String> loginIdIncorrectErrorMessageList = new ArrayList<String>();

			familyNameErrorMessageList = inputChecker.doCheck("姓", familyName, 1, 16, true, true, true, false, false, false, false, false, false);
			firstNameErrorMessageList = inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, false, false, false, false, false);
			familyNameKanaErrorMessageList = inputChecker.doCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
			firstNameKanaErrorMessageList = inputChecker.doCheck("名ふりがな", firstNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
			emailErrorMessageList = inputChecker.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false, false, false);
			loginIdErrorMessageList = inputChecker.doCheck("ユーザーID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
			passwordErrorMessageList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

			if(familyNameErrorMessageList.size()==0
			&& firstNameErrorMessageList.size()==0
			&& familyNameKanaErrorMessageList.size()==0
			&& firstNameKanaErrorMessageList.size()==0
			&& emailErrorMessageList.size()==0
			&& loginIdErrorMessageList.size()==0
			&& passwordErrorMessageList.size()==0) {

				UserInfoDAO userInfoDAO = new UserInfoDAO();
				if(!(userInfoDAO.isExistsLoginIdUserInfo(loginId))) {
					session.put("createUserLoginId", loginId);
					result = SUCCESS;
				} else {
					loginIdIncorrectErrorMessageList.add("使用できないユーザーIDです。");
					session.put("loginIdIncorrectErrorMessageList",loginIdIncorrectErrorMessageList);
				}

			}else {
				session.put("familyNameErrorMessageList",familyNameErrorMessageList);
				session.put("firstNameErrorMessageList", firstNameErrorMessageList);
				session.put("familyNameKanaErrorMessageList",familyNameKanaErrorMessageList);
				session.put("firstNameKanaErrorMessageList",firstNameKanaErrorMessageList );
				session.put("emailErrorMessageList",emailErrorMessageList);
				session.put("loginIdErrorMessageList",loginIdErrorMessageList);
				session.put("passwordErrorMessageList",passwordErrorMessageList);
				result = ERROR;
			}
		return result;
	}

	public List<String> getSexList() {
		return sexList;
	}

	public void setSexList(List<String> sexList) {
		this.sexList = sexList;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
