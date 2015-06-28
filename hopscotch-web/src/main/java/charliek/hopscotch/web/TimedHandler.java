package charliek.hopscotch.web;

import com.google.common.collect.Maps;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;
import rx.Observable;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static charliek.hopscotch.web.FormUtils.getIntParam;
import static ratpack.jackson.Jackson.json;

public class TimedHandler implements Handler {

	@Inject
	public TimedHandler() {
	}

	@Override
	public void handle(Context context) {
		Optional<Integer> delay = getIntParam(context, "delay");
		Observable<Long> oDelay = Observable.just(0L);
		if (delay.isPresent()) {
			oDelay = Observable.timer(delay.get(), TimeUnit.MILLISECONDS);
		}
		RxRatpack.promise(oDelay).then(t -> {
			Map<String, String> m = Maps.newHashMap();
			m.put("hello", "world");
			m.put("foo", "bar");
			context.render(json(m));
		});
	}
}
