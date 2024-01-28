package com.jmant69.ReadAFileMaven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
				Customer customer = new Customer(Long.parseLong(
						values[0]),values[1],values[2],values[3],values[4],values[5],values[6],values[7]);
				String jsonData = mapper.writeValueAsString(customer);
				System.out.println(jsonData);
				
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
