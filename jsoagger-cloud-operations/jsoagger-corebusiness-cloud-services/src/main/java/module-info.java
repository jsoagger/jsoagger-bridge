module io.github.jsoagger.corebusiness.cloud.services {
	exports io.github.jsoagger.core.business.cloud.services.utils;
	exports io.github.jsoagger.core.business.cloud.services.impl;
	exports io.github.jsoagger.core.business.cloud.services.api;

	requires com.google.gson;
	requires io.github.jsoagger.core.bridge;
	requires io.github.jsoagger.core.ioc;
	requires okhttp;
	requires okio;
}