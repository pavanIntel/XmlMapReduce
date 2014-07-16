package com.pavan.xmlmr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class XMLDriver extends Configured implements Tool{

	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 Configuration conf = new Configuration();

		    conf.set("xmlinput.start", "<property>");
		    conf.set("xmlinput.end", "</property>");
		    Job job = new Job(conf);
		    job.setJarByClass(XMLDriver.class);
		    job.setMapperClass(XMLMapper.class);
		    job.setReducerClass(xmlreducer.class);
		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(Text.class);

		    
		    

		    job.setInputFormatClass(XMLInputFormat.class);
		    job.setOutputFormatClass(TextOutputFormat.class);

		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));

		    return (job.waitForCompletion(true) ? 0 : -1);
	}
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(), new XMLDriver(),args);
		System.exit(exitCode);
	}
	

}
