package view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TestMBean implements Serializable {
	
	private String test;
	
	private static final long serialVersionUID = 1L;
	
	public String getTest(){
		return "JUST A TEST";
	}
	
	public void setTest(String test){
		this.test = test;
	}
	

}
