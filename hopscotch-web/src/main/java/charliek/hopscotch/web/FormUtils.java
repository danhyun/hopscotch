package charliek.hopscotch.web;

import ratpack.handling.Context;
import ratpack.util.MultiValueMap;

import java.util.Optional;

public class FormUtils {

	public static Optional<Integer> getIntParam(Context context, String name) {
		MultiValueMap<String, String> params = context.getRequest().getQueryParams();
		String value = params.get(name);
		if (value == null) {
			return Optional.empty();
		} else {
			try {
				return Optional.of(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				return Optional.empty();
			}
		}
	}
}
