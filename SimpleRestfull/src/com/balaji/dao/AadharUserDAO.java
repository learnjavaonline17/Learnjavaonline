package com.balaji.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.balaji.pojos.AadharUser;

public class AadharUserDAO {
	
	
	public List<AadharUser> getAllUsers(){
		List<AadharUser> userList=null;
		 try {
	         File file = new File("AadharUsers.dat");
	         System.out.println(file.getAbsolutePath());
	         if (!file.exists()) {
	        	 AadharUser user = new AadharUser("111111","000000000");
	            userList = new ArrayList<AadharUser>();
	            userList.add(user);
	            saveUserList(userList);		
	         }
	         else{
	            FileInputStream fis = new FileInputStream(file);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            userList = (List<AadharUser>) ois.readObject();
	            ois.close();
	         }
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }		
	      return userList;
		
		
	}
	
	private void saveUserList(List<AadharUser> userList) {
		try {
	         File file = new File("AadharUsers.dat");
	         FileOutputStream fos;

	         fos = new FileOutputStream(file);

	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(userList);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
	}

	public AadharUser getUser(String id){
	      List<AadharUser> users = getAllUsers();
	      System.out.println("I am Here");
	      for(AadharUser user: users){
	         if(user.getUdi_no().equals(id)){
	            return user;
	         }
	      }
	      return null;
	   }
	
	public int addUser(AadharUser user){
		
		 List<AadharUser> userList = getAllUsers();		
		boolean userExist=false;
		for(AadharUser aUser:userList){
			if(user.getUdi_no().equals(aUser.getUdi_no())){
				userExist=true;
				break;
			}
		}
		if(!userExist){
			userList.add(user);
			saveUserList(userList);
			return 1;
		}
		return 0;
	}
	public int updateUser(AadharUser aUser){
	      List<AadharUser> userList = getAllUsers();

	      for(AadharUser user: userList){
	         if(user.getUdi_no().equals( aUser.getUdi_no())){
	            int index = userList.indexOf(user);			
	            userList.set(index, aUser);
	            saveUserList(userList);
	            return 1;
	         }
	      }		
	      return 0;
	   }

	   public int deleteUser(String udid){
	      List<AadharUser> userList = getAllUsers();

	      for(AadharUser user: userList){
	         if(user.getUdi_no().equals(udid)){
	            int index = userList.indexOf(user);			
	            userList.remove(index);
	            saveUserList(userList);
	            return 1;   
	         }
	      }		
	      return 0;
	   }
}
