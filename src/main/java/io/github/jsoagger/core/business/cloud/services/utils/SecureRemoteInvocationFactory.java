/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.utils;

/**
 * TODO collect more information about client and put it into session.
 *
 * @author Administrator
 */

public class SecureRemoteInvocationFactory {//extends DefaultRemoteInvocationFactory {

  //	private static final Logger log = LoggerFactory.getLogger(SecureRemoteInvocationFactory.class);
  //
  //	public static final String	SESSION_ID_KEY	= org.apache.shiro.spring.remoting.SecureRemoteInvocationFactory.class
  //			.getName() + ".SESSION_ID_KEY";
  //	public static final String	HOST_KEY		= org.apache.shiro.spring.remoting.SecureRemoteInvocationFactory.class
  //			.getName() + ".HOST_KEY";
  //
  //	private static final String SESSION_ID_SYSTEM_PROPERTY_NAME = "shiro.session.id";
  //
  //	private String sessionId;
  //
  //
  //	public SecureRemoteInvocationFactory() {
  //	}
  //
  //
  //	public SecureRemoteInvocationFactory(String sessionId) {
  //		this();
  //		this.sessionId = sessionId;
  //	}
  //
  //
  //	/**
  //	 * Creates a {@link RemoteInvocation} with the current session ID as an
  //	 * {@link RemoteInvocation#getAttribute(String) attribute}.
  //	 *
  //	 * @param mi
  //	 *            the method invocation that the remote invocation should be
  //	 *            based on.
  //	 * @return a remote invocation object containing the current session ID as
  //	 *         an attribute.
  //	 */
  //	@Override public RemoteInvocation createRemoteInvocation(MethodInvocation mi) {
  //
  //		Serializable sessionId = null;
  //		String host = null;
  //		boolean sessionManagerMethodInvocation = false;
  //
  //		// If the calling MI is for a remoting SessionManager delegate, we need
  //		// to acquire the session ID from the method
  //		// argument and NOT interact with SecurityUtils/subject.getSession to
  //		// avoid a stack overflow
  //		Class miDeclaringClass = mi.getMethod().getDeclaringClass();
  //		if (SessionManager.class.equals(miDeclaringClass) || NativeSessionManager.class.equals(miDeclaringClass)) {
  //			sessionManagerMethodInvocation = true;
  //			// for SessionManager calls, all method calls except the 'start'
  //			// methods require a SessionKey
  //			// as the first argument, so just get it from there:
  //			if (!mi.getMethod().getName().equals("start")) {
  //				SessionKey key = (SessionKey) mi.getArguments()[0];
  //				sessionId = key.getSessionId();
  //			}
  //		}
  //
  //		// tried the delegate. Use the injected session id if given
  //		if (sessionId == null) sessionId = this.sessionId;
  //
  //		// If sessionId is null, only then try the Subject:
  //		if (sessionId == null) {
  //			try {
  //				// HACK Check if can get the securityManager - this'll cause an
  //				// exception if it's not set
  //				SecurityUtils.getSecurityManager();
  //				if (!sessionManagerMethodInvocation) {
  //					Session session = subject.getSession(false);
  //					if (session != null) {
  //						sessionId = session.getId();
  //						host = session.getHost();
  //					}
  //				}
  //			}
  //			catch (Exception e) {
  //				log.trace("No security manager set. Trying next to get session id from system property");
  //			}
  //		}
  //		// No call to the sessionManager, and the Subject doesn't have a
  //		// session. Try a system property
  //		// as a last result:
  //		if (sessionId == null) {
  //			if (log.isTraceEnabled()) {
  //				log.trace("No Session found for the currently executing subject via subject.getSession(false).  "
  //						+ "Attempting to revert back to the 'shiro.session.id' system property...");
  //			}
  //
  //			sessionId = System.getProperty(SESSION_ID_SYSTEM_PROPERTY_NAME);
  //			if (sessionId == null)
  //				log.trace("No 'shiro.session.id' system property found.  Heuristics have been exhausted; "
  //						+ "RemoteInvocation will not contain a sessionId.");
  //			else log.trace("Session id : " + sessionId);
  //
  //			if (sessionId == null && log.isTraceEnabled()) {
  //				log.trace("No 'shiro.session.id' system property found.  Heuristics have been exhausted; "
  //						+ "RemoteInvocation will not contain a sessionId.");
  //			}
  //		}
  //
  //		RemoteInvocation ri = new RemoteInvocation(mi);
  //		if (sessionId != null) {
  //			ri.addAttribute(SESSION_ID_KEY, sessionId);
  //		}
  //		if (host != null) {
  //			ri.addAttribute(HOST_KEY, host);
  //		}
  //
  //		return ri;
  //	}
}
