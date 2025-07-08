package org.pauldenhez.accountme.common.model.hateoas;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LinksBuilder {
    public static Links buildTransactionUriPagedLink(int size, int currentPageNumber, int totalPages, String baseUri) {
        int n = 5;
        String next = "";
        String prev = "";
        String last = "";
        String first = "";

        int rest = totalPages - currentPageNumber - n;
        int firstNumber = rest < 0 ? currentPageNumber + rest + 1 : currentPageNumber;
        int lastNumber = totalPages - currentPageNumber - n < 0 ? totalPages : currentPageNumber + n - 1;

        int[] nNext = IntStream.rangeClosed(firstNumber, lastNumber).toArray();

        Map<Integer, String> nLinks = IntStream.of(nNext).boxed().collect(Collectors.toMap(
                i -> i,
                i -> baseUri + "?size=" + size + "&page=" + i
        ));

        if(currentPageNumber < totalPages) {
            next = baseUri + "?size=" + size + "&page=" + (currentPageNumber + 1);
            last = baseUri + "?size=" + size + "&page=" + totalPages;
        }
        if(currentPageNumber > 1) {
            prev = baseUri + "?size=" + size + "&page=" + (currentPageNumber - 1);
            first = baseUri + "?size=" + size + "&page=1";
        }

        return  new Links(next, prev, last, first, nLinks, n);
    }

}
