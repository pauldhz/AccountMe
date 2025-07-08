package org.pauldenhez.accountme.common.model.hateoas;

import io.micrometer.common.util.StringUtils;

public final class LinksBuilder {
    public static Links buildTransactionUriPagedLink(int size, int currentPageNumber, int totalPages, String baseUri) {
        String next = "";
        String prev = "";
        String last = "";
        String first = "";

        if(currentPageNumber < totalPages) {
            next = baseUri + "?size=" + size + "&page=" + (currentPageNumber + 1);
            last = baseUri + "?size=" + size + "&page=" + totalPages;
        }
        if(currentPageNumber > 1) {
            prev = baseUri + "?size=" + size + "&page=" + (currentPageNumber - 1);
            first = baseUri + "?size=" + size + "&page=1";
        }
        return  new Links(next, prev, last, first);
    }

}
