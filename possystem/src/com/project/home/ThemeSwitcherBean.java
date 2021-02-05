package com.project.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;



public class ThemeSwitcherBean implements Serializable {
	
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	String themeName = config.getValue(IConfiguration.PROJECT_THEMENAME);
	
	 private Map<String, String> themes;
	    
	    private List<Theme> advancedThemes;
	    
	    private String theme;
	    
	    private GuestPreferences gp;

	    public void setGp(GuestPreferences gp) {
	        this.gp = gp;
	    }
	    
	    public Map<String, String> getThemes() {
	        return themes;
	    }

	    public String getTheme() {
	        return theme;
	    }

	    public void setTheme(String theme) {
	        this.theme = theme;
	    }	    

	    public GuestPreferences getGp() {
			return gp;
		}

		@PostConstruct
	    public void init() {
	    	
	    	if(gp!=null)
	    	{
	        theme = gp.getTheme();
	    	}
	    	else
	    	{
	    	 gp = new GuestPreferences();
	    	 this.setGp(gp);
	    	 theme = themeName;	    	
	    	}
	        
	        advancedThemes = new ArrayList<Theme>();	       
	           
	        advancedThemes.add(new Theme("glass-x", "glass-x.png"));	       
	        advancedThemes.add(new Theme("hot-sneaks", "hot-sneaks.png"));	       
	        advancedThemes.add(new Theme("redmond", "redmond.png"));	       
	        advancedThemes.add(new Theme("sunny", "sunny.png"));
	        advancedThemes.add(new Theme("aristo", "aristo.png"));
	        advancedThemes.add(new Theme("start", "start.png"));  
	        advancedThemes.add(new Theme("home", "home.png"));  
	        
	        themes = new TreeMap<String, String>();	       
	                
	        themes.put("Glass-X", "glass-x");
	        themes.put("Hot-Sneaks", "hot-sneaks");	       
	        themes.put("Redmond", "redmond");	        
	        themes.put("Sunny", "sunny");
	        themes.put("Aristo", "aristo");
	        themes.put("HalTrade", "start");
	        themes.put("Home", "home");
	                
	       
	    }
	    
	    public void saveTheme() {
	        gp.setTheme(theme);
	        this.setTheme(theme);
	    }

	    public List<Theme> getAdvancedThemes() {
	        return advancedThemes;
	    }

}
