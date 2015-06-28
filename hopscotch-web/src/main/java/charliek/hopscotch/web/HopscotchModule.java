package charliek.hopscotch.web;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ratpack.jackson.JacksonModule;
import ratpack.rx.RxRatpack;

public class HopscotchModule extends AbstractModule {
	@Override
	protected void configure() {
		RxRatpack.initialize();
		install(new JacksonModule());

		bind(TimedHandler.class).in(Scopes.SINGLETON);
		bind(ApiHandlerDecorator.class).in(Scopes.SINGLETON);

		// Adding doesn't change the behavior anything
		// Multibinder.newSetBinder(binder(), HandlerDecorator.class).addBinding().to(ApiHandlerDecorator.class);
	}
}
