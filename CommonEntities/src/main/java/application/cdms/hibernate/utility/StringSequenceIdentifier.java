package application.cdms.hibernate.utility;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;

import application.cdms.db.entities.Serialnogenerator;

public class StringSequenceIdentifier implements IdentifierGenerator,Configurable {
	public static final String SEQUENCE_COLUMN_NAME = "seqColumnNm";

	private String tableSEQCOLNM;

   // private String sequenceCallSyntax;
    
	@Override
	public void configure(Type arg0, Properties params, Dialect dialect) throws MappingException {
		tableSEQCOLNM = ConfigurationHelper.getString(SEQUENCE_COLUMN_NAME,params);
       // final String sequencePerEntitySuffix = ConfigurationHelper.getString(SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX,params,SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);
        //final String defaultSequenceName = ConfigurationHelper.getBoolean(SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY,params,false)? params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix: SequenceStyleGenerator.DEF_SEQUENCE_NAME;
        //sequenceCallSyntax = dialect.getSequenceNextValString(ConfigurationHelper.getString(SequenceStyleGenerator.SEQUENCE_PARAM,params,defaultSequenceName));
		System.out.println(tableSEQCOLNM);
	}
	
	//final static String queryString = "FROM Serialnogenerator where seqName=?";
	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
		Session sess=Session.class.cast(session);
		//Query query=sess.createQuery(queryString);
		//query.setParameter(0, tableSEQCOLNM);
		Serialnogenerator serialnogenerator =(Serialnogenerator) sess.get(Serialnogenerator.class, tableSEQCOLNM);
		//Serialnogenerator serialnogenerator = (Serialnogenerator) query.uniqueResult();
		Long id = serialnogenerator.getSeqValue()+1;
		serialnogenerator.setSeqValue(id);
		Serialnogenerator serialnogenerator1=(Serialnogenerator) sess.merge(serialnogenerator);
		String prefix=serialnogenerator1.getSeqPrefix();
		String formatter=null;
		if(prefix!=null){
			formatter="%0"+(serialnogenerator1.getSeqSize()-prefix.length())+"d";
		}
		else{
			prefix="";
			formatter="%0"+(serialnogenerator1.getSeqSize())+"d";
		}
		
		String prefixId=prefix+String.format(formatter,serialnogenerator1.getSeqValue());
		System.out.println(prefixId);
        return prefixId;
	}

}