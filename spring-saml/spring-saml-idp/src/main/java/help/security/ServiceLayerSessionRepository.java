package help.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.session.FindByIndexNameSessionRepository;

public class ServiceLayerSessionRepository implements FindByIndexNameSessionRepository<ServiceLayerSession>
{

	HashMap<String, ServiceLayerSession> sessions = new HashMap<>();
	public ServiceLayerSession createSession() 
	{
		ServiceLayerSession s =  new ServiceLayerSession();
		sessions.put(s.getId(), s);
		return s;
		
	}

	@Override
	public void save(ServiceLayerSession session) 
	{
		System.out.println("123");
		
	}
	@Override
	public void deleteById(String id) {
		sessions.remove(id);
		
	}

	@Override
	public ServiceLayerSession findById(String id) {
		ServiceLayerSession s= sessions.get(id);
		return s;
	}


	public Map<String, ServiceLayerSession> findByIndexNameAndIndexValue(String indexName, String indexValue) 
	{
		System.out.println("123");
		return null;
	}




}
