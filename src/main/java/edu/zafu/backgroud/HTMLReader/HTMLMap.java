package HTMLReader;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HTMLMap extends Mapper<IntWritable, Text, IntWritable, Text> {

    @Override
    protected void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用Jsoup解析HTML内容
        Document doc = Jsoup.parse(value.toString());

        // 调用HTMLScraper类来获取数据
        String data = HTMLScraper.scrape(doc.toString());

        // 使用文件序号作为键，爬取结果为值
        context.write(key, new Text(data));
    }


}

