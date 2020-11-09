package org.piangles.backbone.services.id;

import org.piangles.core.email.EmailSupport;
import org.piangles.core.services.remoting.AbstractContainer;
import org.piangles.core.services.remoting.ContainerException;

public class IdServiceContainer extends AbstractContainer
{
	public static void main(String[] args)
	{
		IdServiceContainer container = new IdServiceContainer();
		try
		{
			container.performSteps(args);
		}
		catch (ContainerException e)
		{
			EmailSupport.notify(e, e.getMessage());
			System.exit(-1);
		}
	}

	public IdServiceContainer()
	{
		super(IdService.NAME);
	}
	
	@Override
	protected Object createServiceImpl() throws ContainerException
	{
		Object service = null;
		try
		{
			service = new IdServiceImpl();
		}
		catch (Exception e)
		{
			throw new ContainerException(e);
		}
		return service;
	}
}
