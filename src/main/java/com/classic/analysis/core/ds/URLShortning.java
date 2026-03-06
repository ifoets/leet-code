package com.classic.analysis.core.ds;

public class URLShortning {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String shortenUrl(String longUrl) {

        if (longUrl == null || longUrl.isBlank()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }

      /*  // idempotent behavior
        Optional<UrlMapping> existing = repository.findByLongUrl(longUrl);
        if (existing.isPresent()) {
            return BASE_URL + existing.get().getShortCode();
        }

        UrlMapping mapping = new UrlMapping();
        mapping.setLongUrl(longUrl);

        // save first to get auto-generated ID
        mapping = repository.save(mapping);

        String shortCode = toBase62(mapping.getId());
        mapping.setShortCode(shortCode);
        repository.save(mapping);

        return BASE_URL + shortCode;*/
        return null;
    }

    public String toBase62(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(BASE62.charAt((int) (num % 62)));
            num /= 62;
        }
        return sb.reverse().toString();
    }
}
