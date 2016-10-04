package tagtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadedJsonfx {
	private JSONObject json;
	private JSONParser parser;
	private JSONArray tags;

	private String text;
	private String id;

	@SuppressWarnings("unchecked")
	public LoadedJsonfx(File file) {
		this.parser = new JSONParser();
		this.tags = new JSONArray();
		try {
			FileInputStream stream = new FileInputStream(file);
			InputStreamReader strReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
			
			Object obj = parser.parse(strReader);
			this.json = (JSONObject) obj;

			id = (String) json.get("id");
			text = (String) json.get("text");
			if (json.get("tags") == null) {
				this.tags = new JSONArray();
				System.out.println("Tags = null");
			} else {
				JSONArray array = (JSONArray) json.get("tags");
				for (int i = 0; i < array.size(); i++) {
					JSONObject o = (JSONObject) array.get(i);
					Long beginLong = (Long) o.get("begin");
					int begin = beginLong.intValue();
					Long endLong = (Long) o.get("end");
					int end = endLong.intValue();
					String tagCode = (String) o.get("tagCode");
					String selection = (String) o.get("selection");
					System.out.println(selection);
					JSONObject toAdd = new JSONObject();

					toAdd.put("begin", begin);
					toAdd.put("end", end);
					toAdd.put("tagCode", tagCode);
					toAdd.put("selection", selection);
					tags.add(toAdd);
				}
				System.out.println(tags);
			}

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void addTag(String selection, String tagCode, int begin, int end) {
		JSONObject tag = new JSONObject();
		tag.put("tagCode", tagCode);
		tag.put("begin", begin);
		tag.put("end", end);
		tag.put("selection", selection);

		tags.add(tag);
	}

	@SuppressWarnings("unchecked")
	public void removeTag(String selection, String tagCode, int begin, int end) {
		JSONObject tag = new JSONObject();
		tag.put("tagCode", tagCode);
		tag.put("begin", begin);
		tag.put("end", end);
		tag.put("selection", selection);

		tags.remove(tag);
	}

	public JSONArray getTags() {
		return tags;
	}

	public void setTags(JSONArray tags) {
		this.tags = tags;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJson() {
		JSONObject obj = new JSONObject();
		obj.put("text", text);
		obj.put("id", id);
		obj.put("tags", tags);
		return obj;
	}

	public void updateTagsFromText(String oldValue, String newValue) {
		// TODO Auto-generated method stub
		
	}

}
