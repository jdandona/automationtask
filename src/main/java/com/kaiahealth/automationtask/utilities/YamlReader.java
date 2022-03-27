package com.kaiahealth.automationtask.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.testng.Reporter;



@SuppressWarnings("unchecked")
public class YamlReader {

	public static String yamlFilePath = null;
	public static Map<String, Object> object = null;

	public static String setYamlFilePath() {

		yamlFilePath = "src"+File.separator+"test"+File.separator+"resources"+File.separator
				+"TestData"+File.separator+"QA_Data.yml";

		System.out.println("Yaml file path ::" + yamlFilePath);
		try {
			new FileReader(yamlFilePath);

		} catch (FileNotFoundException e) {
			Reporter.log("Wrong file path.", true);
			System.exit(0);
		}
		return yamlFilePath;
	}

	public static String getYamlValue(String token) {
		try {
			System.out.println(token);
			return getValue(token);

		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static String getData(String token) {
		return getYamlValue(token);
	}

	public static Map<String, Object> getYamlValues(String token) {
		Reader doc;
		try {
			doc = new FileReader(yamlFilePath);
		} catch (FileNotFoundException ex) {
			System.out.println("File not valid or missing!!!");
			ex.printStackTrace();
			return null;
		}
		Yaml yaml = new Yaml();

		if (object == null) {
			object = (Map<String, Object>) yaml.load(doc);
		}
		return parseMap(object, token + ".");
	}

	private static String getValue(String token) throws FileNotFoundException {
		Reader doc = null;
		try {
			doc = new FileReader(yamlFilePath);
			System.out.println(yamlFilePath);
		} catch (FileNotFoundException e) {
			Reporter.log("Wrong tier passed in 'Configuration' file'", true);
			return null;
		}
		Yaml yaml = new Yaml();
		if (object == null) {
			object = (Map<String, Object>) yaml.load(doc);
		}
		return getMapValue(object, token);
	}

	public static String getMapValue(Map<String, Object> object, String token) {
		String[] st = token.split("\\.");
		return parseMap(object, token).get(st[st.length - 1]).toString();
	}

	private static Map<String, Object> parseMap(Map<String, Object> object, String token) {
		if (token.contains(".")) {
			String[] st = token.split("\\.");
			object = parseMap((Map<String, Object>) object.get(st[0]), token.replace(st[0] + ".", ""));
		}
		return object;
	}
	
	public static Map<String, String> getMapValueFromYml(String mapToken) {

		mapToken = mapToken.substring(1, mapToken.length() - 1);
		String[] keyValuePairs = mapToken.split(",");
		Map<String, String> map = new HashMap<>();

		for (String pair : keyValuePairs) {
			String[] entry = pair.split("=");
			map.put(entry[0].trim(), entry[1].trim());
		}
		return map;
	}

}
