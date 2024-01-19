package reactive.programming;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        // Flux example
        Flux<String> flux = Flux.just("Apple", "Banana", "Cherry");

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

        flux
                .subscribe(
                        item -> {
                            if (item.startsWith("B")) {
                                throw new RuntimeException("Can't accept Banana");
                            } else {
                                System.out.println(item);
                            }
                        },
                        error -> System.err.println("Huge Error in subscription and can't progress: " + error.getMessage()),
                        () -> System.out.println("Completed")
                );
    }
}