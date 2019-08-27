module io.github.jsoagger.corebusiness.offline.operations {
	exports io.github.jsoagger.core.business.offline.operations;
	exports io.github.jsoagger.core.business.offline.operations.notifications;
	exports io.github.jsoagger.core.business.offline.operations.folderTags;

	requires com.google.gson;
	requires io.github.jsoagger.core.bridge;
	requires io.github.jsoagger.core.utils;
	requires io.github.jsoagger.corebusiness.offline.services;
}