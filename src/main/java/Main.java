import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main {
    private final String baseUrl;           // Base URL of the website
    private final Set<String> visitedUrls;  // Track visited pages to avoid duplicates
    private final Queue<String> toVisit;    // Queue of pages to crawl

    // Constructor
    public Main(String baseUrl) {
        this.baseUrl = baseUrl;
        this.visitedUrls = new HashSet<>();
        this.toVisit = new LinkedList<>();
        this.toVisit.add(baseUrl);  // Start with the main page
    }

    // Main method to crawl the website and check links
    public void checkAllLinks() {
        while (!toVisit.isEmpty()) {
            String currentUrl = toVisit.poll();
            if (!visitedUrls.contains(currentUrl)) {
                visitedUrls.add(currentUrl);
                System.out.println("Visiting: " + currentUrl);

                try {
                    // Fetch and parse the current page
                    Document doc = Jsoup.connect(currentUrl).get();
                    Elements links = doc.select("a[href]");

                    // Process each link on the page
                    for (Element link : links) {
                        String href = link.attr("abs:href");  // Get absolute URL
                        // If the link is within the same domain and not visited, queue it
                        if (href.startsWith(baseUrl) && !visitedUrls.contains(href)) {
                            toVisit.add(href);
                        }
                        // Check the status of every link
                        checkLink(href);
                    }
                } catch (Exception e) {
                    System.out.println("Error fetching page: " + currentUrl + " - " + e.getMessage());
                }
            }
        }
    }

    // Helper method to check the HTTP status of a URL
    private void checkLink(String url) {
        try {
            URL linkUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) linkUrl.openConnection();
            connection.setRequestMethod("HEAD");  // Use HEAD to avoid downloading full content
            connection.setConnectTimeout(5000);   // 5-second timeout
            connection.connect();
            int status = connection.getResponseCode();
            if (status >= 400) {
                System.out.println("Broken link: " + url + " - Status: " + status);
            } else {
                System.out.println("Valid link: " + url + " - Status: " + status);
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Error checking link: " + url + " - " + e.getMessage());
        }
    }

    // Entry point
    public static void main(String[] args) {
        Main checker = new Main("https://the-internet.herokuapp.com");
        checker.checkAllLinks();
    }
}