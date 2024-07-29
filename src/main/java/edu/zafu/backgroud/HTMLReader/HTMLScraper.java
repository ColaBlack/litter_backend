package HTMLReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.IOException;

public class HTMLScraper {

    public static String scrape(String htmlContent) throws IOException {
        // 使用Jsoup解析HTML内容
        Document doc = Jsoup.parse(htmlContent);

        // 提取所有包含"时间："的<span>标签并提取年份
        Elements timeSpans = doc.select("span:contains(时间：)");
        String year = "";
        if (!timeSpans.isEmpty()) {
            // 提取第一个时间
            year = timeSpans.first().text().replaceAll(".*时间：([0-9]{4}).*", "$1");
        }

        // 提取所有class为font14 offic的<span>标签并提取部门信息
        Elements classSpans = doc.select("span.font14.offic");
        String department = "";
        if (!classSpans.isEmpty()) {
            department = classSpans.first().text();
        }

        // 提取所有class为gray的<a>标签并提取信件类型
        Elements grayLinks = doc.select("a.gray");
        String type = "";
        if (!grayLinks.isEmpty()) {
            type = grayLinks.get(1).text();
        }

        // 提取所有class为font14 mail_problem的<span>标签并提取信件内容
        Elements mailProblemSpans = doc.select("span.font14.mail_problem");
        String mailProblemText = "";
        if (!mailProblemSpans.isEmpty()) {
            mailProblemText = mailProblemSpans.first().text();
        }

        // 返回提取的内容
        String record = year + "\100" + type + "\100" + department + "\100" + mailProblemText;
        return record;
    }

    public static void main(String[] args) throws IOException {
        // 假设你有一个HTML文件路径
        String filePath = "src/main/resources/dataset/00a9e050a4e5ca1ea966e4febe55a7b1.html";

        // 使用FileReader读取HTML内容
        StringBuilder htmlContent = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            int character;
            while ((character = fileReader.read()) != -1) {
                htmlContent.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // 调用scrape方法并打印结果
        String result = scrape(htmlContent.toString());
        System.out.println(result);
    }
}
