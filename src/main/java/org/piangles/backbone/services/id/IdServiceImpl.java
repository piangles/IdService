package org.piangles.backbone.services.id;

import java.util.UUID;

import org.piangles.backbone.services.Tier2ServiceLocator;
import org.piangles.backbone.services.logging.LoggingService;

public class IdServiceImpl implements IdService
{
	private LoggingService logger = Tier2ServiceLocator.getInstance().getLoggingService();

	public IdServiceImpl()
	{
		
	}
	
	@Override
	public String getNextIdentifier(String idType) throws IdException
	{
		String id = UUID.randomUUID().toString();
		return id.substring(0, id.indexOf('-'));
	}
}
