package org.cloud.federation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DB_Conversation {

	private static List<Conversation> list = new ArrayList<Conversation>();
	
	public synchronized static List<Conversation> listConversation()
	{
		return list;
	}
	
	public synchronized static boolean create_Conversation(String user,
			String session) {
		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) 
				return false ;	
		}
		list.add(new Conversation(user, session));
		return true;
	}
	
	public synchronized static boolean create_ConversationWithOvfFile_cfp(String user,
			String session,String ovfFile_cfp ) {
		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) 
				return false ;	
		}
		list.add(new Conversation(user, session, ovfFile_cfp));
		return true;
	}
	
	public synchronized static boolean remove_Conversation(String user,
			String session) {
		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) 
			{
				list.remove(new Conversation(user, session));
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean setOvfFile_cfpToConversation(String user,String session, String ovfFile_cfp)
	{
		for(int i=0; i<list.size(); i++) {
			Conversation c=list.get(i);
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				c.setOvfFile_cfp(ovfFile_cfp);
				list.add(i, c);
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean setOvfFile_proposalToConversation(String user,String session, String ovfFile_proposal)
	{
		for(int i=0; i<list.size(); i++) {
			Conversation c=list.get(i);
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				c.setOvfFile_proposal(ovfFile_proposal);
				list.add(i, c);
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean setOvfFile_accpetToConversation(String user,String session, String ovfFile_accpet)
	{
		for(int i=0; i<list.size(); i++) {
			Conversation c=list.get(i);
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				c.setOvfFile_accpet(ovfFile_accpet);
				list.add(i, c);
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean setOvfFile_infoToConversation(String user,String session, String ovfFile_info)
	{
		for(int i=0; i<list.size(); i++) {
			Conversation c=list.get(i);
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				c.setOvfFile_info(ovfFile_info);
				list.add(i, c);
				return true;
			}
		}
		return false;
	}
	
	public synchronized static String getOvfFil_cfp(String user,
			String session) {

		String ovfFile_cfp = null;

		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				return c.getOvfFile_cfp();
			}
		}

		return ovfFile_cfp;
	}

	public synchronized static String getOvfFil_proposal(String user,
			String session) {

		String ovfFile_proposal = null;

		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				return c.getOvfFile_proposal();
			}
		}

		return ovfFile_proposal;
	}

	public synchronized static String getOvfFil_accpet(String user,
			String session) {

		String ovfFile_accpet = null;

		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				return c.getOvfFile_accpet();
			}
		}

		return ovfFile_accpet;
	}

	public synchronized static String getOvfFil_info(String user,
			String session) {

		String ovfFile_info = null;

		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				return c.getOvfFile_info();
			}
		}

		return ovfFile_info;
	}

	public synchronized static boolean isFround(String user,
			String session) {

		for (Conversation c : list) {
			if (c.getUser().equals(user) && c.getSession().equals(session)) {
				return true;
			}
		}
		return false;
	}
	public synchronized static Conversation getConveration(String session) {

		for (Conversation c : list) {
			if (c.getSession().equals(session)) {
				return c;
			}
		}
		return null;
	}

	/*
	public synchronized static void showBD() {

		for (Conversation c : list) {
			System.out.println(c.getSession());
	
		}
	}
	*/
	public synchronized static int sizeBD() {

		return list.size();
	}

}
