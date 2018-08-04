package application.cdms.hibernate.utility;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
  
public final class HibernateUtils {
	private static volatile SessionFactory SESSIONFACTORY =null;
	
	private HibernateUtils(){
		
	}
	private static void intiateConnection(){
		try{
			System.out.println("<-----------------------------Building HibernateSessionFactory-------------------->");
			File file = new File("/app/kcd/PropertiesFiles/kcd-hibernate.cfg.xml");
			Configuration cfg = new Configuration().configure(file);
			cfg.addResource("KCD_Namedquery.hbm.xml"); 
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
			SESSIONFACTORY = cfg.buildSessionFactory(serviceRegistryBuilder.build());
			System.out.println("<----------------Hibearnate SessionBuildFactory has been created------------------------>");
		}
		catch(Exception e){
			System.out.println("<--------------------Failed to Create of SessionBuildFactory---------------------> ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	 }
	
	public static SessionFactory getHibernateSessionFactory(){
		   System.out.println("<---------------------------------getting HibernateSessionFactory----------------->");
		   if(SESSIONFACTORY==null){
			  synchronized(HibernateUtils.class){
				   if(SESSIONFACTORY==null){
					   intiateConnection();
				   }
			   }
		   }
		   return SESSIONFACTORY;
	}
	
	public static final ThreadLocal<CustomeTransationManager> CustomeTransationManager1 = new ThreadLocal<CustomeTransationManager>();
	
	public static CustomeTransationManager getCustomeTrasationManager(){
		CustomeTransationManager customeTransationManager = CustomeTransationManager1.get();
		if(customeTransationManager==null){
			System.out.println("<---------------------------------End of HibernateSessionFactory----------------->");
			customeTransationManager=new CustomeTransationManager();
			CustomeTransationManager1.set(customeTransationManager);
		}
		return customeTransationManager;
	}
	
	public static void CloseCustomeTransationManager(){
		CustomeTransationManager customeTransationManager = CustomeTransationManager1.get();
		if(customeTransationManager!=null){
			customeTransationManager.getSession().close();
		}
		customeTransationManager=null;
		CustomeTransationManager1.set(customeTransationManager);
	}
	
	public static void commitCloseCustomeTransationManager(){
		CustomeTransationManager customeTransationManager = CustomeTransationManager1.get();
		if(customeTransationManager!=null){
			customeTransationManager.commitTx();
			customeTransationManager.getSession().close();
		}
		customeTransationManager=null;
		CustomeTransationManager1.set(customeTransationManager);
	}
	
	public static Session getHibernateRunningSession(){
		return HibernateUtils.getHibernateSessionFactory().getCurrentSession();
	}
	
	public static class CustomeTransationManager{
		private Session session = HibernateUtils.getHibernateSessionFactory().openSession();
		private Transaction tx=null;
		public Session getSession() {
			return session;
		}
		public Session initTx(){
			if(tx==null){
				tx=session.beginTransaction();
			}
			return session;
		}
		public Transaction getTx() {
			return tx;
		}
		public void commitTx(){
			if(tx!=null){
				tx.commit();
			}
		}
		public void rollBackTx(){
			if(tx!=null){
				tx.rollback();
			}
		}
	}
}
