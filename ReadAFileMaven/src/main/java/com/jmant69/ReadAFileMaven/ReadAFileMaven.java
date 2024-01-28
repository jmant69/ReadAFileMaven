package com.jmant69.ReadAFileMaven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmant69.model.Customer;

public class ReadAFileMaven {

	public static void main(String[] args) {

		String path = "C:/Users/Julian/Documents/Customer File.txt";
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			while ((line = br.readLine()) != null) {
				ObjectMapper mapper = new ObjectMapper();
				String[] values = line.split(",");
				Customer customer = new Customer(Long.parseLong(values[0]), values[1], values[2], values[3], values[4],
						values[5], values[6], values[7]);
				String jsonData = mapper.writeValueAsString(customer);
//				System.out.println(jsonData);
				URL url = new URL("http://localhost:8080/customer");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Accept", "application/json");
				con.setDoOutput(true);
				try (OutputStream os = con.getOutputStream()) {
					byte[] input = jsonData.getBytes("utf-8");
					os.write(input, 0, input.length);
				}
				try (BufferedReader br2 = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
					StringBuilder response = new StringBuilder();
					String responseLine = null;
					while ((responseLine = br2.readLine()) != null) {
						response.append(responseLine.trim());
					}
					System.out.println(response.toString());
				}

			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
