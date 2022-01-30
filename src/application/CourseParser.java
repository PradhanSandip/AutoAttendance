package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CourseParser {
	//json file
	 Object courses = null;
	 JSONArray jarray = null;
	 JSONParser json = null;
	//constructor load json
	public CourseParser() {
		json = new JSONParser();
		try {
		courses = json.parse(new FileReader(".\\src\\application\\courses.json"));
	
		}catch(Exception e){
			System.out.println(e.toString());
		}

	}
	//return list of courses in json
	public JSONArray getCourse() {
		JSONObject jo = (JSONObject) courses;
		jarray = (JSONArray) jo.get("courses");
		return jarray;
	}
	//delete courses in json
	public boolean deleteCourse(String course) {
		JSONObject jo = (JSONObject) courses;
		boolean result = false;
		jarray = (JSONArray) jo.get("courses");
		for(int i=0; i<jarray.size(); i++) {
			JSONObject j = (JSONObject) jarray.get(i);
			if(course.equals(j.get("name"))) {
				System.out.println(course+" "+j.get("name"));
				jarray.remove(i);
				FileWriter file;
				try {
					file = new FileWriter(".//src//application//courses.json");
					j = new JSONObject();
					j.put("courses", jarray);
					System.out.println(j);
					file.write(j.toString());
					file.flush();
					result = true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result = false;
				}
				
			}
		}
		return result;
	}
	
	//add course to json
	public boolean addCourse(String course, String link) {
		JSONObject jo = (JSONObject) courses;
		jarray = (JSONArray) jo.get("courses");
		Map m = new HashMap();
		m.put("name", course);
		m.put("url", link);
		jarray.add(m);
		JSONObject j = new JSONObject();
		j.put("courses", jarray);
		System.out.println(jarray);
		try {
			FileWriter file = new FileWriter(".//src//application//courses.json");
			file.write(j.toString());
			file.flush();
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	//return link of given course
	public String getLink(String course) {
		JSONObject jo = (JSONObject) courses;
		jarray = (JSONArray) jo.get("courses");
		String result = "";
		for(int i=0; i < jarray.size(); i++) {
			JSONObject j = (JSONObject) jarray.get(i);
			if(course.equals(j.get("name"))) {
				result = (String) j.get("url");
			}
		}
		
		return result;
	}
}


