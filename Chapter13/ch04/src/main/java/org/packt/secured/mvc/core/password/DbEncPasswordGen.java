package org.packt.secured.mvc.core.password;

public class DbEncPasswordGen {

	public static void main(String[] args) {
		AppPasswordEncoder encoder = new AppPasswordEncoder();
		
		System.out.println("sjctrags: " + encoder.md5Encoder("sjctrags", "sjctrags"));
		System.out.println("admin: " + encoder.md5Encoder("admin", "admin"));
		System.out.println("hradmin: " + encoder.md5Encoder("hradmin", "hradmin"));

	}

}
