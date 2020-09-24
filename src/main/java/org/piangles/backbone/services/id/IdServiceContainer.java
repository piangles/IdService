package org.piangles.backbone.services.id;

import com.TBD.core.email.EmailSupport;
import com.TBD.core.services.remoting.AbstractContainer;
import com.TBD.core.services.remoting.ContainerException;

public class IdServiceContainer extends AbstractContainer
{
	public static void main(String[] args)
	{
		IdServiceContainer container = new IdServiceContainer();
		try
		{
			container.performSteps();
		}
		catch (ContainerException e)
		{
			EmailSupport.notify(e, e.getMessage());
			System.exit(-1);
		}
	}

	public IdServiceContainer()
	{
		super("IdService");
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
