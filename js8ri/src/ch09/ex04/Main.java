package ch09.ex04;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) {
        xmlParseProcess();
        reflectionMain();
    }

    // 複数例外をキャッチすることで恩恵を得られる状況
    public static void xmlParseProcess() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2. DocumentBuilderのインスタンスを取得する
        DocumentBuilder builder = null;
        Document document = null;
        try {
            // 3. DocumentBuilderにXMLを読み込ませ、Documentを作る
            builder = factory.newDocumentBuilder();
            document = builder.parse(Paths.get(String.format("%s\\src\\ch09\\ex04\\bookList.xml", CD)).toFile());
            // 4. Documentから、ルート要素(BookList)を取得する
            Element bookList = document.getDocumentElement();
            // 5. BookList配下にある、Book要素を取得する
            NodeList books = bookList.getElementsByTagName("Book");
            // 6. 取得したBook要素でループする
            for (int i = 0; i < books.getLength(); i++) {
                // 7. Book要素をElementにキャストする
                Element book = (Element) books.item(i);

                // 8. Book要素の属性値と、テキストノードの値を取得する
                String isbn = book.getAttribute("isbn");
                String title = book.getAttribute("title");
                String author = book.getAttribute("author");
                String content = book.getTextContent();

                System.out.println("isbn = " + isbn);
                System.out.println("author = " + author);
                System.out.println("title = " + title);
                System.out.println("text = " + content);
                System.out.println();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    // 共通の例外となるスーパークラスにより恩恵を得られる状況
    // (IllegalAccessException,InvocationTargetException,NoSuchMethodException,ClassNotFoundException)を
    // 共通の親クラスReflectiveOperationExceptionとしてキャッチしている
    private static void reflectionMain() {
        try {
            Main.class.getMethod("xmlParseProcess").invoke(null);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

}
