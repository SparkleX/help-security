package help.security;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.session.Session;
import org.springframework.security.core.context.SecurityContextImpl;
public class ServiceLayerSession implements Session
{
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName)
	{
		System.out.println("getAttribute:" + attributeName);
		T o =  (T) this.sessionAttrs.get(attributeName);

		if(false == attributeName.equals(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY))
		{
			return null;
		}
		MyAuthenticationToken auth = 
				new MyAuthenticationToken(null, null);
		SecurityContextImpl rt = new SecurityContextImpl(auth);
		return (T) rt;

		//return o;
	}

	@Override
	public Set<String> getAttributeNames()
	{
		return this.sessionAttrs.keySet();
	}

	@Override
	public void setAttribute(String attributeName, Object attributeValue)
	{
		System.out.println("setAttribute:" + attributeName);
		if (attributeValue == null)
		{
			removeAttribute(attributeName);
		} else
		{
			this.sessionAttrs.put(attributeName, attributeValue);
		}
	}

	@Override
	public void removeAttribute(String attributeName)
	{
		System.out.println("removeAttribute:" + attributeName);
		this.sessionAttrs.remove(attributeName);
	}
	
	public static final int DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS = 1800;

	private String id;
	private String originalId;
	private Map<String, Object> sessionAttrs = new HashMap<>();
	private Instant creationTime = Instant.now();
	private Instant lastAccessedTime = this.creationTime;

	private Duration maxInactiveInterval = Duration.ofSeconds(DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS);

	public ServiceLayerSession()
	{
		this(generateId());
	}

	public ServiceLayerSession(String id)
	{
		this.id = id;
		this.originalId = id;
	}


	public ServiceLayerSession(Session session) 
	{
		if (session == null) {
			throw new IllegalArgumentException("session cannot be null");
		}
		this.id = session.getId();
		this.originalId = this.id;
		this.sessionAttrs = new HashMap<>(
				session.getAttributeNames().size());
		for (String attrName : session.getAttributeNames()) {
			Object attrValue = session.getAttribute(attrName);
			if (attrValue != null) {
				this.sessionAttrs.put(attrName, attrValue);
			}
		}
		this.lastAccessedTime = session.getLastAccessedTime();
		this.creationTime = session.getCreationTime();
		this.maxInactiveInterval = session.getMaxInactiveInterval();
	}

	@Override
	public void setLastAccessedTime(Instant lastAccessedTime)
	{
		this.lastAccessedTime = lastAccessedTime;
	}

	@Override
	public Instant getCreationTime()
	{
		return this.creationTime;
	}

	@Override
	public String getId()
	{
		return this.id;
	}

	public String getOriginalId()
	{
		return this.originalId;
	}

	void setOriginalId(String originalId)
	{
		this.originalId = originalId;
	}

	@Override
	public String changeSessionId()
	{
		String changedId = generateId();
		setId(changedId);
		return changedId;
	}

	@Override
	public Instant getLastAccessedTime()
	{
		return this.lastAccessedTime;
	}

	@Override
	public void setMaxInactiveInterval(Duration interval)
	{
		this.maxInactiveInterval = interval;
	}

	@Override
	public Duration getMaxInactiveInterval()
	{
		return this.maxInactiveInterval;
	}

	@Override
	public boolean isExpired()
	{
		return isExpired(Instant.now());
	}

	boolean isExpired(Instant now)
	{
		if (this.maxInactiveInterval.isNegative())
		{
			return false;
		}
		return now.minus(this.maxInactiveInterval).compareTo(this.lastAccessedTime) >= 0;
	}



	public void setCreationTime(Instant creationTime)
	{
		this.creationTime = creationTime;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof Session && this.id.equals(((Session) obj).getId());
	}

	@Override
	public int hashCode()
	{
		return this.id.hashCode();
	}

	private static String generateId()
	{
		return UUID.randomUUID().toString();
	}

}
