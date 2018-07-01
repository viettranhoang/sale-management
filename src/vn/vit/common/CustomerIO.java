package vn.vit.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import vn.vit.model.Customer;

public class CustomerIO {	
	
		public static void writeFile(ArrayList<Customer> lst) {
			try {
				ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("customer.dat"));
				o.writeObject(lst);
			} catch (Exception e) {
			}
		}
		
		public static ArrayList<Customer> readFile() {
			ArrayList<Customer> lst = new ArrayList();
			try {
				ObjectInputStream o = new ObjectInputStream(new FileInputStream("customer.dat"));
				lst = (ArrayList<Customer>) o.readObject();
			} catch (Exception e) {
			}
			return lst;
		}
	
}
