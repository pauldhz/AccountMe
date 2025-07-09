package org.pauldenhez.accountme.common.model.hateoas;

import java.util.Map;

public record Links(String next, String prev, String last, String first, Map<Integer, String> nNextLinks, int n) {}
