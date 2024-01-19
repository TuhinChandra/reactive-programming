package reactive.programming;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        // Flux example
        Flux<String> flux = Flux.just("Apple", "Banana", "Cherry");

        flux.subscribe(
                item -> System.out.println("Flux Received: " + item),
                error -> System.err.println("Flux Error: " + error.getMessage()),
                () -> System.out.println("Flux Completed")
        );
        flux
                .map(fruit -> {
                    if (fruit.equals("Banana")) {
                        throw new RuntimeException("Banana is not allowed!");
                    }
                    return fruit;
                })
                .onErrorContinue((error, value) -> {
                    System.err.println("Error: " + error.getMessage());
                    System.err.println("Skipped value: " + value);
                })
                .subscribe(
                        item -> System.out.println("Received: " + item),
                        error -> System.err.println("Error in subscription: " + error.getMessage()),
                        () -> System.out.println("Completed")
                );


        // Mono example
        Mono<String> mono = Mono.just("Hello");

        mono.subscribe(
                item -> System.out.println("Mono Received: " + item),
                error -> System.err.println("Mono Error: " + error.getMessage()),
                () -> System.out.println("Mono Completed")
        );
    }
}