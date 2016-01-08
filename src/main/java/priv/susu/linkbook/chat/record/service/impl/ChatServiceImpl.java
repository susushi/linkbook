package priv.susu.linkbook.chat.record.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import priv.susu.linkbook.FlowWrapper;
import priv.susu.linkbook.chat.record.Message;
import priv.susu.linkbook.chat.record.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

	private final ConcurrentHashMap<String, MessageQueue> messageQueueMap = new ConcurrentHashMap<String, MessageQueue>();

	@Override
	public void send(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public FlowWrapper<Message> get(int scope, long value, String breakpoint) {
		FlowWrapper<Message> result = new FlowWrapper<Message>();
		String key = scope + "-" + value;
		MessageQueue queue = messageQueueMap.get(key);
		long timestamp = System.currentTimeMillis();
		if (StringUtils.isNotBlank(breakpoint)) {
			timestamp = Long.parseLong(breakpoint);
		}
		List<Message> messageList = queue.get(timestamp);
		if (messageList == null) {
			return result;
		}
		if (!messageList.isEmpty()) {
			result.setCount(messageList.size());
			result.setItems(messageList);
			Message last = messageList.get(messageList.size() - 1);
			result.setBreakpoint(last.getCreatedTime() + "");
		}
		return result;
	}

	@Override
	public FlowWrapper<Message> getRecord(int scope, long value, String breakpoint, int size) {
		FlowWrapper<Message> result = new FlowWrapper<Message>();
		String key = scope + "-" + value;
		MessageQueue queue = messageQueueMap.get(key);
		long timestamp = System.currentTimeMillis();
		if (StringUtils.isNotBlank(breakpoint)) {
			timestamp = Long.parseLong(breakpoint);
		}
		List<Message> messageList = queue.getRecord(timestamp, size);
		if (messageList == null) {
			return result;
		}
		if (!messageList.isEmpty()) {
			if(messageList.size() < size) {
				// TODO get from DB
			} else {
				result.setCount(messageList.size());
				result.setItems(messageList);
				Message last = messageList.get(messageList.size() - 1);
				result.setBreakpoint(last.getCreatedTime() + "");
			}
		}
		return result;
	}

}
