package reactive.programming;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        // Flux example
        Flux<String> flux = Flux.just("Hello", "Reactive", "Programming");

        flux.subscribe(
                item -> System.out.println("Flux Received: " + item),
                error -> System.err.println("Flux Error: " + error.getMessage()),
                () -> System.out.println("Flux Completed")
        );
        System.out.println("Is Flux Completed?");
        // Mono example
        Mono<String> mono = Mono.just("Hello");

        mono.subscribe(
                item -> System.out.println("Mono Received: " + item),
                error -> System.err.println("Mono Error: " + error.getMessage()),
                () -> System.out.println("Mono Completed")
        );
        System.out.println("Is Mono Completed?");
    }
}