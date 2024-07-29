package HTMLReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class HTMLReader {
    public static void main(String[] args) throws IOException {
        // 任务提交语句yarn jar ./HTMLReader-1.0-SNAPSHOT-jar-with-dependencies.jar HTMLReader.HTMLReader /datasets /result
        // 输入参数检验
        if (args.length < 2) {
            System.err.println("Usage: HTMLReader <input path> <output path>");
            System.exit(-1);
        }

        // 获取客户端
        Configuration configuration = new Configuration();
        String uri = "hdfs://192.168.146.130:9000";
        configuration.set("fs.defaultFS", uri);

        Job job;
        try {
            job = Job.getInstance(configuration, "HTML Reader");

            job.setJarByClass(HTMLReader.class);
            job.setInputFormatClass(WholeFileInputFormat.class);

            // 注册map
            job.setMapperClass(HTMLMap.class);
            job.setMapOutputKeyClass(IntWritable.class);
            job.setMapOutputValueClass(Text.class);

            // 注册reduce
            job.setReducerClass(HTMLReduce.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            String inputPath = args[0];
            String outputPath = args[1];

            // 清空输出目录
            FileSystem fileSystem = FileSystem.get(configuration);
            if (fileSystem.exists(new Path(uri + outputPath))) {
                fileSystem.delete(new Path(uri + outputPath), true);
            }

            // 设置输入输出目录
            FileInputFormat.addInputPath(job, new Path(uri + inputPath));
            FileOutputFormat.setOutputPath(job, new Path(uri + outputPath));

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            System.err.println("Error during job execution: " + e.getMessage());
            System.exit(-1);
        } finally {
            // 关闭文件系统连接
            FileSystem.closeAll();
        }
    }
}
