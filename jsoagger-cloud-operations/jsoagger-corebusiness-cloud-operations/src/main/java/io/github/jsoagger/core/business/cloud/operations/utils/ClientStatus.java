/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.utils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class ClientStatus implements Serializable {

	private static final long	serialVersionUID	= -8875419257188101916L;
	private LocalDateTime		downSince;
	private boolean				loggedIn;
	private LocalDateTime		loggedInSince;


	/**
	 * Constructor
	 */
	public ClientStatus() {
	}


	/**
	 * Getter of loggedInSince
	 *
	 * @return the loggedInSince
	 */
	public LocalDateTime getLoggedInSince() {
		return loggedInSince;
	}


	/**
	 * Setter of loggedInSince
	 *
	 * @param loggedInSince
	 *            the loggedInSince to set
	 */
	public void setLoggedInSince(LocalDateTime loggedInSince) {
		this.loggedInSince = loggedInSince;
	}


	/**
	 * Getter of loggedIn
	 *
	 * @return the loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}


	/**
	 * Setter of loggedIn
	 *
	 * @param loggedIn
	 *            the loggedIn to set
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	/**
	 * Server is down if since parameter is not null.
	 *
	 * @return
	 */
	public boolean isDown() {
		return downSince != null;
	}


	/**
	 * Getter of since
	 *
	 */
	public LocalDateTime downSince() {
		return downSince;
	}


	/**
	 * Setter of since
	 *
	 * @param since
	 *            the since to set
	 */
	public void setDownSince(LocalDateTime since) {
		this.downSince = since;
	}

	/**
	 * @author Ramilafananana VONJISOA
	 * @mailto yvonjisoa@nexitia.com
	 * @date 2019
	 */
	public static class ServerDownEventBuilder {

		private LocalDateTime since;


		public ServerDownEventBuilder since(LocalDateTime since) {
			this.since = since;
			return this;
		}


		public ClientStatus build() {
			return new ClientStatus(this);
		}
	}


	private ClientStatus(ServerDownEventBuilder builder) {
		this.downSince = builder.since;
	}


	/**
	 * @{inheritedDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientStatus [");
		if (downSince != null) {
			builder.append("downSince=");
			builder.append(downSince);
			builder.append(", ");
		}
		builder.append("loggedIn=");
		builder.append(loggedIn);
		builder.append(", ");
		if (loggedInSince != null) {
			builder.append("loggedInSince=");
			builder.append(loggedInSince);
		}
		builder.append("]");
		return builder.toString();
	}
}
