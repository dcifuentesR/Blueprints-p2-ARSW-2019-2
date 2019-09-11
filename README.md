# Blueprints-p2-ARSW-2019-2
### Part I
### Part II
### Part III

There are race conditions when various threads are updating or reading the same blueprint, java.util.concurrent provides a ConcurrentHashMap that supports full concurrency of retrievals and high expected concurrency for updates.

For this use case we only need to change the blueprints HashMap into a ConcurrentHashMap

```private final Map<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();```

