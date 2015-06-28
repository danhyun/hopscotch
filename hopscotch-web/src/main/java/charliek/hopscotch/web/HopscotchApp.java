package charliek.hopscotch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.config.ConfigData;
import ratpack.error.internal.DefaultDevelopmentErrorHandler;
import ratpack.error.internal.ErrorHandler;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

public class HopscotchApp {
	private final static Logger LOG = LoggerFactory.getLogger(HopscotchApp.class);

	public static void main(String[] args) throws Exception {
		ConfigData configData = ConfigData.of(d -> {
			d.env();
			d.sysProps();
		});

		ServerConfig.Builder serverConfig = ServerConfig.findBaseDirProps().sysProps().env();

		RatpackServer.start(spec -> {
			spec.serverConfig(serverConfig);
			spec.registry(Guice.registry(b -> {
				b.bindInstance(ConfigData.class, configData);
				b.add(new HopscotchModule());
				b.bind(ErrorHandler.class, DefaultDevelopmentErrorHandler.class);
			}));
			spec.handlers(c -> {
				c.prefix("static", nested -> {
					nested.assets("/static", "index.html");
				});
			});
		});
	}
}
