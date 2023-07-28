import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class operations {
	static int kis=1;
	
	/*________________________________This File Contains all the Required Functions___________________*/
	
	
	
	
	/*------------------------------Insert Operation-----------------------------------*/
	static int insert(String a1,String a2,String a3, String a4,String a5){
		try
		{
			DAO db=new DAO();
			Connection conn=db.getConnection();
	
			 int id=Integer.parseInt(a1);
			 String name=a2;
			 int price=Integer.parseInt(a3);
			 int quantity=Integer.parseInt(a4);
			 String company=a5;
			 
			 String insertQuery="insert into product_table values(?,?,?,?,?)";
			 PreparedStatement preparedStatement;
			 
			 preparedStatement=conn.prepareStatement(insertQuery);
			 preparedStatement.setInt(1, id);
			 preparedStatement.setString(2,name);
			 preparedStatement.setInt(3,price);
			 preparedStatement.setInt(4,quantity);
			 preparedStatement.setString(5,company);
			 
			 preparedStatement.executeUpdate();
			 return 1; 
		}
		catch(Exception e){
			 System.out.println(e);
			 return 0;
			
		}
		}
	
	
	/*----------------------------Update Operation--------------------------------*/
	static int update(String a1,String a2,String a3, String a4,String a5){
		try{
			DAO db=new DAO();
			Connection conn=db.getConnection();
			
			 int id=Integer.parseInt(a1);
			 String name=a2;
			 int price=Integer.parseInt(a3);
			 int quantity=Integer.parseInt(a4);
			 String company=a5;
			 
			 
			 String selectQuery="update product_table set name=?,price=?,quantity=?,company=? where id=?";
			 PreparedStatement preparedStatement=null;
			 
			 preparedStatement=conn.prepareStatement(selectQuery);
			 preparedStatement.setInt(1, id);
			 preparedStatement.setString(2,name);
			 preparedStatement.setInt(3,price);
			 preparedStatement.setInt(4,quantity);
			 preparedStatement.setString(5,company);
			 
			 int count=preparedStatement.executeUpdate();
			 if(count==0){
				 //No Record Found 
				 return 0;
			 }
			 else{
			 //Data Successfully Updated
			    return 1;
			 }
		}
		catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
	
	
	/*---------------------------------Delete Operation------------------------------------*/
		static int delete(String p){
			try{
				DAO db=new DAO();
				Connection conn=db.getConnection();
		
				int pid=Integer.parseInt(p);
				
				String deleteQuery="delete from product_table where pid=?";
				PreparedStatement preparedStatement;
				
				preparedStatement=conn.prepareStatement(deleteQuery);
				preparedStatement.setInt(1, pid);
				
				int count=preparedStatement.executeUpdate();
				if(count==0){
					//No record Found With Name 
					return 0;
					}
				else
					{
					//Record Deleted!!
					return 1;}
				
				
			}
			catch(Exception e){
				System.out.println(e);	
				return -1;
				}
		}
		
		
		/*---------------------------------------Display All records-----------------*/
		static String displayall(){
			try{
				DAO db=new DAO();
				Connection conn=db.getConnection();
			
				String selectQuery="select * from product_table";
				PreparedStatement preparedStatement;
				
				preparedStatement=conn.prepareStatement(selectQuery);
				ResultSet result=preparedStatement.executeQuery();
				String table_label="      \tProduct ID\tName\tPrice\tQuantity\tCompany\n";
				String string_data="";
				while(result.next()){
					string_data=string_data+"   \t"+result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getInt(3)+"\t"+result.getInt(4)+"\t"+result.getString(5)+"\n";
				}
				return table_label+string_data;
				}
				catch(Exception e){
					return e+"";
				}
		}
		
		
		/*----------------------------Display One By One ---------------------*/
		/* Takes two Parameter val and p 
		 * val is for which operation to be performed i.e val=1 for previous
		 *                                                val=2 for next
		 *                                                val=3 for first
		 *                                                val=4 for last
		 * p is the number of entry ie 1st,2nd,3rd
		 * now this p is incremented or decremented in product.java program
		 * 
		 * One More thing is that _if you increment or decrement circularly the it will be e next advance step you 
		 * will be able to traverse the all data in cicular loop 
		 * How to increment p circularly 
		 * p=(p+1)%size of table
		 */
		
		static String display_one_by_one(int val,int p){
			try{
				DAO db=new DAO();
				Connection conn=db.getConnection();
				String selectQuery="select * from product_table";
				PreparedStatement preparedStatement;
				
				preparedStatement=conn.prepareStatement(selectQuery);
				ResultSet result=preparedStatement.executeQuery();
				String string_data="\n\n\n\n\n\n\t\t\tNo More Data Found!!!";
				if(val==1 ){
					if(result.isLast()){
						  string_data="This is Last Entry!!!";
						  return string_data;
					}
					else{
					      result.absolute(p);
					      string_data="\n\tProduct ID\t"+result.getInt(1)+"\n\tName\t"+result.getString(2)+"\n\tPrice\t"+result.getInt(3)+"\n\tQuantity\t"+result.getInt(4)+"\n\tCompany\t"+result.getString(5)+"\n";
					      return string_data;}
				}
				if(val==2){
					if(result.isFirst()){
						string_data="This is First Entry!!!";
						return string_data;
					}
					else{result.absolute(p);
					    string_data="\n\tProduct ID\t"+result.getInt(1)+"\n\tName\t"+result.getString(2)+"\n\tPrice\t"+result.getInt(3)+"\n\tQuantity\t"+result.getInt(4)+"\n\tCompany\t"+result.getString(5)+"\n";
					    return string_data;}
				}
				if(val==3){
					    result.first();
					    string_data="\n\tProduct ID\t"+result.getInt(1)+"\n\tName\t"+result.getString(2)+"\n\tPrice\t"+result.getInt(3)+"\n\tQuantity\t"+result.getInt(4)+"\n\tCompany\t"+result.getString(5)+"\n";
					    return string_data;
				}
				if(val==4){
					    result.last();
					    string_data="\n\tProduct ID\t"+result.getInt(1)+"\n\tName\t"+result.getString(2)+"\n\tPrice\t"+result.getInt(3)+"\n\tQuantity\t"+result.getInt(4)+"\n\tCompany\t"+result.getString(5)+"\n";
					    return string_data;
				}
				else{
					return string_data;
				}
				}
				catch(Exception e){
					return e+"";
				}
		}
		
		/*------------------Search Operation By ID Just Check Present OR Not---------------------------*/
		
		static int search(int b1){
			try{
				DAO db=new DAO();
				Connection conn=db.getConnection();
			
				String selectQuery="select * from product_table";
				PreparedStatement preparedStatement;
				
				int s1=b1;
				preparedStatement=conn.prepareStatement(selectQuery);
				ResultSet result=preparedStatement.executeQuery();
				
				int data=0;
				while(result.next()){
					if(result.getInt(1)==s1){
						data=1;
					}
				}
				return data;
				}
				catch(Exception e){
					return -1;
				}
		}
		
		/*------------------Search Operation By ID Check And return corresponding data ---------------------------*/
		static String search_by_ID(int b1){
			try{
				DAO db=new DAO();
				Connection conn=db.getConnection();
			
				String selectQuery="select * from product_table";
				PreparedStatement preparedStatement;
				
				int s1=b1;
				preparedStatement=conn.prepareStatement(selectQuery);
				ResultSet result=preparedStatement.executeQuery();
				
				String data="Data Not Found!!";
				while(result.next()){
					if(result.getInt(1)==s1){
						data="\n\tProduct ID\t"+result.getInt(1)+"\n\tName\t"+result.getString(2)+"\n\tPrice\t"+result.getInt(3)+"\n\tQuantity\t"+result.getInt(4)+"\n\tCompany\t"+result.getString(5)+"\n";
						return data;
					}
				}
				return data;
				}
				catch(Exception e){
					return e+"";
				}
		}

}
