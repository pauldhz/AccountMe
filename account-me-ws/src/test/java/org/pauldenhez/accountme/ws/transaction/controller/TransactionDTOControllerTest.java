    package org.pauldenhez.accountme.ws.transaction.controller;

    import com.jayway.jsonpath.DocumentContext;
    import com.jayway.jsonpath.JsonPath;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.web.client.TestRestTemplate;
    import org.springframework.test.context.DynamicPropertyRegistry;
    import org.springframework.test.context.DynamicPropertySource;
    import org.springframework.test.context.junit.jupiter.SpringExtension;
    import org.testcontainers.elasticsearch.ElasticsearchContainer;
    import org.pauldenhez.accountme.ws.repository.TransactionRepository;
    import org.testcontainers.junit.jupiter.Container;
    import org.testcontainers.junit.jupiter.Testcontainers;

    import static org.assertj.core.api.Assertions.assertThat;

    @Testcontainers
    @ExtendWith(SpringExtension.class)
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class TransactionDTOControllerTest {

        private final static int MIN_DATA_TEST_LENGTH = 6;

        @Container
        static ElasticsearchContainer elasticsearchContainer =
                new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.17.2")
                        .withEnv("xpack.security.enabled", "false");
        @Autowired
        TestRestTemplate testRestTemplate;
        @Autowired
        TransactionRepository transactionRepository;

        @DynamicPropertySource
        static void properties(DynamicPropertyRegistry registry) {
            registry.add("spring.elasticsearch.uris", elasticsearchContainer::getHttpHostAddress);
        }

        @Test
        public void shouldElasticSearchAbleToRun() {
            assertThat(elasticsearchContainer.isCreated()).isTrue();
            assertThat(elasticsearchContainer.isRunning()).isTrue();
        }

        @Test
        public void shouldReturnFirstPageTransactions() {
            int SIZE_LIMIT = 5;
            String transactionURI = "/transactions?size=" + SIZE_LIMIT + "&page=1";
            var responseEntity = testRestTemplate.getForEntity(transactionURI, String.class);
            DocumentContext ctx = JsonPath.parse(responseEntity.getBody());
            int length = ctx.read("$.length()");
            assertThat(length).isLessThanOrEqualTo(SIZE_LIMIT);
        }

        @Test
        public void shouldReturnFirstPageWithNextPageLink() {
            int SIZE_LIMIT = 5;
            int PAGE_NUMBER = 1;
            String transactionURI = "/transactions?size=" + SIZE_LIMIT + "&page=" + PAGE_NUMBER;
            var responseEntity = testRestTemplate.getForEntity(transactionURI, String.class);
            DocumentContext ctx = JsonPath.parse(responseEntity.getBody());
            String nextLink = ctx.read("$.links.next");
            assertThat(nextLink).isEqualTo("/transactions?size=" + SIZE_LIMIT + "&page=" + (PAGE_NUMBER + 1));
        }

        @Test
        public void shouldReturnSecondPageWithFirstPageLink() {
            int SIZE_LIMIT = 5;
            int PAGE_NUMBER = 2;
            String transactionURI = "/transactions?size=" + SIZE_LIMIT + "&page=" + PAGE_NUMBER;
            var responseEntity = testRestTemplate.getForEntity(transactionURI, String.class);
            DocumentContext ctx = JsonPath.parse(responseEntity.getBody());
            String prevLink = ctx.read("$.links.prev");
            assertThat(prevLink).isEqualTo("/transactions?size=" + SIZE_LIMIT + "&page=" + (PAGE_NUMBER - 1));
        }

        @Test
        public void shouldReturnFirstLinkWhenPageNotFirst() {
            int SIZE_LIMIT = 5;
            int PAGE_NUMBER = 2;

            String transactionURI = "/transactions?size=" + SIZE_LIMIT + "&page=" + PAGE_NUMBER;
            var responseEntity = testRestTemplate.getForEntity(transactionURI, String.class);
            DocumentContext ctx = JsonPath.parse(responseEntity.getBody());
            String firstLink = ctx.read("$.links.first");
            assertThat(firstLink).isEqualTo("/transactions?size=" + SIZE_LIMIT + "&page=1");
        }

        @Test
        public void shouldReturnLastLinkWhenPageNotLast() {
            int SIZE_LIMIT = 5;
            int PAGE_NUMBER = 1;
            int LAST_PAGE_NUMBER = 2;

            String transactionURI = "/transactions?size=" + SIZE_LIMIT + "&page=" + PAGE_NUMBER;
            var responseEntity = testRestTemplate.getForEntity(transactionURI, String.class);
            DocumentContext ctx = JsonPath.parse(responseEntity.getBody());
            String lastlink = ctx.read("$.links.last");
            assertThat(lastlink).isEqualTo("/transactions?size=" + SIZE_LIMIT + "&page=" + LAST_PAGE_NUMBER);
        }
    }