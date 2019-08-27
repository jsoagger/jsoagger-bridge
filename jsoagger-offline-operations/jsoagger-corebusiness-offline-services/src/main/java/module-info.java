module io.github.jsoagger.corebusiness.offline.services {
	exports io.github.jsoagger.core.business.offline.services.notifications.impl;
	exports io.github.jsoagger.core.business.offline.services.notifications.api;
	exports io.github.jsoagger.core.business.offline.services.notifications;

	requires com.google.gson;
	requires io.github.jsoagger.core.bridge;
}