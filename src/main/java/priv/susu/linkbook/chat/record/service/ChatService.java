package priv.susu.linkbook.chat.record.service;

import priv.susu.linkbook.FlowWrapper;
import priv.susu.linkbook.chat.record.Message;

public interface ChatService {

	public void send(Message message);

	public FlowWrapper<Message> get(int scope, long value, String breakpoint);

	public FlowWrapper<Message> getRecord(int scope, long value, String breakpoint, int size);
}
