/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package moviesdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;  

/**
 *
 * @author user
 */
public class MoviesDB {

      private static Connection CreateNewTable(){
        Connection  conn=null;
        String url="jdbc:sqlite:C:/sqlite3/movies.db";
        String sql = "CREATE TABLE IF NOT EXISTS MOVIES_LIST" +
                        "(ID TEXT NOT NULL,"+
                        " MOVIE_NAME  TEXT  NOT NULL," +
                        " DIRECTOR  TEXT    NOT NULL, " + 
                        " ACTOR     TEXT     NOT NULL, " + 
                        " ACTRESS   TEXT NOT NULL, " + 
                        " YEAR_OF_RELEASE  INTEGER NOT NULL  )";  
        try{            
            conn =DriverManager.getConnection(url);
            Statement st=conn.createStatement();
            st.execute(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            
        }
        return conn;
    }
      public  static void insert(String ID, String MOVIE_NAME,  String DIRECTOR,String  ACTOR,String ACTRESS,Integer YEAR_OF_RELEASE) {  
        String sql = "INSERT INTO MOVIES_LIST(ID, MOVIE_NAME, DIRECTOR, ACTOR, ACTRESS, YEAR_OF_RELEASE) VALUES(?,?,?,?,?,?)";  
   
        try{  
            Connection conn = CreateNewTable();  
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, ID);
            pstmt.setString(2, MOVIE_NAME);
            pstmt.setString(3, DIRECTOR);
            pstmt.setString(4, ACTOR);
            pstmt.setString(5, ACTRESS);
            pstmt.setInt(6, YEAR_OF_RELEASE);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    public static void selectAll(){  
        String sql1= "SELECT * FROM MOVIES_LIST";  
          
        try {  
            Connection conn = CreateNewTable();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql1);  
              
            while (rs.next()) {  
                System.out.println(rs.getString("ID")+ "\t\t" +
                                   rs.getString("MOVIE_NAME") +  "\t\t" +   
                                   rs.getString("DIRECTOR") + "\t\t" +  
                                   rs.getString("ACTOR") + "\t\t" +
                                   rs.getString("ACTRESS") + "\t\t" +
                                   rs.getInt("YEAR_OF_RELEASE"));  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 
public static void delete(String m0,String m1){
     String sql2 = "DELETE FROM MOVIES_LIST WHERE MOVIE_NAME = ?";

        try {
            Connection conn = CreateNewTable();
            PreparedStatement pstmt = conn.prepareStatement(sql2); 
            pstmt.setString(1, m1);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
public static void Display(String A0,String A1){  
        String sql3= "SELECT * FROM MOVIES_LIST WHERE ACTOR=?" ;  
          
        try {  
            Connection conn = CreateNewTable(); 
            PreparedStatement pstmt  = conn.prepareStatement(sql3);  
            pstmt.setString(1, A1);
            ResultSet rs    = pstmt.executeQuery();  
              
            while(rs.next()){
                System.out.println(rs.getString("ID")+ "\t\t" +
                                   rs.getString("MOVIE_NAME") +  "\t\t" +   
                                   rs.getString("DIRECTOR") + "\t\t" +  
                                   rs.getString("ACTOR") + "\t\t" +
                                   rs.getString("ACTRESS") + "\t\t" +
                                   rs.getInt("YEAR_OF_RELEASE"));  
            }
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 
    public static void main(String[] args){
        MoviesDB mdb=new MoviesDB();
        Integer n,yr,c;
        String m_n,dir,act,actrs,t,m1,m0,A0,A1;
        Scanner sc=new Scanner(System.in);
        for(;;){
        
        System.out.println("1:Insert data\n2:Delete data\n3:Display\n4:Display the Movie/s of user specified Actor\n 5:Exit\n");
        c=sc.nextInt();
        switch(c){
            case 1 -> {
                System.out.println("Enter the number of times of Insertion");
                n=sc.nextInt();
                while(n!=0){
                    System.out.println("Enter Sl no");
                    t=sc.nextLine();
                    System.out.println("Enter Movie Name");
                    m_n = sc.nextLine();
                    System.out.println("Enter Director name");
                    dir=sc.nextLine();
                    System.out.println("Enter Actor Name");
                    act=sc.nextLine();
                    System.out.println("Enter Actress Name");
                    actrs=sc.nextLine();
                    System.out.println("Enter Year of release");
                    yr=sc.nextInt();
                    
                    MoviesDB.insert(t,m_n,dir,act,actrs,yr);
                    n--;
                }
            }   
        case 2 -> {
            System.out.println("Enter details");
            m0=sc.nextLine();
            System.out.println("Enter the movie to delete");
            m1=sc.nextLine();
            MoviesDB.delete(m0,m1);
                }
        case 3 ->{
                System.out.println("\t\tMovie_name\tDirector\tActor\t\tActress\t\t Year");
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                MoviesDB.selectAll();
                System.out.println("---------------------------------------------------------------------------------------------------------------");}
        case 4 ->{
                System.out.println("Enter details");
                A0=sc.nextLine();
                System.out.println("Enter the Actor name");
                A1=sc.nextLine();
                System.out.println("\t\tMovie_name\tDirector\tActor\t\tActress\t\t Year");
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                MoviesDB.Display(A0,A1);
                System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
        case 5 ->{ System.exit(0);}
    }
    }
}
}
