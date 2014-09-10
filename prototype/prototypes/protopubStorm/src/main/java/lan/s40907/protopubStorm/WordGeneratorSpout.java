package lan.s40907.protopubStorm;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class WordGeneratorSpout implements IRichSpout {
	private SpoutOutputCollector collector;	
	private int amount;
	private int maxAmount;

	public WordGeneratorSpout(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	@Override
	public void ack(Object arg0) {}

	@Override
	public void activate() {}

	@Override
	public void close() {}

	@Override
	public void deactivate() {}

	@Override
	public void fail(Object object) {
		System.err.println("WordGenerator fail with: " + object.toString());
	}

	@Override
	public void nextTuple() {
		if (this.amount < this.maxAmount) {
			this.amount++;						
			long currentTime = System.currentTimeMillis();
			String currentWord = "STORMwordGenerator";
			this.collector.emit(new Values(currentWord, this.amount, currentTime));
		}
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "amount", "time"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}