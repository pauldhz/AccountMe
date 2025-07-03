    package org.pauldenhez.accountme.ws.transaction.controller;

    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.pauldenhez.accountme.common.model.transaction.Transaction;
    import org.pauldenhez.accountme.common.model.transaction.TransactionMethod;
    import org.pauldenhez.accountme.common.model.transaction.TransactionType;
    import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmount;
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

    import java.util.Date;

    import static org.assertj.core.api.Assertions.assertThat;

    @Testcontainers
    @ExtendWith(SpringExtension.class)
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class TransactionControllerTest {

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

            transactionRepository.save(
                    new Transaction(
                            "1L",
                            new Date(),
                            TransactionMethod.BANK_CARD,
                            "Comment",
                            "label",
                            "comment",
                            TransactionType.CREDIT,
                            new PositiveAmount(50),
                            null,
                            null
                    ));

            var responseEntity = testRestTemplate.getForEntity("/transactions", String.class);
            System.out.println(responseEntity.getBody());
            assertThat(responseEntity).isNotNull();
        }
    }