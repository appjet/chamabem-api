package com.chamabem.util;

import java.util.UUID;

public class KeyGenerator {
	public static void main(String[] args) {
			
		String uuid = UUID.randomUUID().toString();
		String uuid2 = UUID.randomUUID().toString();
		
		System.out.println("Chave: " + uuid + uuid2);
	}
}