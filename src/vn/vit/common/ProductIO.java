package vn.vit.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import vn.vit.model.Customer;
import vn.vit.model.Product;

public class ProductIO {	
	
		public static void writeFile(ArrayList<Product> lst) {
			try {
				ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("product.dat"));
				o.writeObject(lst);
			} catch (Exception e) {
			}
		}
		
		public static ArrayList<Product> readFile() {
			ArrayList<Product> lst = new ArrayList();
			try {
				ObjectInputStream o = new ObjectInputStream(new FileInputStream("product.dat"));
				lst = (ArrayList<Product>) o.readObject();
			} catch (Exception e) {
			}
			return lst;
		}
	
}
