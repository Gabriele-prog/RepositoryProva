
package com.journaldev.jdbc.storedproc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class JDBCStoredProcedureWrite {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement stmt = null;
		
		try{
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{call getPersonaByEta(?,?)}");
			
			stmt.setInt(1,23);			
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			stmt.execute();

			ResultSet rs = (ResultSet) stmt.getObject(2);
		
            while(rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3) );
            }
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
