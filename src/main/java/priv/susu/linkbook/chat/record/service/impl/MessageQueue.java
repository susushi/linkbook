package priv.susu.linkbook.chat.record.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import priv.susu.linkbook.chat.record.Message;

public class MessageQueue {

	private static final int MAX_SIZE = 500;

	private AtomicLong currentSize = new AtomicLong(0);

	private ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<Message>();

	public void add(Message msg) {
		if (currentSize.incrementAndGet() > MAX_SIZE) {
			messageQueue.poll();
		}
		messageQueue.add(msg);
	}

	public List<Message> get(long lastReadTime) {
		List<Message> result = new ArrayList<Message>();
		Iterator<Message> msgIterator = messageQueue.iterator();
		while (msgIterator.hasNext()) {
			Message msg = msgIterator.next();
			result.add(msg);
		}
		return result;
	}

	public List<Message> getRecord(long lastReadTime, int size) {
		List<Message> result = new ArrayList<Message>();
		Message[] recordMessage = messageQueue.toArray(new Message[messageQueue.size()]);
		for (int i = recordMessage.length - 1; i >= 0; i--) {
			Message msg = recordMessage[i];
			if (msg.getCreatedTime() < lastReadTime)
				result.add(msg);
			if (result.size() == size)
				break;
		}
		return result;
	}

}
