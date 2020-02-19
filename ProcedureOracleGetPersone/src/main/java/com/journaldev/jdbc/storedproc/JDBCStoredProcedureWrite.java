
package com.journaldev.jdbc.storedproc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCStoredProcedureWrite {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement stmt = null;
		
		try{
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{call getPersona(?,?,?,?)}");
			
			stmt.setString(1,"Gabriele");			
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			stmt.executeUpdate();
		
            String nome = stmt.getString(2);
            String cognome = stmt.getString(3);
            int eta = stmt.getInt(4);
			
			System.out.println(nome + " " + cognome + " " + eta );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
