package br.com.xtrategia.fiscon.teste;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;
import org.hibernate.mapping.MetaAttribute;

public class CustomHibernateTool extends DelegatingReverseEngineeringStrategy {

	public CustomHibernateTool(ReverseEngineeringStrategy delegate) {
		super(delegate);

	}

	public String columnToPropertyName(TableIdentifier table, String column) {

		if (column.endsWith("PK")) {

			return "id";

		} else {

			return super.columnToPropertyName(table, column);

		}

	}

	
	
	@Override
	public String tableToClassName(TableIdentifier tableIdentifier) {
		return super.tableToClassName(tableIdentifier) + "Pojo";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map tableToMetaAttributes(TableIdentifier tableIdentifier) {
		Map<String, MetaAttribute> metaAttributes = new HashMap<String, MetaAttribute>();
		
		// serialVersionUID
		MetaAttribute classCode = new MetaAttribute("class-code");
		classCode.addValue("private static final long serialVersionUID = 1L;");
		metaAttributes.put("class-code", classCode);

		MetaAttribute extraImport = new MetaAttribute("extra-import");
		extraImport.addValue("br.com.xtrategia.fiscon.infra.TransferObject");
		metaAttributes.put("extra-import", extraImport);
		
		MetaAttribute extraExtends = new MetaAttribute("extends");
		extraExtends.addValue("TransferObject");
		metaAttributes.put("extends", extraExtends);
		
		
		MetaAttribute classDescription = new MetaAttribute("class-description");
		classDescription.addValue("Classe para implementar o POJO " +super.tableToClassName(tableIdentifier)  );
		classDescription.addValue("\n@author Gustavo Marcelo Costa");
		metaAttributes.put("class-description", classDescription);
		
		return metaAttributes;
	}
}