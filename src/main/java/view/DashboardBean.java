package view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


@ManagedBean
@SessionScoped
public class DashboardBean implements Serializable {

	private static final long serialVersionUID = -7094786573876329041L;
	
	private String intro;
	
	public String getIntro(){
		return "YOU ARE IN";
	}
	
	public String getUsername(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}
	
}
