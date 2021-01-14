package ch06.ex10;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            try {
                return readPage("https://www.yahoo.co.jp/");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).thenApply(Main::getLinks).thenAccept(links -> links.forEach(System.out::println));

        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

    static Document readPage(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    static List<String> getLinks(Document document) {
        if (document == null) {
            return Collections.emptyList();
        }
        Elements elements = document.select("a");
        List<String> resultList = new ArrayList<>();
        for (Element elem : elements) {
            resultList.add(elem.attr("href"));
        }
        return resultList;
    }

}
