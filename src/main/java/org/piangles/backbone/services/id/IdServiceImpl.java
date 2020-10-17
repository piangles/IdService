package org.piangles.backbone.services.id;

import java.util.UUID;

import org.piangles.backbone.services.Locator;
import org.piangles.backbone.services.logging.LoggingService;

public class IdServiceImpl implements IdService
{
	private LoggingService logger = Locator.getInstance().getLoggingService();

	public IdServiceImpl()
	{
		
	}
	
	@Override
	public Identifier getNextIdentifier(String idType) throws IdException
	{
		String uuId = UUID.randomUUID().toString();
		String id = uuId.substring(0, uuId.indexOf('-'));
		logger.info("Generating new Id for " + idType + " :" + id);
		return new Identifier(id, System.currentTimeMillis());
	}
}
