package HTMLReader;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class HTMLReduce extends Reducer<IntWritable, Text, Text, Text> {

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            //计算记录的序号id
            String id = String.valueOf(key.get() + 1);
            //计算最终的输出
            Text ret = new Text(id + "\100" + value.toString());
            context.write(new Text(""), ret);
        }
    }


}
