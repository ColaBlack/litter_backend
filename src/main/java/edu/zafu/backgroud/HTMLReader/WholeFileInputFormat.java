package HTMLReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

// 自定义InputFormat，用于将整个文件作为map函数的输入value
public class WholeFileInputFormat extends FileInputFormat<IntWritable, Text> {

    @Override
    public RecordReader<IntWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        WholeFileRecordReader reader = new WholeFileRecordReader();
        reader.initialize(split, context);
        return reader;
    }

    // 自定义RecordReader，用于读取整个文件内容
    public static class WholeFileRecordReader extends RecordReader<IntWritable, Text> {

        private FileSplit fileSplit;
        private Configuration conf;
        private Text value = new Text();
        private IntWritable key = new IntWritable();
        private boolean processed = false;
        private int fileIndex; // 文件序号

        @Override
        public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
            this.fileSplit = (FileSplit) split;
            this.conf = context.getConfiguration();
            // 从任务尝试上下文中获取文件序号
            this.fileIndex = context.getConfiguration().getInt("mapreduce.task.partition", 0);
        }

        @Override
        public boolean nextKeyValue() throws IOException, InterruptedException {
            if (!processed) {
                byte[] contents = new byte[(int) fileSplit.getLength()];
                Path file = fileSplit.getPath();
                FileSystem fs = file.getFileSystem(conf);
                FSDataInputStream in = null;
                try {
                    in = fs.open(file);
                    IOUtils.readFully(in, contents, 0, contents.length);
                    value.set(contents, 0, contents.length);
                    key.set(fileIndex); // 设置文件序号为key
                } finally {
                    IOUtils.closeStream(in);
                }
                processed = true;
                return true;
            }
            return false;
        }

        @Override
        public IntWritable getCurrentKey() throws IOException, InterruptedException {
            return key;
        }

        @Override
        public Text getCurrentValue() throws IOException, InterruptedException {
            return value;
        }

        @Override
        public float getProgress() throws IOException, InterruptedException {
            return processed ? 1.0f : 0.0f;
        }

        @Override
        public void close() throws IOException {
        }
    }
}
