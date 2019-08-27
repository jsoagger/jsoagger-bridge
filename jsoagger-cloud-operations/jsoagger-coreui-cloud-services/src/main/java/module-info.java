module io.github.jsoagger.coreui.cloud.services {
	exports io.github.jsoagger.core.ui.cloud.services.impl;
	exports io.github.jsoagger.core.ui.cloud.services.api;
	exports io.github.jsoagger.core.ui.cloud.services.utils;

	requires com.google.gson;
	requires io.github.jsoagger.core.bridge;
}