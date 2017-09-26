package tfd.system.mobile.feedback.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tfd.system.mobile.feedback.data.Feedback;

public class FeedbackLogic {

	public int feedbackLogic(Connection conn,Feedback feedback) throws SQLException
	{
		int returnData=0;
		String queryStr="INSERT INTO FEEDBACK (FEEDBACK_ID,CONTENT,CLIENT_TYPE,ORG_ID,ATTACH_ID,VERSION)VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, feedback.getFeedbackId());
		ps.setString(2, feedback.getContent());
		ps.setString(3, feedback.getClientType());
		ps.setString(4, feedback.getOrgId());
		ps.setString(5, feedback.getAttachId());
		ps.setString(6, feedback.getVersion());
		returnData = ps.executeUpdate();
		ps.close();
		return returnData;
	}
}
